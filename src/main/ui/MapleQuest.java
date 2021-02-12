package ui;

import model.Hero;
import model.Monster;
import model.Weapon;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class MapleQuest {

    private static final int HEAL_COST = 50;
    private static final int FORGE_COST = 50;
    private static final int UPGRADE_COST = 50;

    private Hero hero;
    private List<Monster> monsters;
    private Scanner input;

    // EFFECTS: runs MapleQuest application
    public MapleQuest() {
        runMapleQuest();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMapleQuest() {
        boolean keepGoing = true;
        String command;

        init();

        System.out.println("Greetings traveller, welcome to the world of Aurora!");
        System.out.println("By what name do you go by?");
        String name = input.next();
        hero = new Hero(name);
        System.out.println("Ah! Well nice to meet you " + name + ", please follow me into town.");

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
    private void processCommand(String command) {
        if (command.equals("h")) {
            heroStatus();
        } else if (command.equals("n")) {
            visitTownNurse();
        } else if (command.equals("w")) {
            visitWeaponSmith();
        } else if (command.equals("i")) {
            displayWeapons();
        } else {
            System.out.println("Please try another input.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes hero, weapons, and monsters
    private void init() {
        monsters = new ArrayList<>();
        input = new Scanner(System.in);
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
        System.out.println("\tq -> Quit MapleQuest");
    }

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
            visitTownNurseDialogue();
        } else {
            System.out.println("See you later!");
        }
    }

    private void visitTownNurseDialogue() {
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
                int newGoldBalance = hero.getGold() - HEAL_COST;
                hero.setGold(newGoldBalance);
                hero.setHealth(hero.heroMaxHealth());
                System.out.println("You are now completely healed! Thanks for coming!");
            } else {
                System.out.println("Sorry, I'm afraid you don't have enough gold, come back later when you do!");
            }
        } else {
            System.out.println("Sorry to hear that, perhaps next time then.");
        }
    }

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
            visitWeaponSmithUpgradeWeaponDialogue();
        } else {
            System.out.println("Hmph, see you next time adventurer.");
        }
    }

    public void visitWeaponSmithForgeWeaponDialogue() {
        System.out.println("That will cost ya " + FORGE_COST + " gold, is that alright?");
        String selection = "";
        while (!(selection.equals("y") || selection.equals("n"))) {
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

    public void visitWeaponSmithForgeWeaponDialogueAgreed() {
        if (hero.getGold() >= FORGE_COST) {
            if (hero.getWeapons().size() < 3) {
                int newGoldBalance = hero.getGold() - FORGE_COST;
                hero.setGold(newGoldBalance);
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
//        while (!(select == 1 || select == 2 || select == 3)) {
//            try {
//                select = input.nextInt();
//                System.out.println("Please type the slot number you wish to remove (i.e. 1).");
//                displayWeapons();
//            } catch (InputMismatchException ex) {
//                System.out.println("Incorrect input, please enter a slot value.");
//            }
//        }
//        if (select == 1) {
//            visitWeaponSmithRemoveWeaponSelectOptions(select);
//        } else if (select == 2) {
//            visitWeaponSmithRemoveWeaponSelectOptions(select);
//        } else {
//            visitWeaponSmithRemoveWeaponSelectOptions(select);
//        }
//    }

    public void visitWeaponSmithUpgradeWeaponDialogue() {
        System.out.println("That will cost ya " + UPGRADE_COST + " gold, is that alright?");
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

    public void visitWeaponSmithUpgradeSelectOptions(int select) {
        if (!(hero.weaponAtSlotNumber(select).weaponMaxTier())) {
            System.out.println("Alright, I've upgraded " + hero.getWeapons().get(select - 1).getWeaponName() + ".");
            hero.getWeapons().get(select - 1).upgradeWeapon();
            int newGoldBalance = hero.getGold() - UPGRADE_COST;
            hero.setGold(newGoldBalance);
        } else {
            System.out.println("Hey buddy, I can't do anything more for this weapon, it's already the best.");
        }
        visitWeaponSmith();
    }

    public void visitWeaponSmithRemoveWeaponSelectOptions(int select) {
        System.out.println("Alright, I've removed " + hero.getWeapons().get(select - 1).getWeaponName());
        hero.removeWeapon(select);
        visitWeaponSmith();
    }

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

    public void weaponStatistics(Weapon weapon) {
        System.out.println(weapon.getWeaponName() + ": " + weapon.getWeaponDamage() + " Damage, Tier: "
                + weapon.getWeaponTier() + " and Type: " + weapon.getWeaponType());
    }

    public void heroStatus() {
        System.out.println(hero.getName() + " has the following status:");
        System.out.println("Health: " + hero.getHealth());
        System.out.println("Experience: " + hero.getExperience());
        System.out.println("Level: " + hero.getLevel());
        System.out.println("Gold: " + hero.getGold());
    }

    public void exploreTheWoods() {
        System.out.println("You're currently exploring the forest outside of Henesys.");
        System.out.println("What would you like to do?");
        System.out.println("\te -> Continue Exploring Deeper Into The Woods");
        System.out.println("\tl -> Leave and Return To Town");
    }
}

