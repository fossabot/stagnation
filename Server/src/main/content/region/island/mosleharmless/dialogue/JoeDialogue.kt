package content.region.island.mosleharmless.dialogue

import config.Items
import config.NPCs
import core.api.inInventory
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Joe pirate dialogue plugin.
 */
@Initializable
class JoeDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        player("Hello!")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> if (!inInventory(player, Items.BOOK_O_PIRACY_7144)) {
                npcl(FacialExpression.FRIENDLY, "Arr? Be ye wantin' te go on account with our gang o' fillibusters?").also { stage = 1 }
            } else {
                npc("Whadda ya want?").also { stage = 4 }
            }
            1 -> npcl(FacialExpression.FRIENDLY, "The powder monkey be takin' a caulk after gettin' rowdy on bumboo, so there be plenty of room for ye.").also { stage++ }
            2 -> player(FacialExpression.STRUGGLE, "Riiiiight...").also { stage++ }
            3 -> playerl(FacialExpression.STRUGGLE, "I'll just be over here if you need me.").also { stage = END_DIALOGUE }
            4 -> options("Whadda ya got?", "Nothing! Honest!", "I wanted to know where you got that harpoon hand.").also { stage++ }.also { stage++ }
            5 -> when (buttonId) {
                1 -> npc("We gots grog and we gots food. Here, take a look.").also { stage = 16 }
                2 -> {
                    end()
                    player("Nothing! Honest!").also { stage = 17 }
                }
                3 -> playerl(FacialExpression.HAPPY, "I wanted to know where you got that harpoon hand.").also { stage = 6 }
            }
            6 -> npcl(FacialExpression.FRIENDLY, "Yer new in town aintcha?").also { stage++ }
            7 -> playerl(FacialExpression.STRUGGLE, "Newish certainly.").also { stage++ }
            8 -> npcl(FacialExpression.FRIENDLY, "Well, if ye wants te hear the story, here goes.").also { stage++ }
            9 -> npcl(FacialExpression.FRIENDLY, "See, I was walkin' along the docks one day, and this fierce storm blew up.").also { stage++ }
            10 -> npcl(FacialExpression.FRIENDLY, "All of a sudden I turn an there, bearin' down on me is this albatross...").also { stage++ }
            11 -> playerl(FacialExpression.NEUTRAL, "Sorry to interrupt, but I would just like to clear something up.").also { stage++ }
            12 -> npcl(FacialExpression.FRIENDLY, "What? I was just gettin' to the good bit.").also { stage++ }
            13 -> playerl(FacialExpression.NEUTRAL, "When I said 'I wanted to know where you got that harpoon hand', I meant where did you get it made, or buy it from?").also { stage++ }
            14 -> npcl(FacialExpression.FRIENDLY, "If ye don't wants to hear the story then I ain't gonna tell ye!").also { stage++ }
            15 -> end()
            16 -> {
                end()
                openNpcShop(player, NPCs.JOE_3163)
                stage = END_DIALOGUE
            }
            17 -> npc("Then stop takin' up me floor space!").also { stage++ }
            18 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.JOE_3163)
    }
}