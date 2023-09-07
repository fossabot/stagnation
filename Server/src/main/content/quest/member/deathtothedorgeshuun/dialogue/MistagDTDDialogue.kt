package content.quest.member.deathtothedorgeshuun.dialogue

import config.NPCs
import core.api.getQuestStage
import core.api.setQuestStage
import core.game.dialogue.DialogueFile
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC

import core.tools.END_DIALOGUE

/**
 * Represents the Mistag dialogue for Death to the Dorgeshuun quest.
 */
class MistagDTDDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.MISTAG_2084)
        val questName = "Death to the Dorgeshuun"
        val questStage = getQuestStage(player!!, questName)
        when {
            (questStage == 0) -> {
                when(stage) {
                    0 -> npc(FacialExpression.OLD_NORMAL,"Surface-dwellers have been visiting the mines for some","time now, but no Dorgeshuun has yet visited the"," surface.").also { stage++ }
                    1 -> npc(FacialExpression.OLD_NORMAL,"We are curious about the surface, and we are also","worried about the dangers it poses. We know that","Lumbridge is friendly but we are worried that the HAM","group may be plotting against us.").also { stage++ }
                    2 -> npc(FacialExpression.OLD_NORMAL,"We are planning to send an agent to the surface and","we would like you to act as a guide.").also { stage++ }
                    3 -> options("I'll act as a guide.", "I'm too busy.").also { stage++ }
                    4 -> when(buttonID){
                        1 -> playerl(FacialExpression.FRIENDLY,"I'll act as a guide.").also { stage = 6 }
                        2 -> playerl(FacialExpression.NEUTRAL,"I'm too busy.").also { stage = 5 }
                    }
                    5 -> npc(FacialExpression.OLD_NORMAL,"Oh dear. Our agent is ready, but the Council will not let this go ahead without a guide. If you reconsider please let me know!").also { stage = END_DIALOGUE }
                    6 -> npc(FacialExpression.OLD_HAPPY,"Thank you!").also { stage++ }
                    7 -> npc(FacialExpression.OLD_NORMAL,"In order to get into the HAM base undetected you will","both need to go in disguise. You should get two full sets","of HAM robes. Once you have them, our agent will","meet you in the cellar of Lumbridge castle.").also {
                        stage = END_DIALOGUE
                        setQuestStage(player!!, "Death to the Dorgeshuun", 1)
                    }
                }
            }
        }
    }

}