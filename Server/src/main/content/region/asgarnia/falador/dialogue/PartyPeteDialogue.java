package content.region.asgarnia.falador.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;

/**
 * Represents the Party Pete dialogue plugin.
 */
@Initializable
public class PartyPeteDialogue extends DialoguePlugin {

	/**
	 * The npc ids pertained to this dialogue.
	 */
	private int[] NPC_IDS = { 659 };

	/**
	 * Constructs a new {@code partyPeteDialoguePlugin}.
	 */
	public PartyPeteDialogue() {

	}

	/**
	 * Constructs a new {@code partyPeteDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public PartyPeteDialogue(Player player) {
		super(player);
	}

	/**
	 * interpreter.sendDialogues(player, FacialExpression.NORMAL, "");
	 * interpreter.sendDialogues(npc, FacialExpression.NORMAL, "");
	 */

	@Override
	public int[] getIds() {
		return NPC_IDS;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi! I'm, Party Pete. Welcome to the Party Room!");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "So, what's this room for?", "What's the big lever over there for?", "What's the gold chest for?", "I wanna party!", "Nothing.");
			stage = 2;
			break;
		case 2:
			switch (interfaceId) {
			case 234:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So, what's this room for?");
					stage = 50;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's the big lever over there for?");
					stage = 55;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's the gold chest for?");
					stage = 61;
					break;
				case 4:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I wanna party!");
					stage = 65;
					break;
				case 5:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Nothing.");
					stage = 100;
					break;
				}
				break;
			}
			break;
		case 50:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This room is for partying the night away!");
			stage = 51;
			break;
		case 51:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How do you have a party in " + GameWorld.getSettings().getName() + "?");
			stage = 52;
			break;
		case 52:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Get a few mates round, get the beers in and have fun!");
			stage = 53;
			break;
		case 53:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Some players organise parties so kee an eye open!");
			stage = 54;
			break;
		case 54:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Woop! Thanks Pete!");
			stage = 100;
			break;
		case 55:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Simple. With the lever you can do some fun stuff.");
			stage = 56;
			break;
		case 56:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What kind of stuff?");
			stage = 57;
			break;
		case 57:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A balloon drop costs 1000 gold. For this, you get 200", "balloons dropped across the whole of the party room. You", "canthen have fun popping the balloons!");
			stage = 58;
			break;
		case 58:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Any items in the Party Drop Chest will be put into balloons", "as soon as you pull the lever.");
			stage = 59;
			break;
		case 59:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When the balloons are released, you can burst them to", "get at the items!");
			stage = 60;
			break;
		case 60:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "For 500 gold, you can summon the Party Room Knights,", "who will dance for your delight. Their singing isn't a", "delight, though.");
			stage = 100;
			break;
		case 61:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Any items in the chest will be dropped inside the ballons", "when you pull the lever.");
			stage = 62;
			break;
		case 62:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Cool! Sounds like a fun way to do a drop party.");
			stage = 63;
			break;
		case 63:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Exactly!");
			stage = 64;
			break;
		case 64:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A word of warning, though. Any items that you put into", "the chest can't be taken out again, and it costs 1000 gold", "pieces for each drop party.");
			stage = 100;
			break;
		case 65:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I've won the Dance Trophy at the Kandarin Ball three", "years in a trot!");
			stage = 66;
			break;
		case 66:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Show me your moves Pete!");
			stage = 67;
			break;
		case 67:
			npc.animate(new Animation(784));
			end();
			break;
		case 100:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new PartyPeteDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi!");
		return true;
	}

}
