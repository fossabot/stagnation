package content.quest.member.thepathofglourphie

import config.Items
import config.Vars
import core.api.rewardXP
import core.api.setVarbit
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * The Path of Glouphrie quest.
 */
@Initializable
class ThePathofGlouphrie : Quest("The Path of Glouphrie", 142, 141, 1, Vars.VARBIT_QUEST_THE_PATH_OF_GLOUPHRIE_3954, 0, 1, 200) {
    companion object { const val ThePathofGlouphrie = "The Path of Glouphrie" }

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

        player.packetDispatch.sendItemZoomOnInterface(Items.ACHEY_TREE_LOGS_2862, 230, 277, 5)

        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "30,000 Strength XP", ln++)
        drawReward(player, "20,000 Slayer XP", ln++)
        drawReward(player, "5,000 Thieving XP", ln++)
        drawReward(player, "5,000 Magic XP", ln++)
        rewardXP(player, Skills.STRENGTH, 30000.0)
        rewardXP(player, Skills.SLAYER, 20000.0)
        rewardXP(player, Skills.THIEVING, 5000.0)
        rewardXP(player, Skills.MAGIC, 5000.0)
        setVarbit(player, Vars.VARBIT_QUEST_THE_PATH_OF_GLOUPHRIE_3954, 200, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//1 quest point
//30,000 Strength experience
//20,000 Slayer experience
//5,000 Thieving experience
//5,000 Magic experience
//Access to Poison Waste Slayer Dungeon
//Access to a new spirit tree
//Ability to use the spirit trees for direct teleportation, skipping the route of travelling back to Tree Gnome Village
//Ability to add the crystal chime to the tool belt.
//2 Treasure Hunter keys (Ironman accounts will not receive these)