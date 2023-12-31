package content.region.kandarin.seers.handlers

import core.api.removeAttribute
import core.api.setAttribute
import core.game.node.entity.Entity
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.world.map.Location
import core.game.world.map.zone.MapZone
import core.game.world.map.zone.ZoneBorders
import core.game.world.map.zone.ZoneBuilder
import core.plugin.Initializable
import core.plugin.Plugin

@Initializable
class MysteriousStatueZone : MapZone("mysterious-statue", true), Plugin<Any?> {

    val northWest = Location.create(2739, 3492, 0)
    val northEast = Location.create(2742, 3492, 0)
    val southEast = Location.create(2742, 3489, 0)
    val southWest = Location.create(2739, 3489, 0)


    override fun newInstance(arg: Any?): MysteriousStatueZone {
        ZoneBuilder.configure(this)
        return this
    }

    override fun configure() {
        super.register(ZoneBorders(2739,3492,2742,3489))
    }

    override fun enter(e: Entity?): Boolean {
        return super.enter(e)
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun locationUpdate(e: Entity?, last: Location?) {
        if (e is Player && !e.isArtificial) {
            val player = e.asPlayer()
            if(!player.achievementDiaryManager.hasCompletedTask(DiaryType.SEERS_VILLAGE,0,1 ) && player.getAttribute("seersStatueProgress",-1) !in 0..3 ){
                setAttribute(player, "seersStatueProgress",0)
                println("statue started")
            }else{
                when(player.location){
                    northWest ->{
                        if(player.getAttribute("seersStatueProgress",-1) == 0){
                            setAttribute(player, "seersStatueProgress",1)
                            println("location 1 of 4 finished")
                            println(player.getAttribute("seersStatueProgress",-1))
                        }else if(player.getAttribute("seersStatueProgress",-1) in 2..3){
                            setAttribute(player, "seersStatueProgress",0)
                            println("location progress reset")
                        }
                    }
                    northEast ->{
                        if(player.getAttribute("seersStatueProgress",0) == 1){
                            setAttribute(player, "seersStatueProgress",2)
                            println("location 2 of 4 finished")
                        }else if(player.getAttribute("seersStatueProgress",0) != 1){
                            setAttribute(player, "seersStatueProgress",0)
                            println("location progress reset")
                        }
                    }
                    southEast ->{
                        if(player.getAttribute("seersStatueProgress",0) == 2){
                            setAttribute(player, "seersStatueProgress",3)
                            println("location 3 of 4 finished")
                        }else if(player.getAttribute("seersStatueProgress",0) != 2){
                            setAttribute(player, "seersStatueProgress",0)
                            println("location progress reset")
                        }
                    }
                    southWest ->{
                        if(player.getAttribute("seersStatueProgress",0) == 3){
                            setAttribute(player, "seersStatueProgress",4)
                            println("location 4 of 4 finished")
                        }else if(player.getAttribute("seersStatueProgress",0) != 3){
                            setAttribute(player, "seersStatueProgress",0)
                            println("location progress reset")
                        }
                    }
                }
            }
            if(player.getAttribute("seersStatueProgress",0) == 4){
                player.achievementDiaryManager.finishTask(player,DiaryType.SEERS_VILLAGE,0,1)
                setAttribute(player, "seersStatueComplete",true)
                removeAttribute(player, "seersStatueProgress")
            }
    }
        super.locationUpdate(e, last)
    }

    override fun leave(e: Entity?, logout: Boolean): Boolean {
        if(e is Player && !e.isArtificial){
            val player = e.asPlayer()
            if(!player.getAttribute("seersStatueComplete",false) && player.getAttribute("seersStatueProgress",-0) in 0..3){
                removeAttribute(player, "seersStatueProgress")
                super.leave(e, logout)
            }else return super.leave(e, logout)
        }
        return super.leave(e, logout)
    }

}