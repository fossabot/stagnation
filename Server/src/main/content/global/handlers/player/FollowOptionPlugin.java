package content.global.handlers.player;

import core.game.interaction.DestinationFlag;
import core.game.interaction.MovementPulse;
import core.game.interaction.Option;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.PulseType;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Represents the plugin used to start following a node.
 */
@Initializable
public final class FollowOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Option._P_FOLLOW.setHandler(this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final Player target = ((Player) node);
		player.getPulseManager().run(new MovementPulse(player, target, DestinationFlag.FOLLOW_ENTITY) {
			@Override
			public boolean pulse() {
				player.face(target);
				return false;
			}

			@Override
			public void stop() {
				super.stop();
				mover.face(null);
			}
		}, PulseType.STANDARD);
		return true;
	}
}
