package content.global.handlers.item.withitem

import config.Items
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.tools.END_DIALOGUE

class TOTTHelmOnCape : InteractionListener {
    override fun defineListeners() {
        onUseWith(
            IntType.ITEM,
            Items.SLAYER_HELMET_13263,
            Items.SLAYER_CAPE_9786,
            Items.SLAYER_CAPET_9787
        ) { player, used, with ->
            val alreadyHasHelm = getAttribute(player, "cape_perks:tott:helmet-stored", false)

            if (alreadyHasHelm) {
                sendDialogue(player, "You've already stored the components of a helmet in this cape.")
            } else {
                openDialogue(player, object : DialogueFile() {
                    override fun handle(componentID: Int, buttonID: Int) {
                        when (stage) {
                            0 -> dialogue("This will destroy your helmet permanently.").also { stage++ }
                            1 -> interpreter!!.sendOptions("Continue?", "Yes", "No").also { stage++ }
                            2 -> when (buttonID) {
                                1 -> {
                                    if (removeItem(player, Items.SLAYER_HELMET_13263, Container.INVENTORY)) {
                                        dialogue(*splitLines("You disassemble the helmet and place the components into their respective pockets on your cape."))
                                        setAttribute(player, "/save:cape_perks:tott:helmet-stored", true)
                                        stage = END_DIALOGUE
                                    }
                                }

                                2 -> end()
                            }
                        }
                    }
                })
            }

            return@onUseWith true
        }
    }
}