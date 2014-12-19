package com.vincas.miceandcheese.entities;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.vincas.miceandcheese.MiceAndCheese;
import com.vincas.miceandcheese.components.Position;
import com.vincas.miceandcheese.components.Velocity;
import com.vincas.miceandcheese.utils.ResourceManager;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MouseEntity extends GameObject {
	private SpriteSheet spriteSheet;
	private Position position;
	private Animation animation;
	private float rotation;

	Polygon triangleLeft, triangleRight;

	public static final int WIDTH = 117;
	public static final int HEIGHT = 117;

	public MouseEntity(World world, Entity owner) {
		super(world, owner);
	}

	@Override
	public void initalize() {
		ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class, world);
		ComponentMapper<Velocity> velocityMapper = ComponentMapper.getFor(Velocity.class, world);
		position = positionMapper.get(owner);
		spriteSheet = new SpriteSheet(ResourceManager.getImage("mouse_sheet"), WIDTH, HEIGHT);
		rotation = velocityMapper.get(owner).getVector().getRotation();
		animation = createAnimation();
	}

	private Animation createAnimation() {
		Animation a = new Animation();
		List<Image> frames = new ArrayList<Image>();

		final float pX = position.getX() + WIDTH / 2;
		final float pY = position.getY() + HEIGHT / 2;
		final float centerX = MiceAndCheese.getGameScreenCenterX();
		final float centerY = MiceAndCheese.getGameScreenCenterY();

		final float tf = 500;

		triangleLeft = new Polygon(new float[]{-tf, -tf, -tf, MiceAndCheese.GAME_HEIGHT + tf,
			centerX, centerY});

		triangleRight = new Polygon(new float[]{MiceAndCheese.GAME_WIDTH + tf, -tf,
			MiceAndCheese.GAME_WIDTH + tf, MiceAndCheese.GAME_HEIGHT + tf,
			centerX, centerY});

		if (triangleLeft.contains(pX, pY)) {
			// Facing right
			frames.add(spriteSheet.getSprite(0, 0).getFlippedCopy(true, false));
			frames.add(spriteSheet.getSprite(1, 0).getFlippedCopy(true, false));
		} else if (triangleRight.contains(pX, pY)) {
			// Facing left
			frames.add(spriteSheet.getSprite(0, 0));
			frames.add(spriteSheet.getSprite(1, 0));
			rotation -= 180f;
		} else if (pY < centerY) {
			// Facing Bottom
			frames.add(spriteSheet.getSprite(2, 0));
			frames.add(spriteSheet.getSprite(3, 0));
			rotation -= 90f;
		} else {
			// Facing top
			frames.add(spriteSheet.getSprite(4, 0));
			frames.add(spriteSheet.getSprite(5, 0));
			rotation -= 270f;
		}

		a.setLooping(true);
		a.setAutoUpdate(true);

		Iterator<Image> frame = frames.iterator();
		while (frame.hasNext()) {
			Image frameImage = frame.next();
			frameImage.setRotation(rotation);
			a.addFrame(frameImage, 150);
		}

		return a;
	}

	@Override
	public void render(Graphics g) {
		final float posX = position.getX();
		final float posY = position.getY();
		animation.draw(posX, posY);
	}
}
