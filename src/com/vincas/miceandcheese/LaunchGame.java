package com.vincas.miceandcheese;

import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LaunchGame {

	public static void main(String[] args) {
		try {
			MiceAndCheese game = new MiceAndCheese("Mice And Cheese");
			game.launch();
		} catch (SlickException e) {
			Logger.getLogger(LaunchGame.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
