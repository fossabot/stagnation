package content.region.island.miscellania.dialogue

import core.game.dialogue.DialogueFile
import core.tools.END_DIALOGUE

/**
 * Represents the Kjallak dialogue file.
 */
class KjallakOnChopDialogue : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        when(stage){
            0 -> npc("Hey! You're not allowed to chop those!").also { stage++ }
            1 -> player("Oh, ok...").also { stage = END_DIALOGUE }
        }
    }
}