package content.global.handlers.item.withitem

import config.Items
import core.api.addItemOrDrop
import core.api.removeItem
import core.api.sendMessage
import core.api.toIntArray
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.item.Item
import kotlin.math.min

/**
 * Listener for Weapon poison, used with items.
 */
class PoisonedWeaponListeners : InteractionListener {
    override fun defineListeners() {
        /**
         * Weapon poison potions
         */
        val poisons = intArrayOf(
            Items.WEAPON_POISON_187,
            Items.WEAPON_POISON_PLUS_5937,
            Items.WEAPON_POISON_PLUS_PLUS_5940
        )

        val poisonableItems = PoisonSets.itemMap.keys.toIntArray()
        val poisonedItems = PoisonSets.itemMap.values.toIntArray()

        onUseWith(IntType.ITEM, poisons, *poisonableItems) { player, used, with ->
            val index = poisons.indexOf(used.id)
            val product = PoisonSets.itemMap[with.id]!![index]
            val amt = min(5, with.asItem().amount)

            if (removeItem(player, Item(with.id, amt))) {
                removeItem(player, used.id)
                addItemOrDrop(player, product, amt)
                addItemOrDrop(player, Items.VIAL_229)
                sendMessage(player, "You poison the ${with.name.toLowerCase()}.")
            }
            return@onUseWith true
        }

        onUseWith(IntType.ITEM, Items.CLEANING_CLOTH_3188, *poisonedItems) { player, _, with ->
            val base = PoisonSets.getBase(with.id) ?: return@onUseWith false
            val amt = min(5, with.asItem().amount)
            removeItem(player, Item(with.id, amt))
            addItemOrDrop(player, base, amt)
            return@onUseWith true
        }
    }

    internal enum class PoisonSets(val base: Int, val p: Int, val pp: Int, val ppp: Int) {
        /**
         * Arrows
         */
        BRONZE_ARROW(
            Items.BRONZE_ARROW_882,
            Items.BRONZE_ARROWP_883,
            Items.BRONZE_ARROWP_PLUS_5616,
            Items.BRONZE_ARROWP_PLUS_PLUS_5622
        ),
        IRON_ARROW(
            Items.IRON_ARROW_884,
            Items.IRON_ARROWP_885,
            Items.IRON_ARROWP_PLUS_5617,
            Items.IRON_ARROWP_PLUS_PLUS_5623
        ),
        STEEL_ARROW(
            Items.STEEL_ARROW_886,
            Items.STEEL_ARROWP_887,
            Items.STEEL_ARROWP_PLUS_5618,
            Items.STEEL_ARROWP_PLUS_PLUS_5624
        ),
        MITHRIL_ARROW(
            Items.MITHRIL_ARROW_888,
            Items.MITHRIL_ARROWP_889,
            Items.MITHRIL_ARROWP_PLUS_5619,
            Items.MITHRIL_ARROWP_PLUS_PLUS_5625
        ),
        ADAMANT_ARROW(
            Items.ADAMANT_ARROW_890,
            Items.ADAMANT_ARROWP_891,
            Items.ADAMANT_ARROWP_PLUS_5620,
            Items.ADAMANT_ARROWP_PLUS_PLUS_5626
        ),
        RUNE_ARROW(
            Items.RUNE_ARROW_892,
            Items.RUNE_ARROWP_893,
            Items.RUNE_ARROWP_PLUS_5621,
            Items.RUNE_ARROWP_PLUS_PLUS_5627
        ),

