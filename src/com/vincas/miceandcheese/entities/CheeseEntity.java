package com.vincas.miceandcheese.entities;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.vincas.miceandcheese.components.Health;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.utils.ResourceManager;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class CheeseEntity extends GameObject {
	private Position position;
	private Health health;
	private Image image;

	public static final int WIDTH = 300;
	public static final int HEIGHT = 305;

	public CheeseEntity(World world, Entity owner)
	{
		super(world, owner);
	}

	@Override
	public void initalize() {
		ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class, world);
		ComponentMapper<Health> healthMapper = ComponentMapper.getFor(Health.class, world);
		health = healthMapper.get(owner);
		position = positionMapper.get(owner);
		image = ResourceManager.getImage("cheese");
	}

	@Override
	public void render(Graphics g) {
		// Anchor point is center for cheese, it might be a bit confusing, so think through...
		float scale = health.getHealth() / health.getMaximumHealth();
		if (health.isAlive())
			image.draw(position.getX() - (WIDTH / 2) * scale, position.getY() - (HEIGHT / 2) * scale, scale);
	}
}
