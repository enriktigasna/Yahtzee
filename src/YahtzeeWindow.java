import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class YahtzeeWindow extends JFrame {

    // ADD TOGGLE TO BUTTONS, AND INTEGER ARRAY OF TOGGLED BUTTONS
    // FOR EACH BUTTON ADD AN EVENT LISTENER THAT TRIGGERS A FUNCTION THAT TAKES INDEX OF BUTTON AS ARGUMENT
    public JButton[] buttons = new JButton[5];
    public JButton rollButton = new JButton("Roll");
    JTable table = new JTable();

    public int[] currentDice;

    public int[] skippedDice = new int[] {0, 0, 0, 0, 0};


    private void toggleSkip(int index) {
        if(skippedDice[index] == 0) {
            skippedDice[index] = 1;
            buttons[index].setBackground(Color.RED);
        } else {
            skippedDice[index] = 0;
            buttons[index].setBackground(Color.WHITE);
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
                {"UPPER TOTAL", new String(String.valueOf(0))},
                {"THREE OF A KIND", new String(String.valueOf(0))},
                {"FOUR OF A KIND", new String(String.valueOf(0))},
                {"YAHTZEE", new String(String.valueOf(0))},
                {"LARGE STRAIGHT", new String(String.valueOf(0))},
                {"SMALL STRAIGHT", new String(String.valueOf(0))},
                {"LOWER TOTAL", new String(String.valueOf(0))},
        };
        for(int i = 0; i < values.length; i++) {
            data[i][1] = values[i];
        }

        table = new JTable(data, columnNames);
        table.setBounds(600, 50, 250, 225);
        add(table);
    }


    YahtzeeWindow() {
        setTitle("Yahtzee");

        rollButton.setBounds(275, 100, 100, 40);
        add(rollButton);

        setSize(900, 500);
        setLayout(null);
        setVisible(true);
        updateTable(new int[] {1, 2, 3, 4, 5, 6});
    }
}
