package content.global.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Shopkeepers dialogue plugin.
 */
@Initializable
public final class ShopkeeperDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code ShopkeeperDialogue} {@code Object}.
	 */
	public ShopkeeperDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ShopkeeperDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ShopkeeperDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShopkeeperDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Can I help you at all?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, please. What are you selling?", "No, thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Yes, please. I'd like to see your stock.");
				stage = 11;
				break;
			case 2:
				player("No thanks, I must be going now.");
				stage = 10;
				break;
			}
			break;
		case 10:
			end();
			break;
		case 11:
			end();
			npc.openShop(player);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 555 };
	}
}
