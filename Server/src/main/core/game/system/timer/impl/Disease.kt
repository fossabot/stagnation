package core.game.system.timer.impl

import core.api.hasTimerActive
import core.api.removeTimer
import core.api.sendMessage
import core.game.node.entity.Entity
import core.game.node.entity.combat.ImpactHandler
import core.game.node.entity.player.Player
import core.game.system.timer.PersistTimer
import core.game.system.timer.RSTimer
import core.game.system.timer.TimerFlag
import core.tools.RandomFunction
import org.json.simple.JSONObject

class Disease : PersistTimer (30, "disease", flags = arrayOf(TimerFlag.ClearOnDeath)) {
    var hitsLeft = 25

    override fun save (root: JSONObject, entity: Entity) {
        root["hitsLeft"] = hitsLeft.toString()
    }

    override fun parse (root: JSONObject, entity: Entity) {
        hitsLeft = root["hitsLeft"].toString().toInt()
    }

    override fun onRegister (entity: Entity) {
        if (hasTimerActive<Disease>(entity))
            removeTimer(entity, this)
        else if (entity is Player)
            sendMessage(entity, "You have become diseased.")
    }

    override fun run (entity: Entity) : Boolean {
        var damage = RandomFunction.random(1, 5)
        entity.impactHandler.manualHit(entity,damage,ImpactHandler.HitsplatType.DISEASE)
        var skillId = RandomFunction.random(24)
        if(skillId == 3) skillId--
        entity.skills.updateLevel(skillId,-damage,0)
        if (--hitsLeft == 0 && entity is Player)
            sendMessage(entity, "The disease has wore off.")
        return hitsLeft > 0
    }

    override fun getTimer (vararg args: Any) : RSTimer {
        val inst = Disease()
        inst.hitsLeft = args.getOrNull(0) as? Int ?: 25
        return inst
    }
}
