package content.global.handlers.item.withnpc

import config.Items
import config.NPCs
import content.quest.member.digsite.dialogue.AEDigsiteUsedOnDialogue
import core.api.openDialogue
import core.game.interaction.IntType
import core.game.interaction.InteractionListener

/**
 * Listener for using various items on Archeological Expert.
 */
open class ArchaeologicalExpertListener() : InteractionListener {
    val staff = Items.ANCIENT_STAFF_4675
    val unidentifiedLiquid = Items.UNIDENTIFIED_LIQUID_702
    val nitroglycerin = Items.NITROGLYCERIN_703
    val ammoniumNitrate = Items.AMMONIUM_NITRATE_701
    val nuggets = Items.NUGGETS_680
    val needle = Items.NEEDLE_1733
    val rottenApple = Items.ROTTEN_APPLE_1984
    val brokenGlass = Items.BROKEN_GLASS_1469
    val brokenArrow = Items.BROKEN_ARROW_687
    val panningTray = Items.PANNING_TRAY_677
    val bones = Items.BONES_526
    val buttons = Items.BUTTONS_688
    val crackedSample = Items.CRACKED_SAMPLE_674
    val oldTooth = Items.OLD_TOOTH_695
    val rustySword = Items.RUSTY_SWORD_686
    val brokenStaff = Items.BROKEN_STAFF_689
    val brokenArmour = Items.BROKEN_ARMOUR_698
    val damagedArmour = Items.DAMAGED_ARMOUR_697
    val ceramicRemains = Items.CERAMIC_REMAINS_694
    val beltBuckle = Items.BELT_BUCKLE_684
    val animalSkull = Items.ANIMAL_SKULL_671
    val specialCup = Items.SPECIAL_CUP_672
    val teddy = Items.TEDDY_673
    val stoneTablet = Items.STONE_TABLET_699

    val items = intArrayOf(
        staff,
        unidentifiedLiquid,
        nitroglycerin,
        ammoniumNitrate,
        nuggets,
        needle,
        rottenApple,
        brokenGlass,
        brokenArrow,
        panningTray,
        bones,
        buttons,
        crackedSample,
        oldTooth,
        rustySword,
        brokenStaff,
        brokenArmour,
        damagedArmour,
        ceramicRemains,
        beltBuckle,
        animalSkull,
        specialCup,
        teddy,
        stoneTablet
    )

    val archy = NPCs.ARCHAEOLOGICAL_EXPERT_619

    val lol = arrayOf(Items)

    override fun defineListeners() {
        onUseWith(IntType.NPC, items, archy) { player, used, with ->
            openDialogue(player, AEDigsiteUsedOnDialogue(used.id))
            return@onUseWith false
        }
    }
}