        /**
         * Knifes
         */
        BRONZE_KNIFE(
            Items.BRONZE_KNIFE_864,
            Items.BRONZE_KNIFEP_870,
            Items.BRONZE_KNIFEP_PLUS_5654,
            Items.BRONZE_KNIFEP_PLUS_PLUS_5661
        ),
        IRON_KNIFE(
            Items.IRON_KNIFE_863,
            Items.IRON_KNIFEP_871,
            Items.IRON_KNIFEP_PLUS_5655,
            Items.IRON_KNIFEP_PLUS_PLUS_5662
        ),
        STEEL_KNIFE(
            Items.STEEL_KNIFE_865,
            Items.STEEL_KNIFEP_872,
            Items.STEEL_KNIFEP_PLUS_5656,
            Items.STEEL_KNIFEP_PLUS_PLUS_5663
        ),
        BLACK_KNIFE(
            Items.BLACK_KNIFE_869,
            Items.BLACK_KNIFEP_874,
            Items.BLACK_KNIFEP_PLUS_5658,
            Items.BLACK_KNIFEP_PLUS_PLUS_5665
        ),
        MITHRIL_KNIFE(
            Items.MITHRIL_KNIFE_866,
            Items.MITHRIL_KNIFEP_873,
            Items.MITHRIL_KNIFEP_PLUS_5657,
            Items.MITHRIL_KNIFEP_PLUS_PLUS_5664
        ),
        ADAMANT_KNIFE(
            Items.ADAMANT_KNIFE_867,
            Items.ADAMANT_KNIFEP_875,
            Items.ADAMANT_KNIFEP_PLUS_5659,
            Items.ADAMANT_KNIFEP_PLUS_PLUS_5666
        ),
        RUNE_KNIFE(
            Items.RUNE_KNIFE_868,
            Items.RUNE_KNIFEP_876,
            Items.RUNE_KNIFEP_PLUS_5660,
            Items.RUNE_KNIFEP_PLUS_PLUS_5667
        ),

        /**
         * Darts
         */
        BRONZE_DART(
            Items.BRONZE_DART_806,
            Items.BRONZE_DARTP_812,
            Items.BRONZE_DARTP_PLUS_5628,
            Items.BRONZE_DARTP_PLUS_PLUS_5635
        ),
        IRON_DART(
            Items.IRON_DART_807,
            Items.IRON_DARTP_813,
            Items.IRON_DARTP_PLUS_5629,
            Items.IRON_DARTP_PLUS_PLUS_5636
        ),
        STEEL_DART(
            Items.STEEL_DART_808,
            Items.STEEL_DARTP_814,
            Items.STEEL_DARTP_PLUS_5630,
            Items.STEEL_DARTP_PLUS_PLUS_5637
        ),
        BLACK_DART(
            Items.BLACK_DART_3093,
            Items.BLACK_DARTP_3094,
            Items.BLACK_DARTP_PLUS_5631,
            Items.BLACK_DARTP_PLUS_PLUS_5638
        ),
        MITHRIL_DART(
            Items.MITHRIL_DART_809,
            Items.MITHRIL_DARTP_815,
            Items.MITHRIL_DARTP_PLUS_5632,
            Items.MITHRIL_DARTP_PLUS_PLUS_5639
        ),
        ADAMANT_DART(
            Items.ADAMANT_DART_810,
            Items.ADAMANT_DARTP_816,
            Items.ADAMANT_DARTP_PLUS_5633,
            Items.ADAMANT_DARTP_PLUS_PLUS_5640
        ),
        RUNE_DART(
            Items.RUNE_DART_811,
            Items.RUNE_DARTP_817,
            Items.RUNE_DARTP_PLUS_5634,
            Items.RUNE_DARTP_PLUS_PLUS_5641
        ),

