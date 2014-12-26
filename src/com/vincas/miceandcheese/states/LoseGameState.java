package com.vincas.miceandcheese.states;

import com.artemis.World;
import com.artemis.managers.TagManager;
import com.vincas.miceandcheese.MiceAndCheese;
import com.vincas.miceandcheese.components.Accuracy;
import com.vincas.miceandcheese.components.Score;
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
	Accuracy acc;
	Score score;

	public LoseGameState(int id) {
		stateId = id;
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		game.enterState(StateIndex.GAME_STATE, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	protected void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) {
		g.setColor(Color.cyan);
		g.drawString("You have lost!",
			gameContainer.getWidth() / 2 - 80,
			gameContainer.getHeight() / 2 - 60);
		g.setColor(Color.green);
		g.drawString("Accuracy: " + acc.getPercentage() + "%",
			gameContainer.getWidth() / 2 - 80,
			300);
		g.setColor(Color.yellow);
		g.drawString("Score: " + score.getScore(),
			gameContainer.getWidth() / 2 - 80,
			260);

	}

	@Override
	protected void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) {

	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame) {
		this.game = stateBasedGame;
	}

	@Override
	public void enterState(GameContainer container, StateBasedGame game) {
		// So that mouse click doesn't activate anything.
		// There is probably a smarter way to do all this...
		World world = MiceAndCheese.gameWorld;
		acc = world.getManager(TagManager.class).getEntity("PLAYER").getComponent(Accuracy.class);
		score = world.getManager(TagManager.class).getEntity("PLAYER").getComponent(Score.class);
		for (int i = 0; i < world.getSystems().size(); i++) {
			MiceAndCheese.gameWorld.deleteSystem(world.getSystems().get(i));
		}
	}

	@Override
	public int getID() {
		return stateId;
	}
}
