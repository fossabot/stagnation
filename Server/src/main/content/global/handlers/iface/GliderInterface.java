package content.global.handlers.iface;

import content.global.travel.glider.GliderPulse;
import content.global.travel.glider.Gliders;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

import static core.api.ContentAPIKt.submitWorldPulse;

/**
 * Represents the glider interface component.
 */
@Initializable
public final class GliderInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(138, this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		final Gliders glider = Gliders.forId(button);
		if (glider == null) {
			return true;
		}
		submitWorldPulse(new GliderPulse(1, player, glider));
		return true;
	}
}
