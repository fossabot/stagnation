package content.global.activity.dailytasks.impl

import config.NPCs
import content.global.activity.dailytasks.Job
import content.global.activity.dailytasks.JobType

/**
 * An enum of the possible combat jobs that can be assigned to a player.
 *
 * Note: Due to how player save files keep track of the player's current job, it is essential that
 * new entries are only appended to the end of this enum, and the ordering of existing entries is not changed.
 */
enum class CombatJobs(val monsterIds: List<Int>, override val employer: Employers) : Job {
    CHICKENS(Monsters.CHICKENS, Employers.RANGED_TUTOR),
    COWS(Monsters.COWS, Employers.MAGIC_TUTOR),
    GIANT_SPIDERS(Monsters.GIANT_SPIDERS, Employers.MAGIC_TUTOR),
    SCORPIONS(Monsters.SCORPIONS, Employers.MELEE_TUTOR),
    GOBLINS(Monsters.GOBLINS, Employers.MELEE_TUTOR);

    override val type = JobType.COMBAT
    override val lower = 22
    override val upper = 28

    object Monsters {

        val GIANT_SPIDERS = listOf(
            NPCs.GIANT_SPIDER_59, NPCs.GIANT_SPIDER_60
        )

        val SCORPIONS = listOf(
            NPCs.SCORPION_107, NPCs.SCORPION_1477, NPCs.SCORPION_4402, NPCs.SCORPION_4403
        )

