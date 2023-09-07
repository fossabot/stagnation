package content.quest.member.undergroundpass

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Underground Pass quest.
 */
@Initializable
class UndergroundPass : Quest("Underground Pass", 129, 128, 5, Vars.VARP_QUEST_UDERGROUND_PASS_161, 0, 1, 10) {
    companion object {
        const val UndergroundPass = "Underground Pass"
    }
    //(isBitFlagged(VARP[162], 11)
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
        player.packetDispatch.sendItemZoomOnInterface(Items.IBANS_STAFF_1409, 230, 277, 5)
        drawReward(player, "5 Quest Points", ln++)
        drawReward(player, "3,000 Agility XP", ln++)
        drawReward(player, "3,000 Attack XP", ln++)
        rewardXP(player, Skills.AGILITY, 3000.0)
        rewardXP(player, Skills.ATTACK, 3000.0)
        addItemOrDrop(player, Items.IBANS_STAFF_1409)
        addItemOrDrop(player, Items.DEATH_RUNE_560, 15)
        addItemOrDrop(player, Items.FIRE_RUNE_554, 30)
        addItemOrDrop(player, Items.KLANKS_GAUNTLETS_1495, 1)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}

//5 quest points
//3,000 Agility experience
//3,000 Attack experience
//Iban's staff
//15 Death runes
//30 Fire runes
//Klank's gauntlets
//2 Treasure Hunter keys (Ironman accounts will not receive these)
