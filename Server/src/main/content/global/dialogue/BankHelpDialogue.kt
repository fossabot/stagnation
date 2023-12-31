package content.global.dialogue

import config.Components
import config.Items
import core.api.openInterface
import core.api.sendDialogue
import core.api.sendItemDialogue
import core.game.dialogue.DialogueFile
import core.game.node.item.Item
import core.tools.START_DIALOGUE

/**
 * Represents the Bank dialogue on interface.
 */
class BankHelpDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when (stage) {
            START_DIALOGUE -> options("Check Bank Value", "Banking Assistance", "Close").also { stage++ }

            1 -> when (buttonID) {
                1 -> player?.let {
                    end()
                    val wealth = it.bank.wealth
                    if (wealth > 0) {
                        val word = if (wealth != 1) "coins" else "coin"
                        sendItemDialogue(it, Item(Items.COINS_995, wealth), "<br>Your bank is worth <col=a52929>${wealth}</col> ${word}.")
                    } else {
                        sendDialogue(it, "You have no valuables in your bank.")
                    }
                }
                2 -> player?.let {
                    end()
                    it.bank.close()
                    openInterface(it, Components.BANK_V2_HELP_767)
                }
                3 -> end()
            }
        }
    }
}