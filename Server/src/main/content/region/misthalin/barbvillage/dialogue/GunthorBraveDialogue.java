package content.region.misthalin.barbvillage.dialogue;

import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Gunthor Brave dialogue plugin.
 */
@Initializable
public final class GunthorBraveDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GunthorBraveDialogue} {@code Object}.
	 */
	public GunthorBraveDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GunthorBraveDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GunthorBraveDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GunthorBraveDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("You look funny.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		npc.getProperties().getCombatPulse().attack(player);
		end();
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 199 };
	}

}
