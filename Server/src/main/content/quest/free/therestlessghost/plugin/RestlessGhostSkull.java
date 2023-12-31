package content.quest.free.therestlessghost.plugin;

import config.Items;
import content.quest.free.therestlessghost.RestlessGhost;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.item.Item;
import core.game.node.scenery.Scenery;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used to add the skull to the cofin.
 */
@Initializable
public final class RestlessGhostSkull extends UseWithHandler {

	/**
	 * Constructs a new {@code RestlessGhostSkull} {@code Object}.
	 */
	public RestlessGhostSkull() {
		super(Items.SKULL_964);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(2145, OBJECT_TYPE, this);
		addHandler(15052, OBJECT_TYPE, this);
		addHandler(15061, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Scenery object = (Scenery) event.getUsedWith();
		if (object.getId() == 2145) {
			event.getPlayer().getDialogueInterpreter().sendDialogue("Maybe I should open it first.");
			return true;
		}
		if (event.getPlayer().getInventory().remove(new Item(Items.SKULL_964, 1))) {
			event.getPlayer().getPacketDispatch().sendMessage("You put the skull in the coffin.");
			event.getPlayer().getQuestRepository().getQuest(RestlessGhost.NAME).finish(event.getPlayer());
		}
		return true;
	}

}