        val GOBLINS = listOf(
            NPCs.GOBLIN_100, NPCs.GOBLIN_101, NPCs.GOBLIN_102, NPCs.GOBLIN_444, NPCs.GOBLIN_445,
            NPCs.GOBLIN_1769, NPCs.GOBLIN_1770, NPCs.GOBLIN_1771, NPCs.GOBLIN_1772, NPCs.GOBLIN_1773,
            NPCs.GOBLIN_1774, NPCs.GOBLIN_1775, NPCs.GOBLIN_1776, NPCs.GOBLIN_2274, NPCs.GOBLIN_2275,
            NPCs.GOBLIN_2276, NPCs.GOBLIN_2277, NPCs.GOBLIN_2278, NPCs.GOBLIN_2279, NPCs.GOBLIN_2280,
            NPCs.GOBLIN_2281, NPCs.GOBLIN_2678, NPCs.GOBLIN_2679, NPCs.GOBLIN_2680, NPCs.GOBLIN_2681,
            NPCs.GOBLIN_3264, NPCs.GOBLIN_3265, NPCs.GOBLIN_3266, NPCs.GOBLIN_3267, NPCs.GOBLIN_3726,
            NPCs.GOBLIN_4261, NPCs.GOBLIN_4262, NPCs.GOBLIN_4263, NPCs.GOBLIN_4264, NPCs.GOBLIN_4265,
            NPCs.GOBLIN_4266, NPCs.GOBLIN_4267, NPCs.GOBLIN_4268, NPCs.GOBLIN_4269, NPCs.GOBLIN_4270,
            NPCs.GOBLIN_4271, NPCs.GOBLIN_4272, NPCs.GOBLIN_4273, NPCs.GOBLIN_4274, NPCs.GOBLIN_4275,
            NPCs.GOBLIN_4276, NPCs.GOBLIN_4407, NPCs.GOBLIN_4408, NPCs.GOBLIN_4409, NPCs.GOBLIN_4410,
            NPCs.GOBLIN_4411, NPCs.GOBLIN_4412, NPCs.GOBLIN_4479, NPCs.GOBLIN_4480, NPCs.GOBLIN_4481,
            NPCs.GOBLIN_4482, NPCs.GOBLIN_4483, NPCs.GOBLIN_4484, NPCs.GOBLIN_4485, NPCs.GOBLIN_4486,
            NPCs.GOBLIN_4487, NPCs.GOBLIN_4488, NPCs.GOBLIN_4489, NPCs.GOBLIN_4490, NPCs.GOBLIN_4491,
            NPCs.GOBLIN_4492, NPCs.GOBLIN_4499, NPCs.GOBLIN_4633, NPCs.GOBLIN_4634, NPCs.GOBLIN_4635,
            NPCs.GOBLIN_4636, NPCs.GOBLIN_4637, NPCs.GOBLIN_5855, NPCs.GOBLIN_5856, NPCs.GOBLIN_6125,
            NPCs.GOBLIN_6126, NPCs.GOBLIN_6132, NPCs.GOBLIN_6133, NPCs.GOBLIN_6279, NPCs.GOBLIN_6280,
            NPCs.GOBLIN_6281, NPCs.GOBLIN_6282, NPCs.GOBLIN_6283, NPCs.GOBLIN_6284, NPCs.GOBLIN_6402,
            NPCs.GOBLIN_6403, NPCs.GOBLIN_6404, NPCs.GOBLIN_6405, NPCs.GOBLIN_6406, NPCs.GOBLIN_6407,
            NPCs.GOBLIN_6408, NPCs.GOBLIN_6409, NPCs.GOBLIN_6410, NPCs.GOBLIN_6411, NPCs.GOBLIN_6412,
            NPCs.GOBLIN_6413, NPCs.GOBLIN_6414, NPCs.GOBLIN_6415, NPCs.GOBLIN_6416, NPCs.GOBLIN_6417,
            NPCs.GOBLIN_6418, NPCs.GOBLIN_6419, NPCs.GOBLIN_6420, NPCs.GOBLIN_6421, NPCs.GOBLIN_6422,
            NPCs.GOBLIN_6423, NPCs.GOBLIN_6424, NPCs.GOBLIN_6425, NPCs.GOBLIN_6426, NPCs.GOBLIN_6427,
            NPCs.GOBLIN_6428, NPCs.GOBLIN_6429, NPCs.GOBLIN_6430, NPCs.GOBLIN_6431, NPCs.GOBLIN_6432,
            NPCs.GOBLIN_6433, NPCs.GOBLIN_6434, NPCs.GOBLIN_6435, NPCs.GOBLIN_6436, NPCs.GOBLIN_6437,
            NPCs.GOBLIN_6438, NPCs.GOBLIN_6439, NPCs.GOBLIN_6440, NPCs.GOBLIN_6441, NPCs.GOBLIN_6442,
            NPCs.GOBLIN_6443, NPCs.GOBLIN_6444, NPCs.GOBLIN_6445, NPCs.GOBLIN_6446, NPCs.GOBLIN_6447,
            NPCs.GOBLIN_6448, NPCs.GOBLIN_6449, NPCs.GOBLIN_6450, NPCs.GOBLIN_6451, NPCs.GOBLIN_6452,
            NPCs.GOBLIN_6453, NPCs.GOBLIN_6454, NPCs.GOBLIN_6455, NPCs.GOBLIN_6456, NPCs.GOBLIN_6457,
            NPCs.GOBLIN_6458, NPCs.GOBLIN_6459, NPCs.GOBLIN_6460, NPCs.GOBLIN_6461, NPCs.GOBLIN_6462,
            NPCs.GOBLIN_6463, NPCs.GOBLIN_6464, NPCs.GOBLIN_6465, NPCs.GOBLIN_6466, NPCs.GOBLIN_6467,
            NPCs.GOBLIN_6490, NPCs.GOBLIN_6491, NPCs.GOBLIN_6492, NPCs.GOBLIN_6493, NPCs.GOBLIN_6494,
            NPCs.GOBLIN_6495, NPCs.GOBLIN_7964, NPCs.GOBLIN_7965
        )

        val COWS = listOf(
            NPCs.COW_81, NPCs.COW_397, NPCs.COW_955, NPCs.COW_1767, NPCs.COW_3309,
            NPCs.COW_CALF_1766, NPCs.COW_CALF_1768, NPCs.COW_CALF_2310
        )

        val CHICKENS = listOf(
            NPCs.CHICKEN_1017, NPCs.CHICKEN_1401, NPCs.CHICKEN_1402, NPCs.CHICKEN_2313,
            NPCs.CHICKEN_2314, NPCs.CHICKEN_2315, NPCs.CHICKEN_288, NPCs.CHICKEN_41, NPCs.CHICKEN_951
        )
    }
}