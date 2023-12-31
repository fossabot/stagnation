package content.region.asgarnia.burthorpe.dialogue;

import config.NPCs;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.plugin.Initializable;

/**
 * Represents the Ghommal dialogue plugin.
 */
@Initializable
public final class GhommalDialogue extends DialoguePlugin {
	public GhommalDialogue() {}
	public GhommalDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GhommalDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getSkills().getLevel(Skills.ATTACK) + player.getSkills().getLevel(Skills.STRENGTH) >= 130 || player.getSkills().getLevel(Skills.ATTACK) == 99 || player.getSkills().getLevel(Skills.STRENGTH) == 99) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ghommal welcome you to Warrior Guild!");
			stage = 10;
		} else {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You not pass. You too weedy.");
			stage = 0;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What? But I'm a warrior!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Heehee... he say he warrior... I not heard that one", "for... at leas' 5 minutes!");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Go on, let me in, you know you want to. I could...", "make it worth your while...");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No! You is not a strong warrior, you not enter till you", "bigger, Ghommal does not takes bribes.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ghommal stick to Warrior's Code of Honour. When", "you a bigger, stronger warriror, you come back.");
			stage = 5;
			break;
		case 5:
			end();
			break;
		case 10:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Umm...thank you, I think.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { NPCs.GHOMMAL_4285 };
	}
}
