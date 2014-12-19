package com.vincas.miceandcheese.components;

import com.artemis.Component;
import com.artemis.utils.Utils;

public class Position extends Component {
	private float x, y;

	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void addX(float x) {
		this.x += x;
	}

	public void addY(float y) {
		this.y += y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDistanceTo(Position t) {
		return Utils.distance(t.getX(), t.getY(), x, y);
	}
}
