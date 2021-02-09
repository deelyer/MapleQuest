package ui;

import model.Hero;
import model.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MapleQuest {

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
        } else {
            System.out.println("Try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes hero, weapons, and monsters
    private void init() {
        hero = new Hero("Timmy");
        monsters = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\th -> hero status");
        System.out.println("\tq -> quit");
    }
}

