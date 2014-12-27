package com.vincas.miceandcheese.states;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.vincas.miceandcheese.LaunchGame;
import com.vincas.miceandcheese.MiceAndCheese;
import com.vincas.miceandcheese.systems.CollisionSystem;
import com.vincas.miceandcheese.systems.InputSystem;
import com.vincas.miceandcheese.systems.MovementSystem;
import com.vincas.miceandcheese.systems.RenderGUISystem;
import com.vincas.miceandcheese.systems.RenderSystem;
import com.vincas.miceandcheese.systems.SpawnSystem;
import com.vincas.miceandcheese.utils.EntityFactory;
import com.vincas.miceandcheese.utils.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;

public class GameState extends NiftyOverlayBasicGameState implements InputProviderListener {
	private Command COMMAND_EXIT = new BasicCommand("exit");
	private InputProvider inputProvider;
	private World world;
	private int stateId;

	public GameState(int id) {
		stateId = id;
	}

	@Override
	protected void enterState(GameContainer gameContainer, StateBasedGame stateBasedGame) {
		// TODO smarter initialization
		try {
			// Initialize world
			MiceAndCheese.gameWorld = new World();
			world = MiceAndCheese.gameWorld;
			world.setManager(new GroupManager());
			world.setManager(new TagManager());
			world.setSystem(new InputSystem(gameContainer));
			world.setSystem(new RenderSystem(gameContainer));
			world.setSystem(new SpawnSystem(500));
			world.setSystem(new MovementSystem());
			world.setSystem(new CollisionSystem(stateBasedGame));
			world.setSystem(new RenderGUISystem(gameContainer));
			world.initialize();

			EntityFactory.createPlayerEntity(world, 100f).addToWorld();

			EntityFactory.createCheeseEntity(world,
				MiceAndCheese.getGameScreenCenterX(),
				MiceAndCheese.getGameScreenCenterY())
				.addToWorld();

			// Change cursor
			Image cursorImage = ResourceManager.getImage("cursor");
			gameContainer.setMouseCursor(cursorImage, cursorImage.getWidth() / 2,
				cursorImage.getHeight() / 2);
		} catch (SlickException e) {
			Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	protected void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) {
		// Initialize nifty
		initNifty(gameContainer, stateBasedGame);

		// Initialize input provider
		inputProvider = new InputProvider(gameContainer.getInput());
		inputProvider.addListener(this);
		inputProvider.bindCommand(new KeyControl(Input.KEY_ESCAPE), COMMAND_EXIT);
		inputProvider.bindCommand(new KeyControl(Input.KEY_Q), COMMAND_EXIT);
	}

	@Override
	protected void leaveState(GameContainer gameContainer, StateBasedGame stateBasedGame) {

	}

	@Override
	protected void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
		world.process();
	}

	@Override
	protected void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) {
		world.setDelta(delta);
	}

	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame) {
		// Load gui here
	}

	public void controlPressed(Command command) {
		if (command.equals(COMMAND_EXIT)) {
			System.exit(0);
		}
	}

	public void controlReleased(Command command) {

	}

	@Override
	public int getID() {
		return stateId;
	}
}
