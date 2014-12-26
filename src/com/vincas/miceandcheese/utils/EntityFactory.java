package com.vincas.miceandcheese.utils;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.vincas.miceandcheese.components.*;
import com.vincas.miceandcheese.components.TimerComponent;
import com.vincas.miceandcheese.entities.MouseEntity;
import com.vincas.miceandcheese.utils.math_utils.Vector;

public class EntityFactory {

	public static Entity createPlayerEntity(World world, float health) {
		Entity e = world.createEntity();
		e.addComponent(new Score());
		e.addComponent(new Health(health));
		e.addComponent(new Accuracy());
		world.getManager(TagManager.class).register("PLAYER", e);
		return e;
	}

	public static Entity createMouseEntity(World world, float x, float y, Vector velocity) {
		Entity e = world.createEntity();
		e.addComponent(new Position(x, y, MouseEntity.WIDTH / 2, MouseEntity.HEIGHT / 2));
		e.addComponent(new Velocity(velocity));
		e.addComponent(new GameObjectForm("Mouse"));
		e.addComponent(new TimerComponent(200)); // Attack Timer
		e.addComponent(new Attack(1f));
		e.addComponent(new KillOnClick(
			Math.max(MouseEntity.HEIGHT / 3.25f, MouseEntity.WIDTH / 3.25f), (int)velocity.getLength()));
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
