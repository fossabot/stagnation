package content.quest.member.greatbrainrobbery

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
 * The Great Brain Robbery quest.
 */
@Initializable
class TheGreatBrainRobbery : Quest("The Great Brain Robbery", 135, 134, 2,Vars.VARBIT_QUEST_THE_GREAT_BRAIN_ROBBERY_980,0,1,130) {
    companion object {
        const val TheGreatBrainRobbery = "The Great Brain Robbery"
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
        player.packetDispatch.sendItemZoomOnInterface(Items.BARRELCHEST_ANCHOR_10887, 230, 277, 5)
        drawReward(player, "2 Quest Points", ln++)
        drawReward(player, "6,000 Prayer XP", ln++)
        drawReward(player, "3,000 Crafting XP", ln++)
        drawReward(player, "2,000 Construction XP", ln++)
        rewardXP(player, Skills.PRAYER, 6000.0)
        rewardXP(player, Skills.CRAFTING, 3000.0)
        rewardXP(player, Skills.CONSTRUCTION, 2000.0)
        addItemOrDrop(player, Items.BLESSED_LAMP_10889)
        setVarbit(player, Vars.VARBIT_QUEST_THE_GREAT_BRAIN_ROBBERY_980, 130, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}
//2 Quest points Quest points
//Prayer 6,000 Prayer experience
//Crafting 3,000 Crafting experience
//Construction 2,000 Construction experience
//Blessed lamp (Skills 5,000 experience in a chosen skill above level 30 and can be placed in your bank)
//Barrelchest anchor (To repair your barrelchest anchor, return to Mos Le'Harmless and make sure you have a book o' piracy in your inventory; then, go to the docks south of the bank and talk to "Smith." He can fix your anchor for 230,000 coins.)
//Prayer book
//Ability to cast Harmony Island Teleport with at least 60% Arceuus favour