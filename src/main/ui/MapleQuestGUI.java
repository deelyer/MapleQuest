package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MapleQuestGUI extends JFrame {

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
    private JTextField userTextEntry;
    private String position;
    private String userTextInput;
    private static String heroName;

    private TitleScreenHandler tsHandler = new TitleScreenHandler();
//    private UserHeroTextHandler userHeroHandler = new UserHeroTextHandler();
    private UserTextHandler userHandler = new UserTextHandler();
//    private UserSelectHandler userSelectHandler = new UserSelectHandler();
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
        mainTextPanel.setBackground(Color.ORANGE);

        mainTextArea = new JTextArea("Main Text Area"); // setting up area of text
        mainTextArea.setBounds(100, 100, 800, 400);
        mainTextArea.setBackground(Color.RED);
        mainTextArea.setForeground(Color.BLACK);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true); // lets text to be wrapped, doesn't run off screen
        mainTextArea.setEditable(false); // unable to edit the text here
        mainTextPanel.add(mainTextArea);

        userTextPanel = new JPanel();
        userTextPanel.setBounds(100, 500, 800, 50);
        userTextPanel.setBackground(Color.GRAY);

        userTextEntry = new JTextField("Please enter text input here and press enter!");
        userTextEntry.setBounds(100, 500, 800, 50);
        userTextEntry.setBackground(Color.BLUE);
        userTextEntry.setForeground(Color.BLACK);
        userTextEntry.setFont(normalFont);
        userTextEntry.addActionListener(userHandler);
        userTextEntry.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (userTextEntry.getText().equals("Please enter text input here and press enter!")) {
                    userTextEntry.setText("");
                    repaint();
                    revalidate();
                }
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
        con.add(userTextEntry);
        con.add(choiceButtonPanel);
        con.add(playerPanel);

        playerPanelSetUp();
        townGate();
    }

    public void playerPanelSetUp() {
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

        heroName = userTextInput;
        game.hero.setName(heroName);
        heroLabelName.setText(heroName);
        heroLabelName.setEnabled(false);

        choice1.setText("Advance to next screen.");
        choice2.setText("N/A");
        choice3.setText("N/A");
        choice4.setText("N/A");
    }

    public void townSquare() {
        userTextEntry.setActionCommand("default");
        position = "townSquare";
        mainTextArea.setText("You're currently in Henesys, home of adventurers!\n"
                + "Please select from the following options:\n"
                + "n -> Visit the Town Nurse\n"
                + "w -> Visit the Weaponsmith\n"
                + "e -> Explore The Woods\n"
                + "h -> Hero Status");
//                + "\ti -> Weapon Inventory"
//                + "\ts -> Save Hero's Weapons"
//                + "\tl -> Load Hero's Weapons"
//                + "\tq -> Quit MapleQuest");

        choice1.setText("Visit the Town Nurse");
        choice2.setText("Visit the Weaponsmith");
        choice3.setText("Explore The Woods");
        choice4.setText("Hero Status");
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
                    userTextInput = userTextEntry.getText();
                    heroName = userTextInput;
                    game.hero.setName(heroName);
                    heroLabelName.setText(heroName);
                    userTextEntry.setText("Please enter text input here and press enter!");
                case "default":
                    userTextInput = userTextEntry.getText();
                    userTextEntry.setText("Please enter text input here and press enter!");
            }
        }
    }

    private class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();

            switch (position) {
                case "townGate":
                    switch (yourChoice) {
                        case "c1":
                            townSquare();
                            break;
                        case "c2":
                        case "c3":
                        case "c4":
                            break;
                    }
            }
        }
    }
}
