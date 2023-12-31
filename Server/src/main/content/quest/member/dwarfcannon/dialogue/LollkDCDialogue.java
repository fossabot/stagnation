package content.quest.member.dwarfcannon.dialogue;

import content.quest.member.dwarfcannon.DwarfCannon;
import core.game.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the Lollk dialogue plugin used for Dwarf Cannon quest.
 */
public class LollkDCDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@Code LollkDialogue} {@Code Object}
	 */
	public LollkDCDialogue() {
		/**
		 * empty
		 */
	}

	/**
	 * Constructs a new {@Code LollkDialogue} {@Code Object}
	 * @param player the player.
	 */
	public LollkDCDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new LollkDCDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(DwarfCannon.NAME);
		switch (quest.getStage(player)) {
		case 40:
			npc("Thank the heavens, you saved me!", "I thought I'd be goblin lunch for sure!");
			break;
		default:
			player.sendMessage("The dwarf doesn't seem interested in talking to you.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 40:
			switch (stage) {
			case 0:
				player("Are you ok?");
				stage++;
				break;
			case 1:
				npc("I think so, I'd better run off home.");
				stage++;
				break;
			case 2:
				player("That's right, you get going. I'll catch up.");
				stage++;
				break;
			case 3:
				npc("Thanks again, brave adventurer.");
				stage++;
				break;
			case 4:
				npc.setInvisible(true);
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 207 };
	}

}
