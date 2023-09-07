package content.quest.member.royaltrouble

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Royal Trouble quest.
 */
@Initializable
class RoyalTrouble : Quest("Royal Trouble", 106, 105, 1,Vars.VARBIT_QUEST_ROYAL_TROUBLE_2140,0,1,21) {
    companion object {
        const val RoyalTrouble = "Royal Trouble"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.BLUE_PARTYHAT_1042, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "20,000 Coins", ln++)
        drawReward(player, "5000 Agility XP", ln++)
        drawReward(player, "5000 Slayer XP", ln++)
        drawReward(player, "5000 Constitution XP", ln++)
        rewardXP(player, Skills.AGILITY, 5000.0)
        rewardXP(player, Skills.SLAYER, 5000.0)
        rewardXP(player, Skills.CONSTRUCTION, 5000.0)
        addItemOrDrop(player, Items.COINS_995, 20000)
        setVarbit(player, Vars.VARBIT_QUEST_ROYAL_TROUBLE_2140, 21, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//1 quest point
//20,000 coins
//5,000 Agility experience lamp
//5,000 Slayer experience lamp
//5,000 Constitution experience lamp
//Increased resources from Managing Miscellania
//Access to the dungeon connecting Miscellania and Etceteria
//2 Treasure Hunter keys (Ironman accounts will not receive these)