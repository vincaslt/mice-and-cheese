package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.Bag;
import com.vincas.miceandcheese.components.GameObjectForm;
import com.vincas.miceandcheese.components.KillOnClick;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.components.Score;
import com.vincas.miceandcheese.entities.CheeseEntity;
import com.vincas.miceandcheese.entities.GameObject;
import com.vincas.miceandcheese.entities.KillableEntity;
import com.vincas.miceandcheese.entities.MouseEntity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class RenderSystem extends EntityProcessingSystem {
	@Mapper private ComponentMapper<Position> positionMapper;
	@Mapper private ComponentMapper<GameObjectForm> gameObjectFormMapper;
	private Bag<GameObject> gameObjects;
	private GameContainer gameContainer;
	private Graphics graphics;

	public RenderSystem(GameContainer gameContainer) {
		super(Aspect.getAspectForAll(Position.class, GameObjectForm.class));
		this.gameContainer = gameContainer;
		this.gameObjects = new Bag<GameObject>();
		this.graphics = gameContainer.getGraphics();
	}

	@Override
	protected void process(Entity e) {
		GameObject gameObject = gameObjects.get(e.getId());
		Position position = positionMapper.get(e);
		KillOnClick killE = e.getComponent(KillOnClick.class);
		Score score = world.getManager(TagManager.class).getEntity("CHEESE").getComponent(Score.class);

		if (gameObject instanceof KillableEntity && killE != null) {
			switch (killE.getState()) {
				case PROCESSING:
					((KillableEntity) gameObject).die();
					score.incrementScore(killE.getReward());
					break;
				case FINISHED:
					e.deleteFromWorld();
					break;
			}
		}

		// Quick hack, make more flexible later
		float width = gameObject instanceof MouseEntity ? MouseEntity.WIDTH : 0;
		float height = gameObject instanceof MouseEntity ? MouseEntity.WIDTH : 0;

		if (position.getX() + width >= 0 && position.getY() + height >= 0 &&
			position.getX() < gameContainer.getWidth() &&
			position.getY() < gameContainer.getHeight() && gameObject != null) {

			gameObject.render(graphics);
		}
	}

	@Override
	protected void inserted(Entity e)
	{
		GameObject gameObject = createGameObject(e);

		if (gameObject != null)
		{
			gameObject.initalize();
			gameObjects.set(e.getId(), gameObject);
		}
	}

	@Override
	protected void removed(Entity e)
	{
		gameObjects.set(e.getId(), null);
	}

	private GameObject createGameObject(Entity e)
	{
		GameObjectForm gameObjectForm = gameObjectFormMapper.get(e);
		String spatialFormFile = gameObjectForm.getGameObjectFormName();

		if ("Cheese".equalsIgnoreCase(spatialFormFile))
			return new CheeseEntity(world, e);
		else if ("Mouse".equalsIgnoreCase(spatialFormFile))
			return new MouseEntity(world, e);

		return null;
	}
}
