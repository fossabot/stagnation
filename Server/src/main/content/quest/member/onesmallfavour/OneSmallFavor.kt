package content.quest.member.onesmallfavour

import config.Items
import config.Vars
import core.api.addItemOrDrop
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * One Small Favor quest.
 */
@Initializable
class OneSmallFavor : Quest("One Small Favor", 97, 96, 2, Vars.VARP_QUEST_ONE_SMALL_FAVOR_416, 0, 1, 276) {
    companion object {
        const val OneSmallFavour = "One Small Favor"
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return

        if (stage == 0) {
            line++
            line(player, "", line, false)
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

        player.packetDispatch.sendItemZoomOnInterface(Items.STEEL_KEY_8866, 230, 277, 5)

        drawReward(player, "2 Quest Points", ln++)
        addItemOrDrop(player, Items.STEEL_KEY_8866, 1)
        addItemOrDrop(player, Items.ANTIQUE_LAMP_11187, 2)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}
//2 quest points
//2 antique lamps giving 10,000 experience to any skill of your choice. Skill must be at least level 30.
//Steel key ring (automatically added to the tool belt)
//Ability to make Guthix rest potions
//Access to the gnome glider route to the Feldip Hills
//Adamantite stone spirit, an uncut ruby and an uncut diamond dropped by Slagilith
//1,000 Smithing experience from repairing the weather vane components
//2 Treasure Hunter keys (Ironman accounts will not receive these)
//Music unlocked
//The following can be unlocked in the lands you venture into if you have not visited them beforehand, but there is no unique music for this quest:
//
//In the Manor
//Grumpy
//Chompy Hunt
//Soundscape