package content.global.skill.member.farming

import config.Items
import config.Sounds
import core.api.inInventory
import core.api.playAudio
import core.api.rewardXP
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.update.flag.context.Animation

class UseWithPatchHandler : InteractionListener {
    val RAKE = Items.RAKE_5341
    val SEED_DIBBER = Items.SEED_DIBBER_5343
    val SPADE = Items.SPADE_952
    val SECATEURS = Items.SECATEURS_5329
    val MAGIC_SECATEURS = Items.MAGIC_SECATEURS_7409
    val TROWEL = Items.GARDENING_TROWEL_5325
    val pourBucketAnim = Animation(2283)
    val wateringCanAnim = Animation(2293)
    val plantCureAnim = Animation(2288)
    val secateursTreeAnim = Animation(2277)
    val magicSecateursTreeAnim = Animation(3340)

    @JvmField
    val allowedNodes = ArrayList<Int>()
    val patches = ArrayList<Int>()

    override fun defineListeners() {
        loadNodes()
        onUseWith(IntType.SCENERY, allowedNodes.toIntArray(), *FarmingPatch.patchNodes.toIntArray()) { player, used, with ->
            val patch = FarmingPatch.forObject(with.asScenery()) ?: return@onUseWith true
            val usedItem = used.asItem()

            if (patch == FarmingPatch.TROLL_STRONGHOLD_HERB) {
               // if (!hasRequirement(player, "My Arm's Big Adventure"))
                    return@onUseWith true
            }

            player.faceLocation(with.location)
            when(usedItem.id){
                RAKE -> PatchRaker.rake(player, patch)
                SEED_DIBBER -> sendMessage(player, "I should plant a seed, not the seed dibber.")
                SPADE -> player.dialogueInterpreter.open(67984003,patch.getPatchFor(player)) //DigUpPatchDialogue.kt
                SECATEURS, MAGIC_SECATEURS -> {
                    val p = patch.getPatchFor(player)
                    if(patch.type == PatchType.TREE) {
                        if(p.isDiseased && !p.isDead) {
                            player.pulseManager.run(object: Pulse(){
                                override fun pulse(): Boolean {
                                    if (usedItem.id == SECATEURS ) player.animator.animate(secateursTreeAnim) else player.animator.animate(magicSecateursTreeAnim)
                                    p.cureDisease()
                                    return true
                                }
                            })
                        } else if(p.plantable == Plantable.WILLOW_SAPLING && p.harvestAmt > 0) {
                            val pulse = CropHarvester.harvestPulse(player, with, Items.WILLOW_BRANCH_5933) ?: return@onUseWith false
                            player.pulseManager.run(pulse)
                        }
                    }
                }
                TROWEL, Items.PLANT_POT_5350 -> {
                    if(!inInventory(player, TROWEL)) {
                        sendMessage(player, "You need a trowel to fill plant pots with dirt.")
                        return@onUseWith true
                    }
                    val p = patch.getPatchFor(player)
                    if(!p.isWeedy()){
                        sendMessage(player, "This patch has something growing in it.")
                        return@onUseWith true
                    } else if (p.currentGrowthStage != 3){
                        sendMessage(player, "I should clear this of weeds before trying to take some dirt.")
                        return@onUseWith true
                    }

                    val potAmount = player.inventory.getAmount(Items.PLANT_POT_5350)

                    if(potAmount == 0){
                        sendMessage(player, "You have no plant pots to fill.")
                        return@onUseWith true
                    }

                    val anim = Animation(2272)

                    player.pulseManager.run(object : Pulse(anim.duration){
                        override fun pulse(): Boolean {
                            if(player.inventory.remove(Item(Items.PLANT_POT_5350))){
                                player.animator.animate(anim)
                                player.inventory.add(Item(Items.PLANT_POT_5354))
                            } else return true
                            return false
                        }
                    })
                }

                Items.PLANT_CURE_6036 -> {
                    val p = patch.getPatchFor(player)
                    if(p.isDiseased && !p.isDead){
                        player.pulseManager.run(object: Pulse(){
                            override fun pulse(): Boolean {
                                player.animator.animate(plantCureAnim)
                                playAudio(player, Sounds.FARMING_PLANTCURE_2438)
                                if(player.inventory.remove(usedItem)){
                                    player.inventory.add(Item(Items.VIAL_229))
                                    p.cureDisease()
                                }
                                return true
                            }
                        })
                    } else {
                        sendMessage(player, "I have no reason to do this right now.")
                    }
                }

                Items.WATERING_CAN1_5333, Items.WATERING_CAN2_5334, Items.WATERING_CAN3_5335, Items.WATERING_CAN4_5336, Items.WATERING_CAN5_5337, Items.WATERING_CAN6_5338, Items.WATERING_CAN7_5339, Items.WATERING_CAN8_5340 -> {
                    val p = patch.getPatchFor(player)
                    val t = p.patch.type
                    if(!p.isWatered && p.plantable != Plantable.SCARECROW && (t == PatchType.ALLOTMENT || t == PatchType.FLOWER || t == PatchType.HOPS) && !p.isGrown()){
                        player.pulseManager.run(object : Pulse(){
                            override fun pulse(): Boolean {
                                if(p.isWeedy()){
                                    sendMessage(player, "You should grow something first.")
                                    return true
                                }
                                player.animator.animate(wateringCanAnim)
                                playAudio(player, Sounds.FARMING_WATERING_2446)
                                if(player.inventory.remove(usedItem)){
                                    player.inventory.add(Item(usedItem.id.getNext()))
                                    p.water()
                                }
                                return true
                            }
                        })
                    }
                }

                Items.SUPERCOMPOST_6034, Items.COMPOST_6032 -> {
                    val p = patch.getPatchFor(player)
                    if(p.compost == CompostType.NONE) {
                        player.animator.animate(pourBucketAnim)
                        playAudio(player, Sounds.FARMING_COMPOST_2427)
                        player.pulseManager.run(object : Pulse(){
                            override fun pulse(): Boolean {
                                if(player.inventory.remove(usedItem,false)){
                                    p.compost = if(usedItem.id == Items.SUPERCOMPOST_6034) CompostType.SUPER else CompostType.NORMAL
                                    if(p.compost == CompostType.SUPER) rewardXP(player, Skills.FARMING, 26.0) else rewardXP(player, Skills.FARMING, 18.5)
                                    if(p.plantable != null && p.plantable?.applicablePatch != PatchType.FLOWER) {
                                        p.harvestAmt += if(p.compost == CompostType.NORMAL) 1 else if(p.compost == CompostType.SUPER) 2 else 0
                                    }
                                    p.cropLives += if(p.compost == CompostType.SUPER) 2 else 1
                                    player.inventory.add(Item(Items.BUCKET_1925))
                                }
                                return true
                            }
                        })
                    } else {
                        sendMessage(player, "This patch has already been treated with compost.")
                    }
                }

                else -> {
                    val plantable = Plantable.forItemID(usedItem.id) ?: return@onUseWith false

                    if(plantable.applicablePatch != patch.type){
                        sendMessage(player, "You can't plant that seed in this patch.")
                        return@onUseWith true
                    }

                    if(plantable.requiredLevel > player.skills.getLevel(Skills.FARMING)){
                        sendMessage(player, "You need a Farming level of ${plantable.requiredLevel} to plant this.")
                        return@onUseWith true
                    }

                    val p = patch.getPatchFor(player)
                    if(p.getCurrentState() < 3 && p.isWeedy()){
                        sendMessage(player, "You must weed your patch before you can plant a seed in it.")
                        return@onUseWith true
                    } else if(p.getCurrentState() > 3){
                        sendMessage(player, "There is already something growing in this patch.")
                        return@onUseWith true
                    }

                    val plantItem =
                        if(patch.type == PatchType.ALLOTMENT) Item(plantable.itemID,3) else if(patch.type == PatchType.HOPS){
                            if(plantable == Plantable.JUTE_SEED) Item(plantable.itemID,3) else Item(plantable.itemID,4)
                        } else {
                            Item(plantable.itemID,1)
                        }

                    if(patch.type == PatchType.ALLOTMENT){
                        if(!player.inventory.containsItem(plantItem)){
                            sendMessage(player, "You need 3 seeds to plant an allotment patch.")
                            return@onUseWith true
                        }
                    }
                    if(patch.type != PatchType.FRUIT_TREE && patch.type != PatchType.TREE){
                        if(!inInventory(player, Items.SEED_DIBBER_5343,1)){
                            sendMessage(player, "You need a seed dibber to plant that.")
                            return@onUseWith true
                        }
                    } else {
                        if(!inInventory(player, Items.SPADE_952,1)){
                            sendMessage(player, "You need a spade to plant that.")
                            return@onUseWith true
                        }
                    }
                    player.lock()
                    if(player.inventory.remove(plantItem)) {
                        player.animator.animate(Animation(2291))
                        playAudio(player, Sounds.FARMING_DIBBING_2432)
                        player.pulseManager.run(object : Pulse(3) {
                            override fun pulse(): Boolean {
                                if(plantable == Plantable.JUTE_SEED && patch == FarmingPatch.MCGRUBOR_HOPS && !player.achievementDiaryManager.hasCompletedTask(DiaryType.SEERS_VILLAGE,0,7)){
                                    player.achievementDiaryManager.finishTask(player,DiaryType.SEERS_VILLAGE,0,7)
                                }
                                p.plant(plantable)
                                player.skills.addExperience(Skills.FARMING, plantable.plantingXP)
                                p.setNewHarvestAmount()
                                if(p.patch.type == PatchType.TREE || p.patch.type == PatchType.FRUIT_TREE){
                                    player.inventory.add(Item(Items.PLANT_POT_5350))
                                }
                                player.unlock()
                                return true
                            }
                        })
                    }
                }
            }

            return@onUseWith true
        }
    }

