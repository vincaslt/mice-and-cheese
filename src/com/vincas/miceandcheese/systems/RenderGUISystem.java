package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.vincas.miceandcheese.MiceAndCheese;
import com.vincas.miceandcheese.components.Accuracy;
import com.vincas.miceandcheese.components.Health;
import com.vincas.miceandcheese.components.Score;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class RenderGUISystem extends EntityProcessingSystem {

	private GameContainer container;
	private Graphics g;
	@Mapper
	private ComponentMapper<Score> scoreMapper;
	@Mapper
	private ComponentMapper<Accuracy> accuracyMapper;
	@Mapper
	private ComponentMapper<Health> healthMapper;

	public RenderGUISystem(GameContainer container) {
		super(Aspect.getAspectForAll(Score.class, Accuracy.class, Health.class));
		this.container = container;
		this.g = container.getGraphics();
	}

	@Override
	protected void process(Entity e) {
		Score score = scoreMapper.get(e);
		Accuracy acc = accuracyMapper.get(e);
		Health hp = healthMapper.get(e);
		g.setColor(Color.red);
		g.drawString("Health: " + hp.getHealthPercentage() + "%", MiceAndCheese.GAME_WIDTH - 600, 20);
		g.setColor(Color.green);
		g.drawString("Accuracy: " + acc.getPercentage(), MiceAndCheese.GAME_WIDTH - 400, 20);
		g.setColor(Color.yellow);
		g.drawString("Score: " + score.getScore(), MiceAndCheese.GAME_WIDTH - 200, 20);

	}
}
