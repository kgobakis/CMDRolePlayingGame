package com.cmdgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public class Settings {

    private String isGameSaved;

    private int maxEnemyHealth;

    private int maxPlayerHealth;

    private int enemyAttackDamage;

    private int playerAttackDamage;

    private int healthPotionHealAmount;

    private int numberOfHealthPotions;

    private int healthPotionDropChance;

    private Properties props;
    private ClassLoader classLoader;
    private Writer writer;
    private Gson gson;

    public Settings() {
    }

    /*
     * Check if saved game exists.
     */ public boolean isGameSaved() {

        File file = new File("src/main/java/com/cmdgame/savedPlayer.json");
        boolean exists = file.exists();

        if (!exists) {
            return false;
        } else {
            return true;
        }

    }

    public void deleteSavedGame() {
        File savedPlayer = new File("src/main/java/com/cmdgame/savedPlayer.json");
        File savedEnemies = new File("src/main/java/com/cmdgame/savedEnemies.json");

        savedPlayer.delete();
        savedEnemies.delete();

    }

    public boolean saveGame(Player player, ArrayList<Enemy> enemies) {
        gson = new Gson();
        try {
            writer = new FileWriter("src/main/java/com/cmdgame/savedEnemies.json");
            // Saving enemies and their stats
            gson.toJson(enemies, writer);
            writer.close();

            // // Saving player and their stats
            writer = new FileWriter("src/main/java/com/cmdgame/savedPlayer.json");
            gson.toJson(player, writer);
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
        classLoader = getClass().getClassLoader();

        try {
            props.load(new FileInputStream(classLoader.getResource("config.properties").getFile()));
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
        classLoader = getClass().getClassLoader();
        gson = new Gson();
        try {
            props.load(new FileInputStream(classLoader.getResource("config.properties").getFile()));
            this.healthPotionHealAmount = Integer.parseInt(props.getProperty("healthPotionHealAmount"));

            Type type = new TypeToken<ArrayList<Enemy>>() {
            }.getType();
            ArrayList<Enemy> enemies = gson.fromJson(new FileReader("src/main/java/com/cmdgame/savedEnemies.json"),
                    type);

            Player player = gson.fromJson(new FileReader("src/main/java/com/cmdgame/savedPlayer.json"), Player.class);

            PlayerEnemyCombination t = new PlayerEnemyCombination(player, enemies);

            return t;
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