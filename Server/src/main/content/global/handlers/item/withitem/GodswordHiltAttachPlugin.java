package content.global.handlers.item.withitem;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.Initializable;
import core.plugin.Plugin;
import core.tools.StringUtils;

/**
 * Handles the attaching of a hilt on the godsword blade.
 */
@Initializable
public final class GodswordHiltAttachPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code GodswordHiltAttachPlugin} {@code Object}.
	 */
	public GodswordHiltAttachPlugin() {
		super(11702, 11704, 11706, 11708);
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		Item item = event.getUsedItem();
		if (item == null)
			return false;
		Item baseItem = event.getBaseItem();
		Player player = event.getPlayer();
		if (!player.getInventory().containsItem(item) || !player.getInventory().containsItem(baseItem)) {
			return false;
		}
		if (player.getInventory().replace(null, item.getSlot(), false) != item || player.getInventory().replace(null, baseItem.getSlot(), false) != baseItem) {
			player.getInventory().update();
			return false;
		}
		item = new Item(item.getId() - 8);
		player.getInventory().add(item);
		String name = item.getDefinition().getName();
		player.getPacketDispatch().sendMessage("You attach the hilt to the blade and make a" + (StringUtils.isPlusN(name) ? "n " : " ") + name + ".");
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(11690, ITEM_TYPE, this);
		return this;
	}

}
