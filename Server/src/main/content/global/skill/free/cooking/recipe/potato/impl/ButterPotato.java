package content.global.skill.free.cooking.recipe.potato.impl;

import content.global.skill.free.cooking.recipe.Recipe;
import core.game.interaction.NodeUsageEvent;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.node.item.Item;

/**
 * Represents the butter potato recipe. This recipe consists of adding a pat of
 * butter to a potato.

 */
public class ButterPotato extends Recipe {

	/**
	 * Represents the baked potato.
	 */
	private static final Item BAKED_POTATO = new Item(6701);

	/**
	 * Represents the potato with butter.
	 */
	private static final Item POTATO_WITH_BUTTER = new Item(6703);

	/**
	 * Represents the pat of butter.
	 */
	private static final Item PAT_OF_BUTTER = new Item(6697);

	@Override
	public void mix(final Player player, final NodeUsageEvent event) {
		if (player.getSkills().getLevel(Skills.COOKING) < 39) {
			player.getDialogueInterpreter().sendDialogue("You need a Cooking level of at least " + 39 + " in order to do this.");
			return;
		}
		super.singleMix(player, event);
		player.getSkills().addExperience(Skills.COOKING, 40.5, true);
	}

	@Override
	public Item getBase() {
		return BAKED_POTATO;
	}

	@Override
	public Item getProduct() {
		return POTATO_WITH_BUTTER;
	}

	@Override
	public Item[] getIngredients() {
		return new Item[] { PAT_OF_BUTTER };
	}

	@Override
	public Item[] getParts() {
		return new Item[] {};
	}

	@Override
	public String getMixMessage(NodeUsageEvent event) {
		return "You add a pat of butter to the potato.";
	}

	@Override
	public boolean isSingular() {
		return true;
	}

}
