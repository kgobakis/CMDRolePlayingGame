// package com.cmdgame;

// import java.util.ArrayList;

// import java.util.Random;
// import java.util.Scanner;
// import java.util.stream.Collectors;

// /**
// * Game
// */
// public class Game {

// public static void main(String[] args) throws Exception {

// Scanner in = new Scanner(System.in);
// Random rand = new Random();
// Settings settings = new Settings();
// Player player;
// ArrayList<Enemy> enemies;

// if (!settings.isGameSaved()) {
// settings.loadDefaultSettings();
// // Creating Player
// player = new Player(settings.getMaxPlayerHealth(),
// settings.getPlayerAttackDamage(),
// settings.getNumberOfHealthPotions());
// // Creating enemies
// enemies = new ArrayList<Enemy>();

// enemies.add(new Enemy("Skeleton", settings.getMaxEnemyHealth(),
// settings.getEnemyAttackDamage(),
// settings.getHealthPotionDropChance(), 1));
// enemies.add(new Enemy("Mage", settings.getMaxEnemyHealth(),
// settings.getEnemyAttackDamage(),
// settings.getHealthPotionDropChance(), 2));
// enemies.add(new Enemy("Warlock", settings.getMaxEnemyHealth(),
// settings.getEnemyAttackDamage(),
// settings.getHealthPotionDropChance(), 3));
// enemies.add(new Enemy("Elderman", settings.getMaxEnemyHealth() + 25,
// settings.getEnemyAttackDamage(),
// settings.getHealthPotionDropChance() + 20, 4));

// } else {
// // Loading Saved Game
// PlayerEnemyCombination data = settings.loadSavedGame();
// player = data.player;
// enemies = data.enemies;

// }
// // Variable for running the whole game.
// boolean running = true;
// // Variable for displaying main menu.
// boolean mainMenu = settings.isGameSaved() ? true : false;

// // Input string will be reused multiple times for user input.
// String input;

// GAME: while (running) {

// while (mainMenu) {
// System.out.println("-------------------------------------------");

// System.out.println("\t1. Save Current Game & Exit.");
// System.out.println("\t2. Start New Game!");
// System.out.println("\t3. Load Latest Saved Game.");
// System.out.println("-------------------------------------------");

// input = in.nextLine();
// if ("1".equals(input)) {
// settings.saveGame(player, enemies);
// System.out.println("\nGame succesfully saved!\n");
// in.close();
// System.exit(0);
// } else if ("2".equals(input)) {
// if ("true".equals(settings.getIsGameSaved())) {
// settings.changeIsSaved();
// }
// Game.main(args);
// } else if ("3".equals(input)) {
// if ("false".equals(settings.getIsGameSaved())) {
// System.out.println("No Saved Game Exists.");
// } else {
// mainMenu = false;
// drawWinningMessage("W e l c o m e b a c k , ",
// ASCIIArtGenerator.ART_SIZE_SMALL);

// drawWinningMessage("\t\t\t\t\t\t\t"
// + player.getName().chars().mapToObj(i -> (char) i + "
// ").collect(Collectors.joining()),
// ASCIIArtGenerator.ART_SIZE_SMALL);

// // System.out.println("\t # Welcome back " + player.getName() + "! # \n \n");
// break;
// }
// }
// }
// if (player.getName() == null) {
// System.out.println("-------------------------------------------");
// System.out.println("\nIn a world where robots run large corporations,"
// + "\none killer has no choice but to save mankind using just his mind."
// + "\nDespite the killer's best efforts, \nthe world ends and everybody
// dies.");
// System.out.println("-------------------------------------------");
// System.out.println("Choose your hero's alias.");
// input = in.nextLine();
// player.setName(input);

// System.out.println("-------------------------------------------");
// }
// Enemy enemy = enemies.get(rand.nextInt(enemies.size()));

// if (enemy.getHealth() > 0)
// System.out.println("\t # " + enemy.getName() + " appeared! #\n");