        /**
         * Bolts
         */
        BLURITE_BOLT(
            Items.BLURITE_BOLTS_9139,
            Items.BLURITE_BOLTSP_9286,
            Items.BLURITE_BOLTSP_PLUS_9293,
            Items.BLURITE_BOLTSP_PLUS_PLUS_9300
        ),
        BRONZE_BOLT(
            Items.BRONZE_BOLTS_877,
            Items.BRONZE_BOLTSP_878,
            Items.BRONZE_BOLTSP_PLUS_6061,
            Items.BRONZE_BOLTSP_PLUS_PLUS_6062
        ),
        IRON_BOLT(
            Items.IRON_BOLTS_9140,
            Items.IRON_BOLTS_P_9287,
            Items.IRON_BOLTSP_PLUS_9294,
            Items.IRON_BOLTSP_PLUS_PLUS_9301
        ),
        STEEL_BOLT(
            Items.STEEL_BOLTS_9141,
            Items.STEEL_BOLTS_P_9288,
            Items.STEEL_BOLTSP_PLUS_9295,
            Items.STEEL_BOLTSP_PLUS_PLUS_9302
        ),
        BLACK_BOLT(
            Items.BLACK_BOLTS_13083,
            Items.BLACK_BOLTSP_13084,
            Items.BLACK_BOLTSP_PLUS_13085,
            Items.BLACK_BOLTSP_PLUS_PLUS_13086
        ),
        MITHRIL_BOLT(
            Items.MITHRIL_BOLTS_9142,
            Items.MITHRIL_BOLTS_P_9289,
            Items.MITHRIL_BOLTSP_PLUS_9296,
            Items.MITHRIL_BOLTSP_PLUS_PLUS_9303
        ),
        ADAMANT_BOLT(
            Items.ADAMANT_BOLTS_9143,
            Items.ADAMANT_BOLTS_P_9290,
            Items.ADAMANT_BOLTSP_PLUS_9297,
            Items.ADAMANT_BOLTSP_PLUS_PLUS_9304
        ),
        RUNE_BOLT(
            Items.RUNE_BOLTS_9144,
            Items.RUNITE_BOLTS_P_9291,
            Items.RUNITE_BOLTSP_PLUS_9298,
            Items.RUNITE_BOLTSP_PLUS_PLUS_9305
        ),
        SILVER_BOLT(
            Items.SILVER_BOLTS_9145,
            Items.SILVER_BOLTS_P_9292,
            Items.SILVER_BOLTSP_PLUS_9299,
            Items.SILVER_BOLTSP_PLUS_PLUS_9306
        ),

        /**
         * Javelins
         */
        IRON_JAVELIN(
            Items.IRON_JAVELIN_826,
            Items.BRONZE_JAVELINP_831,
            Items.IRON_JAVELINP_PLUS_5643,
            Items.BRONZE_JAVNP_PLUS_PLUS_5648
        ),
        BRONZE_JAVELIN(
            Items.IRON_JAVELIN_826,
            Items.IRON_JAVELINP_832,
            Items.BRONZE_JAVELINP_PLUS_5642,
            Items.IRON_JAVELINP_PLUS_PLUS_5649
        ),
        STEEL_JAVELIN(
            Items.STEEL_JAVELIN_827,
            Items.STEEL_JAVELINP_833,
            Items.STEEL_JAVELINP_PLUS_5644,
            Items.STEEL_JAVELINP_PLUS_PLUS_5650
        ),
        MITHRIL_JAVELIN(
            Items.MITHRIL_JAVELIN_828,
            Items.MITHRIL_JAVELINP_834,
            Items.MITHRIL_JAVELINP_PLUS_5645,
            Items.MITHRIL_JAVELINP_PLUS_PLUS_5651
        ),
        ADAMANT_JAVELIN(
            Items.ADAMANT_JAVELIN_829,
            Items.ADAMANT_JAVELINP_835,
            Items.ADAMANT_JAVELINP_PLUS_5646,
            Items.ADAMANT_JAVELINP_PLUS_PLUS_5652
        ),
        RUNE_JAVELIN(
            Items.RUNE_JAVELIN_830,
            Items.RUNE_JAVELINP_836,
            Items.RUNE_JAVELINP_PLUS_5647,
            Items.RUNE_JAVELINP_PLUS_PLUS_5653
        ),
        MORRIGAN_JAVELIN(
            Items.MORRIGANS_JAVELIN_13879,
            Items.MORRIGANS_JAVELINP_13880,
            Items.MORRIGANS_JAVELINP_PLUS_13881,
            Items.MORRIGANS_JAVELINP_PLUS_PLUS_13882
        ),

