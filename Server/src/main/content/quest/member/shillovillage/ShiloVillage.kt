package content.quest.member.shillovillage

import config.Items
import config.Vars
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Shilo Village quest.
 */
@Initializable
class ShiloVillage : Quest("Shilo Village", 114, 113, 2, Vars.VARP_QUEST_SHILO_VILLAGE_116, 0, 1, 15) {
    companion object {
        const val ShiloVillage = "Shilo Village"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.BOMBER_CAP_9945, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "3,875 Crafting XP", ln++)
        rewardXP(player, Skills.CRAFTING, 3875.0)
    }
    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 quest points
//3,875 Crafting experience
//Access to Shilo Village
//Access to Precious gem rocks in Shilo Village
//Access to the Shilo Village cart system, which travels between Brimhaven and Shilo Village for a fare of 10 coins
//After completing the quest, you can sell every item you obtained during the quest to Yanni Salika in Shilo Village for 2,000 coins — 100 coins each for the stone-plaque, tattered scroll, crumpled scroll, Bervirius notes and the bone key, 500 coins for the locating crystal, and 1,000 coins for the beads of the dead.
//2 Treasure Hunter keys (Ironman accounts will not receive these)