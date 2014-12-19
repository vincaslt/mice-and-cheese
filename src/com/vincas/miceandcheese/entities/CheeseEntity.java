package com.vincas.miceandcheese.entities;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.utils.ResourceManager;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class CheeseEntity extends GameObject {
	private Position position;
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
		position = positionMapper.get(owner);
		image = ResourceManager.getImage("cheese");
	}

	@Override
	public void render(Graphics g) {
		image.draw(position.getX(), position.getY());
	}
}
