package com.vincas.miceandcheese.states;

import com.vincas.miceandcheese.utils.StateIndex;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class MainMenuState extends NiftyBasicGameState{
	private int stateId;
	StateBasedGame game;

	public MainMenuState(int id) {
		stateId = id;
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame) {
		this.game = stateBasedGame;
		// TODO Load GUI
	}

	@Override
	protected void renderGame(GameContainer gameContainer, StateBasedGame game, Graphics g) {
		g.drawString("Press any mouse button to play.",
			gameContainer.getWidth() / 2 - 140,
			gameContainer.getHeight() / 2 - 60);
	}

	@Override
	protected void updateGame(GameContainer container, StateBasedGame game, int delta) {

	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (button == 1)
			System.exit(0);
		game.enterState(StateIndex.GAME_STATE, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return stateId;
	}
}
