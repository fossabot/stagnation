package content.region.misthalin.lumbridge.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.plugin.Initializable;

/**
 * Represents the Donie dialogue plugin.
 */
@Initializable
public final class DonieDialogue extends DialoguePlugin {

	public DonieDialogue() {}

	public DonieDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DonieDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there, can I help you?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Where am I?", "How are you today?", "Your shoe lace is untied.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where am I?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How are you today?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Your shoe lace is untied.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This is the town of Lumbridge my friend.");
			stage = 11;
			break;
		case 11:
			interpreter.sendOptions("Select an Option", "Where am I?", "How are you today?", "Your shoe lace is untied.");
			stage = 1;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Aye, not too bad thank you. Lovely weather in", "" + GameWorld.getSettings().getName() + " this fine day.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Weather?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes weather, you know.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The state or condition of the atmosphere at a time and", "place, with respect to variables such as temperature,", "moisture, wind velocity, and barometric pressure.");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Not just a pretty face eh? Ha ha ha.");
			stage = 26;
			break;
		case 26:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No it's not!");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No you're right. I have nothing to back that up.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Fool! Leave me alone!");
			stage = 33;
			break;
		case 33:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2238 };
	}
}
