package com.cmdgame;

public class Player {
    private int health;
    private int attackDamage;
    private int numberOfHealthPotions;
    private int enemiesDefeated;
    private String name;
    private int level;

    public Player(int health, int attackDamage, int numberOfHealthPotions) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.numberOfHealthPotions = numberOfHealthPotions;

    }

    public Player(int health, int attackDamage, int numberOfHealthPotions, String name) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.numberOfHealthPotions = numberOfHealthPotions;
        this.name = name;
    }

    public Player(int health, int attackDamage, int numberOfHealthPotions, String name, int enemiesDefeated) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.numberOfHealthPotions = numberOfHealthPotions;
        this.name = name;
        this.enemiesDefeated = enemiesDefeated;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getNumberOfHealthPotions() {
        return this.numberOfHealthPotions;
    }

    public void setNumberOfHealthPotions(int numberOfHealthPotions) {
        this.numberOfHealthPotions = numberOfHealthPotions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnemiesDefeated() {
        return this.enemiesDefeated;
    }

    public void setEnemiesDefeated(int enemiesDefeated) {
        this.enemiesDefeated = enemiesDefeated;
    }

}