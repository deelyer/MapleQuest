package ui;

import model.Hero;
import model.Monster;
import model.Weapon;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// MapleQuest Simple RPG application
public class MapleQuest {

    private static final int HEAL_COST = 50;
    private static final int FORGE_COST = 50;
    private static final int UPGRADE_COST = 50;
    private static final int REST_HEAL_AMOUNT = 5;
    private static final int WOUNDED_HERO_HEALTH = 10;
    private static final int MAX_WEAPON_TIER = 5;
    private static final String JSON_STORE = "./data/hero.json";

    protected Hero hero;
    protected List<Monster> monsters;
    private Scanner input;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    // getters
    public Hero getHero() {
        return hero;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    // EFFECTS: runs MapleQuest application
    public MapleQuest() {
        runMapleQuest();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Code: partial code snippet taken from TellerApp
    private void runMapleQuest() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThanks for playing MapleQuest!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // Code: partial code snippet taken from TellerApp
    private void processCommand(String command) {
        if (command.equals("h")) {
            heroStatus();
        } else if (command.equals("n")) {
            visitTownNurse();
        } else if (command.equals("w")) {
            visitWeaponSmith();
        } else if (command.equals("e")) {
            if (hero.getWeapons().size() <= 0) {
                System.out.println("Town Guard: Adventurer, you don't have a weapon! Visit the Weaponsmith first!");
            } else {
                System.out.println("Town Guard: Seems like you're prepared, don't die out there.");
                exploreTheWoods();
            }
        } else if (command.equals("i")) {
            displayWeapons();
        } else if (command.equals("s")) {
            saveHeroToFile();
        } else if (command.equals("l")) {
            loadHeroFromFile();
        } else {
            System.out.println("Please try another input.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes hero, weapons, and monsters
    private void init() {
        monsters = new ArrayList<>();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("Greetings traveller, welcome to the world of Aurora!");
        System.out.println("By what name do you go by?");
        String name = input.next();
        hero = new Hero(name);
        hero = new Hero("");
        System.out.println("Ah! Well nice to meet you " + name + ", please follow me into town.");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("You're currently in Henesys, home of adventurers!");
        System.out.println("Please select from the following options:");
        System.out.println("\tn -> Visit the Town Nurse");
        System.out.println("\tw -> Visit the Weaponsmith");
        System.out.println("\te -> Explore The Woods");
        System.out.println("\th -> Hero Status");
        System.out.println("\ti -> Weapon Inventory");
        System.out.println("\ts -> Save Hero's Weapons");
        System.out.println("\tl -> Load Hero's Weapons");
        System.out.println("\tq -> Quit MapleQuest");
    }

    // EFFECTS: generates Town Nurse initial dialogue, processes user input while visiting
    private void visitTownNurse() {
        System.out.println("You're currently at the Town Nurse.");
        System.out.println("Why hello, you must be another adventurer, how many I help you today?");
        String selection = "";
        while (!(selection.equals("h") || selection.equals("l"))) {
            System.out.println("\th -> Heal My Wounds");
            System.out.println("\tl -> Leave");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("h")) {
            if (hero.getHealth() >= hero.heroMaxHealth()) {
                System.out.println("Adventurer, you're completely healthy!  Come back when you're hurt.");
            } else {
                visitTownNurseHealDialogue();
            }
        } else {
            System.out.println("See you later!");
        }
    }

    // MODIFIES: hero
    // EFFECTS: generates dialogue if user selected heal option, processes user input, heals hero if possible
    private void visitTownNurseHealDialogue() {
        String selection = "";
        System.out.println("That will cost " + HEAL_COST + " gold, is that okay?");
        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("\ty -> Yes");
            System.out.println("\tn -> No");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("y")) {
            if (hero.getGold() >= HEAL_COST) {
                hero.heroGoldCostAmount(HEAL_COST);
                hero.setHealth(hero.heroMaxHealth());
                System.out.println("You are now completely healed! Thanks for coming!");
            } else {
                System.out.println("Sorry, I'm afraid you don't have enough gold, come back later when you do!");
            }
        } else {
            System.out.println("Sorry to hear that, perhaps next time then.");
        }
    }

    // EFFECTS: generates Weaponsmith initial dialogue, processes user input while visiting
    private void visitWeaponSmith() {
        System.out.println("You're currently at the Weaponsmith.");
        System.out.println("Hmph, how may I help you today traveller?");
        String selection = "";
        while (!(selection.equals("f") || selection.equals("l") || selection.equals("u"))) {
            System.out.println("\tf -> Forge New Weapon");
            System.out.println("\tu -> Upgrade Your Weapon");
            System.out.println("\tl -> Leave");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("f")) {
            visitWeaponSmithForgeWeaponDialogue();
        } else if (selection.equals("u")) {
            if (hero.getWeapons().size() <= 0) {
                System.out.println("Adventurer, you don't have a weapon! Forge a weapon first!");
            } else {
                visitWeaponSmithUpgradeWeaponDialogue();
            }
        } else {
            System.out.println("Hmph, see you next time adventurer.");
        }
    }

    // EFFECTS: generates Weaponsmith forge weapon dialogue, processes user input
    public void visitWeaponSmithForgeWeaponDialogue() {
        String selection = "";
        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("That will cost ya " + FORGE_COST + " gold, is that alright?");
            System.out.println("\ty -> Yes");
            System.out.println("\tn -> No");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("y")) {
            visitWeaponSmithForgeWeaponDialogueAgreed();
        } else {
            System.out.println("Hmph, well stop wasting my time then.");
        }
    }

    // MODIFIES: hero
    // EFFECTS: generates Weaponsmith forge weapon agreed dialogue, processes user input, adds weapon to hero
    public void visitWeaponSmithForgeWeaponDialogueAgreed() {
        if (hero.getGold() >= FORGE_COST) {
            if (hero.getWeapons().size() < 3) {
                hero.heroGoldCostAmount(FORGE_COST);
                System.out.println("Very well, please give a name to your new weapon.");
                String weaponName = input.next();
                hero.addWeapon(weaponName);
                System.out.println("Congratulations, you now own " + weaponName + ".");
                visitWeaponSmith();
            } else {
                visitWeaponSmithForgeWeaponOverCarrying();
            }
        } else {
            System.out.println("You don't have enough gold, stop wasting my time adventurer.");
        }
    }

    // EFFECTS: generates Weaponsmith dialogue if too many weapons carried during forging, processes user input
    public void visitWeaponSmithForgeWeaponOverCarrying() {
        System.out.println("Hey, you're carrying too much, get rid of a weapon first.");
        System.out.println("Please type the slot number you wish to remove (i.e. 1).");
        displayWeapons();
        int select;
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Hey, you're carrying too much, get rid of a weapon first.");
            System.out.println("Please type the slot number you wish to remove (i.e. 1).");
            displayWeapons();
        }
        select = input.nextInt();
        if (select == 1 || select == 2 || select == 3) {
            if (select <= hero.getWeapons().size()) {
                visitWeaponSmithRemoveWeaponSelectOptions(select);
            } else {
                visitWeaponSmithForgeWeaponOverCarrying();
            }
        } else {
            visitWeaponSmithForgeWeaponOverCarrying();
        }
    }

    // EFFECTS: generates Weaponsmith upgrade weapon diagloue, processes user input
    public void visitWeaponSmithUpgradeWeaponDialogue() {
        String selection = "";
        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("That will cost ya " + UPGRADE_COST + " gold, is that alright?");
            System.out.println("\ty -> Yes");
            System.out.println("\tn -> No");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("y")) {
            visitWeaponSmithUpgradeWeaponAgreed();
        } else {
            System.out.println("Hmph, well stop wasting my time then.");
        }
    }

    // EFFECTS: generates Weaponsmith dialogue if upgrade agreed, processes user input
    public void visitWeaponSmithUpgradeWeaponAgreed() {
        if (hero.getGold() >= UPGRADE_COST) {
            int select;
            System.out.println("Alright, which weapon do you want me to upgrade? (i.e. 1).");
            displayWeapons();
            while (!input.hasNextInt()) {
                input.next();
                System.out.println("Alright, which weapon do you want me to upgrade? (i.e. 1).");
                displayWeapons();
            }
            select = input.nextInt();
            if (select == 1 || select == 2 || select == 3) {
                if (select <= hero.getWeapons().size()) {
                    visitWeaponSmithUpgradeSelectOptions(select);
                } else {
                    visitWeaponSmithUpgradeWeaponAgreed();
                }
            } else {
                visitWeaponSmithUpgradeWeaponAgreed();
            }
        } else {
            System.out.println("You don't have enough gold, stop wasting my time adventurer.");
        }
    }

    // REQUIRES: select must be valid slot number (i.e. weapon must exist in slot)
    // MODIFIES: hero
    // EFFECTS: processes which weapon in specified slot to upgrade, brings back to Weaponsmith dialogue after
    public void visitWeaponSmithUpgradeSelectOptions(int select) {
        if (!(hero.weaponAtSlotNumber(select).weaponMaxTier(MAX_WEAPON_TIER))) {
            System.out.println("Alright, I've upgraded " + hero.getWeapons().get(select - 1).getWeaponName() + ".");
            hero.getWeapons().get(select - 1).upgradeWeapon();
            hero.heroGoldCostAmount(UPGRADE_COST);
        } else {
            System.out.println("Hey buddy, I can't do anything more for this weapon, it's already the best.");
        }
        visitWeaponSmith();
    }

    // REQUIRES: select must be a valid slot number (i.e. weapon must exist in slot)
    // MODIFIES: hero
    // EFFECTS: removes weapon specified in slot selected, brings back to Weaponsmith dialogue after
    public void visitWeaponSmithRemoveWeaponSelectOptions(int select) {
        System.out.println("Alright, I've removed " + hero.getWeapons().get(select - 1).getWeaponName() + ".");
        hero.removeWeapon(select);
        visitWeaponSmith();
    }

    // EFFECTS: displays the list of weapons currently carried
    public void displayWeapons() {
        int slotNumber = 0;
        List<Weapon> allHeroWeapons = hero.getWeapons();
        if (allHeroWeapons.size() == 0) {
            System.out.println("You have no weapons in your inventory.");
        } else {
            System.out.println("You have the following weapons in your inventory:");
            for (Weapon weapon : allHeroWeapons) {
                System.out.print("Slot " + (slotNumber + 1) + ") ");
                weaponStatistics(weapon);
                slotNumber++;
            }
        }
    }

    // EFFECTS: displays the weapon statistics of the specified weapon
    public void weaponStatistics(Weapon weapon) {
        System.out.println(weapon.getWeaponName() + ": " + weapon.getWeaponDamage() + " Damage, Tier: "
                + weapon.getWeaponTier());
//                + " and Type: " + weapon.getWeaponType());
    }

    // EFFECTS: displays the hero's statistics
    public void heroStatus() {
        System.out.println(hero.getName() + " has the following status:");
        System.out.println("Health: " + hero.getHealth());
        System.out.println("Experience: " + hero.getExperience());
        System.out.println("Level: " + hero.getLevel());
        System.out.println("Gold: " + hero.getGold());
    }

    // MODIFIES: hero
    // EFFECTS: generates exploring the woods dialogue, processes user input, allows rest to heal hero's health
    public void exploreTheWoods() {
        System.out.println("You're currently exploring the forest outside of Henesys.");
        System.out.println("What would you like to do?");
        String selection = "";
        while (!(selection.equals("e") || selection.equals("l") || selection.equals("r") || selection.equals("h"))) {
            exploreTheWoodsDialogueOptions();
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("e")) {
            generateRandomEncounter();
        } else if (selection.equals("r")) {
            hero.heroHeal(REST_HEAL_AMOUNT);
            exploreTheWoods();
        } else if (selection.equals("h")) {
            heroStatus();
            exploreTheWoods();
        } else {
            System.out.println("You begin the long trek back to town after some time.");
        }
    }

    // EFFECTS: generates exploring the woods selectable options
    public void exploreTheWoodsDialogueOptions() {
        System.out.println("\te -> Continue Exploring Deeper Into The Woods");
        System.out.println("\tr -> Rest and Recover A Bit Of Health" + " (" + REST_HEAL_AMOUNT + ").");
        System.out.println("\th -> View Hero Statistics");
        System.out.println("\tl -> Leave and Return To Town");
    }

    // MODIFIES: hero and monsters
    // EFFECTS: generates a random encounter with a monster, processes user input to attack or flee
    public void generateRandomEncounter() {
        Monster monster = generateSingleMonsterEncounter(monsters);
        String selection = "";
        while (!(selection.equals("a") || selection.equals("l"))) {
            performBattleScenarioDialogueOptions();
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("a")) {
            performAttackChoiceEntireSequence(monster);
            while (!(hero.heroDeath() || monster.monsterDeath())) {
                performBattleScenarioChoices(monster);
            }
            if (monster.monsterDeath()) {
                resultMonsterDeath(monster);
                monsters.remove(monster);
                exploreTheWoods();
            } else if (hero.heroDeath()) {
                resultHeroDeath();
                monsters.remove(monster);
            }
        } else {
            performAttemptToFlee();
        }
    }

    // MODIFIES: monsters
    // EFFECTS: generates a single monster only encounter
    public Monster generateSingleMonsterEncounter(List<Monster> monsters) {
        Monster monster = new Monster();
        monsters.add(monster);
        monster.scaleMonsterStatistics(hero.getLevel());
        System.out.println("You've encountered a " + monster.getName() + "!");
        return monster;
    }

    // EFFECTS: processes the possible options once in a battle scenario
    public void performBattleScenarioChoices(Monster monster) {
        String selection = "";
        while (!(selection.equals("a") || selection.equals("l"))) {
            performBattleScenarioDialogueOptions();
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("a")) {
            performAttackChoiceEntireSequence(monster);
        } else {
            monster.setHealth(0);
        }
    }

    // EFFECTS: generates the dialogue for selectable options within a battle scenario
    public void performBattleScenarioDialogueOptions() {
        System.out.println("What will you do?");
        System.out.println("\ta -> Attack");
        System.out.println("\tl -> Attempt To Leave");
    }

    // EFFECTS: processes which weapon the user selects to attack with, retrives the approriate weapon damage
    public int performAttackChoice() {
        System.out.println("Which weapon will you attack with? (i.e. 1).");
        displayWeapons();
        int select;
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Which weapon will you attack with?");
            displayWeapons();
        }
        select = input.nextInt();
        if (select == 1 || select == 2 || select == 3) {
            if (select <= hero.getWeapons().size()) {
                return hero.weaponAtSlotNumber(select).getWeaponDamage();
            } else {
                return performAttackChoice();
            }
        } else {
            return performAttackChoice();
        }
    }

