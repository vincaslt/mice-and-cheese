package com.vincas.miceandcheese.components;

import com.artemis.Component;
import com.vincas.miceandcheese.utils.math_utils.Vector;

public class Velocity extends Component {
	private Vector vector; // direction

	public Velocity(Vector vector) {
		this.vector = vector;
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public float getX() {
		return vector.getX();
	}

	public float getY() {
		return vector.getY();
	}
}
