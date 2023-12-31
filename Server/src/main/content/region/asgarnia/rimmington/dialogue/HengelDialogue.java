package content.region.asgarnia.rimmington.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.world.map.RegionManager;
import core.plugin.Initializable;

/**
 * Represents the Hengel Dialogue plugin.
 */
@Initializable
public final class HengelDialogue extends DialoguePlugin {

	/**
	 * 
	 * Constructs a new @{Code HengelDialoguePlugin} object.
	 */
	public HengelDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AjjatDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public HengelDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HengelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		player("Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("What are you doing here?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "I'm just wandering around.", "I was hoping you'd give me some free stuff.", "I've come to kill you.");
			stage = 2;
			break;
		case 2:
			if (buttonId == 1) {
				player("I'm just wondering around.");
				stage = 3;
			} else if (buttonId == 2) {
				player("I was hoping you'd give me some free stuff.");
				stage = 7;
			} else if (buttonId == 3) {
				player("I've come to kill you.");
				stage = 9;
			} 
			break;
		case 3:
			npc("You do realise you're wandering around in my house?");
			stage++;
			break;
		case 4:
			player("Yep.");
			stage++;
			break;
		case 5:
			npc("Well please get out!");
			stage++;
			break;
		case 6:
			player("Sheesh, keep your wig on!");
			stage = 605;
			break;
		case 7:
			npc("No, I jolly well wouldn't!","Get out of my house");
			stage++;
			break;
		case 8:
			player("Meanie!");
			stage = 605;
			break;
		case 9:
			npc.sendChat("Aaaaarrgh!");
			for (NPC npc1 : RegionManager.getLocalNpcs(player)) {
				if (npc1.getName().equalsIgnoreCase("anja")) {
					npc1.sendChat("Eeeek!");
					break;
				}
			}
			end();
			break;
		case 605:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2683 };
	}
}
