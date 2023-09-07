package content.region.asgarnia.handlers

import config.Items
import core.game.global.action.PickupHandler
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.GroundItem
import core.game.world.map.RegionManager

/**
 * Listeners for take the Wine of Zamorak.
 */
class WineOfZamorakInteraction : InteractionListener {

    override fun defineListeners() {
        on(Items.WINE_OF_ZAMORAK_245, IntType.GROUNDITEM,"take"){ player, wine ->
            if(player.location.regionId != 11574){
                PickupHandler.take(player, wine as GroundItem)
                return@on true
            }
            val npcs = RegionManager.getLocalNpcs(player)
            for (n in npcs) {
                if (n.id == 188) {
                    n.sendChat("Hands off zamorak's wine!")
                    n.properties.combatPulse.attack(player)
                }
            }
            return@on true
        }
    }
}