    fun loadNodes(){
        patches.addAll(8550..8557) //allotment wrappers
        patches.addAll(7847..7853) //flower patch wrappers
        patches.addAll(8150..8156) //herb patch wrappers
        patches.addAll(8388..8391) // Tree patches
        patches.add(19147) //Tree patch
        patches.addAll(7962..7965) //fruit trees
        patches.addAll(8173..8176) //hops
        patches.addAll(7577..7580) //bush
        patches.add(23760) //evil turnip
        patches.add(7572) //belladonna
        patches.add(8337) //mushroom
        patches.add(27197) //jade vine
        patches.add(7771) //cactus
        patches.add(7807) //calquat
        patches.addAll(8382..8383)//spirit trees
        patches.add(8338) //spirit tree
        patches.add(18816) //death plateau wrapper
        for(p in Plantable.values()){
            allowedNodes.add(p.itemID)
        }
        allowedNodes.addAll(arrayListOf(RAKE,SEED_DIBBER,SPADE,SECATEURS,MAGIC_SECATEURS,TROWEL,
            Items.SUPERCOMPOST_6034,
            Items.COMPOST_6032,
            Items.PLANT_CURE_6036,
            Items.WATERING_CAN1_5333,
            Items.WATERING_CAN2_5334,
            Items.WATERING_CAN3_5335,
            Items.WATERING_CAN4_5336,
            Items.WATERING_CAN5_5337,
            Items.WATERING_CAN6_5338,
            Items.WATERING_CAN7_5339,
            Items.WATERING_CAN8_5340, Items.PLANT_POT_5350))
    }

    private fun Int.getNext(): Int {
        if(this == Items.WATERING_CAN1_5333) return Items.WATERING_CAN_5331
        else return this - 1
    }
}