    // MODIFIES: monster
    // EFFECTS: deals damage to monster based on weapon choice of user
    public void performAttackChoiceEntireSequence(Monster monster) {
        int damageToMonster = performAttackChoice();
        monster.damageToMonster(damageToMonster);
        hero.damageToHero(monster.getDamage());
        System.out.println("The " + monster.getName() + " hits for " + monster.getDamage()
                + ". Your HP: " + hero.getHealth());
        System.out.println("You swing at the monster and deal " + damageToMonster + " damage!"
                + " Monster HP: " + monster.getHealth());
    }

    // EFFECTS: during a battle encounter, have the option to escape
    public void performAttemptToFlee() {
        System.out.println("You managed to escape!");
    }

    // MODIFIES: hero
    // EFFECTS: updates hero with gold and experience after monster death, level up if possible (i.e. enough exp)
    public void resultMonsterDeath(Monster monster) {
        System.out.println("Congratulations, you've slain the " + monster.getName() + "!");
        hero.heroGainGold(monster.getGold());
        hero.heroGainExperience(monster.getExperience());
        hero.heroLevelUp();
    }

    // MODIFIES: hero
    // EFFECTS: updates hero with wounded health state after losing/dying in battle encounter
    public void resultHeroDeath() {
        System.out.println("You have been defeated in battle!  You pass out on the battlegrounds.");
        System.out.println("You wake up wounded and confused, but it appears someone has brought you back to town.");
        hero.setHealth(WOUNDED_HERO_HEALTH);
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: saves the hero to file
    public void saveHeroToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(hero);
            jsonWriter.close();
            System.out.println("Saved " + hero.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: loads hero from file
    public void loadHeroFromFile() {
        try {
            hero = jsonReader.read();
            System.out.println("Loaded " + hero.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

