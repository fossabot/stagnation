package content.region.fremennik.waterbirth.handlers;

import config.Items;
import content.region.fremennik.waterbirth.npc.DagannothKingNPC;
import content.region.fremennik.waterbirth.npc.SpinolypNPC;
import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.SceneryDefinition;
import core.game.dialogue.DialogueAction;
import core.game.global.action.ClimbActionHandler;
import core.game.interaction.Option;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.skill.Skills;
import core.game.node.scenery.SceneryBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.game.world.map.zone.ZoneRestriction;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.plugin.ClassScanner;
import core.plugin.Initializable;
import core.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the Waterbirth dungeon area.
 */
@Initializable
public final class WaterBirthDungeonZone extends MapZone implements Plugin<Object> {

	/**
	 * The door support locations.
	 */
	private static final Location[] DOOR_SUPPORTS = new Location[] { Location.create(2545, 10145, 0), Location.create(2543, 10143, 0), Location.create(2545, 10141, 0) };

	/**
	 * Constructs a new {@code WaterBirthDungeonZone} {@code Object}.
	 */
	public WaterBirthDungeonZone() {
		super("Water birth dungeon", true, ZoneRestriction.RANDOM_EVENTS);
		ClassScanner.definePlugin(new DagannothKingNPC());
		ClassScanner.definePlugin(new DoorSupportNPC());
		ClassScanner.definePlugin(new DungeonOptionHandler());
		ClassScanner.definePlugin(new SpinolypNPC());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public boolean move(Entity e, Location from, Location to) {
		for (Location location : DOOR_SUPPORTS) {
			if (location.equals(to)) {
				List<NPC> npcs = RegionManager.getLocalNpcs(location, 0);
				NPC npc = null;
				if (npcs.size() != 0) {
					npc = npcs.get(0);
				}
				if (npc != null && npc instanceof DoorSupportNPC) {
					DoorSupportNPC door = (DoorSupportNPC) npc;
					if (door.getId() != door.getOriginalId()) {
						return true;
					}
				}
				return false;
			}
		}
		if (e instanceof Player) {
			final Player p = (e.asPlayer());
			if (p.getLocation().equals(new Location(2545, 10144, 0)) || p.getLocation().equals(new Location(2545, 10142, 0))) {
				final List<NPC> eggs = new ArrayList<>(20);
				NPC n = Repository.findNPC(new Location(2546, 10142, 0));
				if (n != null && n.getId() == 2449) {
					eggs.add(n);
				}
				n = Repository.findNPC(new Location(2546, 10144, 0));
				if (n != null && n.getId() == 2449) {
					eggs.add(n);
				}
				if (eggs.size() == 0) {
					return true;
				}
				for (NPC npc : eggs) {
					if (npc.getAttribute("transforming", 0) > GameWorld.getTicks()) {
						return true;
					}
					npc.setAttribute("transforming", GameWorld.getTicks() + 3);
				}
				GameWorld.getPulser().submit(new Pulse(1) {
					int counter;

					@Override
					public boolean pulse() {
						switch (++counter) {
						case 1:
							for (NPC n : eggs) {
								n.transform(n.getId() + 1);
							}
							break;
						case 3:
							final List<NPC> spawns = new ArrayList<>(20);
							for (NPC n : eggs) {
								n.transform(n.getId() + 1);
								NPC spawn = NPC.create(2454, n.getLocation().transform(-1, 0, 0));
								spawn.init();
								spawn.setWalks(true);
								spawn.setAggressive(true);
								spawn.setRespawn(false);
								spawn.attack(p);
								spawns.add(spawn);
							}
							GameWorld.getPulser().submit(new Pulse(GameWorld.getSettings().isDevMode() ? 10 : 45) {

								@Override
								public boolean pulse() {
									for (NPC n : eggs) {
										n.reTransform();
									}
									for (NPC spawn : spawns) {
										if (spawn.isActive() && !spawn.inCombat()) {
											spawn.clear();
										}
									}
									return true;
								}

							});
							break;
						}
						return counter == 3;
					}

				});
			}
		}
		return super.move(e, from, to);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			final Player player = (Player) e;
			switch (target.getId()) {
			case 8966:
				player.getProperties().setTeleportLocation(Location.create(2523, 3740, 0));
				return true;
			case 10193:
				player.getProperties().setTeleportLocation(new Location(2545, 10143, 0));
				return true;
			case 10177:
				switch (option.getName()) {
				case "Climb":
					player.getDialogueInterpreter().sendOptions("Select an Option", "Climb Up.", "Climb Down.");
					player.getDialogueInterpreter().addAction(new DialogueAction() {

						@Override
						public void handle(Player player, int buttonId) {
							switch (buttonId) {
							case 1:
								ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, new Location(2544, 3741, 0));
								break;
							case 2:
								ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_DOWN, new Location(1799, 4406, 3));
								break;
							}
						}

					});
					break;
				case "Climb-down":
					ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_DOWN, new Location(1799, 4406, 3));
					break;
				case "Climb-up":
					ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, new Location(2544, 3741, 0));
					break;
				}
				return true;
			case 10217:
				player.sendMessage("You need to have completed Horror from the Deep in order to do this.");
				return true;
			case 10230:
				player.teleport(Location.create(2899, 4450, 0));
				return true;
			case 10229:
				player.teleport(Location.create(1912, 4367, 0));
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2423, 10090, 2585, 10195));
		registerRegion(7236);
		registerRegion(7492);
		registerRegion(7748);
		registerRegion(11589);
	}

	/**
	 * Handles the dungeon options.
	 */
	public static final class DungeonOptionHandler extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			SceneryDefinition.forId(8958).getHandlers().put("option:open", this);
			SceneryDefinition.forId(8959).getHandlers().put("option:open", this);
			SceneryDefinition.forId(8960).getHandlers().put("option:open", this);
			NPCDefinition.forId(2440).getHandlers().put("option:destroy", this);
			NPCDefinition.forId(2443).getHandlers().put("option:destroy", this);
			NPCDefinition.forId(2446).getHandlers().put("option:destroy", this);
			for (Location l : DOOR_SUPPORTS) {
				SceneryBuilder.remove(RegionManager.getObject(l));
			}
			return this;
		}
		
		/**
		 * function to handle activation of pressure pads
		 * allows both players and pet rocks to activate the pad
		 */
		private boolean pressurePadActivated(Player player, Location location) {
			if (RegionManager.getLocalPlayers(location,0).size() > 0) {
				return true;
			}
			if (RegionManager.getRegionPlane(location).getItem(Items.PET_ROCK_3695,location,player) != null) {
				return true;
			}
			return false;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			switch (node.getId()) {
			case 8958:
			case 8959:
			case 8960:
				boolean behind = player.getLocation().getX() >= 2492;
				if (!behind) {
					if (!pressurePadActivated(player,node.getLocation().transform(-1, 0, 0)) || !pressurePadActivated(player,node.getLocation().transform(-1, 2, 0))) {
						player.sendMessage("You cannot see a way to open this door...");
						break;
					}
				}
				if (!node.isActive()) {
					return true;
				}
				SceneryBuilder.replace(node.asScenery(), node.asScenery().transform(8962), 30);
				break;
			case 2440:
			case 2443:
			case 2446:
				int x = node.getLocation().getX();
				int y = node.getLocation().getY();
				boolean canAttack = true;
				if (x == 2545 && y == 10145 && player.getLocation().getY() > y) {// top
					// north
					canAttack = false;
				} else if (x == 2543 && y == 10143 && player.getLocation().getX() < x) {// middle
					canAttack = false;
				} else if (x == 2545 && y == 10141 && player.getLocation().getY() < y) {// south
					canAttack = false;
				}
				if (!canAttack) {
					player.sendMessage("The door is propped securely shut from this side...");
					break;
				}
				player.attack(node);
				break;
			}
			return true;
		}

		@Override
		public Location getDestination(Node node, Node n) {
			if (n.getName().equals("Door-support")) {
				Player player = node.asPlayer();
				if (player.getProperties().getCombatPulse().getStyle() != CombatStyle.MELEE) {
					return node.getLocation();
				}
			}
			return null;
		}
	}

	/**
	 * Represents a door support npc.
	 */
	public static final class DoorSupportNPC extends AbstractNPC {

		/**
		 * The death spawn.
		 */
		private long deathSpawn = -1;

		/**
		 * Constructs a new {@Code DoorSupportNPC} {@Code Object}
		 */
		public DoorSupportNPC() {
			super(-1, null);
		}

		/**
		 * Constructs a new {@Code DoorSupportNPC} {@Code Object}
		 * @param id the id.
		 * @param location the location.
		 */
		public DoorSupportNPC(int id, Location location) {
			super(id, location);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new DoorSupportNPC(id, location);
		}

		@Override
		public void init() {
			lock();
			super.init();
			getSkills().setStaticLevel(Skills.HITPOINTS, 1);
			getSkills().setLevel(Skills.HITPOINTS, 1);
			getSkills().setLifepoints(1);
			getProperties().setDeathAnimation(new Animation(-1));
		}

		@Override
		public void handleTickActions() {
			if (deathSpawn != -1 && deathSpawn < GameWorld.getTicks()) {
				deathSpawn = -1;
				transform(getOriginalId());
				getSkills().setStaticLevel(Skills.HITPOINTS, 1);
				getSkills().setLevel(Skills.HITPOINTS, 1);
				getSkills().setLifepoints(1);
				fullRestore();
				getSkills().heal(100);
			}
		}

		@Override
		public boolean face(Entity entity) {
			return false;
		}

		@Override
		public boolean faceLocation(Location locatin) {
			return false;
		}

		@Override
		public void checkImpact(BattleState state) {
			getSkills().setStaticLevel(Skills.HITPOINTS, 1);
			getSkills().setLevel(Skills.HITPOINTS, 1);
			getSkills().setLifepoints(1);
			getSkills().setLifepoints(1);
			state.setEstimatedHit(1);
			lock();
			getWalkingQueue().reset();
		}

		@Override
		public boolean canStartCombat(Entity victim) {
			return false;
		}

		@Override
		public void commenceDeath(Entity killer) {
			getAnimator().reset();
			transform(getOriginalId() + 1);
			lock();
		}

		@Override
		public void finalizeDeath(Entity killer) {
			getAnimator().reset();
			transform(getOriginalId() + 2);
			deathSpawn = GameWorld.getTicks() + 55;
			lock();

		}

		@Override
		public boolean isAttackable(Entity entity, CombatStyle style, boolean message) {
			if (getId() != getOriginalId()) {
				return false;
			}
			if (entity.getLocation().getDistance(getLocation()) <= 3) {
				if (entity instanceof Player) {
					Player player = entity.asPlayer();
                    if(message) {
                        player.sendMessage("The door is propped securely shut from this side...");
                    }
				}
				return false;
			}
			return style != CombatStyle.MELEE;
		}

		@Override
		public int[] getIds() {
			return new int[] { 2440, 2443, 2446 };
		}

	}
}
