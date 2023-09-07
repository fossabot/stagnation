package content.region.island.tutorialisland.dialogue

import config.Components
import config.Items
import config.NPCs
import content.region.island.tutorialisland.handlers.*
import core.ServerConstants
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.TeleportManager
import core.game.node.item.Item
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.worker.ManagementEvents
import proto.management.JoinClanRequest

/**
 * Represents the Magic Tutor dialogue plugin.
 */
@Initializable
class MagicTutorDialogue(player: Player? = null) : DialoguePlugin(player) {

    private val STARTER_PACK = arrayOf(

        Item(Items.BRONZE_AXE_1351, 1),
        Item(Items.TINDERBOX_590, 1),
        Item(Items.SMALL_FISHING_NET_303, 1),
        Item(Items.SHRIMPS_315, 1),
        Item(Items.BUCKET_1925, 1),
        Item(Items.EMPTY_POT_1931, 1),
        Item(Items.BREAD_2309, 1),

        Item(Items.BRONZE_PICKAXE_1265, 1),
        Item(Items.BRONZE_DAGGER_1205, 1),
        Item(Items.BRONZE_SWORD_1277, 1),
        Item(Items.WOODEN_SHIELD_1171, 1),
        Item(Items.SHORTBOW_841, 1),
        Item(Items.BRONZE_ARROW_882, 25),

        Item(Items.AIR_RUNE_556, 25),
        Item(Items.MIND_RUNE_558, 15),
        Item(Items.WATER_RUNE_555, 6),
        Item(Items.EARTH_RUNE_557, 4),
        Item(Items.BODY_RUNE_559, 2))

    private val STARTER_BANK = arrayOf(Item(Items.COINS_995, 25))

