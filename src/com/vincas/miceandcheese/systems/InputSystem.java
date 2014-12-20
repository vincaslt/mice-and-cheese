package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.vincas.miceandcheese.components.GameObjectForm;

import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

public class InputSystem extends EntityProcessingSystem implements InputProviderListener {
	private Command COMMAND_SHOOT = new BasicCommand("shoot");

	public InputSystem() {
		super(Aspect.getAspectForAll(GameObjectForm.class));
	}

	@Override
	protected void process(Entity e) {
		//TODO
	}

	@Override
	public void controlPressed(Command command) {
		//TODO
	}

	@Override
	public void controlReleased(Command command) {
		//TODO
	}
}
