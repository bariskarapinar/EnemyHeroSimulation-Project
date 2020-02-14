package com.CSproject.service;
import java.util.ArrayList;
import java.util.List;

import com.CSproject.models.*;
public class SimulationPlan {
	Hero hero;
	List<Enemy> enemies;
	int endPosition = 0;
	
	public SimulationPlan(Hero hero,List<Enemy> enemies,int endPosition){
		this.hero = hero;
		this.enemies = enemies;
		this.endPosition = endPosition;
	}
	
	public ICharacter checkInterception() {
		for (Enemy enemy : enemies) {
			if(hero.position == enemy.position){
				return enemy;
			}
		}
		return null;
	}
	
	public Boolean heroAtEndPoint(){
		return hero.position >= this.endPosition;
	}
}