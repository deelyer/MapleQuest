package model;

import java.util.ArrayList;
import java.util.List;

// Represents a hero having a name, health points, experience, level, and weapons
public class Hero {

    private static final int INITIAL_HERO_HEALTH = 10;
    private static final int INITIAL_HERO_EXPERIENCE = 0;
    private static final int INITIAL_HERO_LEVEL = 1;
    private static final int INITIAL_HERO_GOLD = 0;
//    private static final Weapon INITIAL_HERO_WEAPON = new Weapon("Sword");
    private static final int INITIAL_LEVEL_UP_EXP_REQUIRED = 1000;

    private String name;            // the hero's name
    private int health;             // the hero's health points
    private int experience;         // the hero's experience points
    private int level;              // the hero's current level
    private int levelUpExpRequired; // the hero's experience required to level
    private int gold;               // the hero's current amount of gold
    private List<Weapon> weapons;   // the hero's current inventory of weapons

    public Hero(String name) {
        this.name = name;
        this.health = INITIAL_HERO_HEALTH;
        this.experience = INITIAL_HERO_EXPERIENCE;
        this.level = INITIAL_HERO_LEVEL;
        this.levelUpExpRequired = INITIAL_LEVEL_UP_EXP_REQUIRED;
        this.gold = INITIAL_HERO_GOLD;
        this.weapons = new ArrayList<>();
//        this.weapons.add(INITIAL_HERO_WEAPON);
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

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public int getLevelUpExpRequired() {
        return levelUpExpRequired;
    }

    // setters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void heroGoldCostAmount(int gold) {
        this.gold -= gold;
    }

    // EFFECTS: gets the hero's maximum health
    public int heroMaxHealth() {
        return INITIAL_HERO_HEALTH * getLevel();
    }

    // MODIFIES: this
    // EFFECTS: heals the hero by the specified amount
    public void heroHeal(int amount) {
        this.health += amount;
    }

    // MODIFIES: this
    // EFFECTS: damages the hero by the specified amount
    public void damageToHero(int damage) {
        this.health -= damage;
    }

    // EFFECTS: determines if the hero's health reaches or passes 0, thus death
    public boolean heroDeath() {
        return this.health <= 0;
    }

    // MODIFIES: this
    // EFFECTS: hero gains the specified amount of gold
    public void heroGainGold(int amount) {
        this.gold += amount;
    }

    // MODIFIES: this
    // EFFECTS: hero gains the specified amount of experience
    public void heroGainExperience(int experience) {
        this.experience += experience;
    }

    // MODIFIES: this
    // EFFECTS: hero levels up if sufficient experience, increases statistics based on increased level
    public void heroLevelUp() {
        while (this.experience >= this.levelUpExpRequired) {
            this.level++;
            this.experience -= this.levelUpExpRequired;
            this.health = heroMaxHealth();
            this.levelUpExpRequired = this.levelUpExpRequired * getLevel();
        }
    }

    // REQUIRES: weaponName needs to be a String character
    // MODIFIES: this
    // EFFECTS: adds a weapon to the hero's inventory
    public void addWeapon(String weaponName) {
        Weapon weapon = new Weapon(weaponName);
        this.weapons.add(weapon);
    }

    // REQUIRES: weaponInInventory > 0
    // MODIFIES: this
    // EFFECTS: removes a weapon from the hero's inventory
    public void removeWeapon(int weaponInInventory) {
        this.weapons.remove(weaponInInventory - 1);
    }

    // REQUIRES: slot number > 0
    // EFFECTS: retrieves the weapon at the indicated slot number in inventory
    public Weapon weaponAtSlotNumber(int slot) {
        return weapons.get(slot - 1);
    }

    // EFFECTS: determining if specified weapon is present within inventory
    public boolean determineWeaponPresent(Weapon weapon) {
        return weapons.contains(weapon);
    }

}
