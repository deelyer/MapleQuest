package ui;

import com.sun.beans.decoder.DocumentHandler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class MapleQuestGUI extends JFrame {
    private static final int HEAL_COST = 50;
    private static final int FORGE_COST = 50;
    private static final int UPGRADE_COST = 50;
    private static final int REST_HEAL_AMOUNT = 5;
    private static final int WOUNDED_HERO_HEALTH = 10;
    private static final int MAX_WEAPON_TIER = 5;
    private static final int MAX_INVENTORY_SIZE = 3;

    private MapleQuest game;

    private Container con;
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
    private boolean keepRunning;

    private TitleScreenHandler tsHandler = new TitleScreenHandler();
    private UserTextHandler userHandler = new UserTextHandler();
    private ChoiceHandler choiceHandler = new ChoiceHandler();

    public static void main(String[] args) {
        new MapleQuestGUI();
    }

    public MapleQuestGUI() {
        super("MapleQuest");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // adds fxn to window to close (i.e. X close)
        getContentPane().setBackground(Color.WHITE); // set background colour of window
        setLayout(null); // allows for customizable layout
        con = getContentPane(); // window -> container -> panels,buttons,etc.
        game = new MapleQuest();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(250, 250, 500, 150); // start at x, y; make with height and width
        titleNamePanel.setBackground(Color.WHITE);
        titleNameLabel = new JLabel("MapleQuest");
        titleNameLabel.setForeground(Color.ORANGE);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(350, 600, 300, 150);
        startButtonPanel.setBackground(Color.WHITE);

        startButton = new JButton("START");
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.ORANGE);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler); // button takes an input (i.e. mouse-click)
        startButton.setFocusPainted(false); // removes the "box outline" from clicking buttons

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        setVisible(true); // allows for the window to actually appear
    }

    public void createMapleQuestScreen() {

        titleNamePanel.setVisible(false); // need to "hide" them, so we see the new screen!
        titleNameLabel.setVisible(false);
        startButtonPanel.setVisible(false);

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


        subTextArea = new JTextArea("NPC Dialogue Here!"); // setting up area of text
        subTextArea.setPreferredSize(new Dimension(800,75));
        subTextArea.setBackground(Color.ORANGE);
        subTextArea.setForeground(Color.BLACK);
        subTextArea.setFont(normalFont);
        subTextArea.setLineWrap(true); // lets text to be wrapped, doesn't run off screen
        subTextArea.setEditable(false); // unable to edit the text here
        mainTextPanel.add(subTextArea);

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
//                if (userTextEntry.getText().equals("Please enter text input here and press enter!")) {
                    userTextEntry.setText("");
//                }
            }

            public void mouseExited(MouseEvent e) {
                userTextEntry.setText("Please enter text input here and press enter!");
            }
        });
        userTextPanel.add(userTextEntry);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(100, 600, 800, 300);
        choiceButtonPanel.setBackground(Color.WHITE);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.WHITE);
        choice1.setForeground(Color.ORANGE);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.WHITE);
        choice2.setForeground(Color.ORANGE);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.WHITE);
        choice3.setForeground(Color.ORANGE);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.WHITE);
        choice4.setForeground(Color.ORANGE);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 800, 50);
        playerPanel.setBackground(Color.WHITE);
        playerPanel.setLayout(new GridLayout(1,6));

        heroLabel = new JLabel("Hero: ");
        heroLabel.setFont(normalFont);
        heroLabel.setForeground(Color.BLACK);
        playerPanel.add(heroLabel);

        heroLabelName = new JLabel();
        heroLabelName.setFont(normalFont);
        heroLabelName.setForeground(Color.BLACK);
        playerPanel.add(heroLabelName);

        hpLabel = new JLabel("HP: ");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.BLACK);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.BLACK);
        playerPanel.add(hpLabelNumber);

        goldLabel = new JLabel("Gold: ");
        goldLabel.setFont(normalFont);
        goldLabel.setForeground(Color.BLACK);
        playerPanel.add(goldLabel);

        goldLabelNumber = new JLabel();
        goldLabelNumber.setFont(normalFont);
        goldLabelNumber.setForeground(Color.BLACK);
        playerPanel.add(goldLabelNumber);

        con.add(mainTextPanel);
        con.add(userTextPanel);
        con.add(choiceButtonPanel);
        con.add(playerPanel);

        updatePlayerPanel();
        townGate();
    }

    public void updatePlayerPanel() {
        String heroName = game.getHero().getName();
        int playerHP = game.getHero().getHealth();
        int goldAmount = game.getHero().getGold();
        heroLabelName.setText(heroName);
        goldLabelNumber.setText(String.valueOf(goldAmount));
        hpLabelNumber.setText(String.valueOf(playerHP));
    }

    public void townGate() {
        userTextEntry.setActionCommand("hero");
        position = "townGate";
        mainTextArea.setText("Greetings traveller, welcome to Henesys!\nBy what name do you go by?");

        choice1.setText("Advance to next screen.");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("N/A");
    }

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

        choice1.setText("Visit the Town Nurse");
        choice2.setText("Visit the Weaponsmith");
        choice3.setText("Explore The Woods");
        choice4.setText("Hero Status");
    }

    // EFFECTS: generates Town Nurse initial dialogue, processes user input while visiting
    private void visitTownNurse() {
        position = "townNurse";
        mainTextArea.setText("You're currently at the Town Nurse.");
        subTextArea.setText("Nurse: Why hello, you must be another adventurer, how may I help you today?");

        choice1.setText("Heal My Wounds (" + HEAL_COST + ")");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");
    }

    // MODIFIES: hero
    // EFFECTS: generates dialogue if user selected heal option, processes subsequent command
    private void visitTownNurseHealDialogue() {
        position = "townNurseHeal";

        if (game.getHero().getHealth() >= game.getHero().heroMaxHealth()) {
            subTextArea.setText("Nurse: You're completely healthy already!\n" + "Come back when you're hurt.");
        } else if (game.getHero().getGold() < HEAL_COST) {
            subTextArea.setText("Nurse: You don't have enough gold! Try again later.");
        } else {
            game.getHero().heroGoldCostAmount(HEAL_COST);
            game.getHero().setHealth(game.getHero().heroMaxHealth());
            subTextArea.setText("You are now completely healed! Thanks for coming!");
            updatePlayerPanel();
        }

        choice1.setText("N/A");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");
    }

    // EFFECTS: generates Weaponsmith initial dialogue, processes user input while visiting
    private void visitWeaponSmith() {
        position = "townWeaponSmith";
        mainTextArea.setText("You're currently at the Weaponsmith.");
        subTextArea.setText("Weaponsmith: Hmph, how may I help you today traveller?");

        choice1.setText("Forge New Weapon (" + FORGE_COST + " Gold)");
        choice2.setText("Upgrade Weapon (" + UPGRADE_COST + " Gold)");
        choice3.setText("N/A");
        choice4.setText("Leave");
    }

    // MODIFIES: hero
    // EFFECTS: generates Weaponsmith forge weapon agreed dialogue, processes user input, adds weapon to hero
    public void visitWeaponSmithForgeWeaponAgreed() {
        position = "townWeaponSmithForge";
        if (game.getHero().getGold() < FORGE_COST) {
            subTextArea.setText("Weaponsmith: You don't have enough gold, stop wasting my time adventurer.");
        } else if (game.getHero().getWeapons().size() >= MAX_INVENTORY_SIZE) {
            subTextArea.setText("Weaponsmith: Hey, you're carrying too much, get rid of a weapon first.");
        } else {
            game.getHero().heroGoldCostAmount(FORGE_COST);
            userTextEntry.setActionCommand("weapon");
            subTextArea.setText("Weaponsmith: Very well, please give a name to your new weapon.");
//            mainTextArea.setText("Current Weapon Name: " + userTextEntry.getText());
//            game.getHero().addWeapon(userTextInput);
            updatePlayerPanel();
        }

        choice1.setText("Create Weapon");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("Leave");
    }

    private class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            createMapleQuestScreen();
        }
    }

    private class UserTextHandler implements ActionListener {

        public void actionPerformed(ActionEvent textEvent) {
            String textChoice = textEvent.getActionCommand();

            switch (textChoice) {
                case "hero":
                    heroName = userTextEntry.getText();
                    game.hero.setName(heroName);
                    heroLabelName.setText(heroName);
                    userTextEntry.setText("Please enter text input here and press enter!");
                case "default":
                    userTextEntry.getText();
                    userTextEntry.setText("Please enter text input here and press enter!");
                    break;
                case "weapon":
                    mainTextArea.setText("Current Weapon Name: " + userTextEntry.getText());
                    break;
            }
        }
    }

    private class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();

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
                            break;
                        case "c3":
//                            exploreTheWoods();
                            break;
                        case "c4":
                            break;
                    }
                    break;
                case "townNurse":
                    if ("c1".equals(yourChoice)) {
                        visitTownNurseHealDialogue();
                    }
                    break;
                case "townNurseHeal":
                case "townWeaponSmithForge":
                    if ("c4".equals(yourChoice)) {
                        townSquare();
                    }
                    break;
                case "townWeaponSmith":
                    switch (yourChoice) {
                        case "c1":
                            visitWeaponSmithForgeWeaponAgreed();
                            break;
                        case "c2":
//                          visitWeaponSmithUpgradeWeaponAgreed();
                            break;
                        case "c3":
//                          visitWeaponSmithRemoveWeaponAgreed();
                            break;
                        case "c4":
                            townSquare();
                            break;
                    }
                    break;
                case "townWeaponSmithForged":
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
    }
}
