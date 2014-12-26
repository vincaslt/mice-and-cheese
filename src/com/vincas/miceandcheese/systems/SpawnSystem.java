package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.vincas.miceandcheese.MiceAndCheese;
import com.vincas.miceandcheese.entities.MouseEntity;
import com.vincas.miceandcheese.utils.EntityFactory;
import com.vincas.miceandcheese.utils.math_utils.Vector;

import java.util.Random;

public class SpawnSystem extends EntitySystem {
	private Random r;
	private float acc;
	private float interval;
	private float prevInterval;
	private float difficulty;

	private final float difficultyIncrease = 0.01f;
	private final float maxDifficulty = 3f;

	public SpawnSystem(float interval) {
		super(Aspect.getEmpty());
		this.interval = interval;
		this.difficulty = 1f;
	}

	@Override
	protected boolean checkProcessing() {
		acc += world.getDelta();
		prevInterval = interval;
		if (acc >= prevInterval) {
			acc -= prevInterval;
			return true;
		}
		return false;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		boolean isMaxDiff = difficulty >= maxDifficulty;
		if (difficulty > maxDifficulty)
			difficulty = maxDifficulty;
		interval -= isMaxDiff ? 0 : (difficulty - 0.8f);

		int appearanceSide = r.nextInt(4);
		float x = 0, y = 0;
		switch (appearanceSide) {
			case 0: //Left
				x = -MouseEntity.WIDTH;
				y = r.nextInt(MiceAndCheese.GAME_HEIGHT + MouseEntity.HEIGHT) - MouseEntity.HEIGHT;
				break;
			case 1: // Top
				x = r.nextInt(MiceAndCheese.GAME_WIDTH + MouseEntity.WIDTH) - MouseEntity.WIDTH;
				y = -MouseEntity.HEIGHT;
				break;
			case 2: // Right
				x = MiceAndCheese.GAME_WIDTH;
				y = r.nextInt(MiceAndCheese.GAME_HEIGHT + MouseEntity.HEIGHT) - MouseEntity.HEIGHT;
				break;
			case 3: // Bottom
				x = r.nextInt(MiceAndCheese.GAME_WIDTH + MouseEntity.WIDTH) - MouseEntity.WIDTH;
				y = MiceAndCheese.GAME_HEIGHT;
				break;
		}
		float speed = (r.nextFloat() * (3f) + 3f) * difficulty;
		difficulty += isMaxDiff ? 0 : difficultyIncrease;

		EntityFactory.createMouseEntity(world, x, y,
			new Vector(x, y,
				MiceAndCheese.getGameScreenCenterX() - MouseEntity.WIDTH / 2,
				MiceAndCheese.getGameScreenCenterY() - MouseEntity.HEIGHT / 2, speed))
			.addToWorld();
	}

	@Override
	protected void initialize() {
		r = new Random();
	}
}
