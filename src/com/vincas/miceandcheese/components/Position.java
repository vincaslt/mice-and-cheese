package com.vincas.miceandcheese.components;

import com.artemis.Component;
import com.artemis.utils.Utils;

public class Position extends Component {
	private float x, y;
	private float centerOffsetX, centerOffsetY;

	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Position(float x, float y, float centerOffsetX, float centerOffsetY) {
		this.x = x;
		this.y = y;
		this.centerOffsetX = centerOffsetX;
		this.centerOffsetY = centerOffsetY;
	}

	public float getCenterOffsetX() {
		return centerOffsetX;
	}

	public void setCenterOffsetX(float centerOffsetX) {
		this.centerOffsetX = centerOffsetX;
	}

	public float getCenterOffsetY() {
		return centerOffsetY;
	}

	public void setCenterOffsetY(float centerOffsetY) {
		this.centerOffsetY = centerOffsetY;
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
