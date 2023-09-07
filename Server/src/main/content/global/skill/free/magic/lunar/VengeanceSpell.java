package content.global.skill.free.magic.lunar;

import config.Sounds;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.spell.MagicSpell;
import core.game.node.entity.combat.spell.Runes;
import core.game.node.entity.combat.spell.SpellType;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.SpellBookManager.SpellBook;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.Initializable;
import core.plugin.Plugin;

import static core.api.ContentAPIKt.playGlobalAudio;

/**
 * Handles the vengeance (other) spell.
 */
@Initializable
public final class VengeanceSpell extends MagicSpell {

	/**
	 * Constructs a new {@code VengeanceSpell} {@code Object}.
	 */
	public VengeanceSpell() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code VengeanceSpell} {@code Object}.
	 * @param level The level required.
	 * @param experience The experience gained from casting this spell.
	 * @param anim The cast animation.
	 * @param graphic The graphics.
	 * @param runes The runes required.
	 */
	public VengeanceSpell(int level, double experience, Animation anim, Graphics graphic, Item... runes) {
		super(SpellBook.LUNAR, level, experience, anim, graphic, null, runes);
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		// Vengeance other
		SpellBook.LUNAR.register(19, new VengeanceSpell(93, 78, Animation.create(4411), new Graphics(725, 96), Runes.ASTRAL_RUNE.getItem(3), Runes.DEATH_RUNE.getItem(2), Runes.EARTH_RUNE.getItem(10)));
		// Vengeance
		SpellBook.LUNAR.register(14, new VengeanceSpell(94, 112, Animation.create(4410), new Graphics(726, 96), Runes.ASTRAL_RUNE.getItem(4), Runes.DEATH_RUNE.getItem(2), Runes.EARTH_RUNE.getItem(10)));
		return this;
	}

	@Override
	public void visualize(Entity entity, Node target) {
		entity.animate(animation);
		((Entity) target).graphics(graphic);
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		int ticks = GameWorld.getTicks();
		boolean vengOther = spellId == 19;
		if (entity.getAttribute("vengeance_delay", -1) > ticks) {
			((Player) entity).getPacketDispatch().sendMessage("You can only cast vengeance spells once every 30 seconds.");
			return false;
		}
		if (vengOther && (target == null || !(target instanceof Player))) {
			return false;
		}
		Player p = (Player) (vengOther ? target : entity);
		if (vengOther) {
			if (!p.getSettings().isAcceptAid()) {
				((Player) entity).getPacketDispatch().sendMessage("The player is not accepting any aid.");
				return false;
			}
		}
		if (!meetsRequirements(entity, true, true)) {
			return false;
		}
		visualize(entity, p);
		entity.setAttribute("vengeance_delay", ticks + 50);
		p.setAttribute("vengeance", true);
		playGlobalAudio(p.getLocation(), vengOther ? Sounds.LUNAR_VENGENCE_OTHER_2908 : Sounds.LUNAR_VENGENCE_2907);
		return true;
	}

}
