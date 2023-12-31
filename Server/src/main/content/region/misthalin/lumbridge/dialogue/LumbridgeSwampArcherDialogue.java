package content.region.misthalin.lumbridge.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Lumbridge Swamp Archer dialogue plugin.
 */
@Initializable
public final class LumbridgeSwampArcherDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code LumbridgeSwampArcher} {@code Object}.
	 */
	public LumbridgeSwampArcherDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code LumbridgeSwampArcher} {@code Object}.
	 * @param player the player.
	 */
	public LumbridgeSwampArcherDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LumbridgeSwampArcherDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Why are you guys hanging around here?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "(ahem)...'Guys'?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Uh... yeah, sorry about that. Why are you all standing", "around out here?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, that's really none of your business.");
			stage = 3;
			break;
		case 3:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 649 };
	}
}
