package content.quest.member.merlinsquest.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the Sir Palomedes dialogue plugin for Merlin Crystal quest.
 */
public class SirPalomedesMCDialogue extends DialoguePlugin {

	public SirPalomedesMCDialogue() {

	}

	public SirPalomedesMCDialogue(Player player) {
		super(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there adventurer, what do you want of me?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
			if (quest.getStage(player) == 100) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Congratulations on freeing Merlin!");
				stage = 20;
			} else if (quest.getStage(player) >= 10 && quest.getStage(player) < 50) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like some advice on breaking that Crystal Merlin's", "trapped in.");
				stage = 1;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am afraid I have no time to chat.");
				stage = 20;
			}
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sorry, I cannot help you with that.");
			stage = 20;
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SirPalomedesMCDialogue(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 3787 };
	}
}