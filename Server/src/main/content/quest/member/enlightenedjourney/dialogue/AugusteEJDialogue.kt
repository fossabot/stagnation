package content.quest.member.enlightenedjourney.dialogue

import config.Components
import config.Items
import config.NPCs
import content.quest.member.enlightenedjourney.cutscene.FirstExperimentCutscene
import content.quest.member.enlightenedjourney.cutscene.SecondExperimentCutscene
import core.api.*
import core.game.dialogue.DialogueFile
import core.game.dialogue.DialoguePlugin
import core.game.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.skill.Skills
import core.game.node.item.Item
import core.game.world.GameWorld
import core.plugin.Initializable
import core.tools.END_DIALOGUE

/**
 * Represents the Auguste dialogue plugin used for Enlightened Journey quest.
 */
@Initializable
class AugusteEJDialogue(player: Player? = null) : DialoguePlugin(player) {

    override fun handle(componentID: Int, buttonID: Int): Boolean {
        val hasPapyrus = inInventory(player!!, Items.PAPYRUS_970, 3)
        val hasCandle = inInventory(player!!, Items.CANDLE_36, 1)
        val hasWool = inInventory(player!!, Items.BALL_OF_WOOL_1759, 1)
        val hasPotatoes = inInventory(player!!, Items.POTATOES10_5438, 1)

        val hasDye = (inInventory(player!!, Items.YELLOW_DYE_1765,1) && inInventory(player!!, Items.RED_DYE_1763,1))
        val hasSandbags = inInventory(player!!, Items.SANDBAG_9943, 8)
        val hasSilk = inInventory(player!!, Items.SILK_950, 10)
        val hasBowl = inInventory(player!!, Items.UNFIRED_BOWL_1791, 1)
        val hasSapling = inInventory(player, Items.AUGUSTES_SAPLING_9932) && inBank(player, Items.AUGUSTES_SAPLING_9932)
        val hasGogglesAndCap = inInventory(player, Items.BOMBER_CAP_9945, Items.GNOME_GOGGLES_9472)

        when(getQuestStage(player, "Enlightened Journey")){
            0 -> when (stage) {
                0 -> npc("Greetings! would you like to be my number one accomplice?", "I mean, assistant?").also { stage++ }
                1 -> player("Who are you?").also { stage++ }
                2 -> npc("I am Auguste. I am going to be the first balloonist in", "all of " + GameWorld.settings!!.name + "!").also { stage++ }
                3 -> player("Balloo-what? I thought only monks lived on Entrana.").also { stage++ }
                4 -> npc("Well, they do...I was a monk. But I'm so sick of living", "on this island! I want to explore new frontiers!").also { stage++ }
                5 -> player("And go where no man has gone before?").also { stage++ }
                6 -> npc("Maybe! That sounds good. I was thinking just over to", "Taverley, though.").also { stage++ }
                7 -> player("Why not take the boat then?").also { stage++ }
                8 -> npc(FacialExpression.ANGRY, "Ugh! Ocean! Don't talk to me about the ocean...oh", "dear... I feel ill.").also { stage++ }
                9 -> player("Whoa, ok, no ocean. How exactly are you going to get", "off the island, then?").also { stage++ }
                10 -> npc("I have devised a new way to travel. But I need an", "assistant balloonist to help me build my design.").also { stage++ }
                11 -> npc(FacialExpression.HALF_ASKING, "Are you an experienced adventurer willing to help me?").also { stage++ }
                12 -> options("Yes! Sign me up.", "Not right now.").also { stage++ }
                13 -> when (buttonID) {
                    1 -> if(hasLevelStat(player, Skills.CRAFTING, 36)
                        && hasLevelStat(player, Skills.FARMING, 30)
                        && hasLevelStat(player, Skills.FIREMAKING, 20)
                        && getQuestPoints(player) >= 21){
                        player("Yes! Sign me up.").also { stage++ }
                    }
                    else {
                        end()
                        sendMessage(player, RED+"You do not meet the requirements to start : 'Enlightened Journey'.")
                        stage = END_DIALOGUE
                    }
                    2 -> player("Not right now.").also { stage = END_DIALOGUE }
                }
                14 -> npc("Wonderful! Let's get started.").also { stage++ }
                15 -> player("Wait. I still don't know exactly what we're doing.").also { stage++ }
                16 -> npc("Of course, of course, how foolish of me.","Well, we are going to make a balloon.").also { stage++ }
                17 -> player("Which is what exactly?").also { stage++ }
                18 -> npc("Let me show you!").also { stage++ }
                19 -> {
                    end()
                    setVarbit(player, 2866, 1, true)
                    setQuestStage(player, "Enlightened Journey", 1).also { stage++ }
                    openInterface(player, Components.HOT_AIR_BALLOON_DIAGRAM_472)
                }
            }
            1 -> when (stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "How exactly do you plan to get to Taverley in that? How will it work?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Have you noticed how ashes float above fires for long periods of time?").also { stage++ }
                2 -> options("Umm, yes. What's your point?", "Err, no. I can't see that kind of detail.").also { stage++ }
                3 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Umm, yes. What's your point?").also { stage = 5 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Err, no. I can't see that kind of detail.").also { stage = 4 }
                }
                4 -> npcl(FacialExpression.FRIENDLY, "You don't? Oh...well...it's just, they rise, you know, from the fire... Maybe you should pay more attention to things!").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Don't you see? It's the hot air! It rises, taking the ashes with it.").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "I had this epiphany while I was at the glass blower's house. If we pump hot air into an envelope it will rise because it is lighter than the cold air around it.").also { stage++ }
                7 -> playerl(FacialExpression.FRIENDLY, "Come again?").also { stage++ }
                8 -> npcl(FacialExpression.FRIENDLY, "We are going to sew a big sack and light a fire under it. Once the sack fills with hot air it will begin to rise, taking us along with it in the basket.").also { stage++ }
                9 -> playerl(FacialExpression.FRIENDLY, "You seem pretty confident about this. Have you tested it?").also { stage++ }
                10 -> npcl(FacialExpression.FRIENDLY, "Well, no. You see, I don't have the materials here to make any test balloons.").also { stage++ }
                11 -> npcl(FacialExpression.FRIENDLY, "That's where you come in!").also { stage++ }
                12 -> npcl(FacialExpression.FRIENDLY, "You are going to collect materials for two test runs of the balloon.").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY, "You will need to get three sheets of papyrus, one ball of wool, one full sack of potatoes, and one unlit candle.").also { stage++ }
                14 -> playerl(FacialExpression.FRIENDLY, "What am I supposed to do with all that junk?").also { stage++ }
                15 -> npcl(FacialExpression.FRIENDLY, "When you have all of it, bring it to me and I'll explain what to do next. Any questions?").also { stage++ }
                16 -> playerl(FacialExpression.FRIENDLY, "Where do I get all this stuff?").also { stage++ }
                17 -> npcl(FacialExpression.FRIENDLY, "You're the adventurer, you should know! Think of logical places, like, churches; they have tons of candles.").also { stage++ }
                18 -> {
                    end()
                    setQuestStage(player, "Enlightened Journey", 2)
                }
            }
            2 -> when (stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Have you gotten the materials?").also { stage++ }
                1 -> options("Yes.", "No.").also { stage++ }
                2 -> when (buttonID) {
                    1 -> if (hasCandle && hasPapyrus && hasWool && hasPotatoes) {
                        playerl(FacialExpression.FRIENDLY, "Yes.").also { stage = 4 }
                    } else {
                        playerl(FacialExpression.FRIENDLY, "Yes.").also { stage = 10 }
                    }
                    2 -> playerl(FacialExpression.FRIENDLY, "No.").also { stage = 3 }
                }
                3 -> npcl(FacialExpression.FRIENDLY, "Failure is not an option.").also { stage = END_DIALOGUE }
                4 -> npcl(FacialExpression.FRIENDLY, "Good, you have everything! Now, I need you to create an origami balloon.").also { stage = 5 }
                5 -> playerl(FacialExpression.FRIENDLY, "How do you make the origami balloon?").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "First, use the papyrus on the ball of wool. The papyrus is folded into an origami box and the yarn will support the heat source.").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "Next, add the unlit candle to the balloon structure. It will act as the heat source.").also { stage++ }
                8 -> npcl(FacialExpression.FRIENDLY, "Once you have done that let me know and we will begin our experiment.").also { stage++ }
                9 -> {
                    end()
                    setQuestStage(player, "Enlightened Journey", 3)
                }
                10 -> {
                    if (!hasCandle && !hasPapyrus && !hasWool && !hasPotatoes) {
                        // Speaking to Auguste after being asked to get the materials WITHOUT all the materials
                        npcl(FacialExpression.FRIENDLY, "You don't seem to have any of the materials. You need a ball of wool, three sheets of papyrus, an unlit candle, and a full sack of potatoes.").also { stage = END_DIALOGUE }
                    } else if (hasCandle && !hasWool && !hasPapyrus && !hasPotatoes) {
                        // Speaking to Auguste after being asked to get the materials WITH the candle WITHOUT the wool, papyrus, or potatoes
                        npcl(FacialExpression.FRIENDLY, "You need a ball of wool, papyrus, and a full sack of potatoes.").also { stage = END_DIALOGUE }
                    } else if (hasWool && !hasPapyrus && !hasCandle && !hasPotatoes) {
                        // Speaking to Auguste after being asked to get the materials WITH the wool WITHOUT the papyrus, candle, or potatoes
                        npcl(FacialExpression.FRIENDLY, "You need more papyrus, a full sack of potatoes, and an unlit candle.").also { stage = END_DIALOGUE }
                    } else if (hasPapyrus && !hasCandle && !hasWool && !hasPotatoes) {
                        // Speaking to Auguste after being asked to get the materials WITH the papyrus WITHOUT the candle, wool, or potatoes
                        npcl(FacialExpression.FRIENDLY, "You need a ball of wool, an unlit candle, and a full sack of potatoes.").also { stage = END_DIALOGUE }
                    } else if (hasPotatoes && !hasCandle && !hasWool && !hasPapyrus) {
                        // Speaking to Auguste after being asked to get the materials WITH the potatoes WITHOUT the candle, wool, or papyrus
                        npcl(FacialExpression.FRIENDLY, "You need a ball of wool, an unlit candle, and papyrus.").also { stage = END_DIALOGUE }
                    } else if (hasCandle && hasWool && !hasPapyrus && !hasPotatoes) {
                        // Speaking to Auguste after being asked to get the materials WITH the candle and wool WITHOUT payrus and potatoes
                        npcl(FacialExpression.FRIENDLY, "You need more papyrus and a full sack of potatoes.").also { stage = END_DIALOGUE }
                    } else if (hasCandle && hasPapyrus && !hasPotatoes && !hasWool) {
                        // Speaking to Auguste after being asked to get the materials WITH the candle and papyrus WITHOUT the potatoes and wool
                        npcl(FacialExpression.FRIENDLY, "You need more papyrus, and a ball of wool.").also { stage = END_DIALOGUE }
                    } else if (hasWool && hasPotatoes && !hasCandle && !hasPapyrus) {
                        // Speaking to Auguste after being asked to get the materials WITH the wool and potatoes WITHOUT the candle or papyrus
                        npcl(FacialExpression.FRIENDLY, "You need papyrus and an unlit candle.").also { stage = END_DIALOGUE }
                    } else if (hasWool && hasPapyrus && !hasPotatoes && !hasCandle) {
                        // Speaking to Auguste after being asked to get the materials WITH the wool and papyrus WITHOUT the potatoes or candle
                        npcl(FacialExpression.FRIENDLY, "You need an unlit candle and a full sack of potatoes.").also { stage = END_DIALOGUE }
                    } else if (hasPotatoes && hasPapyrus && !hasWool && !hasCandle) {
                        // Speaking to Auguste after being asked to get the materials WITH the potatoes and papyrus WITHOUT the wool or candle
                        npcl(FacialExpression.FRIENDLY, "You need a ball of wool and an unlit candle.").also { stage = END_DIALOGUE }
                    } else if (!hasCandle) {
                        // Speaking to Auguste after being asked to get the materials WITH the potatoes, papyrus, wool WITHOUT candle
                        npcl(FacialExpression.FRIENDLY, "You need an unlit candle.").also { stage = END_DIALOGUE }
                    } else if (!hasWool) {
                        // Speaking to Auguste after being asked to get the materials WITH the potatoes, papyrus, candle WITHOUT wool
                        npcl(FacialExpression.FRIENDLY, "You need a ball of wool.").also { stage = END_DIALOGUE }
                    } else if (!hasPapyrus) {
                        // Speaking to Auguste after being asked to get the materials WITH the potatoes, candle, wool WITHOUT papyrus
                        npcl(FacialExpression.FRIENDLY, "You need more papyrus.").also { stage = END_DIALOGUE }
                    } else if (!hasPotatoes) {
                        // Speaking to Auguste after being asked to get the materials WITH the candle, papyrus, wool WITHOUT potatoes
                        npcl(FacialExpression.FRIENDLY, "You need a sack full of potatoes.").also { stage = END_DIALOGUE }
                    }
                }
            }

            3 -> when(stage){
                0 -> if(inInventory(player, Items.ORIGAMI_BALLOON_9934)) {
                    playerl(FacialExpression.HAPPY, "I finished the origami balloon!").also { stage = 4 }
                } else {
                    playerl(FacialExpression.FRIENDLY, "How do you make the origami balloon?").also { stage = 1 }
                }
                1 -> npcl(FacialExpression.FRIENDLY, "First, use the papyrus on the ball of wool. The papyrus is folded into an origami box and the yarn will support the heat source.").also { stage++ }
                2 -> npcl(FacialExpression.FRIENDLY, "Next, add the unlit candle to the balloon structure. It will act as the heat source.").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, "Once you have done that let me know and we will begin our experiment.").also { stage = END_DIALOGUE }
                4 -> npcl(FacialExpression.HAPPY,"Wonderful! I'll take that, and we'll conduct our first experiment.").also { stage++ }
                5 -> {
                    end()
                    FirstExperimentCutscene(player).start()
                    removeItem(player, Items.ORIGAMI_BALLOON_9934)
                }
            }

            4 -> when(stage) {
                0 -> npcl(FacialExpression.HALF_ASKING, "Do you have the other two sheets of papyrus and a full sack of potatoes?").also { stage++ }
                1 -> options("Yes, I have them here.", "Oh, I've misplaced them.").also { stage++ }
                2 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Yes, I have them here.").also { stage = 3 }
                    2 -> playerl(FacialExpression.NEUTRAL, "Oh, I've misplaced them.").also { stage = 4 }
                }
                3 -> if (!hasPapyrus && !hasPotatoes) {
                    npcl(FacialExpression.FRIENDLY, "You need a full sack of potatoes and more papyrus.").also { stage = END_DIALOGUE }
                } else if (!hasPapyrus) {
                    npcl(FacialExpression.NEUTRAL, "You need more papyrus.").also { stage = END_DIALOGUE }
                } else if (!hasPotatoes) {
                    npcl(FacialExpression.NEUTRAL, "You need a full sack of potatoes.").also { stage = END_DIALOGUE }
                } else {
                    npcl(FacialExpression.FRIENDLY, "Commendable. If I may have those, I will construct this experiment.").also { stage = 5 }
                }
                4 -> npcl(FacialExpression.FRIENDLY,"Failure is not an option.").also { stage = END_DIALOGUE }
                5 -> {
                    end()
                    removeItem(player, Item(Items.PAPYRUS_970,3))
                    removeItem(player, Item(Items.POTATOES10_5438,1))
                    SecondExperimentCutscene(player).start()
                }
            }

            5 -> when(stage) {
                0 -> playerl(FacialExpression.FRIENDLY, "Those peasants... where did they come from?").also { stage++ }
                1 -> npcl(FacialExpression.FRIENDLY, "Ahh, the flash mob phenomenon. Many have hypothesized that they are beings of great power sent to smite those who question the gods.").also { stage++ }
                2 -> playerl(FacialExpression.FRIENDLY, " And this isn't worrying because...?").also { stage++ }
                3 -> npcl(FacialExpression.FRIENDLY, " Don't worry! I know exactly what I'm doing.").also { stage++ }
                4 -> npcl(FacialExpression.FRIENDLY, "Those experiments went extraordinarily well.").also { stage++ }
                5 -> playerl(FacialExpression.FRIENDLY, "Was I the ONLY one who saw them burning? BURNING???").also { stage++ }
                6 -> npcl(FacialExpression.FRIENDLY, "Yes, very well indeed. Now we will start building the balloon that will carry us off the island.").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "This task will be much greater than the last two. I hope you are prepared for it.").also { stage++ }
                8 -> playerl(FacialExpression.FRIENDLY, "This is madness.").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "You need to get the following items:").also { stage++ }

                10 -> npcl(FacialExpression.FRIENDLY, "Yellow dye Red dye Ten pieces of silk A clay bowl").also { stage++ }
                11 -> npcl(FacialExpression.FRIENDLY, "and eight sandbags.").also { stage++ }
                12 -> npcl(FacialExpression.FRIENDLY, "Sandbags can be made by getting empty sacks and filling them at the sand pit here on Entrana.").also { stage++ }
                13 -> npcl(FacialExpression.FRIENDLY, "However, there are other sand pits around the world that will work as well; there is one in Yanille, Rellekka, and Zanaris.").also { stage++ }
                14 -> npcl(FacialExpression.FRIENDLY, "You can bring items back to me as you get them, while you are waiting for the tree to grow.").also { stage++ }

                15 -> playerl(FacialExpression.FRIENDLY, "What tree?").also { stage++ }
                16 -> npcl(FacialExpression.FRIENDLY, "I am going to give you a willow sapling and a basket of apples. You must plant the willow sapling at a tree patch.").also { stage++ }
                17 -> npcl(FacialExpression.FRIENDLY, "If you give the basket of apples to the gardener near the patch, he will look after the tree for you while it grows.").also { stage++ }
                18 -> npcl(FacialExpression.FRIENDLY, "Don't lose the sapling! It took me a long time to save up enough for one. If you do, you'll have to pay me 30,000 coins for a new one.").also { stage++ }
                19 -> npcl(FacialExpression.FRIENDLY, "Once the tree is fully grown, cut twelve branches from it using secateurs. Bring the branches back here and use them on the metal frame on the platform to create the basket.").also { stage++ }
                20 -> npcl(FacialExpression.FRIENDLY, "Here you go. Now be very careful not to lose it!").also { stage++ }
                21 -> {
                    end()
                    setQuestStage(player, "Enlightened Journey", 6)
                    addItemOrDrop(player, Items.AUGUSTES_SAPLING_9932)
                }
            }

            6 -> when(stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Do you have anything for me?").also { stage = 1 }
                1 -> options("Yes, I want to give you some items.", "I'm having trouble finding some items.", "I have lost my willow sapling. Can I buy a replacement?").also { stage++ }
                2 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Yes, I want to give you some items.").also { stage = 3 }
                    2 -> playerl(FacialExpression.FRIENDLY, "I'm having trouble finding some items.").also { stage = 12 }
                    3 -> playerl(FacialExpression.FRIENDLY, "I have lost my willow sapling. Can I buy a replacement?").also { stage = 20 }
                }
                3 -> options("Dye.", "Sandbags.", "Silk.", "Bowl.", "Never mind.").also { stage++ }
                4 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Dye.").also { stage = 5 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Sandbags.").also { stage = 7 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Silk.").also { stage = 10 }
                    4 -> playerl(FacialExpression.FRIENDLY, "Bowl.").also { stage = 11 }
                    5 -> playerl(FacialExpression.FRIENDLY, "Never mind.").also { stage = END_DIALOGUE }
                }
                5 -> if(hasDye){
                    setAttribute(player, "enlighted-journey-items", 1)
                    npcl(FacialExpression.FRIENDLY, "Ah, wonderful, yellow dye. Thank you.").also { stage = 25 }
                } else {
                    npcl(FacialExpression.FRIENDLY, "You don't have any dye with you.").also { stage = 6 }
                }
                6 -> npcl(FacialExpression.FRIENDLY, "I need red and yellow dye for the balloon.").also { stage = 3 }
                7 -> if(hasSandbags){
                    setAttribute(player, "enlighted-journey-items", 2)
                    npcl(FacialExpression.FRIENDLY, "Sandbags, thank you. This will allow us to change height.").also { stage = 3 }
                } else {
                    npcl(FacialExpression.FRIENDLY, "You don't have enough sandbags. Please bring me eight.").also { stage = 8 }
                }
                8 -> npcl(FacialExpression.FRIENDLY, "Sandbags can be made by getting empty sacks and filling them at the sandpit here on Entrana.").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "However, there are other sand pits around the world that will work as well.").also { stage = 3 }
                10 -> if(hasSilk){
                    setAttribute(player, "enlighted-journey-items", 3)
                    npcl(FacialExpression.FRIENDLY, "Silk for the balloon, thank you.").also { stage = 3 }
                } else {
                    npcl(FacialExpression.FRIENDLY, "You don't have enough silk. Please bring me ten pieces.").also { stage = 3 }
                }
                11 -> if(hasBowl && getAttribute(player, "enlighted-journey-items", 0) == 3){
                    npcl(FacialExpression.FRIENDLY, "Ah the bowl. This will be used to hold the fuel while it heats the air in the balloon.").also { stage = 3 }
                    setQuestStage(player, "Enlightened Journey", 7)
                } else {
                    npcl(FacialExpression.FRIENDLY, "I need a plain clay-fired bowl; they're quite ease to come by.").also { stage = 3 }
                }
                12 -> npcl(FacialExpression.FRIENDLY, "What do you need help with?").also { stage++ }
                13 -> options("Dye.", "Sandbags.", "Silk.", "Bowl.", "Never mind.").also { stage++ }
                14 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Dye.").also { stage = 15 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Sandbags.").also { stage = 16 }
                    3 -> playerl(FacialExpression.FRIENDLY, "Silk.").also { stage = 18 }
                    4 -> playerl(FacialExpression.FRIENDLY, "Bowl.").also { stage = 19 }
                    5 -> playerl(FacialExpression.FRIENDLY, "Never mind.").also { stage = END_DIALOGUE }
                }
                15 -> npcl(FacialExpression.FRIENDLY, "I was told a while ago that there was a witch who made dye in Draynor Village. Maybe you should start by looking there.").also { stage = END_DIALOGUE }
                16 -> npcl(FacialExpression.FRIENDLY, "Sandbags can be made by getting empty sacks and filling them at the sandpit here on Entrana.").also { stage++ }
                17 -> npcl(FacialExpression.FRIENDLY, "However, there are other sand pits around the world that will work as well; there is one in Yanille, Rellekka, and Zanaris.").also { stage = END_DIALOGUE }
                18 -> npcl(FacialExpression.FRIENDLY, "Hmm, I believe silk is imported from the desert. Perhaps someone there can tell you where to find it.").also { stage = END_DIALOGUE }
                19 -> npcl(FacialExpression.FRIENDLY, "I think there is a spare one in the glass blower's house. I rent a room from him there, so I don't think he'll mind you taking it.").also { stage = END_DIALOGUE }
                20 -> npcl(FacialExpression.FRIENDLY, "It will cost you 30,000 gold coins to replace it, do you want to pay that?").also { stage++ }
                21 -> options("Yes.", "No way!").also { stage++ }
                22 -> when (buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Yes.").also { stage = 23 }
                    2 -> playerl(FacialExpression.FRIENDLY, "No way!").also { stage = END_DIALOGUE }
                }
                23 -> if (!removeItem(player, Item(Items.COINS_995, 30000))) {
                    npcl(FacialExpression.FRIENDLY, "Looks like you don't have enough money. Come back when you do.").also { stage = END_DIALOGUE }
                } else if (freeSlots(player) < 2){
                    npcl(FacialExpression.FRIENDLY, "Looks like you don't have enough room in your inventory for the basket and the sapling. Come back when you do.").also { stage = END_DIALOGUE }
                } else if(removeItem(player, Item(Items.COINS_995, 30000))){
                    npcl(FacialExpression.FRIENDLY, "Here you go. Now be very careful not to lose it again!").also { stage = 24 }
                }
                24 -> {
                    end()
                    addItemOrDrop(player, Items.AUGUSTES_SAPLING_9932)
                    stage = END_DIALOGUE
                }
            }

            7 -> when(stage) {
                0 -> npcl(FacialExpression.FRIENDLY,"You just need to build the basket and I can finish the balloon! How are you getting on with the willow?").also { stage++ }
                1 -> options("I have lost my willow sapling. Can I buy a replacement?", "What do I do again?", "Fine thanks.").also { stage++ }
                2 -> when(buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY,"I have lost my willow sapling. Can I buy a replacement?").also { stage = 3 }
                    2 -> playerl(FacialExpression.FRIENDLY,"What do I do again?").also { stage = 7 }
                    3 -> playerl(FacialExpression.FRIENDLY,"Fine thanks.").also { stage = END_DIALOGUE }
                }
                3 -> npcl(FacialExpression.FRIENDLY,"It will cost you 30,000 gold coins to replace it, do you want to pay that?").also { stage++ }
                4 -> options("Yes.", "No way!").also { stage++ }
                5 -> when(buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY,"Yes.").also { stage = 6 }
                    2 -> playerl(FacialExpression.FRIENDLY,"No way!").also { stage = END_DIALOGUE }
                }
                6 -> if (!hasSapling && !removeItem(player, Item(Items.COINS_995, 30000))) {
                    npcl(FacialExpression.FRIENDLY, "Looks like you don't have enough money. Come back when you do.").also { stage = END_DIALOGUE }
                } else if (freeSlots(player) < 2){
                    npcl(FacialExpression.FRIENDLY, "Looks like you don't have enough room in your inventory for the basket and the sapling. Come back when you do.").also { stage = END_DIALOGUE }
                } else if(!hasSapling && removeItem(player, Item(Items.COINS_995, 30000))){
                    npcl(FacialExpression.FRIENDLY, "Here you go. Now be very careful not to lose it again!").also { stage = 7 }
                } else {
                    npcl(FacialExpression.FRIENDLY,"You already have one! Don't be greedy.").also { stage = END_DIALOGUE }
                }
                7 -> {
                    end()
                    addItemOrDrop(player, Items.AUGUSTES_SAPLING_9932)
                    npcl(FacialExpression.FRIENDLY,"Use the willow sapling I gave you to grow a willow tree. Cut twelve branches from it using secateurs. Use the branches on the platform here.")
                    stage = END_DIALOGUE
                }
            }

            8 -> when(stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Well, let's get going!").also { stage++ }
                1 -> options("Wait; tell me what we're doing.", "Okay.", "No, I'm not ready.").also { stage++ }
                2 -> when(buttonID) {
                    1 -> playerl(FacialExpression.FRIENDLY, "Wait; tell me what we're doing.").also { stage = 3 }
                    2 -> playerl(FacialExpression.FRIENDLY, "Okay.").also { stage = 23 }
                    3 -> playerl(FacialExpression.FRIENDLY, "No, I'm not ready.").also { stage = END_DIALOGUE }
                }
                3 -> npcl(FacialExpression.FRIENDLY, "Theoretically, we are ready for the maiden voyage.").also { stage++ }
                4 -> playerl(FacialExpression.FRIENDLY, "Theoretically?").also { stage++ }
                5 -> npcl(FacialExpression.FRIENDLY, "Well, of course. With you piloting I am sure we will survive.").also { stage++ }
                6 -> playerl(FacialExpression.FRIENDLY, "WHAT!? This is your balloon! Why aren't you going to pilot it? And what do mean 'survive'? You never said anything about me flying this bird!").also { stage++ }
                7 -> npcl(FacialExpression.FRIENDLY, "Don't be silly. I'm not going to pilot it. We will be safe in the basket, the only thing we will lose are the logs. You will pilot it, won't you?").also { stage++ }
                8 -> playerl(FacialExpression.FRIENDLY, "Fine, I will. So how do I control the balloon?").also { stage++ }
                9 -> npcl(FacialExpression.FRIENDLY, "Wonderful! Let me explain my hypothesis on how to control a balloon.").also { stage++ }
                10 -> playerl(FacialExpression.FRIENDLY, "Oh no, not another hypothesis!").also { stage++ }
                11 -> npcl(FacialExpression.FRIENDLY, "The balloon needs ten normal logs for fuel. I believe the balloon will be controlled based on weight, so you must not have more than 40kg with you.").also { stage++ }
                12 -> npcl(FacialExpression.FRIENDLY, "Also, I seem to have lost my tinderbox, do you think you could bring one? We need it to light the fire.").also { stage++ }
                13 -> playerl(FacialExpression.FRIENDLY, "And here, I thought all the legwork was done. Fine, I'll bring a tinderbox.").also { stage++ }
                14 -> npcl(FacialExpression.FRIENDLY, "Right! So, the balloon mechanics.").also { stage++ }
                15 -> npcl(FacialExpression.FRIENDLY, "Your prime direction will always be to land the balloon on the target at the end of our route. I've written to some friends in Taverley who have kindly painted one on the ground there.").also { stage++ }
                16 -> npcl(FacialExpression.FRIENDLY, "We must avoid everything, because the balloon is very fragile. Even clouds will be dangerous! But if my calculations are correct, we will be able to squeeze through some surprising spaces, if you pilot it well.").also { stage++ }
                17 -> npcl(FacialExpression.FRIENDLY, "Those sandbags you made earlier will give the balloon a big lift when dropped, whereas adding the logs will only make it rise a little bit. But don't use them up too quickly! We have no way of replenishing them.").also { stage++ }
                18 -> npcl(FacialExpression.FRIENDLY, "If you do run out of logs and sandbags you will not be able to go up any longer. Just make the best of it and hope we can make it to our destination without going upwards.").also { stage++ }
                19 -> npcl(FacialExpression.FRIENDLY, "I've added two ropes, the red one is an emergency rope, it will let the hot air out of the balloon and we will drop quickly. The other rope will only drop us a little.").also { stage++ }
                20 -> npcl(FacialExpression.FRIENDLY, "If we get into tribb...beg your pardon, trouble, in all likelihood we will crash. But do not fear! We should be fine. Just make sure you come back to Entrana so we can try again.").also { stage++ }
                21 -> npcl(FacialExpression.FRIENDLY, "If it all goes horribly wrong, you can always bail. If we're still over Entrana, we can land quickly and try again. However, once past the island, we will crash.").also { stage++ }
                22 -> npcl(FacialExpression.FRIENDLY, "Are you ready to go?").also { stage++ }
                23 -> {
                    end()
                    openInterface(player, Components.ZEP_INTERFACE_SIDE_471)
                    // openDialogue(player, AugusteEJDialogueFile())
                }
            }

            100 -> when(stage) {
                0 -> npcl(FacialExpression.FRIENDLY, "Do you want to use the balloon? Just so you know, some locations require special logs and high Firemaking skills.").also { stage++ }
                1 -> options("Yes.", "No.", "Could you replace some items for me?").also { stage++ }
                2 -> if(hasGogglesAndCap)
                    when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Yes.").also { stage = 3 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No.").also { stage = END_DIALOGUE }
                        3 -> playerl(FacialExpression.HALF_ASKING, "Could you replace some items for me?").also { stage = 4 }
                        4 -> playerl(FacialExpression.HALF_ASKING, "Could you combine my cap and goggles?").also { stage = 7 }
                    } else {
                    when (buttonID) {
                        1 -> playerl(FacialExpression.FRIENDLY, "Yes.").also { stage = 3 }
                        2 -> playerl(FacialExpression.FRIENDLY, "No.").also { stage = END_DIALOGUE }
                        3 -> playerl(FacialExpression.HALF_ASKING, "Could you replace some items for me?").also { stage = 4 }
                    }
                }
                3 -> {
                    end()
                    openInterface(player, Components.ZEP_BALLOON_MAP_469)
                    setComponentVisibility(player, Components.ZEP_BALLOON_MAP_469, 12, false)
                }
                4 -> npcl(FacialExpression.FRIENDLY, "Here's your cap. Try to keep better track of it.").also {addItemOrDrop(player, Items.BOMBER_CAP_9945)}.also { stage = 5 }
                5 -> npcl(FacialExpression.FRIENDLY, "Here's your jacket, to keep you warm during flight.").also {addItemOrDrop(player, Items.BOMBER_JACKET_9944)}.also { stage = 6 }
                6 -> {
                    end()
                    stage = END_DIALOGUE
                }
                7 -> if(removeItem(player, Items.BOMBER_CAP_9945) && removeItem(player, Items.GNOME_GOGGLES_9472)){
                    end()
                    npcl(FacialExpression.FRIENDLY, "There you go! You look like a true airship pilot now.")
                    addItemOrDrop(player, Items.CAP_AND_GOGGLES_9946)
                    stage = END_DIALOGUE
                } else if (inEquipment(player, Items.BOMBER_CAP_9945) || inEquipment(player, Items.GNOME_GOGGLES_9472)){
                    end()
                    npcl(FacialExpression.FRIENDLY, "I can't combine them if you are wearing them.")
                    stage = END_DIALOGUE
                } else if(freeSlots(player) < 2){
                    end()
                    npcl(FacialExpression.FRIENDLY, "You don't have enough free space for the cap and goggles.")
                    stage = END_DIALOGUE
                }
            }
        }
        return true
    }


    override fun getIds(): IntArray {
        return intArrayOf(NPCs.AUGUSTE_5049)
    }
}

/**
 * Represents the Auguste dialogue file used for Enlightened Journey quest.
 */
class AugusteEJDialogueFile : DialogueFile() {
    override fun handle(componentID: Int, buttonID: Int) {
        npc = NPC(NPCs.AUGUSTE_5049)
        when (stage) {
            0 -> playerl(FacialExpression.FRIENDLY, "So what are you going to do now?").also { stage++ }
            1 -> npcl(FacialExpression.FRIENDLY, "I am considering starting a balloon enterprise. People all over Runescape will be able to travel in a new, exciting way.").also { stage++ }
            2 -> npcl(FacialExpression.FRIENDLY, "As my first assistant, you will always be welcome to use a balloon. You'll have to bring your own fuel, though.").also { stage++ }
            3 -> playerl(FacialExpression.FRIENDLY, "Thanks!").also { stage++ }
            4 -> npcl(FacialExpression.FRIENDLY, "I will base my operations in Entrana. If you'd like to travel to new places, come see me there.").also { stage++ }
            5 -> {
                end()
                finishQuest(player!!, "Enlightened Journey")
            }
        }
    }
}