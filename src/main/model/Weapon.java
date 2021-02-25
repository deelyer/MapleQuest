package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

// Represents a weapon with a name, damage, and tier
public class Weapon implements Writable {

    private static final int INITIAL_WEAPON_DAMAGE = 10;
    private static final int INITIAL_WEAPON_TIER = 1;
    private static final int DAMAGE_UPGRADE_PER_TIER = 10;

    private String weaponName;          // the name of the weapon
    private int weaponDamage;           // the amount of damage a weapon does
    private int weaponTier;             // the weapon tier level (from 1 to 5)
//    private String weaponType;          // the elemental type of the weapon

    // EFFECTS: constructs a new weapon with given name
    public Weapon(String weaponName) {
        this.weaponName = weaponName;
        this.weaponTier = INITIAL_WEAPON_TIER;
        this.weaponDamage = INITIAL_WEAPON_DAMAGE;
//        Random random = new Random();
//        int choice = random.nextInt(100);
//        if (choice < 33) {
//            this.weaponType = "Fire";
//        } else if (choice < 67) {
//            this.weaponType = "Grass";
//        } else {
//            this.weaponType = "Water";
//        }
    }

    // TODO: NEED TO WRITE TEST
    // EFFECTS: constructs a new weapon with given name, damage, and tier
    public Weapon(String weaponName, int weaponDamage, int weaponTier) {
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.weaponTier = weaponTier;
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

    // setters
    public void setWeaponTier(int weaponTier) {
        this.weaponTier = weaponTier;
    }

    // MODIFIES: this
    // EFFECTS: upgrade tier of weapon, and corresponding damage by tier
    public void upgradeWeapon() {
        this.weaponTier++;
        this.weaponDamage = (this.weaponTier * DAMAGE_UPGRADE_PER_TIER);
    }

    // EFFECTS: determines if weapon tier has reached/surpassed the maximum tier
    public boolean weaponMaxTier(int maxTier) {
        return this.weaponTier >= maxTier;
    }


    @Override
    // Code snippet from: WorkRoomApp
    // EFFECTS: converts weapon to JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", weaponName);
        json.put("weapondamage", weaponDamage);
        json.put("weapontier", weaponTier);
        return json;
    }


//    public boolean weaponSuperEffectiveType(String otherType) {
//        if (this.weaponType.equals("Fire") && otherType.equals("Grass")) {
//            return true;
//        } else if (this.weaponType.equals("Grass") && otherType.equals("Water")) {
//            return true;
//        } else {
//            return this.weaponType.equals("Water") && otherType.equals("Fire");
//        }
//    }
//
//    public String getWeaponType() {
//        return weaponType;
//    }
}
