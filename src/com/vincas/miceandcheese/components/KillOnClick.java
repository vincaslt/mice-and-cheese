package com.vincas.miceandcheese.components;

import com.artemis.Component;

/**
 * To be honest, the kill on click could probably be easily solved using groups,
 * without requiring separate component. Probably would be a better practice.
 */
public class KillOnClick extends Component {
	private int reward; // amount of score given on death
	private float radius;
	private State state;

	public enum State {
		ACTIVE, PROCESSING, FINISHED
	}

	public KillOnClick(float radius, int reward) {
		this.radius = radius;
		this.reward = reward;
		state = State.ACTIVE;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
