package content.global.diary.area.varrock

import config.*
import core.api.*
import core.game.event.*
import content.global.handlers.iface.FairyRing
import content.global.skill.free.magic.TeleportMethod
import content.global.skill.free.prayer.Bones
import content.global.travel.canoe.Canoe
import content.quest.member.familycrest.dialogue.DimintheisFamilyCrestDialogue
import content.region.misthalin.varrock.dialogue.CuratorHaigHalenDialogue
import content.region.misthalin.varrock.dialogue.ElsieDialogue
import core.game.diary.AreaDiaryTask
import core.game.diary.DiaryEventHookBase
import core.game.diary.DiaryLevel
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.SpellBookManager
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.entity.skill.Skills
import core.game.world.map.zone.ZoneBorders

/**
 * Represents the Varrock achievement diaries.
 */
class VarrockAchievementDiary : DiaryEventHookBase(DiaryType.VARROCK) {
    companion object {

        private const val ATTRIBUTE_CRAFT_AIR_BATTLESTAFF = "diary:varrock:craft-air-battlestaff"

        private val VARROCK_ROOF_AREA = ZoneBorders(3201, 3467, 3225, 3497, 3)
        private val SOS_LEVEL_2_AREA = ZoneBorders(2040, 5241, 2046, 5246)
        private val VARROCK_PALACE = ZoneBorders(3201, 3456, 3227, 3468)
        private val CHAMPIONS_GUILD = ZoneBorders(3188, 3361, 3194, 3362)
        private val OZIACH_SHOP = ZoneBorders(3066, 3514,3070, 3518)
        private val AIR_OBELISK = ZoneBorders(3087, 3568, 3089, 3570)
        private val FARMING_PATCH = ZoneBorders(3180,3356,3183,3359)
        private val EARTH_ALTAR = ZoneBorders(2654, 4828, 2656, 4830)

        private val STRAY_DOGS = arrayOf(
            NPCs.STRAY_DOG_4766, NPCs.STRAY_DOG_4767,
            NPCs.STRAY_DOG_5917, NPCs.STRAY_DOG_5918
        )

        object EasyTasks {
            const val THESSALIA_BROWSE_CLOTHES = 0
            const val AUBURY_TELEPORT_ESSENCE_MINE = 1
            const val MINE_IRON_SOUTHEAST = 2
            const val MAKE_PLANK_SAWMILL = 3
            const val VISIT_SOS_LEVEL2 = 4
            const val JUMP_OVER_FENCE_SOUTH = 5
            const val LUMBERYARD_CHOP_DYING_TREE = 6
            const val BUY_VARROCK_HERALD = 7
            const val GIVE_STRAY_DOG_A_BONE = 8
            const val BARBARIAN_VILLAGE_SPIN_A_BOWL = 9
            const val EDGEVILLE_ENTER_DUNGEON_SOUTH = 10
            const val MOVE_POH_TO_VARROCK = 11
            const val SPEAK_TO_HAIG_HALEN_50QP = 12
            const val ENTER_EARTH_ALTAR = 13
            const val ELSIE_TELL_A_STORY = 14
            const val PATERDOMUS_MINE_LIMESTONE = 15
            const val BARBARIAN_VILLAGE_CATCH_TROUT = 16
            const val SEWERS_CUT_COBWEB = 17
            const val FIND_HIGHEST_POINT = 18
        }

        object MediumTasks {
            const val APOTHECARY_MAKE_STRENGTH_POTION = 0
            const val CHAMPIONS_GUILD_VISIT = 1
            const val TAKE_DAGONHAI_CHAOS_PORTAL_SHORTCUT = 2
            const val RAT_POLE_FULL_RAT_COMPLEMENT = 3
            const val SEWER_GATHER_RED_SPIDERS_EGGS = 4
            const val USE_SPIRIT_TREE_NORTH = 5
            const val PERFORM_ALL_SOS_EMOTES = 6
            const val SELECT_KITTEN_COLOR = 7
            const val USE_GE_UNDER_WALL_SHORTCUT = 8
            const val ENTER_SOULBANE_RIFT = 9
            const val DIGSITE_PENDANT_TELEPORT = 10
            const val CRAFT_EARTH_TIARA = 11
            const val PALACE_PICKPOCKET_GUARD = 12
            const val CAST_VARROCK_TELEPORT_SPELL = 13
            const val VANNAKA_GET_SLAYER_TASK = 14
            const val SAWMILL_BUY_20_MAHOGANY_PLANKS = 15
            const val PICK_FROM_WHITE_TREE = 16
            const val HOTAIR_BALLOON_TRAVEL_SOMEWHERE = 17
            const val GERTRUDE_GET_CAT_TRAINING_MEDAL = 18
            const val DIAL_FAIRY_RING_WEST = 19
            const val OZIACH_BROWSE_STORE = 20
        }

        object HardTasks {
            const val PICK_POISON_IVY_FARMING_PATCH = 0
            const val USE_MOSS_GIANT_PIPE_SHORTCUT = 1
            const val FANCY_DRESS_SELLER_TRADE_FURS = 2
            const val SMITH_ADAMANT_MED_HELM_SOUTHEAST = 3
            const val SPEAK_TO_ORLANDO_SMITH_153_KUDOS = 4
            const val GIVE_WEAKLAX_A_PIE = 5
            const val CRAFT_AIR_BATTLESTAFF = 6
            const val GIVE_POH_TROPICAL_WOOD_OR_FANCY_STONE_FINISH = 7
            const val MAKE_VARROCK_TELEPORT_TABLET_OR_MAHOGANY_LECTERN = 8
            const val OBTAIN_NEW_SET_OF_FAMILY_CREST_GAUNTLETS = 9
            const val EDGEVILLE_MAKE_WAKA_CANOE = 10
            const val EDGEVILLE_TELEPORT_USING_ANCIENT_MAGICKS = 11
            const val BARBARIAN_VILLAGE_TELEPORT_USING_SKULL_SCEPTRE = 12
        }
    }

