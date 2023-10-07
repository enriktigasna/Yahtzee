import javax.swing.*;

public class YahtzeeWindow extends JFrame {

    // ADD TOGGLE TO BUTTONS, AND INTEGER ARRAY OF TOGGLED BUTTONS
    // FOR EACH BUTTON ADD AN EVENT LISTENER THAT TRIGGERS A FUNCTION THAT TAKES INDEX OF BUTTON AS ARGUMENT
    public JButton[] buttons = new JButton[5];
    public JButton rollButton = new JButton("Roll");


    public void updateButtons(int[] dice){
        for(int i = 0; i < dice.length; i++) {
            if(this.buttons[i] != null){
                remove(this.buttons[i]);
            }

            this.buttons[i] = new JButton(String.valueOf(dice[i]));
            this.buttons[i].setBounds((i+1)*100, 200, 50, 40);
            add(this.buttons[i]);
            buttons[i].updateUI();
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
