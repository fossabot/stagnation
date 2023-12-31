package content.quest.member.lostcity.handlers

import config.NPCs
import content.data.skill.SkillingTool
import content.global.skill.free.woodcutting.WoodcuttingSkillPulse
import content.quest.member.lostcity.npc.TreeSpiritNPC
import core.api.getQuestStage
import core.api.sendMessage
import core.api.setAttribute
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.scenery.Scenery
import core.game.world.map.Location
import config.Scenery as Sceneries

/**
 * Represents interaction listener of Dramen Tree for Lost City Quest.
 */
class DramenTreeListener : InteractionListener {

    override fun defineListeners() {

        on(Sceneries.DRAMEN_TREE_1292, IntType.SCENERY, "chop down"){ player, node ->
            val questStage = getQuestStage(player,"Lost City")
            if (SkillingTool.getHatchet(player) == null) {
                sendMessage(player,"You do not have an axe which you have the level to use.")
                return@on true
            }
            if (questStage < 20) {
                return@on true
            }
            if (questStage == 20) {
                if (player.getAttribute("treeSpawned", false)) {
                    return@on true
                }
                val spirit = TreeSpiritNPC(NPCs.TREE_SPIRIT_655, Location(2862, 9734, 0))
                spirit.target = player
                spirit.init()
                spirit.attack(player)
                setAttribute(player, "treeSpawned", true)
                spirit.sendChat("You must defeat me before touching the tree!")
                return@on true
            }

            player.pulseManager.run(WoodcuttingSkillPulse(player, node as Scenery))
            return@on true
        }

    }

}
