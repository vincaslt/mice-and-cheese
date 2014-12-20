package com.vincas.miceandcheese.components;

import com.artemis.Component;
import com.vincas.miceandcheese.utils.Timer;

public class TimerComponent extends Component {
	private Timer timer;

	public TimerComponent(int interval) {
		timer = new Timer(interval);
	}

	public Timer getTimer() {
		return timer;
	}
}
