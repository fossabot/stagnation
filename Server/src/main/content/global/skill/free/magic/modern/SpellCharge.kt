package content.global.skill.free.magic.modern

import config.Sounds
import core.api.playAudio
import core.api.sendMessage
import core.game.node.entity.Entity
import core.game.node.entity.player.Player
import core.game.system.timer.PersistTimer

class SpellCharge : PersistTimer (700, "magic:spellcharge") {
    override fun run (entity: Entity) : Boolean {
        if (entity !is Player) return false
        sendMessage(entity, "Your magical charge fades away.")
        playAudio(entity, Sounds.CHARGE_GONE_1650)
        return false
    }
}