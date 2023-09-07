package content.global.skill.free.runecrafting.abyss

import config.Animations
import config.Items
import config.NPCs
import config.Vars
import content.data.skill.SkillingTool
import content.global.skill.free.runecrafting.Altar
import core.api.*
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.Node
import core.game.node.entity.impl.Animator
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.game.node.scenery.Scenery
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.update.flag.context.Animation
import core.game.world.update.flag.context.Graphics
import core.plugin.ClassScanner.definePlugin
import core.plugin.Initializable
import core.tools.Log
import core.tools.RandomFunction
import core.tools.colorize
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
import config.Scenery as Sceneries

/**
 * A plugin used to handle the abyss.
 */
@Initializable
class AbyssPlugin : InteractionListener {

    override fun defineListeners() {
        definePlugin(AbyssalNPC())
        definePlugin(DarkMageDialogue())
        definePlugin(ZamorakMageDialogue())

        on(NPCs.MAGE_OF_ZAMORAK_2259, IntType.NPC, "teleport"){ player, node ->
            teleport(player, node as NPC)
            return@on true
        }

        on(NPCs.DARK_MAGE_2262, IntType.NPC, "repair-pouches"){ player, node ->
            player.dialogueInterpreter.open(node.id, node, true)
            return@on true
        }

        on(IntType.SCENERY, "exit-through"){ player, node ->
            val altar = Altar.forObject(node as Scenery)
            altar?.enterRift(player)
            return@on true
        }

        on(Sceneries.PASSAGE_7154, IntType.SCENERY, "go-through",){ player, node ->
            player.properties.teleportLocation = innerRing(node)
            return@on true
        }

        on(Sceneries.ROCK_7158, IntType.SCENERY, "mine"){ player, node ->
            val tool: SkillingTool? = getTool(player, true)
            if (tool == null) {
                sendMessage(player, "You need a pickaxe in order to do that.")
                return@on true
            }
            return@on handleObstacle(
                node,
                player,
                Skills.MINING,
                MINE_PROGRESS,
                tool.animation,
                arrayOf(
                    "You attempt to mine your way through...",
                    "...and manage to break through the rock.",
                    "...but fail to break-up the rock."
                )
            )
        }

        on(Sceneries.TENDRILS_7161, IntType.SCENERY, "chop"){ player, node ->
            val tool: SkillingTool? = getTool(player, false)
            if (tool == null) {
                sendMessage(player, "You need an axe in order to do that.")
                return@on true
            }
            return@on handleObstacle(
                node,
                player,
                Skills.WOODCUTTING,
                CHOP_PROGRESS,
                tool.animation,
                arrayOf(
                    "You attempt to chop your way through...",
                    "...and manage to chop down the tendrils.",
                    "...but fail to cut through the tendrils."
                )
            )
        }

        on(Sceneries.BOIL_7165, IntType.SCENERY, "burn-down"){ player, node ->
            if (!inInventory(player, Items.TINDERBOX_590)) {
                sendMessage(player, "You don't have a tinderbox to burn it.")
                return@on true
            }

            return@on handleObstacle(
                node,
                player,
                Skills.FIREMAKING,
                BURN_PROGRESS,
                Animation(Animations.HUMAN_LIGHT_FIRE_WITH_TINDERBOX_733),
                arrayOf(
                    "You attempt to burn your way through...",
                    "...and manage to burn it down and get past.",
                    "...but fail to set it on fire."
                )
            )
        }

        on(Sceneries.EYES_7168, IntType.SCENERY, "distract"){ player, node ->
            val distractEmote = Animation(distractEmotes[RandomFunction.random(0,distractEmotes.size)])
            return@on handleObstacle(
                node,
                player,
                Skills.THIEVING,
                DISTRACT_PROGRESS,
                distractEmote,
                arrayOf(
                    "You use your thieving skills to misdirect the eyes...",
                    "...and sneak past while they're not looking.",
                    "...but fail to distract the eyes."
                )
            )
        }

        on(Sceneries.GAP_7164, IntType.SCENERY, "squeeze-through"){ player, node ->
            return@on handleObstacle(
                node,
                player,
                Skills.AGILITY,
                null,
                Animation(Animations.HUMAN_SQUEEZE_INTO_GAP_1331),
                arrayOf(
                    "You attempt to squeeze through the narrow gap...",
                    "...and you manage to crawl through.",
                    "...but fail to crawl through."
                )
            )
        }
    }

