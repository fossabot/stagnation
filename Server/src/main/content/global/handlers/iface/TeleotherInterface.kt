package content.global.handlers.iface

import config.Components
import core.game.interaction.InterfaceListener
import core.game.node.entity.player.link.TeleportManager.TeleportType
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics

class TeleotherInterface : InterfaceListener {
    val IFACE = Components.TELEPORT_OTHER_326

    override fun defineInterfaceListeners() {
        on(IFACE){player, _, _, button, _, _ ->
            if(button == 5){
                player.lock(2)
                if (player.teleporter.send(player.getAttribute("t-o_location", player.location), TeleportType.TELE_OTHER)) {
                    player.visualize(Animation.create(1816), Graphics.create(342))
                }
            }
            player.interfaceManager.close()
        }
    }
}