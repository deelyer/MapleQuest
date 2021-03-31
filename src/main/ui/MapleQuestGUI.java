package ui;

import model.Hero;
import model.Monster;
import model.Weapon;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Plays MapleQuest Simple GUI Application
public class MapleQuestGUI extends JFrame {
    private static final int HEAL_COST = 50;
    private static final int FORGE_COST = 50;
    private static final int UPGRADE_COST = 50;
    private static final int REST_HEAL_AMOUNT = 5;
    private static final int WOUNDED_HERO_HEALTH = 10;
    private static final int MAX_WEAPON_TIER = 5;
    private static final int MAX_INVENTORY_SIZE = 3;

    //    private MapleQuest game;
    private Hero hero;
    private List<Monster> monsters;
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    private Container con;
    //    private Container saveGameCon;
//    private Container loadGameCon;
//    private JFrame saveGameFrame;
//    private JFrame loadGameFrame;
//    private JPanel saveGamePanel;
//    private JPanel loadGamePanel;
//    private JButton yesButton;
//    private JButton noButton;
    private JPanel titleNamePanel;
    private JPanel startButtonPanel;
    private JPanel mainTextPanel;
    private JPanel userTextPanel;
    private JPanel choiceButtonPanel;
    private JPanel playerPanel;
    private JLabel titleNameLabel;
    private JLabel heroLabel;
    private JLabel heroLabelName;
    private JLabel hpLabel;
    private JLabel hpLabelNumber;
    private JLabel goldLabel;
    private JLabel goldLabelNumber;
    private Font titleFont = new Font("Courier", Font.BOLD, 90);
    private Font normalFont = new Font("Courier", Font.PLAIN, 30);
    private JButton startButton;
    private JButton choice1;
    private JButton choice2;
    private JButton choice3;
    private JButton choice4;
    private JTextArea mainTextArea;
    private JTextArea subTextArea;
    private JTextField userTextEntry;
    private String position;
    private String userTextInput;
    private static String heroName;
    private String nextPosition1;
    private String nextPosition2;
    private String nextPosition3;
    private String nextPosition4;
    private String choicePath;
    private String upperChoicePath;
    private static final String JSON_STORE = "./data/hero.json";

    private TitleScreenHandler tsHandler = new TitleScreenHandler();
    private UserTextHandler userHandler = new UserTextHandler();
    private ChoiceHandler choiceHandler = new ChoiceHandler();

    public static void main(String[] args) {
        new MapleQuestGUI();
    }

    // EFFECTS: initializes Maple Quest game application with GUI
    public MapleQuestGUI() {
        super("MapleQuest");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // adds fxn to window to close (i.e. X close)
        getContentPane().setBackground(Color.WHITE); // set background colour of window
        setLayout(null); // allows for customizable layout
        con = getContentPane(); // window -> container -> panels,buttons,etc.
        monsters = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        hero = new Hero("");
        upperChoicePath = "A";
        choicePath = "choicePath1";

        setUpTitlePanel();
        setUpStartButtonPanel();

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        setVisible(true); // allows for the window to actually appear
    }