    override fun newInstance(player: Player?): DialoguePlugin {
        return MagicTutorDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        when(getAttribute(player, "tutorial:stage", 0)) {
            67 -> playerl(FacialExpression.FRIENDLY, "Hello.")
            69 -> npc(FacialExpression.FRIENDLY, "Good. This is a list of your spells. Currently you can", "only cast one offensive spell called Wind Strike. Let's", "try it out on one of those chickens.")
            70 -> if(!inInventory(player, Items.AIR_RUNE_556) && !inInventory(player, Items.MIND_RUNE_558))
            {
                sendDoubleItemDialogue(player, Items.AIR_RUNE_556, Items.MIND_RUNE_558, "You receive some spare runes.")
                addItem(player, Items.AIR_RUNE_556, 15)
                addItem(player, Items.MIND_RUNE_558, 15)
                return false
            }
            71 -> npc(FacialExpression.FRIENDLY, "Well you're all finished here now. I'll give you a", "reasonable number of runes when you leave.")
            else -> return false
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (getAttribute(player, "tutorial:stage", 0)) {
            67 -> when (stage++){
                0 -> npc(FacialExpression.FRIENDLY, "Good day, newcomer. My name is Terrova. I'm here", "to tell you about <col=08088A>Magic</col>. Let's start by opening your", "spell list.")
                1 -> {
                    end()
                    setAttribute(player, "tutorial:stage", 68)
                    TutorialStage.load(player, 68)
                }
            }

            69 -> when (stage++){
                0 -> {
                    sendDoubleItemDialogue(player, Items.AIR_RUNE_556, Items.MIND_RUNE_558, "Terrova gives you five <col=08088A>air runes</col> and five <col=08088A>mind runes</col>!")
                    addItemOrDrop(player, Items.AIR_RUNE_556, 5)
                    addItemOrDrop(player, Items.MIND_RUNE_558, 5)
                }

                1 -> {
                    end()
                    setAttribute(player, "tutorial:stage", 70)
                    TutorialStage.load(player, 70)
                }
            }

            71 -> when (stage){
                0 -> {
                    setComponentVisibility(player!!, 228, 6, true)
                    setComponentVisibility(player!!, 228, 9, false)
                    sendDialogueOptions(player, "Do you want to go to the mainland?", "Yes.", "No.").also { stage++ }
                }
                1 -> when (buttonId){
                    1 -> playerl(FacialExpression.NEUTRAL, "I'm ready to go now, thank you.").also { stage++ }
                    2 -> playerl(FacialExpression.NEUTRAL, "No.").also { stage = END_DIALOGUE }
                }

                2 -> npc("Good good. I've deactivated the protective spells around", "the island so now you can teleport yourself out of", "here.").also { stage++ }
                3 -> npc("When you get to the mainland you will find yourself in", "the town of Lumbridge. If you want some ideas on", "where to go next, talk to my friend Phileas, also known", "as the Lumbridge Guide. You can't miss him; he's").also { stage++ }
                4 -> npc("holding a big staff with a question mark on the end. He", "also has a white beard and carries a rucksack full of", "scrolls. There are also tutors willing to teach you about", "the many skills you could learn.").also { stage++ }
                5 -> {
                    openInterface(player, Components.DOUBLEOBJBOX_131).also {
                        player!!.packetDispatch.sendModelOnInterface(7369, Components.DOUBLEOBJBOX_131, 2, 80)
                        player.packetDispatch.sendAngleOnInterface(Components.DOUBLEOBJBOX_131, 2, 880, 450, 1)
                        setInterfaceText(player,"When you get to Lumbridge, look for this icon, on your" + " minimap. The Lumbridge Guide and the other tutors" + " will be standing near one of these. The Lumbridge" + " Guide should be standing slightly to the north-east of", Components.DOUBLEOBJBOX_131, 1)
                    }
                    stage++
                }
                6 -> {
                    openInterface(player, Components.DOUBLEOBJBOX_131).also {
                        player!!.packetDispatch.sendModelOnInterface(7369, Components.DOUBLEOBJBOX_131, 2, 80)
                        player.packetDispatch.sendAngleOnInterface(Components.DOUBLEOBJBOX_131, 2, 880, 450, 1)
                        setInterfaceText(player, " " + "the the castle's courtyard and the others you will find" + " scattered around Lumbridge.", Components.DOUBLEOBJBOX_131, 1)
                    }
                    stage++
                }
                7 -> {
                    openInterface(player, Components.DOUBLEOBJBOX_131).also {
                        player!!.packetDispatch.sendModelOnInterface(7369, Components.DOUBLEOBJBOX_131, 2, 80)
                        player.packetDispatch.sendAngleOnInterface(Components.DOUBLEOBJBOX_131, 2, 880, 450, 1)
                        setInterfaceText(player, " " + " If all else fails, visit the unknown website for a whole" + " chestload of information on quests, skills and minigames" + " as well as a very good starter's guide.", Components.DOUBLEOBJBOX_131, 1)
                    }
                    stage++
                }

                8 -> {
                    setAttribute(player, "/save:tutorial:complete", true)
                    setVarbit(player, 3756, 0)
                    teleport(player, Location.create(3233, 3230), TeleportManager.TeleportType.NORMAL)
                    closeOverlay(player)
                    player.inventory.clear()
                    player.bank.clear()
                    player.equipment.clear()
                    player.interfaceManager.restoreTabs()
                    player.interfaceManager.setViewedTab(3)
                    player.inventory.add(*STARTER_PACK)
                    player.bank.add(*STARTER_BANK)
                    interpreter.sendDialogue("Welcome to Lumbridge! To get more help click on the Lumbridge", "Guide or one of the Tutors - these can be found by looking", "for the question mark icon on your mini-map. If you are lost", "at any time, look for a signpost or use the Lumbridge Home Teleport.",)

                    stage = 9

                    TutorialStage.removeHintIcon(player)
                    player.unhook(TutorialKillReceiver)
                    player.unhook(TutorialFireReceiver)
                    player.unhook(TutorialResourceReceiver)
                    player.unhook(TutorialUseWithReceiver)
                    player.unhook(TutorialInteractionReceiver)
                    player.unhook(TutorialButtonReceiver)

                    if (GameWorld.settings!!.enable_default_clan) {
                        player.communication.currentClan = ServerConstants.SERVER_NAME
                        val clanJoin = JoinClanRequest.newBuilder()
                        clanJoin.clanName = ServerConstants.SERVER_NAME
                        clanJoin.username = player.name
                        ManagementEvents.publish(clanJoin.build())
                    }
                }

                9 -> {
                    setAttribute(player, "close_c_", true)
                    end()
                }
            }
        }
        return true
    }


    override fun getIds(): IntArray {
        return intArrayOf(NPCs.MAGIC_INSTRUCTOR_946)
    }

}