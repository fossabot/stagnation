package content.minigame.pestcontrol.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Squire Magic dialogue plugin for Pest Control minigame.
 */
@Initializable
public final class SquireMagicDialougue extends DialoguePlugin {

	/**
	 * Constructs a new {@code SquireMagicDialougue} {@code Object}.
	 */
	public SquireMagicDialougue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code SquireMagicDialougue} {@code Object}.
	 * @param player the player.
	 */
	public SquireMagicDialougue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SquireMagicDialougue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("Hi, how can I help you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			options("What do you have for sale?", "I'm fine thanks.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				end();
				npc.openShop(player);
				break;
			case 2:
				player("I'm fine thanks.");
				stage = 20;
				break;
			}
			break;
		case 10:
			end();
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 3796, 3798, 3799 };
	}
}
