package content.region.asgarnia.taverley.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Direction;
import core.game.world.map.Location;
import core.plugin.Initializable;

/**
 * Represents the Teegid dialogue plugin.
 */
@Initializable
public class TeegidDialogue extends DialoguePlugin {

	public TeegidDialogue() {

	}

	public TeegidDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 1213 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yeah. What is it to you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nice day for it.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Suppose it is.");
			stage = 3;
			break;
		case 3:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TeegidDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc.setDirection(Direction.SOUTH);
		npc.faceLocation(Location.create(2923, 3418, 0));
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So, you're doing laundry, eh?");
		stage = 0;
		npc.faceLocation(Location.create(2923, 3418, 0));
		return true;
	}
}
