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
        Enemy Skeleton;
        Enemy Mage;
        Enemy Warlock;
        Enemy Elderman;

        if (isGameSaved(settings)) {
            // Player globals
            player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
                    settings.getNumberOfHealthPotions());
            // Creating enemies
            Skeleton = new Enemy("Skeleton", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage());
            Mage = new Enemy("Mage", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage());
            Warlock = new Enemy("Warlock", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage());
            Elderman = new Enemy("Elderman", settings.getMaxEnemyHealth() + 50, settings.getEnemyAttackDamage());
        } else {
            player = new Player(100, 20, 3);
            player.setName("Babis");
            Skeleton = new Enemy("Skeleton", 55, 20);

            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            enemies.add(Skeleton);
            System.out.println("Cabron!");
            settings.saveGame(player, enemies);
        }
        // boolean running = true;
        // GAME: while (running) {

        // }
    }

    private static boolean isGameSaved(Settings settings) {
        return settings.isGameSaved();
    }
}