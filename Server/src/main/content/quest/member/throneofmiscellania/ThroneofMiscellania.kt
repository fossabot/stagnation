package content.quest.member.throneofmiscellania

import config.Items
import config.Vars
import core.api.hasLevelStat
import core.api.isQuestComplete
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Throne of Miscellania quest.
 */
@Initializable
class ThroneofMiscellania : Quest("Throne of Miscellania", 122, 121, 1, Vars.VARP_QUEST_THRONE_OF_MISCELLANIA_359, 0, 1, 100) {
   //https://www.youtube.com/watch?v=gNa8qCmeb-Q&ab_channel=GoingCrazy201

    companion object {
        const val ThroneofMiscellania = "Throne of Miscellania"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return
        if (stage >= 0) {
            line(player, "I can start this quest by talking to !!King Vargas?? in", line++, stage >= 1)
            line(player, "!!Miscellania??. To start this quest I need to have completed:", line++, stage >= 1)
            line(player, "!!Heroes Quest??", line++, isQuestComplete(player, "Heroes' Quest"))
            line(player, "!!The Fremennik Trials??", line++, isQuestComplete(player, "The Fremennik Trials"))
            line++
            line(player, "It would be an adventage to have at least one of the", line++, stage >= 1)
            line(player, "following skills:", line++, stage >= 1)
            line(player, if (hasLevelStat(player, Skills.HERBLORE, 35)) "---Level 35 Herblore/--" else "!!Level 35 Herblore??", line++)
            line(player, if (hasLevelStat(player, Skills.WOODCUTTING, 45)) "---Level 45 Woodcutting/--" else "!!Level 45 Woodcutting??", line++)
            line++
        }

        if (stage == 100) {
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.GOBLIN_MAIL_288, 230, 277, 5)
        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "Your subjects will collect", ln++)
        drawReward(player, "resources for you", ln++)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//1 quest point
//Management of Miscellania
//Ability to teleport to Miscellania with a Ring of Wealth, Ring of Fortune, or Luck of the Dwarves
//Ability to purchase Miscellania Portrait from Sir Renitee
//10,000 coins (accessible from Managing Miscellania)
//2 Treasure Hunter keys (Ironman accounts will not receive these)