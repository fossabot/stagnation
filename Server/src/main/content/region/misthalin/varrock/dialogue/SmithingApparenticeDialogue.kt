package content.region.misthalin.varrock.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Smithing Apparentice dialogue plugin.
 */
@Initializable
class SmithingApparenticeDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Can you teach me the basics of smelting please?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npc(FacialExpression.HALF_GUILTY, "You'll need to have mined some ore to smelt first. Go", "see the mining tutor to the south if you're not sure", "how to do this.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return SmithingApparenticeDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(
            NPCs.SMITHING_TUTOR_7959,
            NPCs.SMELTING_TUTOR_4904
        )
    }

}