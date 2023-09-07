package content.quest.member.theyeofglourphie

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * The Eyes of Glouphrie quest.
 */
@Initializable
class TheEyesOfGlouphrie : Quest("The Eyes of Glouphrie", 56, 55, 2, Vars.VARBIT_QUEST_THE_EYE_OF_GLOUPHRIE_2497, 0, 1, 60) {
    companion object {
        const val TheEyesOfGlouphrie = "The Eyes of Glouphrie"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 100) {
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10
        player.packetDispatch.sendItemZoomOnInterface(Items.FERRET_10092, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "12,000 Magic XP", ln++)
        drawReward(player, "2,500 Woodcutting XP", ln++)
        drawReward(player, "6,000 Runecrafting XP", ln++)
        drawReward(player, "250 Construction XP", ln++)
        rewardXP(player, Skills.MAGIC, 12000.0)
        rewardXP(player, Skills.WOODCUTTING, 2500.0)
        rewardXP(player, Skills.RUNECRAFTING, 6000.0)
        rewardXP(player, Skills.CONSTRUCTION, 250.0)
        setVarbit(player, Vars.VARBIT_QUEST_THE_EYE_OF_GLOUPHRIE_2497, 60, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 Quest points Quest points
//Magic 12,000 Magic experience
//Woodcutting 2,500 Woodcutting experience
//Runecraft 6,000 Runecraft experience
//Construction 250 Construction experience