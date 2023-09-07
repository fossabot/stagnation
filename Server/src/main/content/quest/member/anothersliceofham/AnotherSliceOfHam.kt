package content.quest.member.anothersliceofham

import config.Items
import config.Vars
import core.api.*
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.skill.Skills
import core.plugin.Initializable

/**
 * Another Slice of H.A.M quest.
 */
@Initializable
class AnotherSliceOfHam : Quest("Another Slice of H.A.M.", 138, 137, 1,Vars.VARBIT_QUEST_ANOTHER_SLICE_OF_HAM_3550,0,1,11) {
    companion object {
        const val AnotherSliceOfHam = "Another Slice of H.A.M."
    }

    override fun drawJournal(player: Player?, stage: Int) {
        super.drawJournal(player, stage)
        var line = 11
        player ?: return
        if (stage == 0) {
            line++
            line(player, "I can start this quest by speaking to !!Ambassador Alvijar??", line++, false)
            line(player, "who is in !!Ur-tag's?? house in !!Dorgesh-Kaan.??", line++, false)
            line++
            line(player, "I must completed:", line++, false)
            line(player, "!!Death to the Dorgeshuun.??", line++, isQuestComplete(player,"Death to the Dorgeshuun"))
            line(player, "!!The Digsite.??", line++, isQuestComplete(player, "Dig Site"))
            line(player, "!!The Giant Dwarf.??", line++, isQuestComplete(player,"The Giant Dwarf"))
            line++
            line(player, "and to complete this quest I must have:", line++, false)
            line(player, if (hasLevelStat(player, Skills.ATTACK, 15)) "---Level 15 Attack/--" else "!!30 defence??", line++)
            line(player, if (hasLevelStat(player, Skills.PRAYER, 25)) "---Level 25 Prayer/--" else "!!Level 40 mining (higher mining being an advantage)??", line++)
            line(player, "I must be able to defeat a !!level 60 foe?? and defeat !!two", line++, false)
            line(player, "!!level 30 foes using only Ranged or Magic.??", line++, false)
            line++
        }
        if (stage >= 1) {
            line(player, "I talked to Ambassador Alvijar and agreed to help with", line++, stage >= 2)
            line(player, "the archaeological dig.", line++, stage >= 2)
            line(player, "I went to the train tunnel in south-west Dorgesh-Kaan and", line++, stage >= 2)
            line(player, "talked to Tegdak. I helped extract the remaining artefacts.", line++, stage >= 2)
            line(player, "The only intact artefact was an ancient goblin mace.", line++, stage >= 2)
            line(player, "Zanik and I showed this to the scribe.", line++, stage >= 2)
            line(player, "The scribe suggested that Zanik and", line++, stage >= 2)
            line(player, "I ask the goblin generals about the mace.", line++, stage >= 2)
            line(player, "I talked to Oldak and he teleported us to the Goblin Village.", line++, stage >= 2)
            line(player, "Zanik and I talked to the goblin generals.", line++, stage >= 2)
            line(player, "They told us that the mace was used by ancient goblin warriors", line++, stage >= 2)
            line(player, "to take the favour of the gods away from their enemies.", line++, stage >= 2)
            line(player, "Zanik addressed the Goblin Village,", line++, stage >= 2)
            line(player, "but the Village came under attack by H.A.M. snipers.", line++, stage >= 2)
            line(player, "I killed the H.A.M. snipers in the tower,", line++, stage >= 2)
            line(player, "but I saw Zanik being kidnapped by Sigmund", line++, stage >= 2)
            line(player, "The goblin generals said they will send two", line++, stage >= 2)
            line(player, "goblin sergeants to help me rescue Zanik.", line++, stage >= 2)
            line(player, "They gave me the ancient goblin mace to help with this.", line++, stage >= 2)
            line(player, "I went to Lumbridge Swamp and talked to the goblin sergeants.", line++, stage >= 2)
            line(player, "I snuck through the H.A.M. tunnel and defeated Sigmund.", line++, stage >= 2)
            line++
        }

        if (stage == 100) {
            line(player, "I rescued Zanik.", line++, true)
            line++
            line(player, "<col=FF0000>QUEST COMPLETE!", line, false)
        }
    }

    override fun finish(player: Player?) {
        super.finish(player)
        player ?: return
        var ln = 10

        player.packetDispatch.sendItemZoomOnInterface(Items.ANCIENT_MACE_11061, 230, 277, 5)
        drawReward(player, "1 Quest Point", ln++)
        drawReward(player, "3,000 Mining and Prayer XP", ln++)
        drawReward(player, "Ancient goblin mace", ln++)
        drawReward(player, "Access to Dorgesh-Kaan -", ln++)
        drawReward(player, "Keldagrim train route", ln++)
        drawReward(player, "Ability to buy Goblin Village", ln++)
        drawReward(player, "teleport spheres from Oldak", ln++)
        rewardXP(player, Skills.MINING, 3000.0)
        rewardXP(player, Skills.PRAYER, 3000.0)
        addItemOrDrop(player, Items.ANCIENT_MACE_11061)
        setVarbit(player, Vars.VARBIT_QUEST_ANOTHER_SLICE_OF_HAM_3550, 11, true)
    }

    override fun newInstance(`object`: Any?): Quest {
        return this
    }

}