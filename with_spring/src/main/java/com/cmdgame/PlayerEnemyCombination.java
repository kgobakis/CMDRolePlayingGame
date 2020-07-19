package com.cmdgame;

import java.util.ArrayList;

public class PlayerEnemyCombination {
    public Player player;
    public ArrayList<Enemy> enemies;

    public PlayerEnemyCombination(Player player, ArrayList<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
    }
}