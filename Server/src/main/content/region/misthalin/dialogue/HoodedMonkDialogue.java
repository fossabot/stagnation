package content.region.misthalin.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Hooded Monk dialogue plugin.
 */
@Initializable
public final class HoodedMonkDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HoodedMonkDialogue} {@code Object}.
	 */
	public HoodedMonkDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HoodedMonkDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HoodedMonkDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HoodedMonkDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Excuse me...oh, wait, I thought you were someone else.");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No problem. Have a good day!");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3074 };
	}
}
