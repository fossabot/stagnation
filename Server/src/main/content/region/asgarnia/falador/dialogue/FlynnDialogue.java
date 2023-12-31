package content.region.asgarnia.falador.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Flynn dialogue plugin.
 */
@Initializable
public final class FlynnDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code FlynnDialogue} {@code Object}.
	 */
	public FlynnDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code FlynnDialogue} {@code Object}.
	 * @param player the player.
	 */
	public FlynnDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new FlynnDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Hello. Do you want to buy or sell any maces?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "No, thanks.", "Well, I'll have a look, at least.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No, thanks.");
				stage = 10;
				break;
			case 2:
				end();
				npc.openShop(player);
				break;

			}
			break;
		case 10:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 580 };
	}
}
