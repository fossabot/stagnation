package content.region.misthalin.draynor.handlers;

import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.Initializable;
import core.plugin.Plugin;

@Initializable
public class DraynorMarketZone extends MapZone implements Plugin<Object> {

    public DraynorMarketZone() {
        super("draynor-market", true);
    }

    @Override
    public void configure() {
        register(new ZoneBorders(3074, 3245, 3086, 3255));
    }

    @Override
    public boolean enter(Entity entity) {
        // Visit the Draynor Village market
        if (entity.isPlayer()) {
            Player player = entity.asPlayer();
        }
        return super.enter(entity);
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ZoneBuilder.configure(this);
        return this;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
