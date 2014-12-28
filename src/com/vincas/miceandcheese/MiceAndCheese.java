package com.vincas.miceandcheese;

import com.artemis.World;
import com.vincas.miceandcheese.states.GameState;
import com.vincas.miceandcheese.states.LoadingState;
import com.vincas.miceandcheese.states.LoseGameState;
import com.vincas.miceandcheese.states.MainMenuState;
import com.vincas.miceandcheese.utils.ResourceManager;
import com.vincas.miceandcheese.utils.StateIndex;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.lessvoid.nifty.slick2d.NiftyStateBasedGame;

public class MiceAndCheese extends NiftyStateBasedGame {

	public static final int RES_WIDTH = 1600; // Real resolution
	public static final int RES_HEIGHT = 900;
	public static Dimension SCREEN_SIZE; // Screen size to scale to
	public static int GAME_WIDTH = RES_WIDTH; // Actual playing area, change later
	public static int GAME_HEIGHT = RES_HEIGHT;
	public static boolean RES_FULLSCREEN = true;

	private static AppGameContainer app;
	public static World gameWorld;

	public MiceAndCheese(String gameName) {
		super(gameName);
		ResourceManager.init();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		SCREEN_SIZE = toolkit.getScreenSize();
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		addState(new LoadingState(StateIndex.LOADING_STATE));
		addState(new MainMenuState(StateIndex.MAIN_MENU_STATE));
		addState(new GameState(StateIndex.GAME_STATE));
		addState(new LoseGameState(StateIndex.LOSE_GAME_STATE));
	}

	public void launch() throws SlickException {
		app = new AppGameContainer(new ScalableGame(this, RES_WIDTH, RES_HEIGHT));
		app.setAlwaysRender(true);
		app.setDisplayMode(SCREEN_SIZE.width, SCREEN_SIZE.height, RES_FULLSCREEN);
		app.start();
	}
	
	public static void toggleFullscreen() {
		try {
			RES_FULLSCREEN = !RES_FULLSCREEN;
			if (!MiceAndCheese.RES_FULLSCREEN) {
				app.setDisplayMode(RES_WIDTH, RES_HEIGHT, RES_FULLSCREEN);
			} else {
				app.setDisplayMode(SCREEN_SIZE.width, SCREEN_SIZE.height, RES_FULLSCREEN);
			}
		} catch (SlickException e) {
			Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static float getGameScreenCenterX() {
		return GAME_WIDTH / 2;
	}

	public static float getGameScreenCenterY() {
		return GAME_HEIGHT / 2;
	}
}
