package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Wemund dialogue plugin.
 */
@Initializable
class WemundDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"What can I interest you in? Need any pipes?","I make very sturdy pipes.")
        stage = 0
        return true
    }
    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> player(FacialExpression.HALF_ASKING,"No, not really... what kind of shop is this anyway?").also { stage++ }
            1 -> npc(FacialExpression.OLD_NORMAL,"Why, it's Wemund's Wrench Warehouse.","Wemund, that's me.").also { stage++ }
            2 -> npc(FacialExpression.OLD_NORMAL,"I don't really sell just wrenches, you know, I sell anything","related to the maintenance of dwarven steam machines.").also { stage++ }
            3 -> npc(FacialExpression.OLD_NORMAL,"Sometimes I do repair work as well.","Or I send my assistant.").also { stage++ }
            4 -> player(FacialExpression.HALF_ASKING,"Who's he then?").also { stage++ }
            5 -> npc(FacialExpression.OLD_NORMAL,"Oh, he's right over there... used to work in the mines to","the south, poor lad. Made a bit of a mess of things ","I believe.").also { stage++ }
            6 -> npc(FacialExpression.OLD_NORMAL,"So what'll it be then? Got any ship engines to repair?").also { stage++ }
            7 -> player("Thanks, but I don't think your services are","required.").also { stage++ }
            8 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.WEMUND_2155)
    }
}