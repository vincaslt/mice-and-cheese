package com.vincas.miceandcheese.components;

import com.artemis.Component;

public class GameObjectForm extends Component {
	private String gameObjectFormName;

	public GameObjectForm(String spatialFormName) {
		this.gameObjectFormName = spatialFormName;
	}

	public String getGameObjectFormName() {
		return gameObjectFormName;
	}
}
