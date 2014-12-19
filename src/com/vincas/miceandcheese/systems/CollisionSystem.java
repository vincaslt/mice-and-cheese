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
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.components.Velocity;
import com.vincas.miceandcheese.entities.CheeseEntity;
import com.vincas.miceandcheese.entities.MouseEntity;
import com.vincas.miceandcheese.utils.math_utils.Vector;

public class CollisionSystem extends EntitySystem {
	@Mapper private ComponentMapper<Position> positionMapper;

	public CollisionSystem() {
		super(Aspect.getAspectForAll(Position.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		ImmutableBag<Entity> mice = world.getManager(GroupManager.class).getEntities("MOUSE");
		Entity cheese  = world.getManager(TagManager.class).getEntity("CHEESE");

		if (cheese != null && mice != null) {
			for (int i = 0; i < mice.size(); i++) {
				Entity mouse = mice.get(i);
				if (collisionExists(mouse, cheese, 50, new float[]{
					MouseEntity.WIDTH / 2, MouseEntity.HEIGHT / 2,
					CheeseEntity.WIDTH / 2, CheeseEntity.HEIGHT / 2
				}) && mouse.getComponent(Velocity.class) != null) {
					Vector v = mouse.getComponent(Velocity.class).getVector();
					v.setCoordinatesByLength(0);
					mouse.getComponent(Velocity.class).setVector(v);
				}
			}
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	private boolean collisionExists(Entity e1, Entity e2, float distance) {
		Position p1 = positionMapper.get(e1);
		Position p2 = positionMapper.get(e2);
		return p1.getDistanceTo(p2) < distance;
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

		return Utils.distance(p1.getX() + offsets[0], p1.getY() + offsets[1],
			p2.getX() + offsets[2], p2.getY() + offsets[3]) <= distance;
	}
}
