package content.global.skill.member.slayer;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatSwingHandler;
import core.game.node.entity.combat.MeleeSwingHandler;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;
import core.tools.RandomFunction;

import static core.api.ContentAPIKt.getPathableRandomLocalCoordinate;

/**
 * Handles the abyssal npc.
 */
@Initializable
public class AbyssalDemonNPC extends AbstractNPC {

	/**
	 * The melee swing handler.
	 */
	private static final MeleeSwingHandler SWING_HANDLER = new MeleeSwingHandler() {

		@Override
		public int swing(Entity entity, Entity victim, BattleState state) {
			if (victim instanceof Player && RandomFunction.random(8) <= 2) {
				boolean npc = RandomFunction.random(100) <= 50;
				Entity source = npc ? victim : entity;
				Entity teleported = npc ? entity : victim;
				Location loc = getPathableRandomLocalCoordinate(teleported, 1, source.getLocation(), 3);
				teleported.graphics(Graphics.create(409));
				teleported.teleport(loc, 1);
			}
			return super.swing(entity, victim, state);
		}
	};

	/**
	 * Constructs a new {@Code AbyssalNPC} {@Code Object}
	 */
	public AbyssalDemonNPC() {
		super(-1, null);
	}

	/**
	 * Constructs a new {@Code AbyssalNPC} {@Code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public AbyssalDemonNPC(int id, Location location) {
		super(id, location);
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return SWING_HANDLER;
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new AbyssalDemonNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return Tasks.ABYSSAL_DEMONS.getNpcs();
	}

}
