package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.components.Velocity;

public class MovementSystem extends EntityProcessingSystem {
	@Mapper
	private ComponentMapper<Velocity> velocityMapper;
	@Mapper
	private ComponentMapper<Position> positionMapper;

	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Aspect.getAspectForAll(Velocity.class, Position.class));
	}

	@Override
	protected void process(Entity e) {
		Velocity v = velocityMapper.get(e);
		Position p = positionMapper.get(e);
		p.addX(v.getX() * world.getDelta() / 100);
		p.addY(v.getY() * world.getDelta() / 100);
	}
}
