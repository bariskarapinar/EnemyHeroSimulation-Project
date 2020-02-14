package com.CSproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.CSproject.models.Enemy;
import com.CSproject.models.Hero;
import com.CSproject.models.ParsedData;

public class Parser {
	private static Parser _parser;

	public ParsedData parsedData;
	List<String> keywords;

	private Parser() {
		keywords = Arrays.asList(getKeywords());
		parsedData = new ParsedData();
	}

	public static synchronized Parser getInstance() {
		if (_parser == null)
			_parser = new Parser();

		return _parser;
	}

	public void parseWholeFile(List<String> inputData) {
		parsedData.resourcePoint = parseSpecificLine(inputData.get(0));
		parsedData.hero = new Hero(0, 0, 0);
		parsedData.hero.hp = parseSpecificLine(inputData.get(1));
		parsedData.hero.attackPoint = parseSpecificLine(inputData.get(2));
		parsedData.enemies = extractEnemies(inputData);
	}

	class EnemyDto {
		public int hp;
		public int position;
		public int attackPoint;
		public String name;

		public EnemyDto(String name) {
			this.name = name;
		}
	}

	public List<Enemy> extractEnemies(List<String> inputData) {
		List<Enemy> enemies;
		List<EnemyDto> enemyTypes = new ArrayList<EnemyDto>();

		int totalEnemyType = 0;
		int currentIndex = 3;
		for (String item : inputData) {
			if (checkIfContainsKeyword(item, "Enemy")) {
				//enemy türünü isimlerle çıkar ve ekle
				enemyTypes.add(new EnemyDto(extractEnemyType(item)));
				totalEnemyType += 1;
			}
		}

		currentIndex += totalEnemyType;
		for (int i = 0; i < enemyTypes.size(); i++) {
			//isimleri olan düşmanlarımız var, her düşman, 2
                        // satırlar (i, i + 1) =>
                        // sırasıyla bu 2 satırdan hp & attackPoint extract ediyoruz
			String currentenemyTypeFromLine = extractEnemyType(inputData.get(currentIndex));
			int currentEnemyHp = parseSpecificLine(inputData.get(currentIndex));
			int currentEnemyAttack = parseSpecificLine(inputData.get(currentIndex + 1));// attack
			for (EnemyDto enemyDto : enemyTypes) {
				if(enemyDto.name.equals(currentenemyTypeFromLine)){
					enemyDto.hp = currentEnemyHp;
					enemyDto.attackPoint = currentEnemyAttack;
				}
			}
			
			
			currentIndex += 2; 
		}

		enemies = generateEnemiesWithPosition(inputData, currentIndex);

		for (EnemyDto item : enemyTypes) {
			for (int i = 0; i < enemies.size(); i++) {
				Enemy currentEnemy = enemies.get(i);
				if (currentEnemy.name.equals(item.name)) {
					currentEnemy.hp = item.hp;
					currentEnemy.attackPoint = item.attackPoint;

				}
			}
		}

		return enemies;
	}

	public List<Enemy> generateEnemiesWithPosition(List<String> inputData, int startingIndex) {
		List<Enemy> enemies = new ArrayList<Enemy>();
		List<String> relatedLines = inputData.subList(startingIndex, inputData.size());
		for (String line : relatedLines) {
			line = line.trim();
			if (!line.equals(""))
				enemies.add(extractEnemyWithPosition(line));
		}
		return enemies;
	}

	public Enemy extractEnemyWithPosition(String line) {
		List<String> array = Arrays.asList(line.split(" "));
		String keys = "There is a at position";
		List<String> result = new ArrayList<String>();
		for (String item : array) {
			if (keys.indexOf(item) != -1) {
			} else {
				result.add(item);
			}
		}
		Enemy enemy = new Enemy(result.get(0), Integer.parseInt(result.get(1)));
		return enemy;
	}

	public String extractEnemyType(String line) { // extract enemy adı
		return line.substring(0, line.indexOf(" "));
	}


	public int parseSpecificLine(String resourceLine) { 
                                                        // örnek : Resources
							// are 7500
							// meters away
		String[] array = resourceLine.split(" ");
		for (String item : array) {
			if (checkIfNumber(item)) {
				return Integer.parseInt(item);
			}
		}
		return 0; 
	}

	public Boolean checkIfContainsKeyword(String line, String keyword) {
		List<String> words = Arrays.asList(line.split(" "));
		return words.indexOf(keyword) != -1;
	}

	public String[] getKeywords() {
		return "resources*hero*enemy*has*attack*there is a".split("\\*");
	}

	public int extractNumber(String[] inputArray) {
		for (String item : inputArray) {
			if (checkIfNumber(item) == true)
				return Integer.parseInt(item);
		}

		return 0; 
	}

	Boolean checkIfNumber(String input) {
		input = input.trim();
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
