package content.region.kandarin.seers.handlers

import core.api.MapArea
import core.game.node.entity.Entity
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.node.entity.player.link.prayer.PrayerType
import core.game.world.map.zone.ZoneBorders

class SeersCourthouseZone : MapArea {
    override fun defineAreaBorders(): Array<ZoneBorders> {
        return arrayOf(ZoneBorders(2735,3471,2736,3471))
    }

    override fun areaEnter(entity: Entity) {
        if(entity is Player && !entity.isArtificial){
            if(!entity.achievementDiaryManager.hasCompletedTask(DiaryType.SEERS_VILLAGE,2,3) && entity.prayer.active.contains(PrayerType.PIETY)){
                entity.achievementDiaryManager.finishTask(entity,DiaryType.SEERS_VILLAGE,2,3)
            }
        }
    }
}