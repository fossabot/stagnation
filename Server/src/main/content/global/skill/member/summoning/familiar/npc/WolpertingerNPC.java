package content.global.skill.member.summoning.familiar.npc;

import content.global.skill.member.summoning.familiar.Familiar;
import content.global.skill.member.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.SkillBonus;
import core.game.node.entity.skill.Skills;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;

/**
 * Represents the Wolpertinger familiar.
 */
@Initializable
public class WolpertingerNPC extends Familiar {

	/**
	 * Constructs a new {@code WolpertingerNPC} {@code Object}.
	 */
	public WolpertingerNPC() {
		this(null, 6869);
	}

	/**
	 * Constructs a new {@code WolpertingerNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The NPC id.
	 */
	public WolpertingerNPC(Player owner, int id) {
		super(owner, id, 6200, 12089, 1, WeaponInterface.STYLE_CAST);
		boosts.add(new SkillBonus(Skills.HUNTER, 5));			
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new WolpertingerNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		owner.getSkills().updateLevel(Skills.MAGIC, 7, (owner.getSkills().getStaticLevel(Skills.MAGIC) + 7));
		visualize(Animation.create(8267), Graphics.create(1464));
		return true;
	}

	@Override
	public void visualizeSpecialMove() {
		owner.visualize(Animation.create(7660), Graphics.create(1306));
	}

	@Override
	public int[] getIds() {
		return new int[] { 6869, 6870 };
	}

}
