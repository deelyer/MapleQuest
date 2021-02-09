package model;

import java.util.Random;

public class Weapon {

    private static final int INITIAL_WEAPON_DAMAGE = 10;
    private static final int INITIAL_WEAPON_TIER = 1;
    private static final int DAMAGE_UPGRADE_PER_TIER = 10;

    private String weaponName;          // the name of the weapon
    private int weaponDamage;           // the amount of damage a weapon does
    private int weaponTier;             // the weapon tier level (from 1 to 5)
    private String weaponType;          // the type of weapon

    public Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.weaponTier = INITIAL_WEAPON_TIER;
        this.weaponDamage = INITIAL_WEAPON_DAMAGE;
        Random random = new Random();
        int choice = random.nextInt(3);
        if (choice == 0) {
            this.weaponType = "Fire";
        } else if (choice == 1) {
            this.weaponType = "Grass";
        } else {
            this.weaponType = "Water";
        }
    }

    // getters
    private int getWeaponDamage() {
        return weaponDamage;
    }

    // MODIFIES: this
    // EFFECTS: upgrade tier of weapon, and corresponding damage by tier
    private void upgradeWeapon() {
        this.weaponTier++;
        this.weaponDamage += (this.weaponTier * DAMAGE_UPGRADE_PER_TIER);
    }
}
