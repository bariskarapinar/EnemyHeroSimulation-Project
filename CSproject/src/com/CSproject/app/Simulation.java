package com.CSproject.app;


import com.CSproject.models.*;
import com.CSproject.service.*;

public class Simulation {
	Boolean finished; 
	SimulationPlan plan;
	Hero hero;
	
	public Simulation(Hero hero) {
		this.hero = hero;
	}

	public void start() {
		// to do :
		// update methodunu cagir, set flag
		if (plan == null) {
			plan = new SimulationPlan(this.hero, Parser.getInstance().parsedData.enemies, Parser.getInstance().parsedData.resourcePoint);
			
		}
		LogService.getInstance().logStart(this.hero);
		finished = false;
		update();
		//print output
		LogService.getInstance().writeResultToFile();
	}

	public void update() {
		while (!finished) {
			if (plan.heroAtEndPoint()) {
				LogService.getInstance().logWin(this.hero);
				logResult();
				finished = true;
			}
			Enemy enemy = (Enemy) plan.checkInterception();
			if (enemy != null) { // to do : start an attack
				if (!startBattle(enemy)) {
					LogService.getInstance().logBattleDefeat(hero, enemy);
					finished = true;
					return;
				}
				else{
					LogService.getInstance().logBattleWin(this.hero, enemy);
				}

			}
			hero.move();
		}
	}
	
	public Boolean startBattle(Enemy enemy) { // hero kazanırsa return
		Boolean battleFinished = false;
		Boolean herosTurn = false;
		while (!battleFinished) {
			if (hero.hp <= 0) {
				battleFinished = true;
				return false;
			}
			
			if (enemy.hp <= 0) {
				battleFinished = true;
				return true;
			}

			if (herosTurn) {
				hero.attack(enemy);
				herosTurn = false;
			}

			else if (!herosTurn) {
				enemy.attack(hero);
				herosTurn = true;
			}
		}
		return true;
	}
	
	public void logResult() {
		//Survived veya Dead
		App.Log((hero.hp > 0) ? "Survived" : "Dead");
	}
}
