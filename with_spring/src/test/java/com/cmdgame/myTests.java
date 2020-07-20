package com.cmdgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class myTests {
    @Test
    void testPlayerCreation() {
        Settings settings = new Settings();

        Player player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
                settings.getNumberOfHealthPotions());
        player.setName("Kostas");
        assertEquals(player.getHealth(), settings.getMaxPlayerHealth());
        assertEquals(player.getAttackDamage(), settings.getPlayerAttackDamage());
        assertEquals(player.getNumberOfHealthPotions(), settings.getNumberOfHealthPotions());
        assertEquals(player.getName(), "Kostas");
    }

    @Test
    void testEnemyCreation() {
        Settings settings = new Settings();

        Enemy enemy = new Enemy("Mojo Jojo", settings.getMaxEnemyHealth(), settings.getEnemyAttackDamage(),
                settings.getHealthPotionDropChance(), 5);

        assertEquals(enemy.getName(), "Mojo Jojo");
        assertEquals(enemy.getAttackDamage(), settings.getEnemyAttackDamage());
        assertEquals(enemy.getHealth(), settings.getMaxEnemyHealth());
        assertEquals(enemy.getLevel(), 5);
        assertEquals(enemy.getHealthPotionDropChance(), settings.getHealthPotionDropChance());

    }

    @Test
    void testLoadDefaultSettings() {
        Settings settings = new Settings();

        assertEquals(settings.loadDefaultSettings(), true);
    }

    @Test
    void testDeleteSavedGame() {
        Settings settings = new Settings();

        Player player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
                settings.getNumberOfHealthPotions());
        ArrayList<Enemy> enemies = new ArrayList<>();

        assertEquals(settings.saveGame(player, enemies), true);

        assertEquals(settings.deleteSavedGame(), true);
    }

    @Test
    void testSaveGame() {
        Settings settings = new Settings();

        Player player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
                settings.getNumberOfHealthPotions());
        ArrayList<Enemy> enemies = new ArrayList<>();

        assertEquals(settings.saveGame(player, enemies), true);
    }

    @Test
    void testLoadGame() {
        Settings settings = new Settings();

        Player player = new Player(settings.getMaxPlayerHealth(), settings.getPlayerAttackDamage(),
                settings.getNumberOfHealthPotions());
        ArrayList<Enemy> enemies = new ArrayList<>();

        assertEquals(settings.saveGame(player, enemies), true);
        PlayerEnemyCombination data = settings.loadSavedGame();
        assertEquals(data.player.getName(), player.getName());
    }
}