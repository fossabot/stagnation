package content.quest.member.bigchompybirdhunting.dialogue

import config.Items
import config.NPCs
import content.quest.member.bigchompybirdhunting.ChompyBirdHat
import content.quest.member.bigchompybirdhunting.handlers.ChompyDialoguesHandler
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.dialogue.IfTopic
import core.game.dialogue.Topic
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Initializable
import core.tools.END_DIALOGUE
import core.tools.RandomFunction

/**
 * Represents the Rantz dialogue plugin for Big Chompy Bird Hunting quest.
 */
@Initializable
class RantzChompyDialogue(player: Player? = null) : DialoguePlugin(player) {
  override fun getIds() : IntArray {
    return intArrayOf(NPCs.RANTZ_1010)
  }

  override fun newInstance(player: Player?) : DialoguePlugin {
    return RantzChompyDialogue(player)
  }

  override fun open(vararg args: Any?) : Boolean {
    npc = args[0] as NPC

    val chompyBird = player.questRepository.getQuest("Big Chompy Bird Hunting")
    val chompyStage = chompyBird.getStage(player)

    val hasOgreBow = inInventory(player, Items.OGRE_BOW_2883) || inEquipment(player, Items.OGRE_BOW_2883) || inBank(player, Items.OGRE_BOW_2883)

    if (stage in 60 until 100 && !hasOgreBow) {
      stage = 201
      player.dialogueInterpreter.handle(0,0)
      return true
    }

    when (chompyStage) {
      in 0 until 100 -> loadFile(ChompyDialoguesHandler(chompyBird))
    }

    player.dialogueInterpreter.handle(0,0)

    return true
  }

  override fun handle(componentId: Int, buttonId: Int) : Boolean {
    when(stage) {
	    0 -> npcl(FacialExpression.OLD_NORMAL, "Creature done good, cooking da chompy. Maybe you wants a free hatsie? Rantz got lots of hatsies for chompy shooters.").also { stage++ }
	    1 -> options("What are these 'hatsies'?", "Okay, show me your 'hatsies'.", "No thanks.").also { stage++ }
	    2 -> when(buttonId) {
		    1 -> playerl(FacialExpression.FRIENDLY, "What are these 'hatsies'?").also { stage = 3 }
		    2 -> playerl(FacialExpression.FRIENDLY, "Okay, show me your 'hatsies'.").also { stage = 100 }
		    3 -> playerl(FacialExpression.FRIENDLY, "No thanks.").also { stage = END_DIALOGUE }
		  }
	    3 -> npcl(FacialExpression.OLD_NORMAL, "Creature stupid? Hatsies to wear on head, make you look good. Huh huh huh.").also { stage++ }
    	4 -> playerl(FacialExpression.FRIENDLY, "Ah, I see, you're offering me hats.").also { stage++ }
	    5 -> npcl(FacialExpression.OLD_NORMAL, "Dat's what Rantz said. You want hatsies or not?").also { stage = 1 }
      
      100 -> {
        val hasBow = inInventory(player, Items.OGRE_BOW_2883) || inEquipment(player, Items.OGRE_BOW_2883)

        if (!hasBow) {
          npcl(FacialExpression.OLD_NORMAL, "Stupid creature. Me need bow to see how many chompy creature kill. Bring bow.")
          stage = 200
          return true
        }

        val hats = ChompyBirdHat.getApplicableHats(player)
        if (hats.isEmpty()) {
          npcl(FacialExpression.OLD_NORMAL, "Sorry, creature, no hatsies for you. Come back when kill more chompy.")
        } else {
          val spaces = freeSlots(player)
          if (spaces < hats.size) {
            for (i in 0 until spaces) addItem(player, hats[i]) 
            npcl(FacialExpression.OLD_NORMAL, "There all hats you can fit, creature. Come back when have more room for hatsies.")
          } else {
            for (hat in hats) addItem(player, hat)
            npcl(FacialExpression.OLD_NORMAL, "There all hats, creature. Come back when kill more chompy. Unless you kill all chompy already.")
          }
        }
        stage = END_DIALOGUE
      }

      200 -> showTopics(
        IfTopic("About that...", 201, !inBank(player, Items.OGRE_BOW_2883)),
        Topic("Okay.", END_DIALOGUE)
      )

      201 -> npcl(FacialExpression.OLD_NORMAL, "Yes, creature?").also { stage++ }
      202 -> playerl(FacialExpression.HALF_GUILTY, "I lost my bow.").also { stage++ }
      203 -> npcl(FacialExpression.OLD_NORMAL, "Haha! Stupid creature! That okay, me can sell new one.").also { stage++ }
      204 -> playerl(FacialExpression.FRIENDLY, "Really? How much?").also { stage++ }
      205 -> npcl(FacialExpression.OLD_NORMAL, "Hmm.. let me think...").also { stage++ }
      206 -> {
        val cost = RandomFunction.random(500,550)
        setAttribute(player, "/save:chompybird:new-bow-cost", cost)
        npcl(FacialExpression.OLD_NORMAL, "Me think $cost shiny coins be ok.")
        stage++
      }

      207 -> showTopics(
        IfTopic(
          "Okay.", 208, 
          amountInInventory(player, Items.COINS_995) >= getAttribute(player, "chompybird:new-bow-cost", 500)
        ),
        Topic("No thank you...", END_DIALOGUE)
      )

      208 -> {
        if (removeItem(player, Item(Items.COINS_995, getAttribute(player, "chompybird:new-bow-cost", 500)))) {
          addItemOrDrop(player, Items.OGRE_BOW_2883)
          npcl(FacialExpression.OLD_NORMAL, "There you go, creature, now hunt chompy.")
        } else {
          playerl(FacialExpression.FRIENDLY, "Actually, I can't afford it right now.")
        }
        stage = END_DIALOGUE
      }
    }
    return true
  }
}
