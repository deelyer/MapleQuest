package ui;

import model.Hero;
import model.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MapleQuest {

    private static final int HEAL_COST = 50;

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
        String name = ""; // force entry into loop
        name = input.next();
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
            hero.heroStatus();
        } else if (command.equals("m")) {
            visitTownNurse();
        } else {
            System.out.println("Try again");
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
        System.out.println("\tm -> Visit the Town Nurse");
        System.out.println("\tf -> Visit the Weaponsmith");
        System.out.println("\te -> Explore The Woods");
        System.out.println("\th -> Hero Status");
        System.out.println("\tq -> Quit MapleQuest");
    }

    private void visitTownNurse() {
        System.out.println("Why hello, you must be another adventurer, how many I help you today?");
        String selection = "";
        while (!(selection.equals("h") || selection.equals("l"))) {
            System.out.println("\th -> Heal my wounds");
            System.out.println("\tl -> Leave");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("h")) {
            System.out.println("That will cost " + HEAL_COST + ", is that okay?");
            while (!(selection.equals("y") || selection.equals("n"))) {
                System.out.println("\ty -> Yes");
                System.out.println("\tn -> No");
                selection = input.next();
                selection = selection.toLowerCase();
            }
            if (selection.equals("y")) {
                hero.setHealth(hero.heroMaxHealth());
                System.out.println("You are now completely healed!  Thanks for coming!");
            }
        }
    }
}

