package content.global.handlers.iface;

import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.node.entity.player.Player;
import core.plugin.Initializable;
import core.plugin.Plugin;

import static core.api.ContentAPIKt.getVarp;
import static core.api.ContentAPIKt.setVarp;

/**
 * Handles the setting tab buttons.
 */
@Initializable
public class SettingTabInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(261, this);
		return this;
	}

	@Override
	public boolean handle(Player p, Component component, int opcode, int button, int slot, int itemId) {
		switch (button) {
		case 10:// brightness
			int brightness = button - 7;
			p.getSettings().setBrightness(brightness);
			break;
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:// music
			int volume = 15 - button;
			p.getSettings().setMusicVolume(volume);
			break;
		case 16://530 settings
		/*	if (TutorialSession.getExtension(p).getStage() != TutorialSession.MAX_STAGE) {
				p.sendMessage("You must finish the tutorial before opening the graphic settings.");
				break;
			}*/
			p.getInterfaceManager().open(new Component(742));
			break;
		case 18://530 settings
			p.getInterfaceManager().open(new Component(743));
			break;
		case 17:
		case 19:
		case 20:// sonund
			int volume1 = 20 - button;
			p.getSettings().setSoundEffectVolume(volume1);
			break;
		case 29:
		case 30:
		case 31:
		case 32:
		case 33:// all sound
			int volume11 = 33 - button;
			p.getSettings().setAreaSoundVolume(volume11);
			break;
		case 6:// mouse
			p.getSettings().toggleMouseButton();
			break;
		case 4:// effects
			p.getSettings().toggleChatEffects();
			break;
		case 5:// private chat
			p.getSettings().toggleSplitPrivateChat();
			break;
		case 7:// aid
			if (p.getIronmanManager().checkRestriction()) {
				return true;
			}
			p.getSettings().toggleAcceptAid();
			break;
		case 3:// run
			p.getSettings().toggleRun();
			break;
		case 8:// house
			p.getInterfaceManager().close();
			setVarp(p, 261, getVarp(p, 261) & 0x1);
			p.getInterfaceManager().openSingleTab(new Component(398));
			break;
		}
		return true;
	}
}
