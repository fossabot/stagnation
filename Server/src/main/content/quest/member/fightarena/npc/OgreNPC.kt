package content.quest.member.fightarena.npc

import config.NPCs
import content.quest.member.fightarena.dialogue.GenKhazardFADialogue
import core.api.*
import core.game.node.entity.Entity
import core.game.node.entity.npc.AbstractNPC
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.plugin.Initializable

@Initializable
class OgreNPC(id: Int = 0, location: Location? = null) : AbstractNPC(id, location) {
    var clearTime = 0
    override fun construct(id: Int, location: Location, vararg objects: Any): AbstractNPC {
        return OgreNPC(id, location)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.KHAZARD_OGRE_270)
    }

    override fun handleTickActions() {
        super.handleTickActions()
        if (clearTime++ > 300) poofClear(this)
    }

    companion object {
        fun spawnOgre(player: Player) {
            val ogre = OgreNPC(NPCs.KHAZARD_OGRE_270)
            ogre.location = location(2603, 3166, 0)
            ogre.isWalks = true
            ogre.isAggressive = true
            ogre.isActive = false

            if (ogre.asNpc() != null && ogre.isActive && getAttribute(player, "spawn-ogre", false)) {
                ogre.properties.teleportLocation = ogre.properties.spawnLocation
            }
            ogre.isActive = true
            GameWorld.Pulser.submit(object : Pulse(2, ogre) {
                override fun pulse(): Boolean {
                    ogre.init()
                    ogre.attack(player)
                    registerHintIcon(player, ogre)
                    return true
                }
            })
        }
    }

    override fun finalizeDeath(killer: Entity?) {
        if (killer is Player) {
            val quest = "Fight Arena"
            if (getQuestStage(killer, quest) == 68 || getQuestStage(killer, quest) == 88) {
                setQuestStage(killer, content.quest.member.fightarena.FightArena.FightArenaQuest, 72)
            }
            clearHintIcon(killer)
            removeAttribute(killer, "spawn-ogre")
            openDialogue(killer, GenKhazardFADialogue())
        }
        clear()
        super.finalizeDeath(killer)
    }
}