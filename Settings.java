import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Settings {

    private String isGameSaved;

    private int maxEnemyHealth;

    private int maxPlayerHealth;

    private int enemyAttackDamage;

    private int playerAttackDamage;

    private int healthPotionHealAmount;

    private int numberOfHealthPotions;

    private int healthPotionDropChance;
    Properties props;

    public Settings() {
    }

    public boolean isGameSaved() {
        props = new Properties();
        try {
            props.load(new FileInputStream("config.properties"));
            isGameSaved = props.getProperty("isGameSaved");

            if ("false".equals(isGameSaved)) {
                defaultSettings();
                return false;
            } else {
                return true;
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean saveGame(Player player, ArrayList<Enemy> enemies) {
        props = new Properties();
        File configFile = new File("savedGame.properties");
        try {
            props.setProperty("playerName", player.getName());
            props.setProperty("playerAttackDamage", "" + player.getAttackDamage());
            props.setProperty("playerHealth", Integer.toString(player.getHealth()));
            props.setProperty("playerNumberOfHealthPotions", Integer.toString(player.getNumberOfHealthPotions()));

            for (Enemy enemy : enemies) {
                props.setProperty("" + enemy.getName() + "Health", "" + enemy.getHealth());
                props.setProperty("" + enemy.getName() + "AttackDamage", "" + enemy.getAttackDamage());
            }

            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Player saved game.");
            writer.close();

            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    private void defaultSettings() {
        this.maxEnemyHealth = 100;
        this.maxPlayerHealth = 100;
        this.enemyAttackDamage = 25;
        this.playerAttackDamage = 40;
        this.healthPotionHealAmount = 20;
        this.numberOfHealthPotions = 3;
        this.healthPotionDropChance = 40;
    }

    private void resumeSavedGame() {

    }

    public int getMaxPlayerHealth() {
        return this.maxPlayerHealth;
    }

    public String getIsGameSaved() {
        return this.isGameSaved;
    }

    public int getMaxEnemyHealth() {
        return this.maxEnemyHealth;
    }

    public int getEnemyAttackDamage() {
        return this.enemyAttackDamage;
    }

    public int getPlayerAttackDamage() {
        return this.playerAttackDamage;
    }

    public int getHealthPotionHealAmount() {
        return this.healthPotionHealAmount;
    }

    public int getNumberOfHealthPotions() {
        return this.numberOfHealthPotions;
    }

    public int getHealthPotionDropChance() {
        return this.healthPotionDropChance;
    }
}