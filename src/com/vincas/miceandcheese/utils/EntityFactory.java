package com.vincas.miceandcheese.utils;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.vincas.miceandcheese.components.*;
import com.vincas.miceandcheese.components.TimerComponent;
import com.vincas.miceandcheese.utils.math_utils.Vector;

public class EntityFactory {

	public static Entity createMouseEntity(World world, float x, float y, Vector velocity) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new Velocity(velocity));
		e.addComponent(new GameObjectForm("Mouse"));
		e.addComponent(new TimerComponent(200)); // Attack Timer
		e.addComponent(new Attack(1));
		world.getManager(GroupManager.class).add(e, "MOUSE");
		return e;
	}

	public static Entity createCheeseEntity(World world, float x, float y) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y));
		e.addComponent(new GameObjectForm("Cheese"));
		e.addComponent(new Score());
		e.addComponent(new Health(100f));
		world.getManager(TagManager.class).register("CHEESE", e);
		return e;
	}
}
