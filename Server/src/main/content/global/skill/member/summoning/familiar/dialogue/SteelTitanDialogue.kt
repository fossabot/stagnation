package content.global.skill.member.summoning.familiar.dialogue

import config.NPCs
import core.api.openDialogue
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Steel Titan familiar interaction dialogue.
 */
@Initializable
class SteelTitanDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        openDialogue(player, SteelTitanDialogueFile())
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.STEEL_TITAN_7343, NPCs.STEEL_TITAN_7344)
    }
}

class SteelTitanDialogueFile : DialogueFile() {

    private val randomConversation = RandomFunction.getRandomElement(arrayOf(1, 2, 3, 4, 5))

    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.STEEL_TITAN_7343)
        if (randomConversation == 1) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Forward, master, to a battle that will waken the gods!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'd rather not, if it's all the same to you.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"I shall never meet my end at this rate...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 2) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"How do you wish to meet your end, master?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Hopefully not for a very long time.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"You do not wish to be torn asunder by the thousand limbs of a horde of demons?").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "No! I'm quite happy picking flax and turning unstrung bows into gold...").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 3) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Why must we dawdle when glory awaits?").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "I'm beginning to think you just want me to die horribly...").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"We could have deaths that bards sing of for a thousand years.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "That's not much compensation.").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 4) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Master, we should be marching into glorious battle!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "You know, I think you're onto something.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"We could find a death befitting such heroes of RuneScape!").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "Ah. You know, I'd prefer not to die...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Beneath the claws of a mighty foe shall I be sent into the embrace of death!").also { stage = END_DIALOGUE }
            }
        }

        if (randomConversation == 5) {
            when (stage) {
                0 -> npcl(FacialExpression.OLD_NORMAL,"Let us go forth to battle, my lady/lord!").also { stage++ }
                1 -> playerl(FacialExpression.NEUTRAL, "Why do you like fighting so much? It's not very nice to kill things.").also { stage++ }
                2 -> npcl(FacialExpression.OLD_NORMAL,"It is the most honourable thing in life.").also { stage++ }
                3 -> playerl(FacialExpression.NEUTRAL, "But I summoned you, I'm not sure I can even say that you're alive...").also { stage++ }
                4 -> npcl(FacialExpression.OLD_NORMAL,"Alas, you have discovered the woe of all summoned creatures' existence.").also { stage++ }
                5 -> playerl(FacialExpression.NEUTRAL, "Really? I was right?").also { stage++ }
                6 -> npcl(FacialExpression.OLD_NORMAL,"Oh, woe...woe!").also { stage = END_DIALOGUE }
            }
        }
    }
}