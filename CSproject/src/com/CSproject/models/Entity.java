package com.CSproject.models;

public class Entity {
	public int position;
	public int hp;
	public int attackPoint;

	public Entity() {
		this.position = 0;
	}

	public Entity(int position, int hp, int attackPoint) {
		this.position = position;
		this.hp = hp;
		this.attackPoint = attackPoint;
	}
}