package com.CSproject.models;

import javax.swing.Icon;

public class Hero extends Entity implements IHero {
	public Hero(int position, int hp, int attackPoints) {
		super(position, hp, attackPoints);
	}

	public int move() {
		// TODO Auto-generated method stub
		this.position += 1;
		return this.position;
	}

	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		this.hp -= damage;
	}

	public void attack(ICharacter target) {
		target.takeDamage(this.attackPoint);
	}

	@Override
	public String toString() {
		return "Hero => Pos : " + this.position + " Hp : " + this.hp + " Attack : " + this.attackPoint;
	}

	public int moveRange(int range) {
		this.position += range;
		return this.position;
	}
}