package model;

import java.util.Random;

// Represents a weapon with a name, damage, tier, and elemental type
public class Weapon {

    private static final int INITIAL_WEAPON_DAMAGE = 10;
    private static final int INITIAL_WEAPON_TIER = 1;
    private static final int DAMAGE_UPGRADE_PER_TIER = 10;
    private static final int MAX_WEAPON_TIER = 5;

    private String weaponName;          // the name of the weapon
    private int weaponDamage;           // the amount of damage a weapon does
    private int weaponTier;             // the weapon tier level (from 1 to 5)
    private String weaponType;          // the elemental type of the weapon

    public Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.weaponTier = INITIAL_WEAPON_TIER;
        this.weaponDamage = INITIAL_WEAPON_DAMAGE;
        Random random = new Random();
        int choice = random.nextInt(8);
        if (choice < 3) {
            this.weaponType = "Fire";
        } else if (choice < 6) {
            this.weaponType = "Grass";
        } else {
            this.weaponType = "Water";
        }
    }

    // getters
    public int getWeaponDamage() {
        return weaponDamage;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getWeaponTier() {
        return weaponTier;
    }

    public String getWeaponType() {
        return weaponType;
    }

    // MODIFIES: this
    // EFFECTS: upgrade tier of weapon, and corresponding damage by tier
    public void upgradeWeapon() {
        this.weaponTier++;
        this.weaponDamage += (this.weaponTier * DAMAGE_UPGRADE_PER_TIER);
    }

//    public void weaponStatistics() {
//        System.out.println(getWeaponName() + ": " + getWeaponDamage() + " Damage, Tier: " + getWeaponTier()
//                + " and Type: " + getWeaponType());
//    }

    public boolean weaponMaxTier() {
        int currentWeaponTier = getWeaponTier();
        return currentWeaponTier == MAX_WEAPON_TIER;
    }

    public boolean weaponSuperEffectiveType(String otherType) {
        if (this.weaponType.equals("Fire") && otherType.equals("Grass")) {
            return true;
        } else if (this.weaponType.equals("Grass") && otherType.equals("Water")) {
            return true;
        } else {
            return this.weaponType.equals("Water") && otherType.equals("Fire");
        }
    }
}
