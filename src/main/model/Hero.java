package model;

import java.util.ArrayList;
import java.util.List;

// Represents a hero having a name, health points, experience, level, and weapons
public class Hero {

    private static final int INITIAL_HERO_HEALTH = 10;
    private static final int INITIAL_HERO_EXPERIENCE = 0;
    private static final int INITIAL_HERO_LEVEL = 1;
    private static final int INITIAL_HERO_GOLD = 1000;

    private String name;            // the hero's name
    private int health;             // the hero's health points
    private int experience;         // the hero's experience points
    private int level;              // the hero's current level
    private int gold;               // the hero's current amount of gold
    private List<Weapon> weapons;   // the hero's current inventory of weapons

    public Hero(String name) {
        this.name = name;
        this.health = INITIAL_HERO_HEALTH;
        this.experience = INITIAL_HERO_EXPERIENCE;
        this.level = INITIAL_HERO_LEVEL;
        this.gold = INITIAL_HERO_GOLD;
        this.weapons = new ArrayList<>();
    }

    // getters
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

    // setters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void heroStatus() {
        System.out.println(this.name + " has the following status:");
        System.out.println("Health: " + this.health);
        System.out.println("Experience: " + this.experience);
        System.out.println("Level: " + this.level);
        System.out.println("Gold: " + this.gold);
    }

    public int heroMaxHealth() {
        return INITIAL_HERO_HEALTH * getLevel();
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: heals the hero by the specified amount
    public void heroHeal(int amount) {
        this.health += amount;
    }

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

    public Weapon weaponAtSlotNumber(int slot) {
        return weapons.get(slot - 1);
    }
}
