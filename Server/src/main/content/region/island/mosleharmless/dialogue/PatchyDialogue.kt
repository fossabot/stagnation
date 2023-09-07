package content.region.island.mosleharmless.dialogue

import config.Components
import config.Items
import config.NPCs
import core.api.inInventory
import core.api.openInterface
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Patchy dialogue plugin.
 */
@Initializable
class PatchyDialogue(player: Player? = null) : DialoguePlugin(player) {

    // https://runescape.wiki/w/Transcript:Patchy
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (!inInventory(player, Items.BOOK_O_PIRACY_7144)) {
            npcl(FacialExpression.FRIENDLY, "Arr? Be ye wantin' te go on account with our gang o' fillibusters?").also { stage = 1 }
        } else {
            npcl(FacialExpression.FRIENDLY, "Hello there! Can I sew ye somethin' tegether?").also { stage = 4 }

        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            1 -> npcl(FacialExpression.FRIENDLY, "The powder monkey be takin' a caulk after gettin' rowdy on bumboo, so there be plenty of room for ye.").also { stage++ }
            2 -> player(FacialExpression.STRUGGLE, "Riiiiight...").also { stage++ }
            3 -> playerl(FacialExpression.STRUGGLE, "I'll just be over here if you need me.").also { stage = END_DIALOGUE }
            4 -> playerl(FacialExpression.HALF_ASKING, "What do you mean?").also { stage++ }
            5 -> npc("Well, ye see it works like this; I can sew yer eye", "patches onto yer pirate hats or pirate bandanas.").also { stage++ }
            6 -> npc("Just pirate bandanas mind ye, I hate the feel of", "snakeskin. (Shudder)").also { stage++ }
            7 -> npc("It costs 500 gold te do it, and 600 gold te get them", "separated.").also { stage++ }
            8 -> player(FacialExpression.HALF_ASKING, "Ok, why does it cost more to get them separated than", "put together?").also { stage++ }
            9 -> npc("Wear and tear no scissors.").also { stage++ }
            10 -> npc("On another note, I also sew crab claw gauntlets on to", "pirate hooks, highwayman masks to black cavaliers, and", "mime masks to black berets, If ye have them with ye.").also { stage++ }
            11 -> npc(FacialExpression.HALF_ASKING, "So, do ye have anything ye wants doin?").also { stage++ }
            12 -> options("Yes", "No").also { stage++ }
            13 -> when (buttonId) {
                1 -> player("Yes, please!").also { stage++ }
                2 -> player("No").also { stage = 100 }
            }
            14 -> npc("Now, do you want to be sewing items together or", "separating them?").also { stage++ }
            15 -> options("Sew together", "Separate").also { stage++ }
            16 -> {
                when (buttonId) {
                    1 -> {
                        end()
                        openInterface(player, Components.SEW_INTERFACE_419)
                        stage = END_DIALOGUE
                    }
                    2 -> {
                        end()
                        playerl(FacialExpression.NEUTRAL, "I need something separated.")
                        stage = 17
                    }
                }
            }

            17 -> npc("Aye, aye. Just use yer item on me and I'll see", "if I can separate them.").also { stage = END_DIALOGUE }
            100 -> npc("Fair enough. See you around!").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return PatchyDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.PATCHY_4359)
    }
}