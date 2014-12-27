package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.Utils;
import com.vincas.miceandcheese.components.Attack;
import com.vincas.miceandcheese.components.Health;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.components.TimerComponent;
import com.vincas.miceandcheese.components.Velocity;
import com.vincas.miceandcheese.utils.StateIndex;
import com.vincas.miceandcheese.utils.Timer;
import com.vincas.miceandcheese.utils.math_utils.Vector;

import org.newdawn.slick.state.StateBasedGame;

public class CollisionSystem extends EntitySystem {
	@Mapper
	private ComponentMapper<Position> positionMapper;
	private StateBasedGame game;

	@SuppressWarnings("unchecked")
	public CollisionSystem(StateBasedGame game) {
		super(Aspect.getAspectForAll(Position.class));
		this.game = game;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		ImmutableBag<Entity> mice = world.getManager(GroupManager.class).getEntities("MOUSE");
		Entity cheese = world.getManager(TagManager.class).getEntity("CHEESE");
		Entity player = world.getManager(TagManager.class).getEntity("PLAYER");
		Health health = player.getComponent(Health.class);

		// Probably not the best place for it here, create new system?
		if (!health.isAlive()) {
			game.enterState(StateIndex.LOSE_GAME_STATE);
		}

		if (cheese != null && mice != null) {
			for (int i = 0; i < mice.size(); i++) {
				Entity mouse = mice.get(i);
				Position p = positionMapper.get(mouse);
				if (health.isAlive() && collisionExists(mouse, cheese, 50, new float[]{
					p.getCenterOffsetX(), p.getCenterOffsetY(), 0, 0})) {
					if (mouse.getComponent(Velocity.class) != null) {
						Vector v = mouse.getComponent(Velocity.class).getVector();
						v.setCoordinatesByLength(0);
						mouse.getComponent(Velocity.class).setVector(v);
					}
					Timer timer = mouse.getComponent(TimerComponent.class).getTimer();
					if (!timer.isTimeComplete())
						timer.update((int)world.getDelta());
					else {
						float damage = mouse.getComponent(Attack.class).getAttackDamage();
						health.addDamage(damage);
						timer.reset();
					}
				}
			}
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	/**
	 * If offset is needed, for example, collision between centers.
	 *
	 * @param e1
	 * @param e2
	 * @param distance
	 * @param offsets
	 * @return
	 */
	private boolean collisionExists(Entity e1, Entity e2, float distance, float[] offsets) {
		Position p1 = positionMapper.get(e1);
		Position p2 = positionMapper.get(e2);

		return isColiding(p1, p2, distance, offsets);
	}

	public static boolean isColiding(Position p1, Position p2, float distance, float[] offsets) {
		return Utils.distance(p1.getX() + offsets[0], p1.getY() + offsets[1],
			p2.getX() + offsets[2], p2.getY() + offsets[3]) <= distance;
	}
}