    /**
     * Animations used for the eye's obstacle.
     */

    private val distractEmotes = intArrayOf(
        Animations.HUMAN_SHAKE_HEAD_YES_855,
        Animations.HUMAN_SHAKE_HEAD_NO_856,
        Animations.HUMAN_THINK_857,
        Animations.HUMAN_BOW_858,
        Animations.HUMAN_ANGRY_859,
        Animations.HUMAN_CRY_860,
        Animations.HUMAN_LAUGH_861,
        Animations.HUMAN_CHEER_862,
        Animations.HUMAN_WAVE_863,
        Animations.HUMAN_BECKON_864,
        Animations.HUMAN_CLAP_865,
        Animations.HUMAN_DANCE_866,
        Animations.HUMAN_SHRUG_2113,
        Animations.HUMAN_JUMP_FOR_JOY_2109,
        Animations.HUMAN_YAWN_2111,
        Animations.HUMAN_JIG_2106,
        Animations.HUMAN_TWIRL_2107,
        Animations.HUMAN_HEAD_BANG_2108,
        0x558, // 1368
        Animations.HUMAN_PANIC_2105,
        Animations.HUMAN_BLOW_RASPBERRY_2110,
        Animations.HUMAN_SALUTE_2112,
        Animations.HUMAN_GOBLIN_BOW_2127, //0x84F
        Animations.HUMAN_GOBLIN_SALUTE_2128, //0x850
        Animations.HUMAN_GLASS_BOX_1131,
        Animations.HUMAN_CLIMB_ROPE_1130,
        Animations.HUMAN_LEAN_1129,
        Animations.HUMAN_GLASS_WALL_1128,
        Animations.HUMAN_ANGRY_STOMP_1745,
        Animations.HUMAN_ZOMBIE_WALK_3544,
        Animations.HUMAN_ZOMBIE_DANCE_3543,
        Animations.HUMAN_SCARED_2836
    )

    companion object {

        /**
         * Represents teleporting to the abyss,
         * including randomized location and rotating obstacles accordingly.
         */

        fun teleport(player: Player, npc: NPC) {

            var teleportLoc = AbyssLoc.randomLoc()
            while(!teleportLoc.isValid()) {
                teleportLoc = teleportLoc.attract()
            }

            player.lock(3)
            npc.visualize(Animation(Animations.MAGE_OF_ZAMORAK_TELEOTHER_1979), Graphics(config.Graphics.TELEOTHER_PURPLE_BEAMS_4))
            npc.sendChat("Veniens! Sallakar! Rinnesset!")
            player.skills.decrementPrayerPoints(100.0)
            player.skullManager.checkSkull(player)
            GameWorld.Pulser.submit(object : Pulse(2, player) {
                override fun pulse(): Boolean {
                    rotateObstacles(player,teleportLoc)
                    player.properties.teleportLocation = teleportLoc.toAbs()
                    npc.updateMasks.reset()
                    return true
                }
            })
        }

        /**
         * Represents getting the inner ring location corresponding to a node in the outer ring,
         * used to send a player to the inner ring when they pass an obstacle.
         */

        fun innerRing(node: Node): Location {
            val obstacleLoc = AbyssLoc.fromAbs(node.location)
            var loc = obstacleLoc.attract(5)
            while (!loc.isValid()) {
                loc = loc.attract()
            }
            return loc.toAbs()
        }


        /**
         * Represents rotating the abyssal obstacles for the player,
         * Used to make sure the player lands by the blocked obstacle.
         */

        fun rotateObstacles(player: Player, abyssLoc: AbyssLoc) {
            setVarbit(player, 625, abyssLoc.getSegment(), true)
        }

        /**
         * Handles attempts at passing abyssal obstacles to get from outer ring to inner ring.
         */

        const val MINE_PROGRESS = 12
        const val CHOP_PROGRESS = 14
        const val BURN_PROGRESS = 16
        const val DISTRACT_PROGRESS = 18
        fun handleObstacle(obstacle: Node, player: Player, skill: Int, varbitVal: Int?, animation: Animation, messages: Array<String>): Boolean {
            log(this::class.java, Log.FINE, "handled abyss ${obstacle.name}")
            player.lock()
            player.animate(animation)
            GameWorld.Pulser.submit(object : Pulse(1, player) {
                var count = 0
                override fun pulse(): Boolean {
                    when (count++) {
                        1 -> sendMessage(player, messages[0])
                        3 -> return if (RandomFunction.random(100) < getStatLevel(player,skill)
                        ) {
                            sendMessage(player, colorize("%G${messages[1]}"))
                            if(varbitVal != null) { setVarbit(player, Vars.VARBIT_SCENERY_ABYSS_OBSTACLES_625, varbitVal) }
                            false
                        } else {
                            sendMessage(player, colorize("%R${messages[2]}"))
                            player.unlock()
                            true
                        }
                        5 -> {
                            if(varbitVal != null) { setVarbit(player, Vars.VARBIT_SCENERY_ABYSS_OBSTACLES_625, varbitVal or 1) }
                        }
                        7 -> {
                            player.unlock()
                            player.properties.teleportLocation = innerRing(obstacle)
                            return true
                        }
                    }
                    return false
                }

                override fun stop() {
                    super.stop()
                    player.animate(Animation(-1, Animator.Priority.HIGH))
                }
            })
            return true
        }
    }
}

