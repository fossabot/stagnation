package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Audmann dialogue plugin.
 */
@Initializable
class AudmannDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"Oh, don't bother me human.")
        stage = 0
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> player(FacialExpression.HALF_ASKING,"Why not? What's wrong?").also { stage++ }
            1 -> npc(FacialExpression.OLD_NORMAL,"You are wrong, human. Your attire is outrageous.", "Your presence is obnoxious.").also { stage++ }
            2 -> player(FacialExpression.HALF_ASKING,"What? What are you saying?").also { stage++ }
            3 -> npc(FacialExpression.OLD_NORMAL,"I'm saying you're in my way.").also { stage++ }
            4 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.AUDMANN_2201)
    }
}