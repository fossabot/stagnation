package content.region.wilderness.handlers

import config.Items
import core.api.sendMessage
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.GroundItemManager
import core.game.node.item.Item
import core.game.node.scenery.Scenery
import core.game.node.scenery.SceneryBuilder
import core.game.world.update.flag.context.Animation

/**
 * Handles the Muddy chest in Wilderness.
 */
class MuddyChestHandler : InteractionListener {

    private val CHEST = 170

    override fun defineListeners() {

        on(CHEST, IntType.SCENERY, "open"){ player, node ->
            val key = Item(Items.MUDDY_KEY_991)
            if(player.inventory.containsItem(key)){
                player.inventory.remove(key)
                player.animator.animate(Animation(536))
                SceneryBuilder.replace(node.asScenery(), Scenery(171, node.location, node.asScenery().rotation),3)
                for(item in chestLoot){
                    if(!player.inventory.add(item)) GroundItemManager.create(item,player)
                }
            } else {
                sendMessage(player, "This chest is locked and needs some sort of key.")
            }
            return@on true
        }

    }

    val chestLoot = arrayOf(
            Item(Items.UNCUT_RUBY_1619),
            Item(Items.MITHRIL_BAR_2359),
            Item(Items.MITHRIL_DAGGER_1209),
            Item(Items.ANCHOVY_PIZZA_2297),
            Item(Items.LAW_RUNE_563,2),
            Item(Items.DEATH_RUNE_560,2),
            Item(Items.CHAOS_RUNE_562,10),
            Item(995,50)
    )
}