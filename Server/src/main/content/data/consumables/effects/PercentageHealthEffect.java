package content.data.consumables.effects;

import core.game.consumable.ConsumableEffect;
import core.game.node.entity.player.Player;

public class PercentageHealthEffect extends ConsumableEffect {

    private final double percentage;

    public PercentageHealthEffect(final int percentage) {
        this.percentage = percentage * 0.01;
    }

    @Override
    public void activate(Player p) {
        final HealingEffect effect = new HealingEffect(getHealthEffectValue(p));
        effect.activate(p);
    }

    @Override
    public int getHealthEffectValue(Player player) {
        return (int) (player.getSkills().getMaximumLifepoints() * percentage);
    }
}