    override val areaTasks get() = arrayOf(
        AreaDiaryTask(
            VARROCK_ROOF_AREA,
            DiaryLevel.EASY,
            EasyTasks.FIND_HIGHEST_POINT
        ),

        AreaDiaryTask(
            SOS_LEVEL_2_AREA,
            DiaryLevel.EASY,
            EasyTasks.VISIT_SOS_LEVEL2
        ),

        AreaDiaryTask(
            CHAMPIONS_GUILD,
            DiaryLevel.MEDIUM,
            MediumTasks.CHAMPIONS_GUILD_VISIT
        ),
        AreaDiaryTask(
            EARTH_ALTAR,
            DiaryLevel.EASY,
            EasyTasks.ENTER_EARTH_ALTAR
        ) { player ->
            inInventory(player, Items.EARTH_TALISMAN_1440) || inEquipment(player, Items.EARTH_TIARA_5535)
        }
    )

    override fun onResourceProduced(player: Player, event: ResourceProducedEvent) {
        when (player.viewport.region.id) {
            12341 -> if (event.itemId == Items.RAW_TROUT_335) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.BARBARIAN_VILLAGE_CATCH_TROUT
                )
            }

            13108 -> if (event.itemId == Items.IRON_ORE_440) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.MINE_IRON_SOUTHEAST
                )
            }

            13110 -> {
                if (event.itemId == Items.LOGS_1511 && event.source.id == Scenery.DYING_TREE_24168) {
                    finishTask(
                        player,
                        DiaryLevel.EASY,
                        EasyTasks.LUMBERYARD_CHOP_DYING_TREE
                    )
                }
            }

            13366 -> if (event.itemId == Items.LIMESTONE_3211) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.PATERDOMUS_MINE_LIMESTONE
                )
            }
        }
    }

    override fun onTeleported(player: Player, event: TeleportEvent) {
        when (event.source) {
            is NPC -> if (event.method == TeleportMethod.NPC && event.source.id == NPCs.AUBURY_553) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.AUBURY_TELEPORT_ESSENCE_MINE
                )
            }
        }
    }

    override fun onInteracted(player: Player, event: InteractionEvent) {
        when (player.viewport.region.id) {
            12342 -> if (event.target.id == Scenery.TRAPDOOR_26934) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.EDGEVILLE_ENTER_DUNGEON_SOUTH
                )
            }
            12598 -> if (event.target.id == Scenery.UNDERWALL_TUNNEL_9312 && hasLevelStat(player, Skills.AGILITY, 21)) {
                finishTask(
                    player,
                    DiaryLevel.MEDIUM,
                    MediumTasks.USE_GE_UNDER_WALL_SHORTCUT
                )
            }
            12698 -> if (event.target.id == Scenery.PIPE_29370 && hasLevelStat(player, Skills.AGILITY, 51)) {
                finishTask(
                    player,
                    DiaryLevel.HARD,
                    HardTasks.USE_MOSS_GIANT_PIPE_SHORTCUT
                )
            }
        }

        if (event.option == "pickpocket" && (event.target.id == NPCs.GUARD_5920 && inBorders(player, VARROCK_PALACE)) && hasLevelStat(player, Skills.THIEVING, 40)) {
            finishTask(
                player,
                DiaryLevel.MEDIUM,
                MediumTasks.PALACE_PICKPOCKET_GUARD
            )
        }

        if (player.questRepository.isComplete("Dragon Slayer")) {
            if (event.target.id == NPCs.OZIACH_747 && event.option == "trade" && inBorders(player, OZIACH_SHOP)) {
                finishTask(
                    player,
                    DiaryLevel.MEDIUM,
                    MediumTasks.OZIACH_BROWSE_STORE
                )
            }
        }
    }

    override fun onButtonClicked(player: Player, event: ButtonClickEvent) {
        /* This gets fired even on the login screen, and we don't have a region there, so... */
        player.viewport.region?.let {
            when (it.id) {
                12342 -> {
                    if (event.iface == Components.CANOE_52 && Canoe.getCanoeFromChild(event.buttonId) == Canoe.WAKA) {
                        finishTask(
                            player,
                            DiaryLevel.HARD,
                            HardTasks.EDGEVILLE_MAKE_WAKA_CANOE
                        )
                    }
                }
            }
        }
    }

    override fun onDialogueOptionSelected(player: Player, event: DialogueOptionSelectionEvent) {
        when (event.dialogue) {
            is ElsieDialogue -> if (event.currentStage == 12) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.ELSIE_TELL_A_STORY
                )
            }
            is DimintheisFamilyCrestDialogue -> if (event.currentStage == 6000) {
                finishTask(
                    player,
                    DiaryLevel.HARD,
                    HardTasks.OBTAIN_NEW_SET_OF_FAMILY_CREST_GAUNTLETS
                )
            }
        }
    }

    override fun onUsedWith(player: Player, event: UseWithEvent) {
        when (event.used) {
            in Bones.array -> if (event.with in STRAY_DOGS) {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.GIVE_STRAY_DOG_A_BONE
                )
            }
        }
        when {
            inBorders(player, AIR_OBELISK) -> {
                if (event.used == Items.BATTLESTAFF_1391 && event.with == Items.AIR_ORB_573) {
                    whenTaskRequirementFulfilled(player, ATTRIBUTE_CRAFT_AIR_BATTLESTAFF) {
                        finishTask(
                            player,
                            DiaryLevel.HARD,
                            HardTasks.CRAFT_AIR_BATTLESTAFF
                        )
                    }
                }
            }
        }
    }

    override fun onInterfaceOpened(player: Player, event: InterfaceOpenEvent) {
        when (event.component.id) {
            Components.THESSALIA_CLOTHES_MALE_591,
            Components.THESSALIA_CLOTHES_FEMALE_594 -> {
                finishTask(
                    player,
                    DiaryLevel.EASY,
                    EasyTasks.THESSALIA_BROWSE_CLOTHES
                )
            }
        }
    }

    override fun onSpellCast(player: Player, event: SpellCastEvent) {
        if (event.spellBook == SpellBookManager.SpellBook.MODERN && event.spellId == 15 &&
            hasLevelStat(player, Skills.MAGIC, 25)) {
            finishTask(
                player,
                DiaryLevel.MEDIUM,
                MediumTasks.CAST_VARROCK_TELEPORT_SPELL
            )
        }
        when{
            inBorders(player, AIR_OBELISK) -> {
                if (event.spellBook == SpellBookManager.SpellBook.MODERN && event.spellId == 49) {
                    fulfillTaskRequirement(
                        player,
                        DiaryLevel.HARD,
                        HardTasks.CRAFT_AIR_BATTLESTAFF,
                        ATTRIBUTE_CRAFT_AIR_BATTLESTAFF
                    )
                }
            }
        }
    }

    override fun onFairyRingDialed(player: Player, event: FairyRingDialEvent) {
        if (event.fairyRing == FairyRing.DKR) {
            finishTask(
                player,
                DiaryLevel.MEDIUM,
                MediumTasks.DIAL_FAIRY_RING_WEST
            )
        }
    }
    override fun onPickedUp(player: Player, event: PickUpEvent) {
        when {
            inBorders(player, FARMING_PATCH) -> {
                if (event.itemId == Items.POISON_IVY_BERRIES_6018) {
                    finishTask(
                        player,
                        DiaryLevel.HARD,
                        HardTasks.PICK_POISON_IVY_FARMING_PATCH
                    )
                }
            }
        }
    }

    override fun onDialogueOpened(player: Player, event: DialogueOpenEvent) {
        when (event.dialogue) {
            is CuratorHaigHalenDialogue -> {
                if (getQuestPoints(player) == 50) {
                    finishTask(
                        player,
                        DiaryLevel.EASY,
                        EasyTasks.SPEAK_TO_HAIG_HALEN_50QP
                    )
                }
            }
        }
    }
}