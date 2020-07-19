package com.cmdgame;

public class Enemy {
    private String name;
    private int attackDamage;
    private int health;
    private int healthPotionDropChance;
    private int level;

    public Enemy(String name, int health, int attackDamage, int healthPotionDropChance, int level) {
        this.attackDamage = attackDamage;
        this.health = health;
        this.name = name;
        this.healthPotionDropChance = healthPotionDropChance;
        this.level = level;
    }

    public Enemy(String name, int health, int attackDamage) {
        this.attackDamage = attackDamage;
        this.health = health;
        this.name = name;

    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealthPotionDropChance() {
        return this.healthPotionDropChance;
    }

    public void setHealthPotionDropChance(int healthPotionDropChance) {
        this.healthPotionDropChance = healthPotionDropChance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}