package content.quest.member.trollstronghold.npc

import config.Items
import config.NPCs
import core.api.isQuestInProgress
import core.api.produceGroundItem
import core.api.transformNpc
import core.game.node.entity.Entity
import core.game.node.entity.combat.CombatStyle
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Represents the Berry NPC used in Troll stronghold quest.
 */
@Initializable
class BerryNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {
    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return BerryNPC(id, location)
    }

    override fun isAttackable(entity: Entity, style: CombatStyle, message: Boolean): Boolean {
        val attackable = super.isAttackable(entity, style, message)
        transformNpc(this.asNpc(), NPCs.BERRY_1127, 50)
        return attackable
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BERRY_1127, NPCs.BERRY_1129)
    }

    override fun finalizeDeath(killer: Entity?) {
        if (isQuestInProgress(killer as Player, "Troll Stronghold", 8, 10)) {
            produceGroundItem(killer, Items.CELL_KEY_2_3137, 1, this.location)
        }
        super.finalizeDeath(killer)
    }
}