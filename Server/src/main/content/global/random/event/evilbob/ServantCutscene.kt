package content.global.random.event.evilbob

import core.api.closeInterface
import core.api.openInterface
import core.game.activity.Cutscene
import core.game.node.entity.player.Player

/**
 * Represents a CUTSCENES in "Evil bob" random event.
 */
class ServantCutsceneN(player: Player) : Cutscene(player) {

    override fun setup() {
        setExit(player.location.transform(0, 0, 0))
        loadRegion(13642)
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                fadeToBlack()
                timedUpdate(5)
            }
            1 -> {
                teleport(player, 29, 41)
                moveCamera(30, 43) // +7 from statue
                openInterface(player, 186)
                timedUpdate(2)
            }
            2 -> {
                timedUpdate(2)
                rotateCamera(30, 51, 300, 100) // the statue loc
                fadeFromBlack()
            }
            3 -> {
                timedUpdate(9)
                moveCamera(30, 46, 300, 2) // +4 from statue
            }
            4 -> {
                end()
                closeInterface(player)
            }
        }
    }
}

class ServantCutsceneS(player: Player) : Cutscene(player) {

    override fun setup() {
        setExit(player.location.transform(0, 0, 0))
        loadRegion(13642)
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                fadeToBlack()
                timedUpdate(5)
            }
            1 -> {
                teleport(player, 29, 41)
                moveCamera(29, 38) // +7 from statue
                openInterface(player, 186)
                timedUpdate(2)
            }
            2 -> {
                timedUpdate(2)
                rotateCamera(29, 30, 300, 100) // the statue loc
                fadeFromBlack()
            }
            3 -> {
                timedUpdate(9)
                moveCamera(29, 35, 300, 2) // +4 from statue
            }
            4 -> {
                end()
                closeInterface(player)
            }
        }
    }
}

class ServantCutsceneE(player: Player) : Cutscene(player) {

    override fun setup() {
        setExit(player.location.transform(0, 0, 0))
        loadRegion(13642)
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                fadeToBlack()
                timedUpdate(5)
            }
            1 -> {
                teleport(player, 29, 41)
                moveCamera(35, 41) // +7 from statue
                openInterface(player, 186)
                timedUpdate(2)
            }
            2 -> {
                timedUpdate(2)
                rotateCamera(43, 41, 300, 100) // the statue loc
                fadeFromBlack()
            }
            3 -> {
                timedUpdate(9)
                moveCamera(38, 41, 300, 2) // +4 from statue
            }
            4 -> {
                end()
                closeInterface(player)
            }
        }
    }
}

class ServantCutsceneW(player: Player) : Cutscene(player) {

    override fun setup() {
        setExit(player.location.transform(0, 0, 0))
        loadRegion(13642)
    }

    override fun runStage(stage: Int) {
        when (stage) {
            0 -> {
                fadeToBlack()
                timedUpdate(5)
            }
            1 -> {
                teleport(player, 29, 41)
                moveCamera(25, 40) // +7 from statue
                openInterface(player, 186)
                timedUpdate(2)
            }
            2 -> {
                timedUpdate(2)
                rotateCamera(18, 40, 300, 100) // the statue loc
                fadeFromBlack()
            }
            3 -> {
                timedUpdate(9)
                moveCamera(22, 40, 300, 2) // +4 from statue
            }
            4 -> {
                end()
                closeInterface(player)
            }

        }
    }
}