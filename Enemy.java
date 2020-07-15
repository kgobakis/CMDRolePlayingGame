public class Enemy {
    private String name;
    private int attackDamage;
    private int health;

    public Enemy(String name, int health, int attackDamage) {
        this.attackDamage = attackDamage;
        this.health = health;
        this.name = name;
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