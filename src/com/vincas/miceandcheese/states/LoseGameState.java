package com.vincas.miceandcheese.states;

import com.vincas.miceandcheese.utils.StateIndex;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;

public class LoseGameState extends NiftyBasicGameState {
	private int stateId;
	StateBasedGame game;

	public LoseGameState(int id) {
		stateId = id;
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		game.enterState(StateIndex.GAME_STATE, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	protected void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) {
		g.setColor(Color.magenta);
		g.drawString("You have lost!",
			gameContainer.getWidth() / 2 - 80,
			gameContainer.getHeight() / 2 - 60);
	}

	@Override
	protected void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame) {
		this.game = stateBasedGame;
	}

	@Override
	public int getID() {
		return stateId;
	}
}
