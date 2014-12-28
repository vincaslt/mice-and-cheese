package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.utils.ImmutableBag;
import com.vincas.miceandcheese.components.Accuracy;
import com.vincas.miceandcheese.components.Health;
import com.vincas.miceandcheese.components.KillOnClick;
import com.vincas.miceandcheese.components.Position;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.MouseButtonControl;

public class InputSystem extends EntitySystem implements InputProviderListener {
	@Mapper
	private ComponentMapper<Position> positionMapper;
	@Mapper
	private ComponentMapper<KillOnClick> killMapper;

	private ImmutableBag<Entity> entities;

	private Command COMMAND_SHOOT = new BasicCommand("shoot");
	private InputProvider inputProvider;
	private GameContainer gameContainer;

	@SuppressWarnings("unchecked")
	public InputSystem(GameContainer gameContainer) {
		super(Aspect.getAspectForAll(Position.class, KillOnClick.class));

		this.gameContainer = gameContainer;

		inputProvider = new InputProvider(gameContainer.getInput());
		inputProvider.addListener(this);
		inputProvider.bindCommand(new MouseButtonControl(Input.MOUSE_LEFT_BUTTON), COMMAND_SHOOT);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		this.entities = entities;
	}

	public void controlPressed(Command command) {
		Entity player = world.getManager(TagManager.class).getEntity("PLAYER");
		Health health = player.getComponent(Health.class);
		if (!health.isAlive())
			return;

		if (command.equals(COMMAND_SHOOT) && entities != null) {
			Accuracy acc = player.getComponent(Accuracy.class);
			int x = gameContainer.getInput().getMouseX();
			int y = gameContainer.getInput().getMouseY();
			Position p = new Position(x, y);

			for (int i = entities.size() - 1, s = 0; s <= i; i--) {
				Entity e = entities.get(i);
				KillOnClick killable = killMapper.get(e);
				Position pos = positionMapper.get(e);
				if (CollisionSystem.isColiding(pos, p, killable.getRadius(), new float[]{
					pos.getCenterOffsetX(), pos.getCenterOffsetY(), 0, 0})) {
					killable.setState(KillOnClick.State.PROCESSING);
					acc.hit();
					return; // Probably a bad practice to have a return here...
				}
			}
			acc.miss();
		}
	}

	public void controlReleased(Command command) {
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
