package content.minigame.vinesweeper.dialogue

import config.Items
import config.NPCs
import content.minigame.vinesweeper.handler.FlagsHandler
import core.api.InputType
import core.api.openDialogue
import core.api.sendInputDialogue
import core.game.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Blinkin dialogue plugin used for Vinesweeper mini-game.
 */
@Initializable
class BlinkinDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        openDialogue(player!!, BlinkinDialogueFile(), npc)
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.FARMER_BLINKIN_7131)
    }
}

/**
 * Represents the Blinkin dialogue file used for Vinesweeper mini-game.
 */
class BlinkinDialogueFile : FlagsHandler() {
    override fun handle(componentID: Int, buttonID: Int) {
        when(stage) {
            0 -> npcl("'Ello there! Welcome to Winkin's Farm. What can I do for ya?").also { stage++ }
            1 -> options("What is this place?", "Do you have any flags?", "Where is Mr. Winkin?").also { stage++ }
            2 -> when(buttonID) {
                1 -> playerl("What is this place?").also { stage = 10 }
                2 -> playerl("Do you have any flags?").also { stage = 20 }
                3 -> playerl("Where is Mr. Winkin?").also { stage = 30 }
            }
            10 -> npcl("Ha! I told ya, it's Winkin's Farm. This is where we grow the magical ogleroots for the rest of the world.").also { stage++ }
            11 -> playerl("So, what can I do here?").also { stage++ }
            12 -> npcl("Ya can improve yer Farming skill by getting some flags from me or Mrs. Winkin, inside. Then, head out to the fields and flag where ya think plants are, er, planted.").also { stage++ }
            13 -> playerl("Is that it?").also { stage++ }
            14 -> npcl("Not at all! There's more. When ya've placed yar flags, the farmers will collect them up and give out points. Ya can trade these points for seeds or experience at the shop inside.").also { stage++ }
            15 -> playerl("Okay, that sounds great! I'll get planting, then.").also { stage++ }
            16 -> npcl("Aye, but be careful where ya plant the flags. If there's no plant under yar flag, the farmer will keep it and they cost a pretty penny to buy more.").also { stage++ }
            17 -> playerl("I see. Thanks for the help.").also { stage++ }
            18 -> npcl("Bye, for now. If I have to say 'flag' one more time, I tell ya...").also { stage = END_DIALOGUE }
            20, 21, 22, 220, 221, 222, 223, 23 -> handleFlags(componentID, buttonID, BLINKIN_FLAG_LINES)
            30 -> npcl("Farmer Winkin? Well, last I 'eard he was heading into market with a fresh load of Ogleroots.").also { stage++ }
            31 -> playerl("Ogleroots?").also { stage++ }
            32 -> npcl("Aye! We get them growing a lot here in the farmyard. We dig 'em up and sell 'em to yar lot.").also { stage++ }
            33 -> playerl("My lot?").also { stage++ }
            34 -> npcl("Aye! Humans.").also { stage++ }
            35 -> playerl("Oh, I see. Thanks!").also { stage++ }
            36 -> npcl("Bye now!").also { stage = END_DIALOGUE }
            // Not accessible in normal conversation, requires "buy-roots" right click
            40 -> playerl("Do you have any Ogleroots to feed the rabbits?").also { stage++ }
            41 -> npcl("I sure do. They'll cost ya 10 gold each. Any ya leave with will be returned to us, but ya'll get yer money back for 'em. How many do ya want?").also { stage++ }
            42 -> {
                sendInputDialogue(player!!, InputType.AMOUNT, "Enter an amount:") { value ->
                    val amount = value as Int
                    val price = Item(Items.COINS_995, 10 * amount)
                    if(price.amount <= 0){
                        return@sendInputDialogue
                    }
                    if(player!!.inventory.containsItem(price) && player!!.inventory.remove(price)) {
                        if(player!!.inventory.add(Item(Items.OGLEROOT_12624, amount))) {
                            npcl("There ya go! Good luck!")
                            stage = END_DIALOGUE
                        } else {
                            npcl("TODO (crash): dialogue for no space for ogleroots")
                            // Refund the coins, can't fail because we just removed them
                            player!!.inventory.add(price)
                            stage = END_DIALOGUE
                        }
                    } else {
                        npcl("Sorry, ya can't afford that many. Come back when yer feeling a bit richer if ya like!")
                        stage = END_DIALOGUE
                    }
                    //openDialogue(player!!, this, npc as Any)
                }
                player!!.dialogueInterpreter.sendInput(false, "Enter the amount:")
            }
        }
    }
}