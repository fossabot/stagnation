package content.region.misthalin.varrock.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Guidors Wife dialogue plugin.
 */
@Initializable
public final class GuidorsWifeDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GuidorsWifeDialogue} {@code Object}.
	 */
	public GuidorsWifeDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GuidorsWifeDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GuidorsWifeDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GuidorsWifeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args.length == 2) {
			interpreter.sendDialogues(342, FacialExpression.HALF_GUILTY, "Please leave my husband alone. He's very sick, and I don't", "want anyone bothering him.");
			stage = 100;
			return true;
		}
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendDialogues(342, FacialExpression.HALF_GUILTY, "Oh hello, I can't chat now. I have to keep an eye on my", "husband. He's very ill!");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm sorry to hear that!");
			stage = 2;
			break;
		case 2:
			end();
			break;
		case 100:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 342 };
	}
}
