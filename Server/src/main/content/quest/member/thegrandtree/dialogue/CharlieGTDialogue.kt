package content.quest.member.thegrandtree.dialogue

import config.Items
import config.NPCs
import content.quest.member.thegrandtree.TheGrandTree
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.global.action.DoorActionHandler
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.GameWorld.settings
import core.game.world.map.Direction
import core.game.world.map.Location
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents Charlie dialogue in the grand tree quest.
 */
@Initializable
class CharlieGTDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (getQuestStage(player!!, TheGrandTree.questName)) {
            46 -> {
                when (stage) {
                    0 -> playerl(FacialExpression.FRIENDLY,"Tell me. Why would you want to kill the Grand Tree?").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY,"What do you mean?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"Don't tell me, you just happened to be caught carrying Daconia rocks!").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"All I know is that I did what I was asked.").also { stage++ }
                    4 -> playerl(FacialExpression.FRIENDLY,"I don't understand.").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY,"Glough paid me to go to this gnome on a hill. I gave the gnome a seal and he gave me some Daconia rocks to give to Glough.").also { stage++ }
                    6 -> npcl(FacialExpression.FRIENDLY,"I've been doing it for weeks, this time though Glough locked me up here! I just don't understand it.").also { stage++ }
                    7 -> playerl(FacialExpression.FRIENDLY,"Sounds like Glough is hiding something.").also { stage++ }
                    8 -> npcl(FacialExpression.FRIENDLY,"I don't know what he's up to. If you want to find out, you'd better search his home.").also { stage++ }
                    9 -> playerl(FacialExpression.FRIENDLY,"OK. Thanks Charlie.").also { stage++ }
                    10 -> npcl(FacialExpression.FRIENDLY,"Good luck!").also {
                        setQuestStage(player!!, TheGrandTree.questName, 47)
                        stage = END_DIALOGUE
                    }
                }
            }
            47 -> {
                when (stage) {
                    0 -> npcl(FacialExpression.FRIENDLY,"Hello adventurer. Have you figured out what's going on?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY,"No idea.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY,"To get to the bottom of this you'll need to search Glough's home.").also { stage = END_DIALOGUE }
                }
            }
            50 -> {
                when(stage) {
                    0 -> npcl(FacialExpression.FRIENDLY,"So they got you as well?").also { stage++ }
                    1 -> playerl(FacialExpression.FRIENDLY,"It's Glough! He's trying to cover something up.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY,"I shouldn't tell you this adventurer. But if you want to get to the bottom of this you should go and talk to the Karamja Shipyard foreman.").also { stage++ }
                    3 -> playerl(FacialExpression.FRIENDLY,"Why?").also { stage++ }
                    4 -> npcl(FacialExpression.FRIENDLY,"Glough sent me to Karamja to meet him. I delivered a large amount of gold. For what? I don't know. He may be able to tell you what Glough's up to. That's if you can get out of here. You'll find him").also { stage++ }
                    5 -> npcl(FacialExpression.FRIENDLY,"in the Karamja Shipyard, east of Shilo Village. Be careful! If he discovers you're not working for Glough, there'll be trouble! The sea men use the password Ka-Lu-Min.").also { stage++ }
                    6 -> playerl(FacialExpression.FRIENDLY,"Thanks Charlie!").also {
                        stage = END_DIALOGUE
                        GameWorld.Pulser.submit(object : Pulse(0) {
                            var count = 0
                            val npc = NPC.create(NPCs.KING_NARNODE_SHAREEN_670, Location.create(2467, 3496, 3), null)
                            override fun pulse(): Boolean {
                                when (count) {
                                    0 -> {
                                        // Spawn in narnode
                                        npc.init();
                                        lock(player!!,10)
                                        forceWalk(npc, player!!.location.transform(Direction.EAST, 2), "SMART")
                                    }

                                    2 -> {
                                        sendNPCDialogue(player!!, npc.id,"Traveller please accept my apologies! Glough had no right to arrest you! I just think he's scared of humans. Let me get you out of there.")
                                    }

                                    4 -> {
                                        DoorActionHandler.handleAutowalkDoor(player!!,
                                            getScenery(2465,3496,3)
                                        )
                                        openDialogue(player!!, KingNarnodeUpstairsDialogue(), npc)
                                    }
                                    8 -> {
                                        unlock(player!!)
                                        npc.clear();
                                        setQuestStage(player!!, TheGrandTree.questName, 55)
                                        return true;
                                    }
                                }
                                count++
                                return false
                            }
                        })
                    }
                }
            }
            55 -> {
                if(player!!.hasItem(Item(Items.LUMBER_ORDER_787))){
                    when(stage) {
                        0 -> playerl(FacialExpression.FRIENDLY,"How are you doing Charlie?").also { stage++ }
                        1 -> npcl(FacialExpression.FRIENDLY,"I've been better.").also { stage++ }
                        2 -> playerl(FacialExpression.FRIENDLY,"Glough has some plan to rule "+settings!!.name+"!").also { stage++ }
                        3 -> npcl(FacialExpression.FRIENDLY,"I wouldn't put it past him, the Gnome's crazy!").also { stage++ }
                        4 -> playerl(FacialExpression.FRIENDLY,"I need some proof to convince the King.").also { stage++ }
                        5 -> npcl(FacialExpression.FRIENDLY,"Hmm...you could be in luck! Before Glough had me locked up I heard him mention that he'd left his chest keys at his girlfriend's.").also { stage++ }
                        6 -> playerl(FacialExpression.FRIENDLY,"Where does she live?").also { stage++ }
                        7 -> npcl(FacialExpression.FRIENDLY,"Just west of the toad swamp.").also { stage++ }
                        8 -> playerl(FacialExpression.FRIENDLY,"OK, I'll see what I can find.").also {
                            setQuestStage(player!!, TheGrandTree.questName, 60)
                            stage = END_DIALOGUE
                        }
                    }
                } else {
                    when(stage) {
                        0 -> playerl(FacialExpression.FRIENDLY,"I can't figure this out Charlie!").also { stage++ }
                        1 -> npcl(FacialExpression.FRIENDLY,"Go and see the foreman in the Karamja jungle, there's a shipyard there, you might find some clues. Don't forget the password is Ka-Lu-Min;").also { stage++ }
                        2 -> npcl(FacialExpression.FRIENDLY,"Tf they realise that you're not working for Glough there'll be trouble!").also { stage = END_DIALOGUE }
                    }
                }
            } else -> {
                when(stage){
                    0 -> sendDialogue(player!!, "The prisoner is in no mood to talk.").also { stage = END_DIALOGUE }
                }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.CHARLIE_673)
    }
}