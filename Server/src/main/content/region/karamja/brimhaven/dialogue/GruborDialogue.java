package content.region.karamja.brimhaven.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Grubor dialogue plugin.
 */
@Initializable
public final class GruborDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GruborDialogue} {@code Object}.
	 */
	public GruborDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GruborDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GruborDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GruborDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes? What do you want?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Would you like your hedges trimming?", "I want to come in.", "Do you want to trade?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Would you like your hedges trimming?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I want to come in.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you want to trade?");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Eh? Don't be daft! We don't even HAVE any hehdges!");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, go away.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, I'm busy.");
			stage = 31;
			break;
		case 31:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 789 };
	}
}
