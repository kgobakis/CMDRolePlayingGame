public class Player {
    private int health;
    private int attackDamage;
    private int numberOfHealthPotions;
    private String name;

    public Player(int health, int attackDamage, int numberOfHealthPotions) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.numberOfHealthPotions = numberOfHealthPotions;

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

}