package core.game.worldevents.holiday.easter

import config.Items
import config.NPCs
import core.api.inEquipment
import core.api.inInventory
import core.api.sendMessage
import core.game.interaction.DestinationFlag
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.interaction.MovementPulse
import core.game.node.item.GroundItemManager

class EasterEventListeners : InteractionListener {

    val EGG_ATTRIBUTE = "/save:easter:eggs"
    val eggs = intArrayOf(Items.EASTER_EGG_11027, Items.EASTER_EGG_11028, Items.EASTER_EGG_11029, Items.EASTER_EGG_11030)

    override fun defineListeners() {

        on(eggs, IntType.ITEM, "take"){ player, node ->
            player.pulseManager.run(object : MovementPulse(player,node, DestinationFlag.ITEM){
                override fun pulse(): Boolean {
                    if(inInventory(player, Items.BASKET_OF_EGGS_4565,1) || inEquipment(player, Items.BASKET_OF_EGGS_4565,1)){
                        val item = GroundItemManager.get(node.id,node.location,null)
                        GroundItemManager.destroy(item)
                        player.incrementAttribute(EGG_ATTRIBUTE)
                        sendMessage(player, "You place the egg in the basket. You now have ${player.getAttribute<Int>(EGG_ATTRIBUTE,0)} eggs!")
                    } else {
                        sendMessage(player, "You need to have your egg basket with you to collect these.")
                    }
                    return true
                }
            })
            return@on true
        }

        on(Items.BASKET_OF_EGGS_4565, IntType.ITEM, "operate"){ player, node ->
            val numEggs = player.getAttribute<Int>(EGG_ATTRIBUTE) ?: 0
            player.dialogueInterpreter.sendDialogue("You have $numEggs eggs in the basket.")
            return@on true
        }

        on(NPCs.EASTER_BUNNY_7197, IntType.NPC, "talk-to"){ player, node ->
            val npc = node.asNpc()
            npc.faceLocation(player.location)
            val NEED_BASKET = !(inInventory(player,Items.BASKET_OF_EGGS_4565,1) || inEquipment(player,
                Items.BASKET_OF_EGGS_4565,1) || player!!.bank.contains(Items.BASKET_OF_EGGS_4565,1))
            player.dialogueInterpreter.open(EasterBunnyDialogueFile(NEED_BASKET),npc)
            return@on true
        }

    }

}