// while (enemy.getHealth() > 0) {
// System.out.println("\tYour Current HP is: " + player.getHealth());
// System.out.println("\t" + enemy.getName() + "'s HP is: " +
// enemy.getHealth());
// System.out.println("\n\tMake your next choice.");
// System.out.println("\t1. Attack");
// System.out.println("\t2. Drink HP Potion");
// System.out.println("\t3. Run from Enemy...");
// System.out.println("\n\t0. Main menu.");

// String i = in.nextLine();
// if ("1".equals(i)) {
// int damageDealt = rand.nextInt(player.getAttackDamage());
// int damageTaken = rand.nextInt(enemy.getAttackDamage());

// enemy.setHealth(enemy.getHealth() - damageDealt);
// player.setHealth(player.getHealth() - damageTaken);

// System.out.println("\t> You strike the " + enemy.getName() + " for " +
// damageDealt + " damage.");
// System.out.println("\t> You receive " + damageTaken + " damage in
// return.\n\n\n");
// if (player.getHealth() < 1) {
// System.out.println("\t You have underwent severe damage, the robots have
// won!");
// in.close();
// System.exit(0);
// }
// if (enemy.getHealth() < 1) {
// System.out.println("-------------------------------------------");
// System.out.println(" \t# " + enemy.getName() + " has been defeated! # ");
// System.out.println(" \t# You have " + player.getHealth() + " HP left. # \n");
// System.out.println("-------------------------------------------");

// if (rand.nextInt(100) <= enemy.getHealthPotionDropChance()) {
// player.setNumberOfHealthPotions(player.getNumberOfHealthPotions() + 1);
// // System.out.println(" # The " + enemy.getName() + " dropped a health
// potion! #
// // ");
// drawWinningMessage("P o t i o n", ASCIIArtGenerator.ART_SIZE_SMALL);
// drawWinningMessage("d r o p p e d !", ASCIIArtGenerator.ART_SIZE_SMALL);
// // drawWinningMessage(" . . . ", ASCIIArtGenerator.ART_SIZE_SMALL);
// System.out.println("-------------------------------------------");

// System.out.println(
// " # You have " + player.getNumberOfHealthPotions() + " health potion(s)! #
// ");
// }
// player.setEnemiesDefeated(player.getEnemiesDefeated() + 1);

// break;
// }
// } else if ("2".equals(i)) {
// if (player.getNumberOfHealthPotions() > 0) {
// player.setHealth(player.getHealth() + settings.getHealthPotionHealAmount());
// player.setNumberOfHealthPotions(player.getNumberOfHealthPotions() - 1);
// System.out.println("\t> You drink a health potion, healing yourself for "
// + settings.getHealthPotionHealAmount() + "." + "\n\t> You now have "
// + player.getHealth() + " HP." + "\n\t> You have " +
// player.getNumberOfHealthPotions()
// + " health potions left. \n");
// } else {
// System.out.println(
// "\t> You have no health potions left! It is said that Robots carry them to
// battle!\n");
// }
// } else if ("3".equals(i)) {
// System.out.println("-------------------------------------------");

// System.out.println("\tYou run away from the " + enemy.getName() + "!\n");
// continue GAME;
// } else if ("0".equals(i)) {
// mainMenu = true;
// continue GAME;
// } else {
// System.out.println("-------------------------------------------");

// System.out.println("\tInvalid command!");
// System.out.println("-------------------------------------------");

// }

// }
// if (player.getHealth() < 1) {
// System.out.println("You have taken too much damage, you are too week to keep
// going!");
// mainMenu = true;
// break;
// }

// if (player.getEnemiesDefeated() == 4) {
// drawWinningMessage(" (: C o n g r a t s! :)",
// ASCIIArtGenerator.ART_SIZE_SMALL);
// System.out.println("\n\t\t\t\t\t\t# You did it! You have saved our planet!
// #\n");
// in.close();
// System.exit(0);
// }
// }
// }

// private static void drawWinningMessage(String message, int size) throws
// Exception {
// ASCIIArtGenerator.run(message, size);
// }

// }
