package com.vincas.miceandcheese;

import com.vincas.miceandcheese.states.GameState;
import com.vincas.miceandcheese.states.LoadingState;
import com.vincas.miceandcheese.states.LoseGameState;
import com.vincas.miceandcheese.states.MainMenuState;
import com.vincas.miceandcheese.utils.ResourceManager;
import com.vincas.miceandcheese.utils.StateIndex;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import de.lessvoid.nifty.slick2d.NiftyStateBasedGame;

public class MiceAndCheese extends NiftyStateBasedGame {

	public static final int RES_WIDTH = 1280;
	public static final int RES_HEIGHT = 800;
	public static final int GAME_WIDTH = RES_WIDTH;
	public static final int GAME_HEIGHT = RES_HEIGHT;
	public static final boolean RES_FULLSCREEN = false;

	public MiceAndCheese(String gameName) {
		super(gameName);
		ResourceManager.init();
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		addState(new LoadingState(StateIndex.LOADING_STATE));
		addState(new MainMenuState(StateIndex.MAIN_MENU_STATE));
		addState(new GameState(StateIndex.GAME_STATE));
		addState(new LoseGameState(StateIndex.LOSE_GAME_STATE));

	}

	public void launch() throws SlickException {
		AppGameContainer app = new AppGameContainer(this);

		app.setDisplayMode(RES_WIDTH, RES_HEIGHT, RES_FULLSCREEN);
		app.setTargetFrameRate(60);
		app.start();
	}

	public static float getGameScreenCenterX() {
		return GAME_WIDTH / 2;
	}

	public static float getGameScreenCenterY() {
		return GAME_HEIGHT / 2;
	}
}