    // EFFECTS: setup the title panel containing game name
    public void setUpTitlePanel() {
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(250, 250, 500, 150); // start at x, y; make with height and width
        titleNamePanel.setBackground(Color.WHITE);
        titleNameLabel = new JLabel("MapleQuest");
        titleNameLabel.setForeground(Color.ORANGE);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);
    }

    // EFFECTS: setup the start button panel containing start game button
    public void setUpStartButtonPanel() {
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(350, 600, 300, 150);
        startButtonPanel.setBackground(Color.WHITE);

        startButton = new JButton("START");
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.ORANGE);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler); // button takes an input (i.e. mouse-click)
        startButton.setFocusPainted(false); // removes the "box outline" from clicking buttons

        startButtonPanel.add(startButton);
    }

    // EFFECTS: initializes main game screen
    public void createMapleQuestScreen() {
        titleNamePanel.setVisible(false); // need to "hide" them, so we see the new screen!
        titleNameLabel.setVisible(false);
        startButtonPanel.setVisible(false);

        setUpMainGamePanel();
        setUpSubTextGamePanel();

//        mainTextPanel = new JPanel();
//        mainTextPanel.setBounds(100, 100, 800, 400);
//        mainTextPanel.setBackground(Color.WHITE);
//        mainTextPanel.setLayout(new BoxLayout(mainTextPanel, BoxLayout.PAGE_AXIS));
//
//        mainTextArea = new JTextArea("Main Text Area"); // setting up area of text
//        mainTextArea.setPreferredSize(new Dimension(800, 300));
//        mainTextArea.setBackground(Color.ORANGE);
//        mainTextArea.setForeground(Color.BLACK);
//        mainTextArea.setFont(normalFont);
//        mainTextArea.setLineWrap(true); // lets text to be wrapped, doesn't run off screen
//        mainTextArea.setEditable(false); // unable to edit the text here
//        mainTextPanel.add(mainTextArea);
//
//        mainTextPanel.add(Box.createRigidArea(new Dimension(0, 5)));
//
//
//        subTextArea = new JTextArea("NPC Dialogue Here!"); // setting up area of text
//        subTextArea.setPreferredSize(new Dimension(800,75));
//        subTextArea.setBackground(Color.ORANGE);
//        subTextArea.setForeground(Color.BLACK);
//        subTextArea.setFont(normalFont);
//        subTextArea.setLineWrap(true); // lets text to be wrapped, doesn't run off screen
//        subTextArea.setEditable(false); // unable to edit the text here
//        mainTextPanel.add(subTextArea);

        setUpUserTextEntryPanel();

//        userTextPanel = new JPanel();
//        userTextPanel.setBounds(100, 525, 800, 50);
//        userTextPanel.setBackground(Color.WHITE);
//
//        userTextEntry = new JTextField("Please enter text input here and press enter!");
//        userTextEntry.setBounds(100, 525, 800, 50);
//        userTextEntry.setBackground(Color.ORANGE);
//        userTextEntry.setForeground(Color.WHITE);
//        userTextEntry.setFont(normalFont);
//        userTextEntry.addActionListener(userHandler);
//        userTextEntry.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                userTextEntry.setText("");
//            }
//
//            public void mouseExited(MouseEvent e) {
//                userTextEntry.setText("Please enter text input here and press enter!");
//            }
//        });
//        userTextPanel.add(userTextEntry);

        setUpChoiceButtonPanel();
//        choiceButtonPanel = new JPanel();
//        choiceButtonPanel.setBounds(100, 600, 800, 300);
//        choiceButtonPanel.setBackground(Color.WHITE);
//        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        setUpChoiceButton(choice1, "c1");
        setUpChoiceButton(choice2, "c2");
        setUpChoiceButton(choice3, "c3");
        setUpChoiceButton(choice4, "c4");
//        choice1 = new JButton("Choice 1");
//        choice1.setBackground(Color.WHITE);
//        choice1.setForeground(Color.ORANGE);
//        choice1.setFont(normalFont);
//        choice1.setFocusPainted(false);
//        choice1.addActionListener(choiceHandler);
//        choice1.setActionCommand("c1");
//        choiceButtonPanel.add(choice1);
//
//        choice2 = new JButton("Choice 2");
//        choice2.setBackground(Color.WHITE);
//        choice2.setForeground(Color.ORANGE);
//        choice2.setFont(normalFont);
//        choice2.setFocusPainted(false);
//        choice2.addActionListener(choiceHandler);
//        choice2.setActionCommand("c2");
//        choiceButtonPanel.add(choice2);
//
//        choice3 = new JButton("Choice 3");
//        choice3.setBackground(Color.WHITE);
//        choice3.setForeground(Color.ORANGE);
//        choice3.setFont(normalFont);
//        choice3.setFocusPainted(false);
//        choice3.addActionListener(choiceHandler);
//        choice3.setActionCommand("c3");
//        choiceButtonPanel.add(choice3);
//
//        choice4 = new JButton("Choice 4");
//        choice4.setBackground(Color.WHITE);
//        choice4.setForeground(Color.ORANGE);
//        choice4.setFont(normalFont);
//        choice4.setFocusPainted(false);
//        choice4.addActionListener(choiceHandler);
//        choice4.setActionCommand("c4");
//        choiceButtonPanel.add(choice4);

//        playerPanel = new JPanel();
//        playerPanel.setBounds(100, 15, 800, 50);
//        playerPanel.setBackground(Color.WHITE);
//        playerPanel.setLayout(new GridLayout(1,6));
//
//        heroLabel = new JLabel("Hero: ");
//        heroLabelName = new JLabel();
//        hpLabel = new JLabel("HP: ");
//        hpLabelNumber = new JLabel();
//        goldLabel = new JLabel("Gold: ");
//        goldLabelNumber = new JLabel();
        setUpPlayerPanel();
        setUpPlayerPanelLabels(heroLabel);
        setUpPlayerPanelLabels(heroLabelName);
        setUpPlayerPanelLabels(hpLabel);
        setUpPlayerPanelLabels(hpLabelNumber);
        setUpPlayerPanelLabels(goldLabel);
        setUpPlayerPanelLabels(goldLabelNumber);
//        heroLabel = new JLabel("Hero: ");
//        heroLabel.setFont(normalFont);
//        heroLabel.setForeground(Color.BLACK);
//        playerPanel.add(heroLabel);
//
//        heroLabelName = new JLabel();
//        heroLabelName.setFont(normalFont);
//        heroLabelName.setForeground(Color.BLACK);
//        playerPanel.add(heroLabelName);
//
//        hpLabel = new JLabel("HP: ");
//        hpLabel.setFont(normalFont);
//        hpLabel.setForeground(Color.BLACK);
//        playerPanel.add(hpLabel);
//
//        hpLabelNumber = new JLabel();
//        hpLabelNumber.setFont(normalFont);
//        hpLabelNumber.setForeground(Color.BLACK);
//        playerPanel.add(hpLabelNumber);
//
//        goldLabel = new JLabel("Gold: ");
//        goldLabel.setFont(normalFont);
//        goldLabel.setForeground(Color.BLACK);
//        playerPanel.add(goldLabel);
//
//        goldLabelNumber = new JLabel();
//        goldLabelNumber.setFont(normalFont);
//        goldLabelNumber.setForeground(Color.BLACK);
//        playerPanel.add(goldLabelNumber);

        setUpPanelIntoContainer();
//        con.add(mainTextPanel);
//        con.add(userTextPanel);
//        con.add(choiceButtonPanel);
//        con.add(playerPanel);
        updatePlayerPanel();
        townGate();
    }

    // EFFECTS: setting up main text area panel and functionality
    public void setUpMainGamePanel() {
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 800, 400);
        mainTextPanel.setBackground(Color.WHITE);
        mainTextPanel.setLayout(new BoxLayout(mainTextPanel, BoxLayout.PAGE_AXIS));

        mainTextArea = new JTextArea("Main Text Area"); // setting up area of text
        mainTextArea.setPreferredSize(new Dimension(800, 300));
        mainTextArea.setBackground(Color.ORANGE);
        mainTextArea.setForeground(Color.BLACK);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true); // lets text to be wrapped, doesn't run off screen
        mainTextArea.setEditable(false); // unable to edit the text here
        mainTextPanel.add(mainTextArea);

        mainTextPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    // EFFECTS: setting up sub text area panel (NPC dialogue) and functionality
    public void setUpSubTextGamePanel() {
        subTextArea = new JTextArea("NPC Dialogue Here!"); // setting up area of text
        subTextArea.setPreferredSize(new Dimension(800, 75));
        subTextArea.setBackground(Color.ORANGE);
        subTextArea.setForeground(Color.BLACK);
        subTextArea.setFont(normalFont);
        subTextArea.setLineWrap(true); // lets text to be wrapped, doesn't run off screen
        subTextArea.setEditable(false); // unable to edit the text here
        mainTextPanel.add(subTextArea);
    }

    // EFFECTS: setting up user text entry panel and functionality
    public void setUpUserTextEntryPanel() {
        userTextPanel = new JPanel();
        userTextPanel.setBounds(100, 525, 800, 50);
        userTextPanel.setBackground(Color.WHITE);

        userTextEntry = new JTextField("Please enter text input here and press enter!");
        userTextEntry.setBounds(100, 525, 800, 50);
        userTextEntry.setBackground(Color.ORANGE);
        userTextEntry.setForeground(Color.WHITE);
        userTextEntry.setFont(normalFont);
        userTextEntry.addActionListener(userHandler);
        userTextEntry.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                userTextEntry.setText("");
            }

            public void mouseExited(MouseEvent e) {
                userTextEntry.setText("Please enter text input here and press enter!");
            }
        });
        userTextPanel.add(userTextEntry);
    }

    // EFFECTS: setting up choice button panel, adding relevant 4 buttons
    public void setUpChoiceButtonPanel() {
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(100, 600, 800, 300);
        choiceButtonPanel.setBackground(Color.WHITE);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choice1 = new JButton("Choice 1");
        choice2 = new JButton("Choice 2");
        choice3 = new JButton("Choice 3");
        choice4 = new JButton("Choice 4");

//        choice1 = new JButton("c1");
//        choice1.setBackground(Color.WHITE);
//        choice1.setForeground(Color.ORANGE);
//        choice1.setFont(normalFont);
//        choice1.setFocusPainted(false);
//        choice1.addActionListener(choiceHandler);
//        choice1.setActionCommand("c1");
//        choiceButtonPanel.add(choice1);
//
//        choice2 = new JButton("c2");
//        choice2.setBackground(Color.WHITE);
//        choice2.setForeground(Color.ORANGE);
//        choice2.setFont(normalFont);
//        choice2.setFocusPainted(false);
//        choice2.addActionListener(choiceHandler);
//        choice2.setActionCommand("c2");
//        choiceButtonPanel.add(choice2);
//
//        choice3 = new JButton("c3");
//        choice3.setBackground(Color.WHITE);
//        choice3.setForeground(Color.ORANGE);
//        choice3.setFont(normalFont);
//        choice3.setFocusPainted(false);
//        choice3.addActionListener(choiceHandler);
//        choice3.setActionCommand("c3");
//        choiceButtonPanel.add(choice3);
//
//        choice4 = new JButton("c4");
//        choice4.setBackground(Color.WHITE);
//        choice4.setForeground(Color.ORANGE);
//        choice4.setFont(normalFont);
//        choice4.setFocusPainted(false);
//        choice4.addActionListener(choiceHandler);
//        choice4.setActionCommand("c4");
//        choiceButtonPanel.add(choice4);
    }

    // EFFECTS: setting up JButton formatting and functionality
    public void setUpChoiceButton(JButton button, String choice) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.ORANGE);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.addActionListener(choiceHandler);
        button.setActionCommand(choice);
        choiceButtonPanel.add(button);
    }

    // EFFECTS: setting up player panel with JLabels added in
    public void setUpPlayerPanel() {
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 800, 50);
        playerPanel.setBackground(Color.WHITE);
        playerPanel.setLayout(new GridLayout(1, 6));

        heroLabel = new JLabel("Hero: ");
        heroLabelName = new JLabel();
        hpLabel = new JLabel("HP: ");
        hpLabelNumber = new JLabel();
        goldLabel = new JLabel("Gold: ");
        goldLabelNumber = new JLabel();
    }

    // EFFECTS: setting up all JLabels in player panel
    public void setUpPlayerPanelLabels(JLabel label) {
        label.setFont(normalFont);
        label.setForeground(Color.BLACK);
        playerPanel.add(label);
    }

    // EFFECTS: adding all set up panels into container
    public void setUpPanelIntoContainer() {
        con.add(mainTextPanel);
        con.add(userTextPanel);
        con.add(choiceButtonPanel);
        con.add(playerPanel);
    }

    // EFFECTS: updates the player panel with current name, hp, and gold
    public void updatePlayerPanel() {
        String heroName = hero.getName();
        int playerHP = hero.getHealth();
        int goldAmount = hero.getGold();
        heroLabelName.setText(heroName);
        goldLabelNumber.setText(String.valueOf(goldAmount));
        hpLabelNumber.setText(String.valueOf(playerHP));
    }

    // EFFECTS: starting screen for entering hero name
    public void townGate() {
        userTextEntry.setActionCommand("hero");
        position = "townGate";
        mainTextArea.setText("Greetings traveller, welcome to Henesys!\nBy what name do you go by?");

        upperChoicePath = "A";
        choicePath = "choicePath1";
        choice1.setText("Advance to next screen.");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("N/A");

//        nextPosition1 = "townSquare";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "";
    }

    // EFFECTS: the main game screen for all available options
    public void townSquare() {
        userTextEntry.setActionCommand("default");
        position = "townSquare";
        mainTextArea.setText("You're currently in Henesys, home of adventurers!\n"
                + "Please select from the following options:\n");
//                + "\ti -> Weapon Inventory"
//                + "\ts -> Save Hero's Weapons"
//                + "\tl -> Load Hero's Weapons"
//                + "\tq -> Quit MapleQuest");
        subTextArea.setText("NPC Dialogue Here!");

        upperChoicePath = "A";
        choicePath = "choicePath1";
        choice1.setText("Visit the Town Nurse");
        choice2.setText("Visit the Weaponsmith");
        choice3.setText("Explore The Woods");
        choice4.setText("Hero Status");

//        nextPosition1 = "townNurse";
//        nextPosition2 = "townWeaponSmith";
//        nextPosition3 = "";
//        nextPosition4 = "displayHeroStatus";
    }

    // EFFECTS: generates Town Nurse initial dialogue, processes user input while visiting
    private void visitTownNurse() {
        position = "townNurse";
        mainTextArea.setText("You're currently at the Town Nurse.");
        subTextArea.setText("Nurse: Why hello, you must be another adventurer, how may I help you today?");

        upperChoicePath = "A";
        choicePath = "choicePath2";
        choice1.setText("Heal My Wounds (" + HEAL_COST + " Gold)");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

//        nextPosition1 = "townNurseHeal";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "townSquare";
    }

    // MODIFIES: hero
    // EFFECTS: generates dialogue if user selected heal option, processes subsequent command
    private void visitTownNurseHealDialogue() {
        position = "townNurseHeal";

        if (hero.getHealth() >= hero.heroMaxHealth()) {
            subTextArea.setText("Nurse: You're completely healthy already!\n" + "Come back when you're hurt.");
        } else if (hero.getGold() < HEAL_COST) {
            subTextArea.setText("Nurse: You don't have enough gold! Try again later.");
        } else {
            hero.heroGoldCostAmount(HEAL_COST);
            hero.setHealth(hero.heroMaxHealth());
            subTextArea.setText("You are now completely healed! Thanks for coming!");
            updatePlayerPanel();
        }

        upperChoicePath = "A";
        choicePath = "choicePath2";
        choice1.setText("N/A");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

//        nextPosition1 = "";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "townSquare";
    }

    // EFFECTS: generates Weaponsmith initial dialogue, processes user input while visiting
    private void visitWeaponSmith() {
        position = "townWeaponSmith";
        mainTextArea.setText("You're currently at the Weaponsmith.");
        subTextArea.setText("Weaponsmith: Hmph, how may I help you today traveller?");

        upperChoicePath = "A";
        choicePath = "choicePath3";
        choice1.setText("Forge New Weapon (" + FORGE_COST + " Gold)");
        choice2.setText("Upgrade Weapon (" + UPGRADE_COST + " Gold)");
        choice3.setText("Remove Weapon");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithForge";
//        nextPosition2 = "townWeaponSmithUpgrade";
//        nextPosition3 = "townWeaponSmithRemove";
//        nextPosition4 = "townSquare";
    }

    // MODIFIES: hero
    // EFFECTS: generates Weaponsmith forge weapon agreed dialogue, processes user input
    public void visitWeaponSmithForgeWeaponAgreed() {
        userTextEntry.setActionCommand("default");
        position = "townWeaponSmithForge";
        if (hero.getGold() < FORGE_COST) {
            subTextArea.setText("Weaponsmith: You don't have enough gold,\nstop wasting my time adventurer.");
        } else if (hero.getWeapons().size() >= MAX_INVENTORY_SIZE) {
            subTextArea.setText("Weaponsmith: Hey, you're carrying too much,\nget rid of a weapon first.");
        } else {
            hero.heroGoldCostAmount(FORGE_COST);
            userTextEntry.setActionCommand("weapon");
            subTextArea.setText("Weaponsmith: Very well, please give a name\nto your new weapon.");
            updatePlayerPanel();
        }

        upperChoicePath = "A";
        choicePath = "choicePath4";
        choice1.setText("Create Weapon");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithForgeComplete";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "townSquare";
    }

    // MODIFIES: hero
    // EFFECTS: generates new weapon for hero
    public void visitWeaponSmithForgeWeaponComplete() {
        position = "townWeaponSmithForgeComplete";
        hero.addWeapon(userTextInput);
        userTextEntry.setActionCommand("default");
        displayWeapons();
        subTextArea.setText("Weaponsmith: Here's your new weapon.");

        upperChoicePath = "A";
        choicePath = "choicePath4";
        choice1.setText("Forge Another Weapon (" + FORGE_COST + " Gold)");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithForge";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "townSquare";
    }

    // EFFECTS: generates Weaponsmith dialogue if upgrade agreed, processes user input
    public void visitWeaponSmithUpgradeWeaponAgreed() {
        position = "townWeaponSmithUpgrade";
        if (hero.getGold() < UPGRADE_COST) {
            subTextArea.setText("Weaponsmith: You don't have enough gold, stop\nwasting my time adventurer.");
        } else if (hero.getWeapons().size() <= 0) {
            subTextArea.setText("Weaponsmith: You don't have a weapon, come back\nwhen you do!");
        } else {
            subTextArea.setText("Weaponsmith: Alright, which weapon do you\nwant me to upgrade?");
            displayWeapons();
        }

        upperChoicePath = "A";
        choicePath = "choicePath5";
        choice1.setText("Slot 1");
        choice2.setText("Slot 2");
        choice3.setText("Slot 3");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithUpgradeComplete";
//        nextPosition2 = "townWeaponSmithUpgradeComplete";
//        nextPosition3 = "townWeaponSmithUpgradeComplete";
//        nextPosition4 = "townSquare";
    }

    // MODIFIES: hero
    // EFFECTS: processes which weapon in specified slot to upgrade, brings back to Weaponsmith dialogue after
    public void visitWeaponSmithUpgradeWeaponComplete(int select) {
        position = "townWeaponSmithUpgradeComplete";
        try {
            if (hero.weaponAtSlotNumber(select).weaponMaxTier(MAX_WEAPON_TIER)) {
                mainTextArea.setText("Please return back to town.");
                subTextArea.setText("Weaponsmith: This weapon is fully upgraded already.");
            } else {
                mainTextArea.setText("Please return back to town.");
                subTextArea.setText("Alright, I've upgraded "
                        + hero.getWeapons().get(select - 1).getWeaponName() + ".");
                hero.getWeapons().get(select - 1).upgradeWeapon();
                hero.heroGoldCostAmount(UPGRADE_COST);
                updatePlayerPanel();
            }
        } catch (IndexOutOfBoundsException e) {
            mainTextArea.setText("There's no weapon in that slot.\n" + "Please try again with another weapon");
            subTextArea.setText("");
        }

        upperChoicePath = "A";
        choicePath = "choicePath6";
        choice1.setText("Try Another Weapon");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithUpgrade";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "townSquare";
    }

    // EFFECTS: confirming which weapon to remove
    public void visitWeaponSmithRemoveWeaponAgreed() {
        position = "townWeaponSmithRemove";
        if (hero.getWeapons().size() <= 1) {
            mainTextArea.setText("Please return back to town.");
            subTextArea.setText("Weaponsmith: You only have one weapon, hang on to it.");
        } else {
            subTextArea.setText("Weaponsmith: Alright, which weapon do you\nwant me to remove?");
            displayWeapons();
        }

        upperChoicePath = "A";
        choicePath = "choicePath7";
        choice1.setText("Slot 1");
        choice2.setText("Slot 2");
        choice3.setText("Slot 3");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithRemoveComplete";
//        nextPosition2 = "townWeaponSmithRemoveComplete";
//        nextPosition3 = "townWeaponSmithRemoveComplete";
//        nextPosition4 = "townSquare";
    }

    // MODIFIES: hero
    // EFFECTS: removes weapon specified in slot selected, brings back to Weaponsmith dialogue after
    public void visitWeaponSmithRemoveWeaponComplete(int select) {
        position = "townWeaponSmithRemoveComplete";
        try {
            hero.removeWeapon(select);
            displayWeapons();
            subTextArea.setText("Weaponsmith: Alright, I've removed the weapon.");
        } catch (IndexOutOfBoundsException e) {
            mainTextArea.setText("There's no weapon in that slot.\n"
                    + "Please try again with another weapon");
            subTextArea.setText("");
        }

        upperChoicePath = "A";
        choicePath = "choicePath6";
        choice1.setText("Remove Another Weapon");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

//        nextPosition1 = "townWeaponSmithRemove";
//        nextPosition2 = "";
//        nextPosition3 = "";
//        nextPosition4 = "townSquare";
    }

    // EFFECTS: displays both list of weapons and hero level and experience in main text area
    public void displayHeroStatus() {
        position = "displayHeroStatus";
        displayWeapons();
        displayHeroLevelStats();

        upperChoicePath = "B";
        choicePath = "choicePath8";
        choice1.setText("Save Weapons");
        choice2.setText("Load Weapons");
        choice3.setText("N/A");
        choice4.setText("Leave");

        nextPosition1 = "saveGame";
        nextPosition2 = "loadGame";
        nextPosition3 = "";
        nextPosition4 = "townSquare";
    }

    // EFFECTS: displays hero's current list of weapons in main text area
    public void displayWeapons() {
        int slotNumber = 0;
        mainTextArea.setText("Weapons In Inventory:\n");
        for (Weapon weapon : hero.getWeapons()) {
            mainTextArea.append("Slot " + (slotNumber + 1) + ") Name: " + weapon.getWeaponName()
                    + " Damage: " + weapon.getWeaponDamage() + " Tier: " + weapon.getWeaponTier() + "\n");
            slotNumber++;
        }
    }

    // EFFECTS: displays the hero level and experience onto main text area
    public void displayHeroLevelStats() {
        mainTextArea.append("Level: " + hero.getLevel() + "\nExperience: " + hero.getExperience());
    }

    // EFFECTS: opens the save game prompt screen
    public void openSaveGamePrompt() {
        position = "saveGame";
        mainTextArea.setText("Will you save your game?\nThis will overwrite any previous data.");

        upperChoicePath = "B";
        choicePath = "choicePath9";
        choice1.setText("Yes");
        choice2.setText("No");
        choice3.setText("N/A");
        choice4.setText("Leave");

        nextPosition1 = "saveGameComplete";
        nextPosition2 = "townSquare";
        nextPosition3 = "";
        nextPosition4 = "townSquare";

//        saveGameFrame = new JFrame();
//        saveGameFrame.setSize(500, 500);
//        saveGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // adds fxn to window to close (i.e. X close)
//        saveGameFrame.getContentPane().setBackground(Color.WHITE); // set background colour of window
//        setLayout(null); // allows for customizable layout
//        saveGameCon = saveGameFrame.getContentPane();
//
//        saveGamePanel = new JPanel();
//        saveGamePanel.setBackground(Color.BLUE);
//        saveGamePanel.setBounds(0, 0, 300, 200);
//        JTextArea saveGamePanelText = new JTextArea();
//        saveGamePanelText.setText("Do you want to overwrite any existing save game?");
//        saveGamePanelText.setPreferredSize(new Dimension(300, 150));
//        saveGamePanelText.setLineWrap(true);
//        saveGamePanelText.setEditable(false);
//        saveGamePanelText.setBackground(Color.RED);
//        saveGamePanelText.setForeground(Color.BLACK);
//        saveGamePanelText.setFont(normalFont);
//        saveGamePanel.add(saveGamePanelText);
//
//        JPanel saveGameButtonPanel = new JPanel();
//        saveGameButtonPanel.setBounds(0, 250, 50, 50);
//        saveGameButtonPanel.setBackground(Color.ORANGE);
//        yesButton = new JButton("Yes");
//        noButton = new JButton("No ");
//        saveGameButtonPanel.add(yesButton);
//        saveGameButtonPanel.add(noButton);

//        yesButton.setBackground(Color.BLUE);
//        yesButton.setForeground(Color.ORANGE);
//        yesButton.setFont(normalFont);
//        yesButton.setFocusPainted(false);
//        yesButton.addActionListener(choiceHandler);
//        yesButton.setActionCommand("c1");

//        saveGameCon.add(saveGamePanel);
//        saveGameCon.add(saveGameButtonPanel);
//        saveGameFrame.setVisible(true);
    }

    // EFFECTS: confirms game was successfully saved
    public void openSaveGamePromptComplete() {
        position = "saveGameComplete";
        mainTextArea.setText("Game was successfully saved.");
        saveHeroToFile();

        upperChoicePath = "B";
        choicePath = "choicePath9";
        choice1.setText("N/A");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

        nextPosition1 = "";
        nextPosition2 = "";
        nextPosition3 = "";
        nextPosition4 = "townSquare";
    }

    // EFFECTS: open the load game prompt
    public void openLoadGamePrompt() {
        position = "loadGame";
        mainTextArea.setText("Will you load your previous game?\nThis will overwrite any current data.");

        upperChoicePath = "B";
        choicePath = "choicePath10";
        choice1.setText("Yes");
        choice2.setText("No");
        choice3.setText("N/A");
        choice4.setText("Leave");

        nextPosition1 = "loadGameComplete";
        nextPosition2 = "townSquare";
        nextPosition3 = "";
        nextPosition4 = "townSquare";
    }

    // EFFECTS: confirm that user has successfully loaded game
    public void openLoadGamePromptComplete() {
        position = "loadGameComplete";
        mainTextArea.setText("Game was successfully loaded.");
        loadHeroFromFile();
        updatePlayerPanel();

        upperChoicePath = "B";
        choicePath = "choicePath10";
        choice1.setText("N/A");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");

        nextPosition1 = "";
        nextPosition2 = "";
        nextPosition3 = "";
        nextPosition4 = "townSquare";
    }

    // EFFECTS: plays anvil sound when opening visiting Weaponsmith
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: saves the hero to file
    public void saveHeroToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(hero);
            jsonWriter.close();
