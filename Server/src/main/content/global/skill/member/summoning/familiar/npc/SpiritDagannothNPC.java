package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;

/**
 * Represents the Spirit Dagannoth familiar.
 */
@Initializable
public class SpiritDagannothNPC extends Familiar {

	/**
	 * Constructs a new {@code SpiritDagannothNPC} {@code Object}.
	 */
	public SpiritDagannothNPC() {
		this(null, 6804);
	}

	/**
	 * Constructs a new {@code SpiritDagannothNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public SpiritDagannothNPC(Player owner, int id) {
		super(owner, id, 5700, 12017, 6, WeaponInterface.STYLE_CONTROLLED);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new SpiritDagannothNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6804, 6805 };
	}

}
