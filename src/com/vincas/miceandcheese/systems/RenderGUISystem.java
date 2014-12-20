package com.vincas.miceandcheese.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.vincas.miceandcheese.MiceAndCheese;
import com.vincas.miceandcheese.components.Score;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class RenderGUISystem extends EntityProcessingSystem {

	private GameContainer container;
	private Graphics g;
	@Mapper
	private ComponentMapper<Score> scoreMapper;

	public RenderGUISystem(GameContainer container) {
		super(Aspect.getAspectForAll(Score.class));
		this.container = container;
		this.g = container.getGraphics();
	}

	@Override
	protected void process(Entity e) {
		Score score = scoreMapper.get(e);
		g.setColor(Color.yellow);
		g.drawString("Score: " + score.getScore(), MiceAndCheese.GAME_WIDTH - 400, 20);
	}
}
