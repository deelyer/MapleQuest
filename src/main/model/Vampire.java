package model;

public class Vampire extends Monster {
    private static final int HEALTH_STEAL_AMOUNT = 2;

    // EFFECTS: constructs a new monster called "Vampire"
    public Vampire() {
        super();
        this.name = "Vampire";
    }

    // MODIFIES: this
    // EFFECTS: performs special monster move, recovers a small amount of health
    @Override
    public String performSpecialEffect() {
        this.health += HEALTH_STEAL_AMOUNT;
        return "The vampire stole a bit of health after it attacked.";
    }
}
