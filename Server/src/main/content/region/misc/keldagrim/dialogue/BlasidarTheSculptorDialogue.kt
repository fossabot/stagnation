package content.region.misc.keldagrim.dialogue

import config.NPCs
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * Represents the Blasidar The Sculptor dialogue plugin.
 */
@Initializable
class BlasidarTheSculptorDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        npc(FacialExpression.OLD_NORMAL,"The new statue looks beautiful, don't you agree?")
        stage = 0
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when (stage) {
            0 -> player(FacialExpression.NEUTRAL, "Oh yes, quite.").also { stage++ }
            1 -> npc(FacialExpression.OLD_NORMAL, "My finest piece of work, without a doubt.").also { stage++ }
            2 -> player(FacialExpression.HALF_ASKING, "Say, I was wondering, did you do the statues down", "in the mines as well?").also { stage++ }
            3 -> npc(FacialExpression.OLD_NORMAL,"What, out with the trolls?").also { stage++ }
            4 -> npc(FacialExpression.OLD_NORMAL, "If only! But no, they've been there for many thousands of years,", "actually. Been there before dwarven recorded history.").also { stage++ }
            5 -> player(FacialExpression.HALF_WORRIED,"No no, I'm sure they weren't there before!").also { stage++ }
            6 -> npc(FacialExpression.OLD_NORMAL,"Of course they were, don't be silly!").also { stage++ }
            7 -> player("Alright... my mistake then.").also { stage++ }
            8 -> end()
            9 -> npc(FacialExpression.OLD_NORMAL,"Why, yes, yes, that I am!").also { stage++ }
            10 -> player(FacialExpression.FRIENDLY, "Oh, well, I'm ${player.username}. Veldaban of the Black Guard", "sent me to you, you see. Said you might need some help with the statue.").also { stage++ }
            11 -> npc(FacialExpression.OLD_NORMAL, "Oh, I don't need an assistant sculptor, really,", "I can do just fine on my own.").also { stage++ }
            12 -> player(FacialExpression.NEUTRAL,"I'm not a sculptor.").also { stage++ }
            13 -> npc(FacialExpression.OLD_NORMAL, "And I've also got a model here, you see,", "to base my statue on.").also { stage++ }
            14 -> player(FacialExpression.ANNOYED,"I'm not a model!").also { stage++ }
            15 -> npc(FacialExpression.OLD_NORMAL, "But come to think of it, I do need some errands run.", "Yes, quite important errands too.").also { stage++ }
            16 -> npc(FacialExpression.OLD_NORMAL,"Yes, you will do just fine.").also { stage++ }
            17 -> player(FacialExpression.NEUTRAL,"Ehm, right, okay.").also { stage++ }
            18 -> player(FacialExpression.HALF_ASKING,"So what did you need doing?").also { stage++ }
            19 -> npc(FacialExpression.OLD_NORMAL, "Well, see... the Consortium has commisioned me to rebuild", "the statue exactly the way it was.").also { stage++ }
            20 -> npc(FacialExpression.OLD_NORMAL, "But the statue itself has broken into a million pieces...", "and we dwarves aren't very good painters, you know, so we don't", "have any paintings of it either.").also { stage++ }
            21 -> player(FacialExpression.HALF_ASKING,"Not a very artistic race, then, are you?").also { stage++ }
            22 -> npc(FacialExpression.OLD_NORMAL,"No, that's not what I said,","I said we're not very good painters.").also { stage++ }
            23 -> npc(FacialExpression.OLD_NORMAL,"We're excellent with, for example, stone. Oh yes,", "quite good if I say so myself.").also { stage++ }
            24 -> npc(FacialExpression.OLD_NORMAL, "But I digress... simply speaking, I need some help", "with getting some items that I can base the new", "statue on.").also { stage++ }
            25 -> npc(FacialExpression.OLD_NORMAL,"Will you be able to help me with this, ${player.username}?").also { stage++ }
            26 -> options("No, I can't be bothered.", "Yes, I will do this.").also { stage++ }
            27 -> when (buttonId) {
                1 -> player(FacialExpression.NEUTRAL,"No, I can't be bothered.").also { stage++ }
                2 -> player(FacialExpression.FRIENDLY,"Yes, I will do this.").also { stage = 29 }
            }
            28 -> npc(FacialExpression.OLD_NORMAL,"How rude is that? I don't think the Black Guard will be", "very pleased with you!").also { stage = 100 }
            29 -> npc(FacialExpression.OLD_NORMAL,"Excellent, excellent! Now what I need is the following...").also { stage++ }
            30 -> npc(FacialExpression.OLD_NORMAL, "I will need a pair of boots, the most exquisite boots you can find.", "The more they look like the original the better,", "but I don't think you'll be able to find an exact match.").also { stage++ }
            31 -> npc(FacialExpression.OLD_NORMAL, "For the torso, I need clothes that look like King Alvis' clothes.", "I'm not sure where you would be able to find that,", "but I'm sure you'll manage.").also { stage++ }
            32 -> npc(FacialExpression.OLD_NORMAL, "Also, I need a battleaxe. Now this is particularly important.", "You are in luck, however, as I believe King Alvis'", " original axe still survives.").also { stage++ }
            33 -> player(FacialExpression.HALF_ASKING, "Right, so I need a pair of boots, a piece of clothing", "and a battleaxe. Is that all?").also { stage++ }
            34 -> npc(FacialExpression.OLD_NORMAL,"For now, ${player.username}, for now! I wish you good luck,", "and hurry back with the items!").also { stage++ }
            35 -> player(FacialExpression.HALF_ASKING,"But... wait, wait, how am I supposed to find all this?").also { stage++ }
            36 -> npc(FacialExpression.OLD_NORMAL,"I don't know! You're supposed to find that out for me!").also { stage++ }
            37 -> npc(FacialExpression.OLD_NORMAL, "But it's a beautiful city, Keldagrim is,", "I'm sure you'll see many things while you do these tasks!").also { stage = 100 }
            38 -> player(FacialExpression.SAD,"I don't have your boots yet.").also { stage++ }
            39 -> player(FacialExpression.SAD,"I don't have your boots yet.").also { stage++ }
            40 -> npc(FacialExpression.OLD_NORMAL,"What else?").also { stage++ }
            41 -> player(FacialExpression.NEUTRAL,"I also don't have your clothes yet,", "I'm afraid.").also { stage++ }
            42 -> npc(FacialExpression.OLD_NORMAL,"And? The battleaxe?").also { stage++ }
            43 -> player(FacialExpression.NEUTRAL,"Sorry, no, I'm still looking for it.").also { stage++ }
            44 -> npc(FacialExpression.OLD_NORMAL,"Look a little harder please!").also { stage++ }
            100 -> end()
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.BLASIDAR_THE_SCULPTOR_2141)
    }
}