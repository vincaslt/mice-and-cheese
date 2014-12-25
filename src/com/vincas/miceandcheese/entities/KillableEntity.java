package com.vincas.miceandcheese.entities;

import com.artemis.Entity;
import com.artemis.World;

public abstract class KillableEntity extends GameObject{

	public KillableEntity(World world, Entity owner) {
		super(world, owner);
	}

	public abstract void die();
}
