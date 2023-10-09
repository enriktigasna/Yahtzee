import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class YahtzeeWindow extends JFrame {

    // ADD TOGGLE TO BUTTONS, AND INTEGER ARRAY OF TOGGLED BUTTONS
    // FOR EACH BUTTON ADD AN EVENT LISTENER THAT TRIGGERS A FUNCTION THAT TAKES INDEX OF BUTTON AS ARGUMENT
    public JButton[] buttons = new JButton[5];
    public JButton rollButton = new JButton("Roll");
    public LockedYahtzeeCounter lockedYCounter = new LockedYahtzeeCounter();

    JTable table = new JTable();
    JTable lockedTable = new JTable();

    public int[] currentDice = new int[5];

    public int[] skippedDice = new int[] {0, 0, 0, 0, 0};
    public int rerollsLeft = 2;
    public JLabel rerollText = new JLabel();
    public JLabel roundText = new JLabel();

    String[] options = { "brownie", "pie", "cake" };


    private void toggleSkip(int index) {
        if(skippedDice[index] == 0) {
            skippedDice[index] = 1;
            buttons[index].setBackground(Color.RED);
        } else {
            skippedDice[index] = 0;
            buttons[index].setBackground(Color.WHITE);
        }
    }

    public void resetButtons() {
        currentDice = new int[5];
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setVisible(false);
            remove(buttons[i]);
            buttons[i].updateUI();
        }
    }

    public void updateButtons(int[] dice){
        currentDice = dice;
        for(int i = 0; i < dice.length; i++) {
            if(buttons[i] != null){
                remove(buttons[i]);
            }

            buttons[i] = new JButton(String.valueOf(dice[i]));
            buttons[i].setBounds((i+1)*100, 200, 50, 40);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFocusPainted(false);

            add(buttons[i]);
            buttons[i].updateUI();

            int tmp = i;
            buttons[i].addActionListener(e -> toggleSkip(tmp));
        }
    }

    public void updateTable (int[] values) {
        if(table != null) {
            remove(table);
        }

        String[] columnNames = {"UPPER SECTION", "Value"};
        Object[][] data = {
                {"ACES", new String(String.valueOf(0))},
                {"TWOS", new String(String.valueOf(0))},
                {"THREES", new String(String.valueOf(0))},
                {"FOURS", new String(String.valueOf(0))},
                {"FIVES", new String(String.valueOf(0))},
                {"SIXES", new String(String.valueOf(0))},
                {"BONUS", new String(String.valueOf(0))},
                // {"UPPER TOTAL", new String(String.valueOf(0))},
                {"THREE OF A KIND", new String(String.valueOf(0))},
                {"FOUR OF A KIND", new String(String.valueOf(0))},
                {"YAHTZEE", new String(String.valueOf(0))},
                {"LARGE STRAIGHT", new String(String.valueOf(0))},
                {"SMALL STRAIGHT", new String(String.valueOf(0))},
                // {"LOWER TOTAL", new String(String.valueOf(0))},
        };
        for(int i = 0; i < values.length; i++) {
            data[i][1] = values[i];
        }

        table = new JTable(data, columnNames);
        table.setBounds(600, 50, 250, 190);
        add(table);
    }

    public void updateLockedTable (int[] values) {
        if(lockedTable != null) {
            remove(lockedTable);
        }

        String[] columnNames = {"UPPER SECTION", "Value"};
        Object[][] data = {
                {"ACES", new String(String.valueOf(0))},
                {"TWOS", new String(String.valueOf(0))},
                {"THREES", new String(String.valueOf(0))},
                {"FOURS", new String(String.valueOf(0))},
                {"FIVES", new String(String.valueOf(0))},
                {"SIXES", new String(String.valueOf(0))},
                {"BONUS", new String(String.valueOf(0))},
                {"THREE OF A KIND", new String(String.valueOf(0))},
                {"FOUR OF A KIND", new String(String.valueOf(0))},
                {"YAHTZEE", new String(String.valueOf(0))},
                {"LARGE STRAIGHT", new String(String.valueOf(0))},
                {"SMALL STRAIGHT", new String(String.valueOf(0))},
                {"UPPER TOTAL", new String(String.valueOf(0))},
                {"LOWER TOTAL", new String(String.valueOf(0))},
                {"GRAND TOTAL", new String(String.valueOf(0))},
        };
        for(int i = 0; i < values.length; i++) {
            data[i][1] = values[i];
        }

        lockedTable = new JTable(data, columnNames);
        lockedTable.setBounds(600, 250, 250, 240);
        add(lockedTable);
    }

    public void updateRerolls() {
        rerollText.setText("Rerolls left: " + String.valueOf(rerollsLeft));
        rerollText.setBounds(50, 0, 100, 40);
        add(rerollText);
    }

    public void updateRounds(int roundsLeft) {
        roundText.setText("Rounds left: " + String.valueOf(roundsLeft));
        roundText.setBounds(480, 0, 100, 40);
        add(roundText);
    }

    public String createLockingPopup() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please make a selection:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("ACES");
        model.addElement("TWOS");
        model.addElement("THREES");
        model.addElement("FOURS");
        model.addElement("FIVES");
        model.addElement("SIXES");
        model.addElement("BONUS");
        model.addElement("THREE OF A KIND");
        model.addElement("FOUR OF A KIND");
        model.addElement("YAHTZEE");
        model.addElement("LARGE STRAIGHT");
        model.addElement("SMALL STRAIGHT");

        JComboBox comboBox = new JComboBox(model);
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "What score to lock?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        return comboBox.getSelectedItem().toString();
    }


    YahtzeeWindow() {
        setTitle("Yahtzee");

        rollButton.setBounds(275, 100, 100, 40);
        add(rollButton);

        setSize(900, 550);
        setLayout(null);
        setVisible(true);

        updateTable(new int[5]);
        updateLockedTable(new int[5]);
        updateRerolls();
        updateRounds(13);
    }
}
