package content.global.handlers.item.withobject

import config.Items
import content.global.skill.free.woodcutting.WoodcuttingNode
import content.global.skill.free.woodcutting.WoodcuttingSkillPulse
import core.api.submitIndividualPulse
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Axe On Tree listener.
 */
class AxeOnTree : InteractionListener {
    val axes = intArrayOf(
        Items.BRONZE_AXE_1351,
        Items.MITHRIL_AXE_1355,
        Items.IRON_AXE_1349,
        Items.BLACK_AXE_1361,
        Items.STEEL_AXE_1353,
        Items.ADAMANT_AXE_1357,
        Items.RUNE_AXE_1359,
        Items.DRAGON_AXE_6739,
        Items.INFERNO_ADZE_13661
    )
    val trees = WoodcuttingNode.values().map { it.id }.toIntArray()

    override fun defineListeners() {
        onUseWith(IntType.SCENERY, axes, *trees) { player, _, with ->
            submitIndividualPulse(player, WoodcuttingSkillPulse(player, with.asScenery()))
            return@onUseWith true
        }
    }

}