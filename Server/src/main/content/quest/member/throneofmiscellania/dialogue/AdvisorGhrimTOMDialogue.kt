package content.quest.member.throneofmiscellania.dialogue

import config.NPCs
import content.global.diary.dialogue.AdvisorGhrimDiaryDialogue
import core.api.openDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Handles Advisor Ghrim dialogue plugin for Throne of Miscellania quest.
 */
@Initializable
class AdvisorGhrimTOMDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun newInstance(player: Player?): DialoguePlugin {
        return AdvisorGhrimTOMDialogue(player)
    }

    override fun open(vararg args: Any?): Boolean {
        npcl(FacialExpression.HALF_GUILTY, "Greetings, ${if (player.isMale) "Sir" else "Madam"}")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("How do I make peace with Etceteria?", "About the Achievement Diary...").also { stage++ }
            1 -> when (buttonId) {
                1 -> playerl(FacialExpression.ANGRY, "How do I make peace with Etceteria?").also { stage++ }
                2 -> openDialogue(player, AdvisorGhrimDiaryDialogue()).also { stage = 100 }
            }
            2 -> npcl(FacialExpression.HALF_GUILTY, "You should go talk to Queen Sigrid of Etceteria.").also { stage = 100 }

            100 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ADVISOR_GHRIM_1375)
    }
}