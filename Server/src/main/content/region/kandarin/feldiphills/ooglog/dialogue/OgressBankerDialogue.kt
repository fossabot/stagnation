package content.region.kandarin.feldiphills.ooglog.dialogue

import config.NPCs
import core.api.sendNPCDialogue
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Provides dialogue tree for the Ogress Bankers in the city of Oo'glog.
 */
@Initializable
class OgressBankerDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun getIds(): IntArray = intArrayOf(
        NPCs.OGRESS_BANKER_7049,
        NPCs.OGRESS_BANKER_7050
    )

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            START_DIALOGUE -> npcl(FacialExpression.CHILD_NORMAL, "...").also { stage++ }
            1 -> playerl(FacialExpression.NEUTRAL, "Excuse me, can I get some service here, please?").also { stage++ }
            2 -> npcl(FacialExpression.CHILD_EVIL_LAUGH, "GRAAAAAH! You go away, human! Me too busy with training to talk to puny thing like you.").also { stage++ }
            3 -> sendNPCDialogue(player, NPCs.BALNEA_7047, "I do apologise, sir. We're temporarily unable to meet your banking needs.", FacialExpression.FRIENDLY).also { stage++ }
            4 -> sendNPCDialogue(player, NPCs.BALNEA_7047, "We'll be open as soon as we realize our customer experience goals " + "and can guarantee the high standards of service that you expect from all " + "branches of the Bank of Gielinor.", FacialExpression.FRIENDLY).also { stage++ }
            5 -> playerl(FacialExpression.THINKING, "What did you just say to me?").also { stage++ }
            6 -> sendNPCDialogue(player, NPCs.BALNEA_7047, "We're closed until I can teach these wretched creatures some manners.", FacialExpression.FRIENDLY).also { stage++ }
            7 -> playerl(FacialExpression.NEUTRAL, "Ah, right. Good luck with that.").also { stage = END_DIALOGUE }
        }
        return true
    }
}