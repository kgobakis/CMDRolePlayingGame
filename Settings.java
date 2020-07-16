import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Settings {

    private String isGameSaved;

    private int maxEnemyHealth;

    private int maxPlayerHealth;

    private int enemyAttackDamage;

    private int playerAttackDamage;

    private int healthPotionHealAmount = 25;

    private int numberOfHealthPotions;

    private int healthPotionDropChance;
    Properties props;

    public Settings() {
    }

    // public boolean deleteSavedGame() {
    // props = new Properties();
    // File configFile = new File("savedGame.properties");
    // try {
    // props.setProperty("isGameSaved", "player.getName()");

    // FileWriter writer = new FileWriter(configFile);
    // writer.close();

    // return true;
    // } catch (FileNotFoundException ex) {
    // ex.printStackTrace();
    // return false;
    // } catch (IOException ex) {
    // ex.printStackTrace();
    // return false;
    // }

    // }

    public boolean isGameSaved() {
        props = new Properties();
        try {
            props.load(new FileInputStream("config.properties"));
            isGameSaved = props.getProperty("isGameSaved");

            if ("false".equals(isGameSaved)) {
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

    public boolean changeIsSaved() {
        props = new Properties();

        try {
            FileInputStream in = new FileInputStream("config.properties");
            props.load(in);
            in.close();
            FileOutputStream out = new FileOutputStream("config.properties");
            if ("false".equals(this.isGameSaved)) {
                props.setProperty("isGameSaved", "true");
                this.isGameSaved = "true";

            } else {
                props.setProperty("isGameSaved", "false");
                this.isGameSaved = "false";

            }
            props.store(out, "System Configuration");
            out.close();
            return true;
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
        File savedGame = new File("savedGame.properties");

        try {
            if ("false".equals(getIsGameSaved())) {
                this.changeIsSaved();
            }
            props.setProperty("playerName", player.getName());
            props.setProperty("playerAttackDamage", "" + player.getAttackDamage());
            props.setProperty("playerHealth", Integer.toString(player.getHealth()));
            props.setProperty("playerNumberOfHealthPotions", Integer.toString(player.getNumberOfHealthPotions()));
            props.setProperty("healthPotionHealAmount", Integer.toString(this.healthPotionHealAmount));
            
            healthPotionHealAmount=20

            for (Enemy enemy : enemies) {
                props.setProperty("" + enemy.getName() + "Health", "" + enemy.getHealth());
                props.setProperty("" + enemy.getName() + "AttackDamage", "" + enemy.getAttackDamage());
            }

            FileWriter writer = new FileWriter(savedGame);
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

    public boolean loadDefaultSettings() {
        props = new Properties();
        try {
            props.load(new FileInputStream("config.properties"));
            isGameSaved = props.getProperty("isGameSaved");
            this.maxEnemyHealth = Integer.parseInt(props.getProperty("maxEnemyHealth"));
            this.maxPlayerHealth = Integer.parseInt(props.getProperty("maxPlayerHealth"));
            this.enemyAttackDamage = Integer.parseInt(props.getProperty("enemyAttackDamage"));
            this.playerAttackDamage = Integer.parseInt(props.getProperty("playerAttackDamage"));
            this.healthPotionHealAmount = Integer.parseInt(props.getProperty("healthPotionHealAmount"));
            this.numberOfHealthPotions = Integer.parseInt(props.getProperty("numberOfHealthPotions"));
            this.healthPotionDropChance = Integer.parseInt(props.getProperty("healthPotionDropChance"));

            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public PlayerEnemyCombination loadSavedGame() {
        props = new Properties();
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        try {
            props.load(new FileInputStream("savedGame.properties"));
            Enemy Mage = new Enemy("Mage", Integer.parseInt(props.getProperty("MageHealth")),
                    Integer.parseInt(props.getProperty("MageAttackDamage")));
            Enemy Skeleton = new Enemy("Skeleton", Integer.parseInt(props.getProperty("SkeletonHealth")),
                    Integer.parseInt(props.getProperty("SkeletonAttackDamage")));
            Enemy Warlock = new Enemy("Warlock", Integer.parseInt(props.getProperty("WarlockHealth")),
                    Integer.parseInt(props.getProperty("WarlockAttackDamage")));
            Enemy Elderman = new Enemy("Elderman", Integer.parseInt(props.getProperty("EldermanHealth")),
                    Integer.parseInt(props.getProperty("EldermanAttackDamage")));
            Collections.addAll(enemies, Mage, Skeleton, Warlock, Elderman);
            Player player = new Player(Integer.parseInt(props.getProperty("playerHealth")),
                    Integer.parseInt(props.getProperty("playerAttackDamage")),
                    Integer.parseInt(props.getProperty("playerNumberOfHealthPotions")),
                    props.getProperty("playerName"));

            return new PlayerEnemyCombination(player, enemies);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
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