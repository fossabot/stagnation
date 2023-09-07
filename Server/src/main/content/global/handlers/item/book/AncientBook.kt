package content.global.handlers.item.book

import config.Items
import content.global.handlers.iface.BookInterface
import content.global.handlers.iface.BookLine
import content.global.handlers.iface.Page
import content.global.handlers.iface.PageSet
import core.game.interaction.IntType
import core.game.interaction.InteractionListener
import core.game.node.entity.player.Player

/**
 * Ancient Book listener.
 */
class AncientBook : InteractionListener {
    companion object {
        private val TITLE = "Ancient book"
        private val CONTENTS = arrayOf(
            PageSet(
                Page(
                    BookLine("Though little is known", 55),
                    BookLine("of the fourth age, especially", 56),
                    BookLine("in the land now known", 57),
                    BookLine("as Morytania, some small", 58),
                    BookLine("details of that dark time", 59),
                    BookLine("do yet remain. This work", 60),
                    BookLine("is as complete an account", 61),
                    BookLine("as can be mustered by", 62),
                    BookLine("the author. Its purpose", 63),
                    BookLine("is to straighten the times", 64),
                    BookLine("and tales of those would-be", 65),
                ),
                Page(
                    BookLine("heroes buried under Paterdomus.", 66),
                    BookLine("The datum here are vague", 67),
                    BookLine("at best and no truer gauge", 68),
                    BookLine("could be hardly best sought.", 69),
                    BookLine("The least hope of the", 70),
                    BookLine("lowly author herein lies;", 71),
                    BookLine("to recount what small", 72),
                    BookLine("morsels of truth remain", 73),
                    BookLine("in the minds of those", 74),
                    BookLine("that still live and entreat", 75),
                    BookLine("those very same to paper", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("lest they forever be forgotten.", 55),
                    BookLine("With my rudimentary abilities", 56),
                    BookLine("I prevailed to update", 57),
                    BookLine("the place names of those", 58),
                    BookLine("dark times and instead", 59),
                    BookLine("use those which are in", 60),
                    BookLine("common use so as to give", 61),
                    BookLine("meaning where previously", 62),
                    BookLine("there was confusion. Eight", 63),
                    BookLine("centuries and a score", 64),
                    BookLine("into the history of our", 65),
                ),
                Page(
                    BookLine("land there came a darkness", 66),
                    BookLine("which fell about the land", 67),
                    BookLine("once called Hallowvale.", 68),
                    BookLine("The Queen of that sad", 69),
                    BookLine("time, and Iycene of once", 70),
                    BookLine("formidable power, became", 71),
                    BookLine("to be deposed by 'his-dark-self'", 72),
                    BookLine("and his hordes. Against", 73),
                    BookLine("all her considerable will", 74),
                    BookLine("she was forced to kneel", 75),
                    BookLine("and attend 'his-dark-self'", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("in order that she might", 55),
                    BookLine("save her loving husband", 56),
                    BookLine("Ascertes. The darkness", 57),
                    BookLine("which 'his-dark-self'", 58),
                    BookLine("brought with him at that", 59),
                    BookLine("time writhed through Hallowvale", 60),
                    BookLine("and wrought its way ever", 61),
                    BookLine("onwards into Misthalin.", 62),
                    BookLine("Though many men did slow", 63),
                    BookLine("its passage to what is", 64),
                    BookLine("now called Varrock, none", 65),
                ),
                Page(
                    BookLine("did manage to halt its", 66),
                    BookLine("progress proper and many", 67),
                    BookLine("brave warriors so employed", 68),
                    BookLine("did die a most painful", 69),
                    BookLine("and lingering death. Fearing", 70),
                    BookLine("the darkness would sweep", 71),
                    BookLine("Saradomin's glory from", 72),
                    BookLine("the land, some odd collection", 73),
                    BookLine("of priestly advocates", 74),
                    BookLine("and pious followers of", 75),
                    BookLine("brave virtue stood forwards", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("to steel the hearts of", 55),
                    BookLine("the village fighters and", 56),
                    BookLine("town militias, the last", 57),
                    BookLine("defence against the evil", 58),
                    BookLine("now fresh from Hallowvale,", 59),
                    BookLine("and from that time till", 60),
                    BookLine("now called Morytania. This", 61),
                    BookLine("colleciton of priestly", 62),
                    BookLine("valour did cast Saradomin's", 63),
                    BookLine("blessings upon the untrained", 64),
                    BookLine("and fearful fighters of", 65),
                ),
                Page(
                    BookLine("Varrock and those of her", 66),
                    BookLine("outside villagers. This", 67),
                    BookLine("did raise bravery, courage", 68),
                    BookLine("and righteousness in the", 69),
                    BookLine("hearts of those brave", 70),
                    BookLine("young Misthalanian knights. At", 71),
                    BookLine("long last it came the", 72),
                    BookLine("time that the rabble of", 73),
                    BookLine("the Misthalin defence", 74),
                    BookLine("started to bite and the", 75),
                    BookLine("tide of evil from Morytania", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("became smashed and torn", 55),
                    BookLine("on their pikes and staves.", 56),
                    BookLine("All caught courage from", 57),
                    BookLine("the seven priestly warriors", 58),
                    BookLine("whose names shall be disgorged", 59),
                    BookLine("forthwith. 1: Iriandul", 60),
                    BookLine("Caistlyn - A true leader", 61),
                    BookLine("of the holy seven, though", 62),
                    BookLine("rarely ever a footfall", 63),
                    BookLine("in front of Ivandis, who", 64),
                    BookLine("was ever eager to smash", 65),
                ),
                Page(
                    BookLine("the evil and force the", 66),
                    BookLine("tide back into Morytania. 2:", 67),
                    BookLine("Sarl Dunegun of Edgeville", 68),
                    BookLine("- A pious yet brutal man.", 69),
                    BookLine("When wielding his mace", 70),
                    BookLine("he led many fighters on", 71),
                    BookLine("skirmishes to keep the", 72),
                    BookLine("evil from spreading and", 73),
                    BookLine("outflanking the group. 3:", 74),
                    BookLine("Friar Twiblick of Taverley", 75),
                    BookLine("- A devout man of worship", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("whose brave talks and", 55),
                    BookLine("uncommon speeches raised", 56),
                    BookLine("the spirits and abilities", 57),
                    BookLine("of men above that of mortals", 58),
                    BookLine("so they fought as though", 59),
                    BookLine("Guthix himself were at", 60),
                    BookLine("their side, redressing", 61),
                    BookLine("the balance that 'his-dark-self'", 62),
                    BookLine("has spoilt. 4: Derygull", 63),
                    BookLine("Templeton of Falador -", 64),
                    BookLine("A great oak of a man,", 65),
                ),
                Page(
                    BookLine("so well imbued with Saradomin's", 66),
                    BookLine("will and martial ability", 67),
                    BookLine("that he fair commanded", 68),
                    BookLine("people's respect and admiration", 69),
                    BookLine("and they eagerly followed", 70),
                    BookLine("him into battle. His booming", 71),
                    BookLine("voice rallied man in times", 72),
                    BookLine("of fierce fighting, this", 73),
                    BookLine("allowing them to regroup", 74),
                    BookLine("and focus their brutal,", 75),
                    BookLine("savage violence and force", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("capitulation from writhing,", 55),
                    BookLine("cowardly, evil hordes. 5:", 56),
                    BookLine("Ivandis Seergaze of Lumbridge", 57),
                    BookLine("- A daft and well skilled", 58),
                    BookLine("man of unusual appearance,", 59),
                    BookLine("he held a most bizarre", 60),
                    BookLine("control over some powerful", 61),
                    BookLine("minions of Drakan's horde.", 62),
                    BookLine("A rod or staff helped", 63),
                    BookLine("imbue him with such power.", 64),
                    BookLine("Then with the aide of", 65),
                ),
                Page(
                    BookLine("a Guthix serum he would", 66),
                    BookLine("turn the vile entity,", 67),
                    BookLine("often times destroying", 68),
                    BookLine("the creature instantly", 69),
                    BookLine("or reverting it to an", 70),
                    BookLine("origional incarnation", 71),
                    BookLine("or worst of all enraging", 72),
                    BookLine("it great, much to the", 73),
                    BookLine("cagrin of his holy brothers. 6:", 74),
                    BookLine("Erysail the Pious of Rimmington", 75),
                    BookLine("- A woman of such noble", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("and just nature that she", 55),
                    BookLine("commanded the will of", 56),
                    BookLine("Misthalanian fighters", 57),
                    BookLine("high and right, ever at", 58),
                    BookLine("the front of battle displaying", 59),
                    BookLine("courage and aiding those", 60),
                    BookLine("in need. 7: Essiandar", 61),
                    BookLine("Gar of Draynor - A fearless", 62),
                    BookLine("woman at even such a worthy", 63),
                    BookLine("age. It was said that", 64),
                    BookLine("her indignation at the", 65),
                ),
                Page(
                    BookLine("evil scourge was enough", 66),
                    BookLine("to make the most cowardly", 67),
                    BookLine("of men in her ranks act", 68),
                    BookLine("like lions in the fiercest", 69),
                    BookLine("fighting. And with these", 70),
                    BookLine("seven priestly warriors", 71),
                    BookLine("all together committed", 72),
                    BookLine("to this act, they brought", 73),
                    BookLine("the furious intent of", 74),
                    BookLine("Saradomin's will and forced", 75),
                    BookLine("the evil tide from Morytania", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("back into those very same", 55),
                    BookLine("dark lands. The fighting", 56),
                    BookLine("became fiercest at Salvarea", 57),
                    BookLine("and they stood side by", 58),
                    BookLine("side to prevent the evil", 59),
                    BookLine("from entering Misthalin", 60),
                    BookLine("again. The very spot", 61),
                    BookLine("where Paterdomus now stands", 62),
                    BookLine("is near the final battle", 63),
                    BookLine("ground for the saving", 64),
                    BookLine("of Misthalin. The temple", 65),
                ),
                Page(
                    BookLine("stands even now as a contentious", 66),
                    BookLine("reminder to those evil", 67),
                    BookLine("minions who would seek", 68),
                    BookLine("to wreak havoc in the", 69),
                    BookLine("land of Saradomin. As", 70),
                    BookLine("the priestly brothers", 71),
                    BookLine("and sisters fought on,", 72),
                    BookLine("some of them cast blessings", 73),
                    BookLine("into the Salve and sealed", 74),
                    BookLine("it in the vengeful power", 75),
                    BookLine("of Saradomin who would", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("act as Misthalin's guardian", 55),
                    BookLine("against 'his-dark-self'. Upon", 56),
                    BookLine("this foundation of holy", 57),
                    BookLine("power Paterdomus was built.", 58),
                    BookLine("Within its holy eminence", 59),
                    BookLine("the saintly remains of", 60),
                    BookLine("those seven priestly warriors,", 61),
                    BookLine("it is believed, now rest,", 62),
                    BookLine("though there is sometimes", 63),
                    BookLine("discord about one such", 64),
                    BookLine("burial. It was through", 65),
                ),
                Page(
                    BookLine("hapenstance that the author", 66),
                    BookLine("became aware of a document", 67),
                    BookLine("which I shall herein duplicate", 68),
                    BookLine("without any additions", 69),
                    BookLine("or omissions. It is said", 70) ,
                    BookLine("to be written by the brothers", 71),
                    BookLine("in arms of Ivandis Seergaze,", 72),
                    BookLine("the ever eager: 'The", 73),
                    BookLine("resting place of Ivandis", 74),
                    BookLine("Seergaze of Lumbridge", 75),
                    BookLine("is easily missed for its", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("simpleness and trivial", 55),
                    BookLine("appearance. In the midst", 56),
                    BookLine("of Mort Myre lies a long", 57),
                    BookLine("cavern running from North", 58),
                    BookLine("West to South East, with", 59),
                    BookLine("three caverns along the", 60),
                    BookLine("South Western wall and", 61),
                    BookLine("two caverns on the North", 62),
                    BookLine("Eastern. Ivandis was put", 63),
                    BookLine("to rest in the smaller", 64),
                    BookLine("of the two caverns along", 65),
                ),
                Page(
                    BookLine("the North Eastern wall", 66),
                    BookLine("with his worldly possessions.", 67),
                    BookLine("Appropriate preparations", 68),
                    BookLine("were made to ensure the", 69),
                    BookLine("safety of his mortal remains", 70),
                    BookLine("and the cavern that formed", 71),
                    BookLine("his tomb was sealed up", 72),
                    BookLine("with boards. I, Keorgius", 73),
                    BookLine("Feryis, along with his", 74),
                    BookLine("many other brothers in", 75),
                    BookLine("arms did score marks into", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("the sides of the wall", 55),
                    BookLine("next to the cavern in", 56),
                    BookLine("order to mark it out for", 57),
                    BookLine("later recovery and to", 58),
                    BookLine("deter possible intruders", 59),
                    BookLine("by making the marks look", 60),
                    BookLine("like those of some fearsome", 61),
                    BookLine("beast.' Though I have", 62),
                    BookLine("made but preliminary and", 63),
                    BookLine("trepidatious journeys", 64),
                    BookLine("into Morytania thus far,", 65),
                ),
                Page(
                    BookLine("the 'proposed' location", 66),
                    BookLine("of Ivandis' primitive", 67),
                    BookLine("tomb has evaded me. It", 68),
                    BookLine("is with the author's most", 69),
                    BookLine("delicate and humble of", 70) ,
                    BookLine("suggestions that this", 71),
                    BookLine("'primitive' tomb be cast", 72),
                    BookLine("from the reader's mind", 73),
                    BookLine("as it is, in all likelihood,", 74),
                    BookLine("a trap made by evil minions", 75),
                    BookLine("to beguile the unwary", 76),
                )
            ),
            PageSet(
                Page(
                    BookLine("into Morytania.", 55),
                ),
            )
        )
    }

    private fun display(player: Player, pageNum: Int, buttonID: Int): Boolean {
        BookInterface.pageSetup(player, BookInterface.FANCY_BOOK_3_49, TITLE, CONTENTS)
        return true
    }

    override fun defineListeners() {
        on(Items.ANCIENT_BOOK_7633, IntType.ITEM, "read") { player, _ ->
            BookInterface.openBook(player, BookInterface.FANCY_BOOK_3_49, ::display)
            return@on true
        }
    }
}