/**
 * Polar coordinates class for abyss.
 */

class AbyssLoc(val radius: Double, val angle: Double) {

    /**
     * Attract the location towards the center.
     */

    fun attract(steps: Int = 1): AbyssLoc {
        return AbyssLoc(radius-steps.toDouble(),angle)
    }

    /**
     * Get the segment of an abyssloc - its angle as an integer modulo 12, with south = 0 and positive = clockwise
     * this is used to determine which of the 12 evenly spaced obstacles around the outer ring the location is nearest to.
     */

    fun getSegment() : Int {
        val segments = 12
        val angleToCircle = angle * segments / ( 2 * Math.PI )
        val angleSegment = (angleToCircle + 0.5).toInt()
        // now 'normalize' the segment, so that 0 is the southernmost obstacle and 1 is clockwise from it
        val normalSegment = ( 9 - angleSegment ).mod(12)
        return normalSegment
    }

    /**
     * Transform back to absolute coordinates.
     */

    fun toAbs() : Location {
        val x = (radius * cos(angle)).toInt()
        val y = (radius * sin(angle)).toInt()
        return origin.transform(x,y,0)
    }

    /**
     * Check if location is valid.
     */

    fun isValid() : Boolean {
        val abs = toAbs()
        return (RegionManager.isTeleportPermitted(abs) && RegionManager.getObject(abs) == null)
    }
    companion object {

        /**
         * origin and outer radius values of the abyss itself
         */

        // origin: the exact center of the abyss, inside that spinny ball thing
        val origin = Location(3039,4832,0)
        // the outer ring is generally at radius 24-26; testing shows that a minimum of 25.1 guarantees that players don't end up on an obstacle or inner wall
        const val outerRadius = 25.1

        /**
         * turn an absolute location into an abyss location.
         */

        fun fromAbs(loc: Location) : AbyssLoc {
            val local = Location.getDelta(origin,loc)
            val radius = Math.sqrt((local.x * local.x + local.y * local.y).toDouble())
            val angle = Math.atan2(local.y.toDouble(),local.x.toDouble())
            return AbyssLoc(radius,angle)
        }

        /**
         * get a random location around the abyss outer ring.
         */

        fun randomLoc() : AbyssLoc {
            val angle = Random.nextDouble() * 2 * Math.PI
            return AbyssLoc(outerRadius,angle)
        }
    }
}