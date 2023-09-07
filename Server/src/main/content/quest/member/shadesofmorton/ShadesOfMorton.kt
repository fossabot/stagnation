package content.quest.member.shadesofmorton

import config.Items
import config.Vars
import core.api.rewardXP
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Shades of Mort'ton quest.
 */
@Initializable
class ShadesOfMorton : Quest("Shades of Mort'ton", 111, 110, 3, Vars.VARP_QUEST_SHADES_OF_MORTON_339, 0, 1, 85) {
    companion object {
        const val ShadesOfMorton = "Shades of Mort'ton"
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

        player.packetDispatch.sendItemZoomOnInterface(Items.DRAGON_SCIMITAR_4587, 230, 277, 5)

        drawReward(player, "3 Quest Points", ln++)
        drawReward(player, "2,000 Herblore XP", ln++)
        drawReward(player, "2,000 Crafting XP", ln++)
        rewardXP(player, Skills.HERBLORE, 2000.0)
        rewardXP(player, Skills.CRAFTING, 2000.0)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//3 quest points
//2,000 Herblore experience
//2,000 Crafting experience
//A few hundred coins if the player uses Serum 208 on Razmire and Ulsquire
//Ability to create Serum 207 and Serum 208
//Ability to play the Shades of Mort'ton minigame, burn shades, and claim the rewards from the shade keys.
//After the quest, players can take the Diary of Herbi Flax to the Apothecary in Varrock who will exchange the book for 335 Herblore experience. This can only be done once.
//2 Treasure Hunter keys (Ironman accounts will not receive these)