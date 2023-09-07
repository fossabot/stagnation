package content.quest.member.hazeelcult.dialogue

import config.Items
import config.NPCs
import content.quest.member.hazeelcult.HazeelCult
import content.quest.member.hazeelcult.HazeelCultListeners
import content.quest.member.hazeelcult.HazeelCultListeners.Companion.CARNILLEAN
import content.quest.member.hazeelcult.HazeelCultListeners.Companion.MAHJARRAT
import core.api.*
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Ceril Carnillean dialogue plugin for Hazeel Cult quest.
 */
@Initializable
class CerilCarnilleanHCDialogue(player: Player? = null) : DialoguePlugin(player) {
    override fun open(vararg args: Any?): Boolean {
        npc = args[0] as NPC
        if (getQuestStage(player,HazeelCult.questName) == 1) {
            playerl(FacialExpression.FRIENDLY, "Hello, Ceril.").also { stage++ }
        } else if (getAttribute(player!!, CARNILLEAN, true) && getQuestStage(player,HazeelCult.questName) == 4) {
            playerl(FacialExpression.FRIENDLY, "I've killed the cult leader, and he dropped your armour!").also { stage++ }
        } else if (getAttribute(player!!, CARNILLEAN, true) && getQuestStage(player,HazeelCult.questName) == 5 && inBorders(player, 2571, 3267, 2575, 3273)) {
            npcl(FacialExpression.HALF_ASKING, "Jones! This commoner says you had something to do with the theft of my armour. What do you have to say for yourself about that?").also { stage++ }
        } else {
            playerl(FacialExpression.FRIENDLY, "Hello there.").also { stage++ }
        }
        return true
    }
    override fun handle(componentID: Int, buttonID: Int): Boolean {
        when (getQuestStage(player!!, HazeelCult.questName)) {
            0 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Blooming, thieving cultists! Why don't they leave me alone? String them all up, that's what I say!").also { stage++ }
                1 -> options("What's wrong?", "You probably deserve it.", "You seem uptight, I'll leave you alone.").also { stage++ }
                2 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "What's wrong?").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "You probably deserve it.").also { stage = 22 }
                    3 -> playerl(FacialExpression.FRIENDLY, "You seem uptight, I'll leave you alone.").also { stage = 23 }
                }
                3 -> npcl(FacialExpression.FRIENDLY, "It's those blooming cultists from the south! They keep breaking into my house!").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "Have they taken much?").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Well... no. They stole a suit of armour when they first broke in months ago, but they've been back four more times since then and taken nothing.").also { stage++ }
                6 -> playerl(FacialExpression.FRIENDLY, "I see. And you are?").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "You don't know me? Why, I am Sir Ceril Carnillean! Mine is quite a famous bloodline, you know. We've played a vital role in the politics of Ardougne for many generations.").also { stage++ }
                8 -> playerl(FacialExpression.FRIENDLY, "Well I'm ${player.username}. Maybe I can help you with these cultists?").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "Interesting... I suppose the authorities haven't been much help so far. Yes, very well. If you return the stolen armour, I will provide you with a modest cash reward!").also { stage++ }
                10 -> options("Yes.", "No.").also { stage++ }
                11 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Yes, of course, I'd be happy to help.").also { stage++ }
                    2 -> playerl(FacialExpression.FRIENDLY, "No thanks. I've got other plans.").also { stage = 21 }
                }
                12 -> npcl(FacialExpression.FRIENDLY, "That's very noble of you! Keep that attitude up and one day you might have the honour of working full-time for someone like me.").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY, "Now, I caught a glimpse of the thieves leaving, but due to... uh... my cold, I was unable to give chase.").also { stage++ }
                14 -> npcl(FacialExpression.FRIENDLY, "What I do know is that they belong to some sort of cult. They seem to be based in a cave just south of the city, near the Clock Tower.").also { stage++ }
                15 -> playerl(FacialExpression.FRIENDLY, "How do you know that?").also { stage++ }
                16 -> npcl(FacialExpression.FRIENDLY, "My old butler, Higson, once followed them there. Unfortunately, the next night he died in his sleep.").also { stage++ }
                17 -> playerl(FacialExpression.FRIENDLY, "That's awful!").also { stage++ }
                18 -> npcl(FacialExpression.FRIENDLY, "No, it's okay. Jones, a replacement from the Servants' Guild, arrived the next day. He's been great - makes an excellent broth!").also { stage++ }
                19 -> playerl(FacialExpression.FRIENDLY, "Right... I'll see if I can find this cave.").also { stage++ }
                20 -> {
                    end()
                    setQuestStage(player, "Hazeel Cult", 1)
                }
                21 -> npcl(FacialExpression.FRIENDLY, "Well no wonder I'm the one with the big house and you're the one on the streets.").also { stage = END_DIALOGUE }
                22 -> npcl(FacialExpression.FRIENDLY, "And who are you to judge me? You look like a peasant anyway. I'm wasting my time talking to you.").also { stage = END_DIALOGUE }
                23 -> npcl(FacialExpression.FRIENDLY, "Yes, I doubt you could help anyway.").also { stage = END_DIALOGUE }
            }

            1 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "That's Sir Ceril to you, you impudent scamp. Show a bit of respect to your betters. Now, shouldn't you be recovering my armour?").also { stage++ }
                1 -> playerl(FacialExpression.FRIENDLY, "Yeah yeah, I'm on it.").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Good. I suggest you start with that cave south of the city, near the Clock Tower. That is where the cult is based.").also { stage = END_DIALOGUE }
            }

            3 -> when(stage){
                0 -> if(getAttribute(player, MAHJARRAT, true) && !getAttribute(player, CARNILLEAN, true)) {
                    playerl(FacialExpression.FRIENDLY,"Hello again.").also { stage++ }
                } else {
                    sendMessage(player, "They aren't interested in talking to you.").also { stage = END_DIALOGUE }
                }
                1 -> npcl(FacialExpression.ANNOYED, "Oh the inhumanity... the cruelty... the misery... the pain...").also { stage++ }
                2 -> npcl(FacialExpression.ANNOYED, "My son is a good boy, really, but how could he give his dinner to Scruffy without having the servants test it for poison first? How?").also { stage++ }
                3 -> npcl(FacialExpression.ANNOYED, "How could he be so thoughtless and careless? He knows we are all under threat!").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY,"Scruffy?").also { stage++ }
                5 -> npcl(FacialExpression.ANNOYED, "He's been with our family for twenty years... that's 140 in dog years! The poor dog... What did he ever do to deserve such a fate?").also { stage++ }
                6 -> playerl(FacialExpression.FRIENDLY,"Your dog got poisoned? That's not right.").also { stage++ }
                7 -> npcl(FacialExpression.NEUTRAL,"I agree! I hope whichever evildoer is responsible gets the full weight of the law brought upon them!").also { stage++ }
                8 -> playerl(FacialExpression.NEUTRAL, "Uh... yeah... me too.").also { stage = END_DIALOGUE }
           }

            4 -> when (stage) {
                0 -> if (removeItem(player, Items.CARNILLEAN_ARMOUR_2405)) {
                    npcl(FacialExpression.FRIENDLY, "Well done! I must say that I am very impressed. Come on, hand it over.").also { stage++ }
                } else {
                    npcl(FacialExpression.FRIENDLY, "Yes, I doubt you could help anyway.").also { stage = END_DIALOGUE }
                }
                1 -> npcl(FacialExpression.FRIENDLY, "Before we send you on your way with your payment, I'll just get Jones to whip you up a batch of his special broth.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, "I'd rather not, if it's all the same to you. I overheard the cultists talking, and apparently Jones is in league with them.").also { stage++ }
                3 -> npcl(FacialExpression.SAD, "W-what? JONES! Get over here! We're going to blooming-well sort this out, once and for all!").also { stage++ }
                4 -> {
                    end()
                    setQuestStage(player, "Hazeel Cult", 5)
                }
            }

            5 -> when (stage) {
                0 -> sendNPCDialogue(player, NPCs.BUTLER_JONES_890, "It wasn't me, m'lud. I am, as you know, a loyal servant.").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Hmph. Quite right too. I cannot fathom why this scoundrel would accuse you of such a crime without evidence to back it up.").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Right, I have decided. I have given my word as a nobleman to reward you for your efforts in retrieving my armour,").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "but I must also compensate Jones for this terrible slander you have made against him.").also { stage++ }
                4 -> sendDialogue(player, "Sir Ceril gives you 5 coins. Sir Ceril gives Jones 1,995 coins.").also{ addItemOrDrop(player, Items.COINS_995, 5) }.also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Now, take it and leave, you scoundrel! Don't darken my doorstep again!").also { stage++ }
                6 -> sendNPCDialogue(player, NPCs.BUTLER_JONES_890, "Don't worry, m'lud, this fool won't be bothering us any more.").also { stage++ }
                7 -> sendDialogue(player, "Jones smirks at you. You are going to need more than words to prove his treachery.").also { stage++ }
                8 -> {
                    end()
                    finishQuest(player, HazeelCult.questName)
                }
            }

            100 -> when(stage){
                0 -> if(getAttribute(player, MAHJARRAT, true) && !getAttribute(player, HazeelCultListeners.CARNILLEAN, true)){
                    //Sided with Hazeel
                   npcl(FacialExpression.FRIENDLY, "Oh... I may be wrong... but ever since I asked for your help, things around here have gone from bad to worse...").also { stage = 2 }
                } else {
                    //Sided with Carnillean
                    npcl(FacialExpression.FRIENDLY, "Well hello again adventurer! It's good to see you again! If it wasn't for your quick thinking the treacherous Jones would have poisoned my family and me by now! We are in your debt.").also { stage = 1 }
                }
                1 -> playerl(FacialExpression.FRIENDLY, "Don't worry about it, a good deed is its own reward. And that 2000 gold didn't hurt either.").also { stage = END_DIALOGUE }
                2 -> npcl(FacialExpression.NEUTRAL, "I think you'd better keep out of my way. And for the last time, it's Sir Ceril! Sir! It's not that hard to blooming remember!").also { stage = END_DIALOGUE }
            }
        }
        return true
    }
    override fun getIds(): IntArray {
        return intArrayOf(NPCs.CERIL_CARNILLEAN_885)
    }
}