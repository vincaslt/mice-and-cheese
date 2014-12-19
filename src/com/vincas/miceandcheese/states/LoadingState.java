package com.vincas.miceandcheese.states;

import com.vincas.miceandcheese.utils.ResourceManager;
import com.vincas.miceandcheese.utils.StateIndex;
import com.vincas.miceandcheese.utils.Timer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class LoadingState extends NiftyBasicGameState {
	private int stateId = 0;
	private static final int WAIT_TIME_BEFORE_NEXT_RESOURCE_LOAD = 100;
	private Timer waitTimer;

	public LoadingState(int stateId) {
		this.stateId = stateId;
		waitTimer = new Timer(WAIT_TIME_BEFORE_NEXT_RESOURCE_LOAD);
	}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) {
		g.drawString("Loading... " + ResourceManager.getProgress() + "%",
			gameContainer.getWidth() / 2 - 80, gameContainer.getHeight() / 2 - 60);
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
		if (ResourceManager.isLoadComplete()) {
			stateBasedGame.enterState(StateIndex.MAIN_MENU_STATE, new FadeOutTransition(), new FadeInTransition());
		} else {
			waitTimer.update(delta);
			if (waitTimer.isTimeComplete()) {
				ResourceManager.loadNextResource();
				waitTimer.resetTime();
			}
		}
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame) {

	}

	@Override
	public int getID() {
		return stateId;
	}
}
