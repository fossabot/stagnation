package content.quest.member.treegnomevillage.dialogue

import config.Items
import config.NPCs
import content.quest.member.treegnomevillage.TreeGnomeVillage
import core.api.getQuestStage
import core.api.inInventory
import core.api.removeItem
import core.api.setQuestStage
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Commander Montai dialogue file used in Tree Gnome Village quest.
 */
@Initializable
class CommanderTGVMontaiDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val questStage = getQuestStage(player!!, TreeGnomeVillage.questName)
        if (questStage == 10) {
            when(stage) {
                0 -> playerl(FacialExpression.FRIENDLY,"Hello.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY,"Hello traveller, are you here to help or just to watch?").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY,"I've been sent by King Bolren to retrieve the orb of protection.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY,"Excellent we need all the help we can get.").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY,"I'm Commander Montai. The orb is in the Khazard stronghold to the north, but until we weaken their defences we can't get close.").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY,"What can I do?").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY,"Firstly we need to strengthen our own defences. We desperately need wood to make more battlements, once the battlements are gone it's all over. Six loads of normal logs should do it.").also { stage++ }
                7 -> options("Ok, I'll gather some wood.", "Sorry, I no longer want to be involved.").also { stage++ }
                8 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY,"Ok, I'll gather some wood.").also { stage = 10 }
                    2 -> playerl(FacialExpression.FRIENDLY,"Sorry, I no longer want to be involved.").also { stage = 9 }
                }
                9 -> npcl(FacialExpression.FRIENDLY,"That's a shame, we could have done with your help.").also { stage = END_DIALOGUE }
                10 -> npcl(FacialExpression.FRIENDLY,"Please be as quick as you can, I don't know how much longer we can hold out.").also {
                    setQuestStage(player!!, TreeGnomeVillage.questName, 20)
                    stage = END_DIALOGUE
                }
            }
        } else if (questStage in 11..20) {
            if(inInventory(player!!, Items.LOGS_1511,6)){
                when(stage) {
                    0 -> playerl(FacialExpression.FRIENDLY,"Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY,"Hello again, we're still desperate for wood soldier.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"I have some here. (You give six loads of logs to the commander.)").also{ stage++ }
                    3 -> {
                        // Remove the 6 normal logs
                        for(i in 1..6) { removeItem(player!!, Items.LOGS_1511) }
                        setQuestStage(player!!, TreeGnomeVillage.questName, 25)
                        npcl(FacialExpression.FRIENDLY,"That's excellent, now we can make more defensive battlements. Give me a moment to organize the troops and then come speak to me. I'll inform you of our next phase of attack.")
                        stage = END_DIALOGUE
                    }
                }
            } else {
                when(stage) {
                    0 -> playerl(FacialExpression.FRIENDLY,"Hello.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY,"Hello again, we're still desperate for wood soldier. We need six loads of normal logs.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"I'll see what I can do.").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"Thank you.").also { stage = END_DIALOGUE }
                }
            }
        } else if (questStage in 21..29) {
            when(stage) {
                0 -> playerl(FacialExpression.HALF_ASKING,"How are you doing Montai?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY,"We're hanging in there soldier. For the next phase of our attack we need to breach their stronghold.").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY,"The ballista can break through the stronghold wall, and then we can advance and seize back the orb.").also { stage++ }
                3 -> playerl(FacialExpression.HALF_ASKING,"So what's the problem?").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY,"From this distance we can't get an accurate enough shot. We need the correct coordinates of the stronghold for a direct hit. I've sent out three tracker gnomes to gather them.").also { stage++ }
                5 -> playerl(FacialExpression.HALF_ASKING,"Have they returned?").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY,"I'm afraid not, and we're running out of time. I need you to go into the heart of the battlefield, find the trackers, and bring back the coordinates.").also { stage++ }
                7 -> npcl(FacialExpression.HALF_ASKING,"Do you think you can do it?").also { stage++ }
                8 -> options("No, I've had enough of your battle.", "I'll try my best.").also { stage++ }
                9 -> when(buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY,"No, I've had enough of your battle.").also { stage = 10 }
                    2 -> playerl(FacialExpression.FRIENDLY,"I'll try my best.").also { stage = 11 }
                }
                10 -> npcl(FacialExpression.FRIENDLY,"I understand, this isn't your fight.").also { stage = END_DIALOGUE }
                11 -> npcl(FacialExpression.FRIENDLY,"Thank you, you're braver than most.").also { stage++ }
                12 -> npcl(FacialExpression.FRIENDLY,"I don't know how long I will be able to hold out. Once you have the coordinates come back and fire the ballista right into those monsters.").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY,"If you can retrieve the orb and bring safety back to my people, none of the blood spilled on this field will be in vain.").also {
                    setQuestStage(player!!, TreeGnomeVillage.questName, 30)
                    stage = END_DIALOGUE
                }
            }
        } else if (questStage == 30) {
            when(stage) {
                0 -> playerl(FacialExpression.FRIENDLY,"Hello.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY,"Hello warrior. We need the coordinates for a direct hit from the ballista.").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY,"Once you have a direct hit you will be able to enter the stronghold and retrieve the orb.").also { stage = END_DIALOGUE }
            }
        } else if (questStage == 31) {
            if(inInventory(player!!, Items.ORB_OF_PROTECTION_587)){
                when(stage) {
                    0 -> playerl(FacialExpression.FRIENDLY,"I have the orb of protection.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY,"Incredible, for a human you really are something.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY,"Thanks... I think!").also { stage++ }
                    3 -> npcl(FacialExpression.FRIENDLY,"I'll stay here with my troops and try and hold Khazard's men back. You return the orb to the gnome village. Go as quick as you can, the village is still unprotected.").also { stage = END_DIALOGUE }
                }

            } else {
                when(stage) {
                    0 -> playerl(FacialExpression.FRIENDLY,"I've breached the stronghold.").also { stage++ }
                    1 -> npcl(FacialExpression.FRIENDLY,"I saw, that was a beautiful sight. The Khazard troops didn't know what hit them.").also { stage++ }
                    2 -> npcl(FacialExpression.FRIENDLY,"Now is the time to retrieve the orb. It's all in your hands. I'll be praying for you.").also { stage = END_DIALOGUE }
                }
            }
        } else if (questStage != 0){
            when(stage) {
                0 -> playerl(FacialExpression.FRIENDLY,"Hello Montai, how are you?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY,"I'm ok, this battle is going to take longer to win than I expected. The Khazard troops won't give up even without the orb.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY,"Hang in there.").also { stage = END_DIALOGUE }
            }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.COMMANDER_MONTAI_470)
    }
}