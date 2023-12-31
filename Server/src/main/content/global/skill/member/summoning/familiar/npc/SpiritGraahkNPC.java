package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.SkillBonus;
import core.game.node.entity.skill.Skills;
import core.plugin.Initializable;

/**
 * Represents the Spirit Graahk familiar.
 */
@Initializable
public class SpiritGraahkNPC extends Familiar {

	/**
	 * Constructs a new {@code SpiritGraahkNPC} {@code Object}.
	 */
	public SpiritGraahkNPC() {
		this(null, 7363);
	}

	/**
	 * Constructs a new {@code SpiritGraahkNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public SpiritGraahkNPC(Player owner, int id) {
		super(owner, id, 4900, 12810, 3, WeaponInterface.STYLE_AGGRESSIVE);
		boosts.add(new SkillBonus(Skills.HUNTER, 5));		
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new SpiritGraahkNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (!super.isOwnerAttackable()) {
			return false;
		}
		call();
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7363, 7364 };
	}

}
