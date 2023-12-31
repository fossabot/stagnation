package content.region.desert.alkharid.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Ollie The Camel dialogue plugin.
 */
@Initializable
public class OllieTheCamelDialogue extends DialoguePlugin {

	public OllieTheCamelDialogue() {

	}

	public OllieTheCamelDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 2811 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			player.getPacketDispatch().sendMessage("The camel tries to stamp on your foot, but you pull it back quickly.");
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new OllieTheCamelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I wonder if that camel has fleas...");
		stage = 0;
		return true;
	}
}
