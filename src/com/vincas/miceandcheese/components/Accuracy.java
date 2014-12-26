package com.vincas.miceandcheese.components;

import com.artemis.Component;

import java.math.BigDecimal;

public class Accuracy extends Component {
	private int totalCount;
	private int hitCount;

	public Accuracy() {
		totalCount = 0;
		hitCount = 0;
	}

	public void miss() {
		totalCount++;
	}

	public void hit() {
		totalCount++;
		hitCount++;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getHitCount() {
		return hitCount;
	}

	public float getPercentage() {
		if (totalCount == 0f)
			return 0f;
		return round((float)hitCount / totalCount * 100f, 2).floatValue();
	}

	private static BigDecimal round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
}
