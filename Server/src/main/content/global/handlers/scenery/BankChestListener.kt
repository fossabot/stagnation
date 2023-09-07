package content.global.handlers.scenery

import config.Scenery
import core.api.openBankAccount
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

private val BANK_CHESTS = intArrayOf(
    Scenery.BANK_CHEST_3194, Scenery.BANK_CHEST_4483,
    Scenery.BANK_CHEST_10562, Scenery.BANK_CHEST_14382,
    Scenery.BANK_CHEST_16695, Scenery.BANK_CHEST_16696,
    Scenery.BANK_CHEST_21301, Scenery.BANK_CHEST_27662,
    Scenery.BANK_CHEST_27663
)

/**
 * Allows the user to interact with Bank Chests.
 */
class BankChestListener : InteractionListener {
    override fun defineListeners() {
        defineInteraction(IntType.SCENERY, BANK_CHESTS,  "bank", "use") {player, node, state ->
            openBankAccount(player)
            return@defineInteraction true
        }
    }
}