package com.vincas.miceandcheese.utils.math_utils;

/**
 * @author Vincas Stonys
 */
public class Vector {
	private float x, y;
	private float startX, startY;
	private float targetX, targetY;

	public Vector(float startX, float startY, float targetX, float targetY) {
		setStartCoordinates(startX, startY);
		setTargetCoordinates(targetX, targetY);
		setCoordinates();
	}

	public Vector(float startX, float startY, float targetX, float targetY, float length) {
		setStartCoordinates(startX, startY);
		setTargetCoordinates(targetX, targetY);
		setCoordinatesByLength(length);
	}

	/**
	 * Set coordinates the vector is starting at.
	 *
	 * @param x
	 * @param y
	 */
	public void setStartCoordinates(float x, float y) {
		startX = x;
		startY = y;
	}

	/**
	 * Set coordinates the vector is pointing to.
	 *
	 * @param x
	 * @param y
	 */
	public void setTargetCoordinates(float x, float y) {
		targetX = x;
		targetY = y;
	}

	/**
	 * Set vector coordinates by vector length.
	 * Vector is directed to the target coordinates.
	 * <p/>
	 * Starting point and target point must already be set.
	 *
	 * @param length
	 */
	public void setCoordinatesByLength(float length) {
		setCoordinates();
		float a = (float) (length / Math.sqrt(Math.pow((double) x, 2) + Math.pow((double) y, 2)));
		x *= a;
		y *= a;
	}

	/**
	 * Sets vector coordinates according to it's start and target coordinates.
	 * <p/>
	 * Starting point and target point must already be set.
	 */
	public void setCoordinates() {
		x = targetX - startX;
		y = targetY - startY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getRotation() {
		float x = this.x, y = this.y;

		if (y == 0 && x == 0) {
			Vector v = new Vector(startX, startY, targetX, targetY, 1);
			x = v.getX();
			y = v.getY();
		}

		double deg = StrictMath.toDegrees(StrictMath.atan2((double) y, (double) x));
		if (deg < -360.0D || deg > 360.0D) {
			deg %= 360.0D;
		}

		if (deg < 0.0D) {
			deg += 360.0D;
		}

		return (float) deg;
	}

	public float getLength() {
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
	}
}
