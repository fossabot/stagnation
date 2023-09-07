package content.quest.member.trollstronghold

import config.Components
import config.Items
import config.Vars
import core.api.*
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Troll Stronghold quest.
 */
@Initializable
class TrollStronghold : Quest("Troll Stronghold", 128, 127, 1, Vars.VARP_QUEST_TROLL_TRONGHOLD_317, 0, 1, 50) {


    // Quest description:
    // The Imperial Guard raid was a failure, and Dunstan's son has been captured by the trolls!
    // Journey through Trollheim to the Troll Stronghold, and rescue him!
    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 12
        var stage = getStage(player)
        var started = getQuestStage(player!!, "Troll Stronghold") > 0

        val hasBoots = hasAnItem(player, Items.CLIMBING_BOOTS_3105).container != null

        if(!started){
            line(player, "I can start this quest by speaking to !!Denulth?? in his tent at", line++)
            line(player, "the !!Imperial Guard camp?? in !!Burthorpe?? after completing the", line++)
            line(player, "!!Death Plateau Quest??", line++, isQuestComplete(player, "Death Plateau"))
            line++
            line(player, "To complete this quest I need:", line++)
            line(player, "Level 15 Agility.", line++, hasLevelStat(player, Skills.AGILITY, 15))
            line(player, "I also need to be able to defeat a !!level 113 Troll??.", line++,player.properties.combatLevel >= 100)
            line(player, "Level 30 Thieving might be useful.", line++, hasLevelStat(player, Skills.THIEVING, 30))
            if (isQuestComplete(player, "Death Plateau") && hasLevelStat(player, Skills.AGILITY, 15) && hasLevelStat(player, Skills.THIEVING, 30)) {
                line(player, "I have all the requirements to start this quest.", line++)
            }
        } else {
            line(player, "I promised !!Denulth?? that I would rescue !!Godric?? from the !!Troll??", line++, stage == 100)
            line(player, "!!Stronghold??", line++, stage == 100)
            line++
            if (stage >= 5 || hasBoots) {
                line(player, "I got some !!climbing boots?? from !!Tenzing??", line++, true)
            } else {
                line(player, "I have to get some !!climbing boots?? from !!Tenzing??", line++)
            }
            line++
            if (stage >= 5) {
                line(player, "I have defeated the !!Troll Champion??", line++, true)
            } else if (stage >= 3) {
                line(player, "I have to defeat the !!Troll Champion??", line++)
            }
            if (stage in 5..7) {
                line++
                line(player, "I have to find a way to get into the !!Troll Stronghold??", line++)
            }
            line++
            if (stage >= 8) {
                line(player, "I found my way into the !!Prison??", line++, true)
            } else if (stage >= 5) {
                line(player, "I have to find a way to get into the !!prison??", line++)
            }
            line++
            if (stage >= 11) {
                line(player, "I've rescued !!Godric?? and !!Mad Eadgar??", line++, true)
            } else if (stage >= 8) {
                line(player, "I have to rescue !!Godric?? and !!Mad Eadgar??", line++)
            }
            if (stage >= 100) {
                line++
                line(player, "I talked to Dunstan and he rewarded me.", line++, true)
            } else if (stage >= 11) {
                line(player, "I should return and tell !!Dunstan?? his son is safe.", line++)
            }
            if (stage >= 100) {
                line++
                line(player,"<col=FF0000>QUEST COMPLETE!</col>", line)
            }
        }
    }

    override fun finish(player: Player) {
        var ln = 10
        super.finish(player)
        sendItemZoomOnInterface(player, Components.QUEST_COMPLETE_SCROLL_277,5,Items.MYSTERIOUS_LAMP_13227, 240)

        drawReward(player,"1 Quest Point", ln++)
        drawReward(player,"2 Reward lamps giving 10,000", ln++)
        drawReward(player,"XP each", ln)

        addItemOrDrop(player, Items.MYSTERIOUS_LAMP_13227, 2)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }
}

//1 quest point
//Two mysterious lamps that give 10,000 experience in any skill level 30 or above.
//Access to Trollheim and the Troll Stronghold
//Access to God Wars Dungeon with the requirement of 60 Agility or 60 Strength.