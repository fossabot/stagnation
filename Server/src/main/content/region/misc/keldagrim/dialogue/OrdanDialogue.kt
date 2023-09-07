package content.region.misc.keldagrim.dialogue

import config.Items
import config.NPCs
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.START_DIALOGUE

/**
 * Represents the dialogue used for Ordan.
 * Handles unnoting ores.
 */
@Initializable
class OrdanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            START_DIALOGUE-> playerl(FacialExpression.FRIENDLY, "Can you un-note any of my items?").also { stage++ }
            1 -> npcl(FacialExpression.OLD_DEFAULT,"I can un-note Tin, Copper, Iron, Coal, and Mithril.").also { stage++ }
            2 -> npcl(FacialExpression.OLD_DEFAULT,"I can even un-note Adamantite and Runite, but you're gonna need deep pocktes for that.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return OrdanDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.ORDAN_2564)
    }

    class OrdanUnnoteListener : InteractionListener {
        val notedOre = intArrayOf(
            Items.IRON_ORE_441,
            Items.COPPER_ORE_437,
            Items.TIN_ORE_439,
            Items.COAL_454,
            Items.MITHRIL_ORE_448,
            Items.ADAMANTITE_ORE_450,
            Items.SILVER_ORE_443,
            Items.GOLD_ORE_445,
            Items.RUNITE_ORE_452
        )
        val UNNOTE_PRICES = hashMapOf(
            Items.IRON_ORE_441 to 8,
            Items.COPPER_ORE_437 to 10,
            Items.TIN_ORE_439 to 10,
            Items.COAL_454 to 22,
            Items.MITHRIL_ORE_448 to 81,
            Items.ADAMANTITE_ORE_450 to 330,
            Items.SILVER_ORE_443 to 37,
            Items.GOLD_ORE_445 to 75,
            Items.RUNITE_ORE_452 to 1000
        )
        override fun defineListeners() {
            onUseWith(IntType.NPC, notedOre, NPCs.ORDAN_2564){ player, noteType, _  ->
                val noteAmount = amountInInventory(player, noteType.id)
                val noteName = noteType.name
                var unNoteAmount = 0
                var inventorySlots = freeSlots(player)
                var actualAmount = 0
                var proceed = false
                var wait = false
                player.dialogueInterpreter.sendOptions("$noteName un-note?","1","5","10","X")
                player.dialogueInterpreter.addAction { player, button ->
                    when(button) {
                        2 -> unNoteAmount = 1
                        3 -> unNoteAmount = 5
                        4 -> unNoteAmount = 10
                        5 ->{sendInputDialogue(player, true, "Enter amount:") { value ->
                                 unNoteAmount = value as Int
                            actualAmount = if(unNoteAmount > inventorySlots){
                                inventorySlots
                            }else{
                                unNoteAmount
                            }
                            if(actualAmount > noteAmount){
                                actualAmount = noteAmount
                            }

                            val totalPrice = UNNOTE_PRICES[noteType.id]!! * (actualAmount)
                            if(amountInInventory(player,Items.COINS_995) >= totalPrice && actualAmount > 0 ){
                                removeItem(player, Item(995,totalPrice),Container.INVENTORY)
                                removeItem(player, Item(noteType.id,actualAmount),Container.INVENTORY)
                                addItem(player,noteType.id - 1,actualAmount)
                            }
                            }
                        }
                    }
                    actualAmount = if(unNoteAmount > inventorySlots){
                        inventorySlots
                    }else{
                        unNoteAmount
                    }
                    if(actualAmount > noteAmount){
                        actualAmount = noteAmount
                    }
                    val totalPrice = UNNOTE_PRICES[noteType.id]!! * (actualAmount)
                    if(amountInInventory(player,Items.COINS_995) >= totalPrice && actualAmount > 0 ){
                        removeItem(player, Item(995,totalPrice),Container.INVENTORY)
                        removeItem(player, Item(noteType.id,actualAmount),Container.INVENTORY)
                        addItem(player,noteType.id - 1,actualAmount)
                    }
                }
                return@onUseWith true
            }
        }
    }
}