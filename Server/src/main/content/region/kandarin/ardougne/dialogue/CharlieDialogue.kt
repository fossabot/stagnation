package content.region.kandarin.ardougne.dialogue

import config.NPCs
import core.api.getQuestStage
import core.api.getStatLevel
import core.api.isQuestComplete
import core.api.openNpcShop
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Charlie dialogue plugin.
 */
@Initializable
class CharlieDialogue(player: Player? = null) : DialoguePlugin(player){

    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if(isQuestComplete(player, "Eagles' Peak")){
            playerl(FacialExpression.HALF_ASKING,"Hi, how are you getting on with that ferret?").also { stage = 25 }
        } else {
            playerl(FacialExpression.NEUTRAL, "Hi!").also { stage = 0 }
        }
        return true
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npcl(FacialExpression.FRIENDLY, "Yes, er, welcome to the zoo! I'm afraid I'm kind of busy right now; what was it you wanted?").also { stage++ }
            1 -> options("Ah, you sound like someone who needs a quest doing!", "Where did you get the animals from?").also { stage++ }
            2 -> when(buttonId){
                1 -> playerl(FacialExpression.NEUTRAL, "Ah, you sound like someone who needs a quest doing!").also { stage++ }
                2 -> playerl(FacialExpression.NEUTRAL, "Where did you get the animals from?").also { stage = 21 }
            }
            3  -> if(getStatLevel(player, Skills.HUNTER) < 27){
                npcl(FacialExpression.NEUTRAL, "Well... maybe. How much do you know about hunting?").also { stage = 9 }
            } else {
                npcl(FacialExpression.NEUTRAL, "Actually, now that you come to mention it, you might be able to help me.").also { stage = 11 }
            }
            9  -> playerl(FacialExpression.NEUTRAL, "Well I know a little bit...").also { stage++ }
            10 -> npcl(FacialExpression.NEUTRAL, "No, I could do with some help, but I'm afraid I need somebody with a little more experience.").also { stage = END_DIALOGUE }
            11 -> npcl(FacialExpression.NEUTRAL, "A few weeks ago we had a delivery of a northern ferret. They're funny little creatures. Turns out they're tricksy little blighters too.").also { stage++ }
            12 -> npcl(FacialExpression.NEUTRAL, "Whilst we were unloading him from the cart he managed to escape his cage. What's more he gave the driver a nasty nip on the way out too.").also { stage++ }
            13 -> npcl(FacialExpression.NEUTRAL, "This was obviously a bit of a blow, you can't make a new attraction without any animals.").also { stage++ }
            14 -> npcl(FacialExpression.NEUTRAL, "We got in contact with one of our associates, a huntsman called Nickolaus, who agreed to capture us another.").also { stage++ }
            15 -> npcl(FacialExpression.NEUTRAL, "Funny thing is we haven't heard from him for some time now. I don't suppose you'd mind having a look for him?").also { stage++ }
            16 -> options("Sure. Any idea where I should start looking?", "Sorry, I don't have the time.").also { stage++ }
            17 -> when(buttonId){
                1 -> playerl(FacialExpression.HALF_ASKING, "Sure. Any idea where I should start looking?").also { stage = 18 }
                2 -> playerl(FacialExpression.NEUTRAL, "Sorry, I don't have the time.").also { stage = 20 }
            }
            18 -> npcl(FacialExpression.NEUTRAL, "Well the northern ferret is mostly found around the mountains just west of the Gnome Stronghold, so that would probably be as good a place as any to start looking.").also { stage++ }
            19 -> playerl(FacialExpression.FRIENDLY, "Okay, thanks.").also { stage = END_DIALOGUE }
            20 -> npcl(FacialExpression.NEUTRAL, "Oh well, I'm sure he'll be okay out there on his own.").also { stage = END_DIALOGUE }
            21 -> npcl(FacialExpression.FRIENDLY, "We get them from all over the place! The most exotic creatures have been brought back by explorers and sold to us.").also { stage++ }
            22 -> playerl(FacialExpression.HALF_ASKING, "Where on Gielinor did you get that scary looking Cyclops?!").also { stage++ }
            23 -> npcl(FacialExpression.LAUGH, "Yes he is scary looking!").also { stage++ }
            24 -> npcl(FacialExpression.FRIENDLY, "He's from a very far away land, we couldn't find out more as the explorer who brought him back died shortly afterwards!").also { stage = END_DIALOGUE }
            25 -> npcl(FacialExpression.FRIENDLY,"Well he seems to be just as vicious as the one we lost, but we're still very grateful for your help in acquiring him.").also { stage = END_DIALOGUE }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return CharlieDialogue(player)
    }

    override fun getIds(): IntArray {
        return intArrayOf(NPCs.CHARLIE_5138)
    }
}
