package content.region.desert.sophanem.handlers;

import core.cache.def.impl.SceneryDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.node.scenery.Scenery;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Initializable;
import core.plugin.Plugin;

/**
 * Handles the Sophanem wall shortcut interaction.
 */
@Initializable
public final class WallShortcutHandler extends OptionHandler {

    private static final Animation CLIMB_DOWN = Animation.create(2589);

    private static final Animation CRAWL_THROUGH = Animation.create(2590);

    private static final Animation CLIMB_UP = Animation.create(2591);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        SceneryDefinition.forId(6620).getHandlers().put("option:climb-through", this);
        return null;
    }

    @Override
    public boolean handle(final Player player, Node node, String option) {
        if (player.getSkills().getLevel(Skills.AGILITY) < 21) {
            player.getDialogueInterpreter().sendDialogue("You need an Agility level of at least 21 to do this.");
            return true;
        }
        player.lock(4);
        final Scenery o = (Scenery) node;
        if (o.getId() == 6620) {
            ForceMovement.run(player, Location.create(3320, 2796, 0), o.getLocation(), CLIMB_DOWN);
            GameWorld.getPulser().submit(new Pulse(1, player) {
                int count;

                @Override
                public boolean pulse() {
                    switch (++count) {
                        case 2:
                            player.animate(CRAWL_THROUGH);
                            player.getProperties().setTeleportLocation(Location.create(3324, 2796, 0));
                            break;
                        case 3:
                            ForceMovement.run(player, Location.create(3324, 2796, 0), Location.create(3324, 2796, 0), CLIMB_UP);
                            return true;
                    }
                    return false;
                }
            });
        }
        return true;
    }
}
