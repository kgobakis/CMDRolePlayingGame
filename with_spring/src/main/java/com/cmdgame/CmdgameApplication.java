package com.cmdgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class CmdgameApplication {
	private Settings settings;
	private Random rand;
	private Scanner in;
	private final String[] enemiesList = new String[] { "The Flamehand", "The Vampwraith", "The Soulwing", "Rustbeing",
			"The Elderman", "The Warlock", "The Quiet Vision", "The Skeleton", "The Ruthless Bane Behemoth",
			"The Mage" };

	public static void main(String[] args) {
		SpringApplication.run(CmdgameApplication.class, args);
		CmdgameApplication game = new CmdgameApplication();
		game.run();
	}

	public void run() {

		in = new Scanner(System.in);
		rand = new Random();
		settings = new Settings();
		Player player;
		ArrayList<Enemy> enemies;

		if (!settings.isGameSaved()) {
			settings.loadDefaultSettings();
			// Creating Player
			player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
					settings.getNumberOfHealthPotions());
			enemies = randomlyGenerateEnemies(rand.nextInt(10) + 1);

		} else {
			// Loading Saved Game
			PlayerEnemyCombination data = settings.loadSavedGame();
			player = data.player;
			enemies = data.enemies;

		}
		// Variable for running the whole game.
		boolean running = true;
		// Variable for displaying main menu.
		boolean mainMenu = settings.isGameSaved() ? true : false;

		// Input string will be reused multiple times for user input.
		String input;

		GAME: while (running) {

			while (mainMenu) {
				System.out.println("-------------------------------------------");

				System.out.println("\t1. Save Current Game & Exit.");
				System.out.println("\t2. Start New Game!");
				System.out.println("\t3. Load Latest Saved Game.");
				System.out.println("-------------------------------------------");

				input = in.nextLine();
				if ("1".equals(input)) {
					settings.saveGame(player, enemies);
					System.out.println("\nGame succesfully saved!\n");
					in.close();
					System.exit(0);
				} else if ("2".equals(input)) {
					settings.deleteSavedGame();
					run();
				} else if ("3".equals(input)) {
					if (!settings.isGameSaved()) {
						System.out.println("No Saved Game Exists.");
					} else {
						mainMenu = false;
						drawWinningMessage("W e l c o m e b a c k , ", ASCIIArtGenerator.ART_SIZE_SMALL);

						drawWinningMessage("\t\t\t\t\t\t\t"
								+ player.getName().chars().mapToObj(i -> (char) i + " ").collect(Collectors.joining()),
								ASCIIArtGenerator.ART_SIZE_SMALL);

						break;
					}
				}
			}
			if (player.getName() == null) {
				System.out.println("-------------------------------------------");
				System.out.println("\nIn a world where robots run large corporations,"
						+ "\none killer has no choice but to save mankind using just his mind."
						+ "\nDespite the killer's best efforts, \nthe world ends and everybody dies.");
				System.out.println("-------------------------------------------");
				System.out.println("Choose your hero's alias.");
				input = in.nextLine();
				player.setName(input);

				System.out.println("\n\n \t " + enemies.size() + " robots have spawned! Good luck!\n\n");

			}
			Enemy enemy = enemies.get(rand.nextInt(enemies.size()));

			if (enemy.getHealth() > 0)
				System.out.println("\t # " + enemy.getName() + " appeared! #\n");

			while (enemy.getHealth() > 0) {
				System.out.println("\tYour Current HP is: " + player.getHealth());
				System.out.println("\t" + enemy.getName() + "'s HP is: " + enemy.getHealth());
				System.out.println("\n\tMake your next choice.");
				System.out.println("\t1. Attack");
				System.out.println("\t2. Drink HP Potion");
				System.out.println("\t3. Run from Enemy...");
				System.out.println("\n\t0. Main menu.");

				String i = in.nextLine();
				if ("1".equals(i)) {
					int damageDealt = rand.nextInt(player.getAttackDamage());
					int damageTaken = rand.nextInt(enemy.getAttackDamage());

					enemy.setHealth(enemy.getHealth() - damageDealt);
					player.setHealth(player.getHealth() - damageTaken);

					System.out.println("\t> You strike the " + enemy.getName() + " for " + damageDealt + " damage.");
					System.out.println("\t> You receive " + damageTaken + " damage in return.\n\n\n");
					if (player.getHealth() < 1) {
						System.out.println("\t You have underwent severe damage, the robots have won!");
						in.close();
						System.exit(0);
					}
					if (enemy.getHealth() < 1) {
						System.out.println("-------------------------------------------");
						System.out.println(" \t# " + enemy.getName() + " has been defeated! # ");
						System.out.println(" \t# You have " + player.getHealth() + " HP left. # \n");
						System.out.println("-------------------------------------------");

						player.setExperience(player.getExperience() + 1);
						if (player.getExperience() == 3) {
							player.setLevel(player.getLevel() + 1);
							player.setExperience(0);
							drawWinningMessage("L e v e l - " + (player.getLevel() + 1) + " !",
									ASCIIArtGenerator.ART_SIZE_SMALL);

							// Increasing player's AD on level up
							player.setAttackDamage(player.getAttackDamage() + 2);
						}
						if (rand.nextInt(100) <= enemy.getHealthPotionDropChance()) {
							player.setNumberOfHealthPotions(player.getNumberOfHealthPotions() + 1);
							drawWinningMessage("P o t i o n + 1", ASCIIArtGenerator.ART_SIZE_SMALL);
							System.out.println("-------------------------------------------");

							System.out.println(
									" # You have " + player.getNumberOfHealthPotions() + " health potion(s)! # ");
						}
						player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);

						break;
					}
				} else if ("2".equals(i)) {
					if (player.getNumberOfHealthPotions() > 0) {
						player.setHealth(player.getHealth() + settings.getHealthPotionHealAmount());
						player.setNumberOfHealthPotions(player.getNumberOfHealthPotions() - 1);
						System.out.println("\t> You drink a health potion, healing yourself for "
								+ settings.getHealthPotionHealAmount() + "." + "\n\t> You now have "
								+ player.getHealth() + " HP." + "\n\t> You have " + player.getNumberOfHealthPotions()
								+ " health potions left. \n");
					} else {
						System.out.println(
								"\t> You have no health potions left! It is said that Robots carry them to battle!\n");
					}
				} else if ("3".equals(i)) {
					System.out.println("-------------------------------------------");

					System.out.println("\tYou run away from the " + enemy.getName() + "!\n");
					continue GAME;
				} else if ("0".equals(i)) {
					mainMenu = true;
					continue GAME;
				} else {
					System.out.println("-------------------------------------------");

					System.out.println("\tInvalid command!");
					System.out.println("-------------------------------------------");

				}

			}
			if (player.getHealth() < 1) {
				System.out.println("You have taken too much damage, you are too week to keep going!");
				mainMenu = true;
				break;
			}

			if (player.getEnemiesDefeated() == enemies.size()) {
				drawWinningMessage(" (: _ C o n g r a t s! _ :)", ASCIIArtGenerator.ART_SIZE_SMALL);
				System.out.println("\n\t\t\t\t\t\t# You did it! You have saved our planet! #\n");
				in.close();
				System.exit(0);
			}
		}
	}

	public ArrayList<Enemy> randomlyGenerateEnemies(int number) {
		ArrayList<Enemy> toReturn = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		int elderManHealthBonus = 50;

		if (number > 0 && number < 10) {
			while (toReturn.size() < number) {
				int randomIndex = rand.nextInt(10);
				if (!set.contains(randomIndex)) {
					if ("Elderman".equals(enemiesList[randomIndex])) {
						toReturn.add(
								new Enemy(enemiesList[randomIndex], settings.getMaxEnemyHealth() + elderManHealthBonus,
										settings.getEnemyAttackDamage() + (3 * randomIndex),
										settings.getHealthPotionDropChance() + (randomIndex * 2), randomIndex));

						set.add(randomIndex);
						continue;
					}
					toReturn.add(new Enemy(enemiesList[randomIndex], settings.getMaxEnemyHealth(),
							settings.getEnemyAttackDamage() + (3 * randomIndex),
							settings.getHealthPotionDropChance() + (randomIndex * 2), randomIndex));
					set.add(randomIndex);
				}
			}
			return toReturn;
		} else if (number == 10) {
			for (int i = 0; i < enemiesList.length; i++) {
				if ("Elderman".equals(enemiesList[i])) {
					toReturn.add(new Enemy(enemiesList[i], settings.getMaxEnemyHealth() + elderManHealthBonus,
							settings.getEnemyAttackDamage() + (3 * i), settings.getHealthPotionDropChance() + (i * 2),
							i));
					continue;
				}
				toReturn.add(new Enemy(enemiesList[i], settings.getMaxEnemyHealth(),
						settings.getEnemyAttackDamage() + (3 * i), settings.getHealthPotionDropChance() + (i * 2), i));
			}
			return toReturn;
		} else {
			return null;
		}

	}

	private static void drawWinningMessage(String message, int size) {
		try {
			ASCIIArtGenerator.run(message, size);
		} catch (Exception e) {
		}
	}

}
