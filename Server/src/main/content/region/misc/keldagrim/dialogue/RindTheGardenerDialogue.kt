package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Rind The Gardener dialogue plugin.
 */
@Initializable
class RindTheGardenerDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_DEFAULT, "Come to visit my garden then, have you, human?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> options("This is your garden?", "Not really, no.").also { stage++ }
            1 -> when (buttonId) {
                1 -> player("This is your garden?").also { stage = 4 }
                2 -> player("Not really, no.").also { stage = 3 }
            }
            3 -> {
                npc("Oh, alright then.")
                stage = 100
            }
            4 -> npc(FacialExpression.OLD_DEFAULT, "No no, not quite. But I do look after it. It's the","palace garden, you see. I keep it tidy, keep it living.").also { stage++ }
            5 -> npc(FacialExpression.OLD_DEFAULT, "It's hard to grow much on these hard rocks", "deep beneath these mountains, you know.").also { stage++ }
            6 -> player("I can imagine. Where do you get all your food from?", "Or even the soil for this garden?").also { stage++ }
            7 -> npc(FacialExpression.OLD_DEFAULT, "We can grow a little food underground, but most of it","from the extensive steamcart network that runs beneath", "the earth.").also { stage++ }
            8 -> npc(FacialExpression.OLD_DEFAULT, "In the Era of Kings, many centuries ago, it was", "harder of course. Our steam technology was not developed", "as far yet and getting fresh supplies of food was always", "a problem.").also { stage++ }
            9 -> npc(FacialExpression.OLD_DEFAULT, "Oh, I'm not boring you, am I?").also { stage++ }
            10 -> options("Not at all!", "A little, yes.").also { stage++ }
            11 -> when (buttonId) {
                1 -> {
                    player("Not at all! Please, go on!")
                    stage = 13
                }
                2 -> {
                    player("A little, yes.")
                    stage = 21
                }
            }
            13 -> npc(FacialExpression.OLD_DEFAULT, "Oh yes, as I was saying, it was difficult to make a", "decent living here. I'm afraid this garden was rather", "neglected during that time.").also { stage++ }
            14 -> player("Why did you dwarves stay so deep under ground,", "then? Why not simply go above ground?").also { stage++ }
            15 -> npc(FacialExpression.OLD_DEFAULT, "What, with the wars raging up there?").also { stage++ }
            16 -> npc(FacialExpression.OLD_DEFAULT, "We dwarves went underground a long time ago, see.","For a while the city of Keldagrim was completely cut","off from the rest of Gielinor simply waiting out the","wars of the humans and the gods.").also { stage++ }
            17 -> npc(FacialExpression.OLD_DEFAULT, "This made it hard, though, to determine when the wars","above the ground were over. Perhaps Keldagrim","had remained hidden for far too long.").also { stage++ }
            18 -> npc(FacialExpression.OLD_DEFAULT, "Only when King Alvis heroically defended the city","against an invasion of trolls did we dare to venture","out and see how much the world had changed.").also { stage++ }
            19 -> npc(FacialExpression.OLD_DEFAULT, "Of course, after that...").also { stage++ }
            20 -> npc(FacialExpression.OLD_DEFAULT, "Ah well, enough history lessons I should say.","I must really get back to my gardening.").also { stage = 100 }
            21 -> player("Thanks for the info!").also { stage++ }
            22 -> player("A little, yes. I don't really know what all", "this has to do with anything.").also { stage++ }
            23 -> npc(FacialExpression.OLD_DEFAULT, "I suppose it doesn't, really, it's just the mumblings","of an old dwarf.").also { stage++ }
            100 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.RIND_THE_GARDENER_2170)
    }
}