//            System.out.println("Saved " + hero.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Code snippet from: WorkRoomApp
    // EFFECTS: loads hero from file
    public void loadHeroFromFile() {
        try {
            hero = jsonReader.read();
//            System.out.println("Loaded " + hero.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

//    public void selectPosition(String nextPosition) {
//        switch (nextPosition) {
//            case "townSquare":
//                townSquare();
//                break;
//            case "townNurse":
//                visitTownNurse();
//                break;
//            case "townNurseHeal":
//                visitTownNurseHealDialogue();
//                break;
//            case "townWeaponSmith":
//                visitWeaponSmith();
//            case "townWeaponSmithForge":
//                visitWeaponSmithForgeWeaponAgreed();
//            case "townWeaponSmithForgeComplete":
//                visitWeaponSmithForgeWeaponComplete();
//            case "townWeaponSmithUpgrade":
//                visitWeaponSmithUpgradeWeaponAgreed();
//            case "townWeaponSmithUpgradeComplete":
//        }
//    }

    // EFFECTS: loads up main game screen from title
    private class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            createMapleQuestScreen();
        }
    }

    // EFFECTS: register user text input after pressing enter
    private class UserTextHandler implements ActionListener {

        public void actionPerformed(ActionEvent textEvent) {
            String textChoice = textEvent.getActionCommand();

            switch (textChoice) {
                case "hero":
                    heroName = userTextEntry.getText();
                    hero.setName(heroName);
                    heroLabelName.setText(heroName);
                    userTextEntry.setText("Please enter text input here and press enter!");
                case "default":
                    userTextEntry.getText();
                    userTextEntry.setText("Please enter text input here and press enter!");
                    break;
                case "weapon":
                    userTextInput = userTextEntry.getText();
                    mainTextArea.setText("Current Weapon Name: " + userTextEntry.getText());
                    userTextEntry.setText("Please enter text input here and press enter!");
                    break;
            }
        }
    }

    // EFFECTS: choice paths from townGate and townSquare
    public void choicePath1(String yourChoice) {
        switch (position) {
            case "townGate":
                if ("c1".equals(yourChoice)) {
                    townSquare();
                }
                break;
            case "townSquare":
                switch (yourChoice) {
                    case "c1":
                        visitTownNurse();
                        break;
                    case "c2":
                        visitWeaponSmith();
                        playSound("./data/hammer_anvil.wav");
                        break;
                    case "c4":
                        displayHeroStatus();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path from townNurse
    public void choicePath2(String yourChoice) {
        switch (position) {
            case "townNurse":
                switch (yourChoice) {
                    case "c1":
                        visitTownNurseHealDialogue();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
            case "townNurseHeal":
                if ("c4".equals(yourChoice)) {
                    townSquare();
                }
                break;
        }
    }

    // EFFECTS: choice path from Weaponsmith
    public void choicePath3(String yourChoice) {
        switch (position) {
            case "townWeaponSmith":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithForgeWeaponAgreed();
                        break;
                    case "c2":
                        visitWeaponSmithUpgradeWeaponAgreed();
                        break;
                    case "c3":
                        visitWeaponSmithRemoveWeaponAgreed();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for forging weapons from Weaponsmith
    public void choicePath4(String yourChoice) {
        switch (position) {
            case "townWeaponSmithForge":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithForgeWeaponComplete();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
            case "townWeaponSmithForgeComplete":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithForgeWeaponAgreed();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for upgrading weapon at Weaponsmith
    public void choicePath5(String yourChoice) {
        switch (position) {
            case "townWeaponSmithUpgrade":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithUpgradeWeaponComplete(1);
                        break;
                    case "c2":
                        visitWeaponSmithUpgradeWeaponComplete(2);
                        break;
                    case "c3":
                        visitWeaponSmithUpgradeWeaponComplete(3);
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for after completing upgrade or removal of weapon from Weaponsmith
    public void choicePath6(String yourChoice) {
        switch (position) {
            case "townWeaponSmithUpgradeComplete":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithUpgradeWeaponAgreed();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
            case "townWeaponSmithRemoveComplete":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithRemoveWeaponAgreed();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for selecting weapon to remove from Weaponsmith
    public void choicePath7(String yourChoice) {
        switch (position) {
            case "townWeaponSmithRemove":
                switch (yourChoice) {
                    case "c1":
                        visitWeaponSmithRemoveWeaponComplete(1);
                        break;
                    case "c2":
                        visitWeaponSmithRemoveWeaponComplete(2);
                        break;
                    case "c3":
                        visitWeaponSmithRemoveWeaponComplete(3);
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for displaying hero status screen
    public void choicePath8(String yourChoice) {
        switch (position) {
            case "displayHeroStatus":
                switch (yourChoice) {
                    case "c1":
                        openSaveGamePrompt();
                        break;
                    case "c2":
                        openLoadGamePrompt();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for saving your game
    public void choicePath9(String yourChoice) {
        switch (position) {
            case "saveGame":
                switch (yourChoice) {
                    case "c1":
                        openSaveGamePromptComplete();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
            case "saveGameComplete":
                switch (yourChoice) {
                    case "c1":
                        openSaveGamePromptComplete();
                        break;
                    case "c2":
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice path for loading your game
    public void choicePath10(String yourChoice) {
        switch (position) {
            case "loadGame":
                switch (yourChoice) {
                    case "c1":
                        openLoadGamePromptComplete();
                        break;
                    case "c2":
                    case "c4":
                        townSquare();
                        break;
                }
                break;
            case "loadGameComplete":
                switch (yourChoice) {
                    case "c1":
                        openLoadGamePromptComplete();
                        break;
                    case "c4":
                        townSquare();
                        break;
                }
                break;
        }
    }

    // EFFECTS: choice paths for majority of game elements, not including displaying hero information or save/load game
    public void upperChoicePathA(String yourChoice) {
        switch (choicePath) {
            case "choicePath1":
                choicePath1(yourChoice);
                break;
            case "choicePath2":
                choicePath2(yourChoice);
                break;
            case "choicePath3":
                choicePath3(yourChoice);
                break;
            case "choicePath4":
                choicePath4(yourChoice);
                break;
            case "choicePath5":
                choicePath5(yourChoice);
                break;
            case "choicePath6":
                choicePath6(yourChoice);
                break;
            case "choicePath7":
                choicePath7(yourChoice);
                break;
        }
    }

    // EFFECTS: choice paths for displaying hero status, saving or loading game
    public void upperChoicePathB(String yourChoice) {
        switch (choicePath) {
            case "choicePath8":
                choicePath8(yourChoice);
                break;
            case "choicePath9":
                choicePath9(yourChoice);
                break;
            case "choicePath10":
                choicePath10(yourChoice);
                break;
        }
    }

    // EFFECTS: registers button choices for any given prompt
    private class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            switch (upperChoicePath) {
                case "A":
                    upperChoicePathA(yourChoice);
                    break;
                case "B":
                    upperChoicePathB(yourChoice);
                    break;
            }
        }
    }
//            switch (choicePath) {
//                case "choicePath1":
//                    choicePath1(yourChoice);
//                    break;
//                case "choicePath2":
//                    choicePath2(yourChoice);
//                    break;
//                case "choicePath3":
//                    choicePath3(yourChoice);
//                    break;
//                case "choicePath4":
//                    choicePath4(yourChoice);
//                    break;
//                case "choicePath5":
//                    choicePath5(yourChoice);
//                    break;
//                case "choicePath6":
//                    choicePath6(yourChoice);
//                    break;
//                case "choicePath7":
//                    choicePath7(yourChoice);
//                    break;
//                case "choicePath8":
//                    choicePath8(yourChoice);
//                    break;
//                case "choicePath9":
//                    choicePath9(yourChoice);
//                    break;
//                case "choicePath10":
//                    choicePath10(yourChoice);
//                    break;

//            switch (yourChoice) {
//                case "c1":
//                    selectPosition(nextPosition1);
//                    break;
//                case "c2":
//                    selectPosition(nextPosition2);
//                    break;
//                case "c3":
//                    selectPosition(nextPosition3);
//                    break;
//                case "c4":
//                    selectPosition(nextPosition4);
//                    break;
//            }

//            switch (position) {
//                case "townGate":
//                    if ("c1".equals(yourChoice)) {
//                        townSquare();
//                    }
//                    break;
//                case "townSquare":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitTownNurse();
//                            break;
//                        case "c2":
//                            visitWeaponSmith();
//                            playSound("./data/hammer_anvil2.wav");
//                            break;
//                        case "c3":
////                            exploreTheWoods();
//                            break;
//                        case "c4":
//                            displayHeroStatus();
//                            break;
//                    }
//                    break;
//                case "townNurse":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitTownNurseHealDialogue();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townNurseHeal":
//                    if ("c4".equals(yourChoice)) {
//                        townSquare();
//                    }
//                    break;
//                case "townWeaponSmith":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithForgeWeaponAgreed();
//                            break;
//                        case "c2":
//                            visitWeaponSmithUpgradeWeaponAgreed();
//                            break;
//                        case "c3":
//                            visitWeaponSmithRemoveWeaponAgreed();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townWeaponSmithForge":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithForgeWeaponComplete();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townWeaponSmithForgeComplete":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithForgeWeaponAgreed();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townWeaponSmithUpgrade":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithUpgradeWeaponComplete(1);
//                            break;
//                        case "c2":
//                            visitWeaponSmithUpgradeWeaponComplete(2);
//                            break;
//                        case "c3":
//                            visitWeaponSmithUpgradeWeaponComplete(3);
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townWeaponSmithUpgradeComplete":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithUpgradeWeaponAgreed();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townWeaponSmithRemove":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithRemoveWeaponComplete(1);
//                            break;
//                        case "c2":
//                            visitWeaponSmithRemoveWeaponComplete(2);
//                            break;
//                        case "c3":
//                            visitWeaponSmithRemoveWeaponComplete(3);
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "townWeaponSmithRemoveComplete":
//                    switch (yourChoice) {
//                        case "c1":
//                            visitWeaponSmithRemoveWeaponAgreed();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "displayHeroStatus":
//                    switch (yourChoice) {
//                        case "c1":
//                            openSaveGamePrompt();
//                            break;
//                        case "c2":
//                            openLoadGamePrompt();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "saveGame":
//                    switch (yourChoice) {
//                        case "c1":
//                            openSaveGamePromptComplete();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                case "saveGameComplete":
//                    switch (yourChoice) {
//                        case "c1":
//                            openSaveGamePromptComplete();
//                            break;
//                        case "c2":
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "loadGame":
//                    switch (yourChoice) {
//                        case "c1":
//                            openLoadGamePromptComplete();
//                            break;
//                        case "c2":
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//                case "loadGameComplete":
//                    switch (yourChoice) {
//                        case "c1":
//                            openLoadGamePromptComplete();
//                            break;
//                        case "c4":
//                            townSquare();
//                            break;
//                    }
//                    break;
//            }
//        }
}
