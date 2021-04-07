package model;

// Represents a monster with name, health, damage, gold amount, level, and experience amount
public class Monster {

    private static final int INITIAL_MONSTER_HEALTH = 10;
    private static final int INITIAL_MONSTER_EXPERIENCE = 200;
    private static final int INITIAL_MONSTER_LEVEL = 1;
    private static final int INITIAL_MONSTER_GOLD = 100;
    private static final int INITIAL_MONSTER_DAMAGE = 2;
    private static final int DROPPED_GOLD_AMOUNT = 5;

    protected String name;               // the monster's name
    protected int health;                // the monster's health
    protected int experience;            // the monster's experience points
    protected int level;                 // the monster's level
    protected int gold;                  // the monster's amount of gold carried
    protected int damage;                // the monster's amount of damage done

    // EFFECTS: constructs a new monster named "Goblin"
    public Monster() {
        this.name = "Goblin";
        this.health = INITIAL_MONSTER_HEALTH;
        this.experience = INITIAL_MONSTER_EXPERIENCE;
        this.level = INITIAL_MONSTER_LEVEL;
        this.gold = INITIAL_MONSTER_GOLD;
        this.damage = INITIAL_MONSTER_DAMAGE;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public int getDamage() {
        return damage;
    }

    // setters
    public void setHealth(int health) {
        this.health = health;
    }

    // MODIFIES: this
    // EFFECTS: deals the specified damage to monster's health
    public void damageToMonster(int damage) {
        this.health -= damage;
    }

    // MODIFIES: this
    // EFFECTS: scales monsters level according to hero level, then adjusts monsters stats accordingly
    public void scaleMonsterStatistics(int heroLevel) {
        if (heroLevel <= 1) {
            this.health = INITIAL_MONSTER_HEALTH;
            this.experience = INITIAL_MONSTER_EXPERIENCE;
            this.level = INITIAL_MONSTER_LEVEL;
            this.gold = INITIAL_MONSTER_GOLD;
            this.damage = INITIAL_MONSTER_DAMAGE;
        } else {
            this.level = heroLevel - 1;
            this.health = this.health * this.level;
            this.experience = this.experience * this.level;
            this.gold = this.gold * this.level;
            this.damage = this.damage * this.level;
        }
    }

    // EFFECTS: determine if monster's health has surpassed or reached 0
    public boolean monsterDeath() {
        return this.health <= 0;
    }

    // MODIFIES: this
    // EFFECTS: performs special monster move, removes a small amount of gold
    public String performSpecialEffect() {
        if (this.gold >= DROPPED_GOLD_AMOUNT) {
            this.gold -= DROPPED_GOLD_AMOUNT;
            return "The goblin dropped a bit of gold after you hit it.";
        } else {
            this.gold = 0;
            return "The golbin has no more gold to drop!";
        }
    }
}


