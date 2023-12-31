package content.region.misthalin.barbvillage.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Litara dialogue plugin.
 */
@Initializable
public final class LitaraDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LitaraDialogue} {@code Object}.
	 */
	public LitaraDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LitaraDialogue} {@code Object}.
	 * @param player the player.
	 */
	public LitaraDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LitaraDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there. You look lost - are you okay?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "I'm looking for  stronghold, or something.", "I'm fine, just passing through.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm looking for a stronghold, or something.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm fine, just passing through.");
				stage = 20;
				break;
			}

			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh, the Strnghold of Security. It's down there.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogue("Litara points to the hole in the ground that looks", "like you could squeeze, through.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Looks kind of ...deep and dark.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yeah, tell that to my brother. He still hasn't come back.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Your brother?");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "He's an explorer too. When the miner fell down that hole", "he'd made and came back babbling about treasure, my", "brother went to explore. No one has seen him since.");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh, that's not good.");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Lots of people have been down there, but none of them", "have seen him. Let me know if you do, will you?");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll certainly keep my eyes open.");
			stage = 19;
			break;
		case 19:
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
		return new int[] { 4376 };
	}
}
