package content.quest.member.horrorfromthedeep.npc

import config.NPCs
import core.api.location
import core.game.node.entity.player.Player

/**
 * Handles session for Dagannoth Mother in Horror from the deep quest.
 */
class DagannothSession(val player: Player) {

    private val dagannoth: DagannothMotherNPC = DagannothMotherNPC(
        NPCs.DAGANNOTH_MOTHER_1351, location(2520, 4645, 0), this)

    init {
        if (player.getExtension<Any?>(DagannothSession::class.java) != null) {
            player.removeExtension(DagannothSession::class.java)
        }
        player.addExtension(DagannothSession::class.java, this)
    }

    fun start() {
        dagannoth.init()
        player.unlock()
    }

    fun close() {
        dagannoth.clear()
        player.removeExtension(DagannothSession::class.java)
    }

    companion object {
        fun create(player: Player): DagannothSession {
            return DagannothSession(player)
        }

        fun getSession(player: Player): DagannothSession {
            return player.getExtension(DagannothSession::class.java)
        }
    }
}