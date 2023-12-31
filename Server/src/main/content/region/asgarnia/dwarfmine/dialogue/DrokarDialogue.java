package content.region.asgarnia.dwarfmine.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Drokar dialogue plugin.
 */
@Initializable
public final class DrokarDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code DrokarDialogue} {@code Object}.
	 */
	public DrokarDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DrokarDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DrokarDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DrokarDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello, how are you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Packages, packages and more!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ugh.. Okay, have a good day.");
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
		return new int[] { 7729 };
	}
}
