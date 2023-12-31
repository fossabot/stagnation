package content.quest.member.thelosttribe.handlers

import config.Components
import core.api.closeInterface
import core.api.openInterface
import core.api.sendMessage
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location

/**
 * Small object for the goblin follow options.
 */
object GoblinFollower {
    fun sendToMines(player: Player){
        travel(player,Location.create(3319, 9616, 0))
        sendMessage(player, "Kazgar shows you the way through the tunnels.")
    }
    fun sendToLumbridge(player: Player){
        travel(player,Location.create(3232, 9610, 0))
        sendMessage(player, "Mistag shows you the way through the tunnels.")
    }

    private fun travel(player: Player,location: Location){
        GameWorld.Pulser.submit(object: Pulse(){
            var counter = 0
            override fun pulse(): Boolean {
                when(counter++){
                    0 -> player.lock().also {
                        openInterface(player,Components.FADE_TO_BLACK_120)
                    }
                    3 -> player.properties.teleportLocation = location
                    4 -> {
                        closeInterface(player)
                        openInterface(player,Components.FADE_FROM_BLACK_170)
                    }
                    6 -> player.unlock().also {
                        closeInterface(player)
                        return true
                    }
                }
                return false
            }
        })
    }
}