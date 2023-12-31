package content.quest.member.horrorfromthedeep.npc

import config.NPCs
import content.quest.member.horrorfromthedeep.dialogue.JossikHFTDUndergroundDialogue
import core.api.*
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.Initializable

/**
 * Represents the Dagannoth Baby NPC.
 */
@Initializable
class DagonnothBabyNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {
    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return DagonnothBabyNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.DAGANNOTH_3591)
    }

    companion object {
        fun spawnDagannothBaby(player: Player) {
            val dag = DagonnothBabyNPC(NPCs.DAGANNOTH_1347)
            dag.location = location(2520, 4645, 0)
            dag.isWalks = true
            dag.isAggressive = true
            dag.isActive = false

            if (dag.asNpc() != null && dag.isActive) {
                dag.properties.teleportLocation = dag.properties.spawnLocation
            }
            dag.isActive = true
            GameWorld.Pulser.submit(object : Pulse(2, dag) {
                override fun pulse(): Boolean {
                    dag.init()
                    registerHintIcon(player, dag)
                    dag.attack(player)
                    return true
                }
            })
        }
    }

    override fun finalizeDeath(killer: Entity?) {
        if (killer is Player) {
            lock(killer, 10)
            setQuestStage(killer, "Horror from the Deep", 60)
            clearHintIcon(killer)
            runTask(killer, 1) {
                face(findNPC(NPCs.JOSSIK_1335)).also {
                    openDialogue(killer, JossikHFTDUndergroundDialogue())
                }
            }
        }
        clear()
        super.finalizeDeath(killer)
    }
}