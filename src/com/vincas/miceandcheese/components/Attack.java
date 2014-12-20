package com.vincas.miceandcheese.components;

import com.artemis.Component;

public class Attack extends Component {
	private int attackDamage;

	public Attack(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getAttackDamage() {
		return attackDamage;
	}
}
