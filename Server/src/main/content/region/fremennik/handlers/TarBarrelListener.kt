package content.region.fremennik.handlers

import config.Items
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item
import core.game.node.scenery.SceneryBuilder
import core.tools.RandomFunction

/**
 * Represents the option listener for tar barrel.
 */
class TarBarrelListener : InteractionListener {

    val FULL_TAR_BARREL_16860 = 16860
    val EMPTY_TAR_BARREL_16688 = 16688
    val EMPTY_BARREL_STRING = "The barrel became empty!"

    override fun defineListeners() {

        on(FULL_TAR_BARREL_16860, IntType.SCENERY, "take-from") { player, node ->
            val incrementAmount = RandomFunction.random(83, 1000)

            if (player.inventory.isFull) {
                sendMessage(player, "Not enough space in your inventory!")
                return@on true
            }

            if (node.asScenery().charge >= 0) {
                node.asScenery().charge = node.asScenery().charge - incrementAmount
                player.inventory.add(Item(Items.SWAMP_TAR_1939))

                if (node.asScenery().charge <= 0) {
                    player.sendMessage(EMPTY_BARREL_STRING)
                    SceneryBuilder.replace(node.asScenery(), node.asScenery().transform(EMPTY_TAR_BARREL_16688), 38)
                    node.asScenery().charge = 1000
                }
            }

            return@on true
        }

    }
}