package model;

public class Ogre extends Monster {
    private static final int DAMAGE_INCREASE = 2;
    private static final int OGRE_INITIAL_HEALTH = 20;

    // EFFECTS: constructs a new monster named "Ogre"
    public Ogre() {
        super();
        this.name = "Ogre";
        this.health = OGRE_INITIAL_HEALTH;
    }

    // MODIFIES: this
    // EFFECTS: performs special monster move, increases damage of attack
    @Override
    public String performSpecialEffect() {
        this.damage += DAMAGE_INCREASE;
        return "The ogre strengthened itself and does more damage!";
    }
}
