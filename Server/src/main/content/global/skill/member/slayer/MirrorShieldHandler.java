package content.global.skill.member.slayer;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.MeleeSwingHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;

/**
 * The swing handler for an npc that requires a mirror shield with its victim.
 */
public final class MirrorShieldHandler extends MeleeSwingHandler {

	/**
	 * The singleton of this handler.
	 */
	public static final MirrorShieldHandler SINGLETON = new MirrorShieldHandler();

	/**
	 * The skills to drain.
	 */
	private static final int[] SKILLS = new int[] { Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE, Skills.RANGE };

	@Override
	public void impact(Entity entity, Entity victim, BattleState state) {
		if (victim instanceof Player) {
			final Player player = (Player) victim;
			if (!hasShield(player)) {
				state.setEstimatedHit(11);
				for (int skill : SKILLS) {
					int drain = (int) (player.getSkills().getStaticLevel(skill) * 0.25);
					player.getSkills().updateLevel(skill, -drain, player.getSkills().getStaticLevel(skill) - drain);
				}
			}
		}
		super.impact(entity, victim, state);
	}

	/**
	 * Checks the impact of a victim with or without a shield.
	 * @param state the state.
	 */
	public void checkImpact(final BattleState state) {
		if (state.getAttacker() instanceof Player) {
			final Player player = (Player) state.getAttacker();
			if (!hasShield(player)) {
				state.setEstimatedHit(0);
				if (state.getSecondaryHit() > 0) {
					state.setSecondaryHit(0);
				}
			}
		}
	}

	/**
	 * Checks if the player has the mirror shield.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private static boolean hasShield(final Player player) {
		return SlayerEquipmentFlags.hasMirrorShield(player);
	}

}
