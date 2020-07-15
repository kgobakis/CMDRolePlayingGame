import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Game
 */
public class Game {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        Settings settings = new Settings();
        Player player;
        ArrayList<Enemy> enemies;

        if (!isGameSaved(settings)) {
            settings.loadDefaultSettings();
            // Player globals
            player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
                    settings.getNumberOfHealthPotions());
            // Creating enemies
            enemies = new ArrayList<Enemy>();
            enemies.add(new Enemy("Skeleton", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage()));
            enemies.add(new Enemy("Mage", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage()));
            enemies.add(new Enemy("Warlock", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage()));
            enemies.add(new Enemy("Elderman", settings.getMaxEnemyHealth() + 50, settings.getEnemyAttackDamage()));

        } else {
            player = new Player(100, 20, 3);
            player.setName("Babis");
            // settings.loadSavedGame();
            enemies = new ArrayList<Enemy>();
            enemies.add(new Enemy("Skeleton", 55, 20));

            settings.saveGame(player, enemies);
        }

        boolean running = true;
        boolean selection = settings.isGameSaved() ? true : false;

        System.out.println("-------------------------------------------");
        System.out.println("\nIn a world where robots run large corporations,"
                + "\none killer has no choice but to save mankind using just his mind."
                + "\nDespite the killer's best efforts, \nthe world ends and everybody dies.");
        System.out.println("-------------------------------------------");

        String input;

        GAME: while (running) {

            while (selection) {
                System.out.println("-------------------------------------------");

                System.out.println("\t1.Save Current Game.");
                System.out.println("\t2.Start New Game!");
                System.out.println("\t3.Resume Latest Saved Game.");
                System.out.println("-------------------------------------------");

                input = in.nextLine();
                if ("1".equals(input)) {
                    settings.saveGame(player, enemies);
                    Game.main(args);
                } else if ("2".equals(input)) {
                    Game.main(args);
                } else if ("3".equals(input)) {
                    if ("false".equals(settings.getIsGameSaved())) {
                        settings.changeIsSaved();
                    }
                    // Game.main(args);
                    System.out.println("11");
                }
            }
            if (player.getName() == null) {
                System.out.println("Choose your hero's alias.");
                input = in.nextLine();
                player.setName(input);

                System.out.println("-------------------------------------------");
            }

            Enemy enemy = enemies.get(rand.nextInt(enemies.size()));
            System.out.println("\t # " + enemy.getName() + " appeared! #\n");

            while (enemy.getHealth() > 0) {
                System.out.println("\tYour Current HP is: " + player.getHealth());
                System.out.println("\t" + enemy.getName() + "'s HP is: " + enemy.getHealth());
                System.out.println("\n\tMake your next choice.");
                System.out.println("\t1.Attack");
                System.out.println("\t2.Drink HP Potion");
                System.out.println("\t3.Run from Enemy...");
                System.out.println("\n\t0.To go to the main menu.");

                String i = in.nextLine();
                if ("1".equals(i)) {
                    int damageDealt = rand.nextInt(player.getAttackDamage());
                    int damageTaken = rand.nextInt(enemy.getAttackDamage());

                    enemy.setHealth(enemy.getHealth() - damageDealt);
                    player.setHealth(player.getHealth() - damageTaken);

                    System.out.println("\t> You strike the " + enemy.getName() + " for " + damageDealt + " damage.");
                    System.out.println("\t> You receive " + damageTaken + " damage in return.");
                    if (player.getHealth() < 1) {
                        System.out.println("\t You have underwent severe damage, the robots have won!");
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
                    System.out.println("\tYou run away from the " + enemy.getName() + "!");
                    continue GAME;
                } else if ("0".equals(i)) {
                    selection = true;
                    continue GAME;
                } else {
                    System.out.println("\tInvalid command!");
                }

            }
        }
    }

    private static boolean isGameSaved(Settings settings) {
        return settings.isGameSaved();
    }
}