        /**
         * Daggers
         */
        IRON_DAGGER(
            Items.IRON_DAGGER_1203,
            Items.IRON_DAGGERP_1219,
            Items.IRON_DAGGERP_PLUS_5668,
            Items.IRON_DAGGERP_PLUS_PLUS_5686
        ),
        BRONZE_DAGGER(
            Items.BRONZE_DAGGER_1205,
            Items.BRONZE_DAGGERP_1221,
            Items.BRONZE_DAGGERP_PLUS_5670,
            Items.BRZE_DAGGERP_PLUS_PLUS_5688
        ),
        STEEL_DAGGER(
            Items.STEEL_DAGGER_1207,
            Items.STEEL_DAGGERP_1223,
            Items.STEEL_DAGGERP_PLUS_5672,
            Items.STEEL_DAGGERP_PLUS_PLUS_5690
        ),
        BLACK_DAGGER(
            Items.BLACK_DAGGER_1217,
            Items.BLACK_DAGGERP_1233,
            Items.BLACK_DAGGERP_PLUS_5682,
            Items.BLACK_DAGGERP_PLUS_PLUS_5700
        ),
        BONE_DAGGER(
            Items.BONE_DAGGER_8872,
            Items.BONE_DAGGER_P_8874,
            Items.BONE_DAGGER_P_PLUS_8876,
            Items.BONE_DAGGER_P_PLUS_PLUS_8878
        ),
        MITHRIL_DAGGER(
            Items.MITHRIL_DAGGER_1209,
            Items.MITHRIL_DAGGERP_1225,
            Items.MITHRIL_DAGGERP_PLUS_5674,
            Items.MITHRIL_DAGGERP_PLUS_PLUS_5692
        ),
        ADAMANT_DAGGER(
            Items.ADAMANT_DAGGER_1211,
            Items.ADAMANT_DAGGERP_1227,
            Items.ADAMANT_DAGGERP_PLUS_5676,
            Items.ADAMANT_DAGGERP_PLUS_PLUS_5694
        ),
        RUNE_DAGGER(
            Items.RUNE_DAGGER_1213,
            Items.RUNE_DAGGERP_1229,
            Items.RUNE_DAGGERP_PLUS_5678,
            Items.RUNE_DAGGERP_PLUS_PLUS_5696
        ),
        DRAGON_DAGGER(
            Items.DRAGON_DAGGER_1215,
            Items.DRAGON_DAGGERP_1231,
            Items.DRAGON_DAGGERP_PLUS_5680,
            Items.DRAGON_DAGGERP_PLUS_PLUS_5698
        ),

        /**
         * Spears
         */
        BRONZE_SPEAR(
            Items.BRONZE_SPEAR_1237,
            Items.BRONZE_SPEARP_1251,
            Items.BRONZE_SPEARP_PLUS_5704,
            Items.BRONZE_SPEARP_PLUS_PLUS_5718
        ),
        IRON_SPEAR(
            Items.IRON_SPEAR_1239,
            Items.IRON_SPEARP_1253,
            Items.IRON_SPEARP_PLUS_5706,
            Items.IRON_SPEARP_PLUS_PLUS_5720
        ),
        STEEL_SPEAR(
            Items.STEEL_SPEAR_1241,
            Items.STEEL_SPEARP_1255,
            Items.STEEL_SPEARP_PLUS_5708,
            Items.STEEL_SPEARP_PLUS_PLUS_5722
        ),
        BLACK_SPEAR(
            Items.BLACK_SPEAR_4580,
            Items.BLACK_SPEARP_4582,
            Items.BLACK_SPEARP_PLUS_5734,
            Items.BLACK_SPEARP_PLUS_PLUS_5736
        ),
        MITHRIL_SPEAR(
            Items.MITHRIL_SPEAR_1243,
            Items.MITHRIL_SPEARP_1257,
            Items.MITHRIL_SPEARP_PLUS_5710,
            Items.MITHRIL_SPEARP_PLUS_PLUS_5724
        ),
        ADAMANT_SPEAR(
            Items.ADAMANT_SPEAR_1245,
            Items.ADAMANT_SPEARP_1259,
            Items.ADAMANT_SPEARP_PLUS_5712,
            Items.ADAMANT_SPEARP_PLUS_PLUS_5726
        ),
        RUNE_SPEAR(
            Items.RUNE_SPEAR_1247,
            Items.RUNE_SPEARP_1261,
            Items.RUNE_SPEARP_PLUS_5714,
            Items.RUNE_SPEARP_PLUS_PLUS_5728
        ),
        DRAGON_SPEAR(
            Items.DRAGON_SPEAR_1249,
            Items.DRAGON_SPEARP_1263,
            Items.DRAGON_SPEARP_PLUS_5716,
            Items.DRAGON_SPEARP_PLUS_PLUS_5730
        );

        companion object {
            val itemMap = values().map { it.base to intArrayOf(it.p, it.pp, it.ppp) }.toMap()

            fun getBase(poisoned: Int): Int? {
                for ((base, set) in itemMap.entries) {
                    if (set.contains(poisoned)) return base
                }
                return null
            }
        }
    }
}