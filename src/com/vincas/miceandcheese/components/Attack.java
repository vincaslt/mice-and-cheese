package com.vincas.miceandcheese.components;

import com.artemis.Component;

public class Attack extends Component {
	private float attackDamage;

	public Attack(float attackDamage) {
		this.attackDamage = attackDamage;
	}

	public void setAttackDamage(float attackDamage) {
		this.attackDamage = attackDamage;
	}

	public float getAttackDamage() {
		return attackDamage;
	}
}
