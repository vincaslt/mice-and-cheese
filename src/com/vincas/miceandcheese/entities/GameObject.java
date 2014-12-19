package com.vincas.miceandcheese.entities;

import com.artemis.Entity;
import com.artemis.World;

import org.newdawn.slick.Graphics;

public abstract class GameObject {
	protected World world;
	protected Entity owner;

	public GameObject(World world, Entity owner)
	{
		this.world = world;
		this.owner = owner;
	}

	public abstract void initalize();

	public abstract void render(Graphics g);
}
