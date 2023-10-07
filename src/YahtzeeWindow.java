import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class YahtzeeWindow extends JFrame {

    // ADD TOGGLE TO BUTTONS, AND INTEGER ARRAY OF TOGGLED BUTTONS
    // FOR EACH BUTTON ADD AN EVENT LISTENER THAT TRIGGERS A FUNCTION THAT TAKES INDEX OF BUTTON AS ARGUMENT
    public JButton[] buttons = new JButton[5];
    public JButton rollButton = new JButton("Roll");

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


    YahtzeeWindow() {
        setTitle("Yahtzee");

        rollButton.setBounds(275, 100, 100, 40);
        add(rollButton);

        setSize(700, 500);
        setLayout(null);
        setVisible(true);
    }
}
