package content.global.handlers.iface;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.game.world.repository.Repository;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the interface used to logout of the game.
 */
@Initializable
public final class LogoutInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(182, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		if (!player.getZoneMonitor().canLogout()) {
			return true;
		}
		if (player.inCombat()) {
			player.getPacketDispatch().sendMessage("You can't log out until 10 seconds after the end of combat.");
			return true;
		}
                if (player.isTeleporting()) {
                        player.sendMessage("Please finish your teleport before logging out.");
                        return true;
                }
                Repository.getDisconnectionQueue().add(player);
		return true;
	}
}
