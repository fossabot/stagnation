package content.quest.member.trollstronghold.npc

import config.Items
import config.NPCs
import core.api.isQuestInProgress
import core.api.produceGroundItem
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Represents the Troll Generals NPC used in Troll stronghold quest.
 */
@Initializable
class TrollGeneralsNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id,location) {

    override fun construct(id: Int, location: Location?, vararg objects: Any?): AbstractNPC {
        return TrollGeneralsNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.TROLL_GENERAL_1115, NPCs.TROLL_GENERAL_1116, NPCs.TROLL_GENERAL_1117)
    }

    override fun finalizeDeath(killer: Entity?) {
        if(isQuestInProgress(killer as Player, "Troll Stronghold", 1, 7)) {
            produceGroundItem(killer, Items.PRISON_KEY_3135, 1, this.location)
        }
        super.finalizeDeath(killer)
    }
}