package content.global.random.event.supriseexam

import config.Items
import config.NPCs
import content.global.handlers.iface.ExperienceInterface
import core.api.setAttribute
import core.game.component.Component
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.world.map.Location

/**
 * Listeners for Suprise Exam random event.
 */
class SupriseExamListeners : InteractionListener {
    val MORDAUT = NPCs.MR_MORDAUT_6117
    val BOOK_OF_KNOWLEDGE = Items.BOOK_OF_KNOWLEDGE_11640
    override fun defineListeners() {

        on(MORDAUT, IntType.NPC, "talk-to"){ player, node ->
            player.faceLocation(Location.create(1886, 5024, 0))
            val examComplete = player.getAttribute(SupriseExamUtils.SE_KEY_CORRECT,0) == 3
            player.dialogueInterpreter.open(MordautDialogue(examComplete),node.asNpc())
            return@on true
        }

        on(SupriseExamUtils.SE_DOORS, IntType.SCENERY, "open"){ player, node ->
            val correctDoor = player.getAttribute(SupriseExamUtils.SE_DOOR_KEY,-1)

            if(correctDoor == -1){
                player.dialogueInterpreter.open(SEDoorDialogue(true))
                return@on true
            }

            if(node.id == correctDoor){
                SupriseExamUtils.cleanup(player)
                return@on true
            }

            player.dialogueInterpreter.open(SEDoorDialogue(false))
            return@on true
        }

        on(BOOK_OF_KNOWLEDGE, IntType.ITEM, "read"){ player, _ ->
            setAttribute(player, "caller"){skill: Int,p: Player ->
                if(p.inventory.remove(Item(BOOK_OF_KNOWLEDGE))) {
                    val level = p.skills.getStaticLevel(skill)
                    val experience = level * 15.0
                    p.skills.addExperience(skill, experience)
                }
            }
            player.interfaceManager.open(Component(ExperienceInterface.COMPONENT_ID))
            return@on true
        }

    }

    override fun defineDestinationOverrides() {
        setDest(IntType.NPC,MORDAUT){ _, _ ->
            return@setDest Location.create(1886, 5025, 0)
        }
    }
}