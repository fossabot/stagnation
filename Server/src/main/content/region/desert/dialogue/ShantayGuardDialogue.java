package content.region.desert.dialogue;

import content.global.skill.member.agility.AgilityHandler;
import core.game.dialogue.DialoguePlugin;
import core.game.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.path.Pathfinder;
import core.game.world.repository.Repository;
import core.plugin.Initializable;

/**
 * Represents the Shantay Guard dialogue plugin.
 */
@Initializable
public final class ShantayGuardDialogue extends DialoguePlugin {

	/**
	 * Represents a shantay pass item.
	 */
	private static final Item PASS = new Item(1854);

	/**
	 * Constructs a new {@code ShantayGuard} {@code Object}.
	 */
	public ShantayGuardDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ShantayGuard} {@code Object}.
	 * @param player the player.
	 */
	public ShantayGuardDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShantayGuardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		if (args[0] != null && args[0] instanceof NPC) {
			npc = ((NPC) args[0]);
		}
		if (args.length == 2) {
			interpreter.sendDialogues(838, null, "Can I see your Desert Pass please?");
			stage = 13;
			return true;
		}
		if (npc != null && npc.getId() != 837) {
			interpreter.sendDialogues(838, FacialExpression.HALF_GUILTY, "Hello there!");
			stage = 0;
		} else {
			interpreter.sendDialogues(838, null, "Go talk to Shantay. I'm on duty and I don't have time", "to talk to the likes of you!");
			stage = 100;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(838, FacialExpression.HALF_GUILTY, "What can I do for you?");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "I'd like to go into the desert please.", "Nothing thanks.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'd like to go into the desert please.");
				stage = 10;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(838, FacialExpression.HALF_GUILTY, "Of course!");
			stage = 11;
			break;
		case 11:
			if (!player.getInventory().containsItem(PASS)) {
				player.getDialogueInterpreter().sendDialogues(Repository.findNPC(838), null, "You need a Shantay pass to get through this gate. See", "Shantay, he will sell you one for a very reasonable", "price.");
				stage = 12;
				return true;
			}
			interpreter.sendDialogues(838, null, "Can I see your Desert Pass please?");
			stage = 13;
			break;
		case 12:
			end();
			break;
		case 13:
			if (!player.getInventory().containsItem(PASS)) {
				player("Sorry, I don't have one with me.");
				stage = 101;
				return true;
			}
			interpreter.sendItemMessage(PASS, "You hand over a Shantay Pass.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, null, "Sure, here you go!");
			stage = 15;
			break;
		case 15:
			if (player.getInventory().remove(PASS)) {
				final Location dest = player.getLocation().getY() < 3304 ? Location.create(3303, 3117, 0) : Location.create(3305, 3117, 0);
				Pathfinder.find(player, dest).walk(player);
				player.lock();
				GameWorld.getPulser().submit(new Pulse(1, player) {
					@Override
					public boolean pulse() {
						if (player.getLocation().equals(dest)) {
							player.unlock();
							handleShantayPass(player);
							return true;
						}
						return false;
					}
				});
			}
			end();
			break;
		case 100:
			interpreter.sendDialogue("The guard seems quite bad tempered, probably from having to wear", "heavy armour in this intense heat.");
			stage = 101;
			break;
		case 101:
			end();
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the shantay pass.
	 * @param player the player.
	 */
	public static void handleShantayPass(Player player) {
		AgilityHandler.walk(player, 0, player.getLocation(), player.getLocation().transform(0, player.getLocation().getY() > 3116 ? -2 : 2, 0), null, 0, null);
	}

	@Override
	public int[] getIds() {
		return new int[] { 837, 838 };
	}
}
