package content.region.karamja.npc;

import core.game.node.entity.npc.AbstractNPC;
import core.game.system.config.NPCConfigParser;
import core.game.world.map.Location;
import core.plugin.Initializable;

/**
 * Represents the tribesamn npc.
 */
@Initializable
public final class TribesmanNPC extends AbstractNPC {

	/**
	 * Represents the npc ids.
	 */
	private static final int[] IDS = new int[] { 191, 2496, 2497 };

	/**
	 * Constructs a new {@code TribesmanNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public TribesmanNPC(int id, Location location) {
		super(id, location, true);
		getDefinition().getHandlers().put(NPCConfigParser.POISONOUS, true);
	}

	/**
	 * Constructs a new {@code TribesmanNPC} {@code Object}.
	 */
	public TribesmanNPC() {
		super(0, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new TribesmanNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return IDS;
	}

}
