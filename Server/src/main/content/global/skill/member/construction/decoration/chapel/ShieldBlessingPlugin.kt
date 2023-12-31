package content.global.skill.member.construction.decoration.chapel

import config.Items
import config.Scenery
import config.Sounds
import core.api.playAudio
import core.api.sendMessage
import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.world.update.flag.context.Animation
import core.plugin.Initializable
import core.plugin.Plugin

const val HOLY_ELIXER = 13754
const val SPIRIT_SHIELD = 13734

/**
 * Handles blessing the Spirit shields.
 */
@Initializable
class ShieldBlessingPlugin : UseWithHandler(HOLY_ELIXER, SPIRIT_SHIELD) {
    override fun newInstance(arg: Any?): Plugin<Any> {
        for (i in arrayOf(
            Scenery.ALTAR_13185,
            Scenery.ALTAR_13188,
            Scenery.ALTAR_13191,
            Scenery.ALTAR_13194,
            Scenery.ALTAR_13197)
        )
        {
            addHandler(i, OBJECT_TYPE, this)
        }
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        val player = event?.player
        player ?: return false
        if (player.ironmanManager.isIronman && !player.houseManager.isInHouse(player)) {
            sendMessage(player, "You cannot do this on someone else's altar.")
            return true
        }
        if(player.skills.getLevel(Skills.PRAYER) < 85){
            sendMessage(player, "You need 85 prayer to do this.")
            return true
        }

        player.animator.animate(Animation(896))
        playAudio(player, Sounds.POH_OFFER_BONES_958)

        if(player.inventory.remove(Item(HOLY_ELIXER)) && player.inventory.remove(Item(SPIRIT_SHIELD)))
            player.inventory.add(Item(Items.BLESSED_SPIRIT_SHIELD_13736))
        return true
    }
}