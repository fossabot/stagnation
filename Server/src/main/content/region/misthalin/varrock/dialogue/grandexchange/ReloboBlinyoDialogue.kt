package content.region.misthalin.varrock.dialogue.grandexchange

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.ge.GEGuidePrice
import core.game.ge.GEGuidePrice.GuideType
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Relobo Blinyo logs dialogue plugin.
 */
@Initializable
class ReloboBlinyoDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any): Boolean {
        npc = args[0] as NPC
        player(FacialExpression.HALF_GUILTY, "Hey there.")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> npcl(FacialExpression.HALF_GUILTY, "Why hello to you too, my friend.").also { stage++ }
            1 -> options("You look like you've travelled a fair distance.", "I'm trying to find the prices for logs.", "Sorry, I need to be macking tracks.").also { stage++ }
            2 -> when (buttonId) {
                1 -> playerl(FacialExpression.HALF_GUILTY, "You look like you've travelled a fair distance.").also { stage = 5 }
                2 -> playerl(FacialExpression.HALF_GUILTY, "I'm trying to find the prices for logs.").also { stage = 3 }
                3 -> playerl(FacialExpression.HALF_GUILTY, "Sorry, I need to be making tracks.").also { stage = 12 }
            }
            3 -> npcl(FacialExpression.HALF_GUILTY, "Then you've come to the right person.").also { stage++ }
            4 -> { end(); GEGuidePrice.open(player, GuideType.LOGS) }
            5 -> npcl(FacialExpression.HALF_GUILTY, "What gave me away?").also { stage++ }
            6 -> player(FacialExpression.HALF_GUILTY, "I don't mean to be rude, but the face paint and", "hair, for startes.").also { stage++ }
            7 -> npc(FacialExpression.HALF_GUILTY, "Ah, yes I'm from Shilo Village on Karamja. It's a style", "I've had since I was little.").also { stage++ }
            8 -> playerl(FacialExpression.HALF_GUILTY, "Then tell me, why are you so far from home?").also { stage++ }
            9 -> npc(FacialExpression.HALF_GUILTY, "This Grand Exchange! Isn't it marvellous I've never seen", "anything like it in my life. My people were not pleased to", "see me break traditions to visit such a place. But i hope", "to make some serious profit. then they'll see I was right!").also { stage++ }
            10 -> player(FacialExpression.HALF_GUILTY, "So, what are you selling?").also { stage++ }
            11 -> npc(FacialExpression.HALF_GUILTY, "Logs! of all kinds! That's my plan, at least. Nature", "is one thing my people understand very well.").also { stage = 13 }
            12 -> npc(FacialExpression.HALF_GUILTY, "Okay. Nice talking to you!").also { stage++ }
            13 -> end()
        }
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return ReloboBlinyoDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.RELOBO_BLINYO_LOGS_6526)
    }

}