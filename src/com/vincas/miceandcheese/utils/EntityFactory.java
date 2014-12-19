package com.vincas.miceandcheese.utils;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.vincas.miceandcheese.components.GameObjectForm;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.components.Velocity;
import com.vincas.miceandcheese.utils.math_utils.Vector;

public class EntityFactory {

	public static Entity createMouseEntity(World world, float x, float y, Vector velocity) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new Velocity(velocity));
		e.addComponent(new GameObjectForm("Mouse"));
		world.getManager(GroupManager.class).add(e, "MOUSE");
		return e;
	}

	public static Entity createCheeseEntity(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new GameObjectForm("Cheese"));
		world.getManager(TagManager.class).register("CHEESE", e);
		return e;
	}
}
