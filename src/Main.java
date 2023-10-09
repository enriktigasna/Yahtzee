import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static YahtzeeArray yahtzeeArray = new YahtzeeArray();
    static YahtzeeWindow yWin = new YahtzeeWindow();
    static final boolean DEBUG = false;

    // Parses a string of numbers like "1 4 6 7 9" into an integer array
    public static int[] parseNumbers(String unparsed) {
        if(Objects.equals(unparsed, "")){
            return new int[] {};
        }
        String[] parsedNumbersToSkip = unparsed.split(" ");
        int size = parsedNumbersToSkip.length;

        int [] arr = new int [size];
        for(int i=0; i<size; i++) {
            arr[i] = Integer.parseInt(parsedNumbersToSkip[i]) - 1;
        }

        return arr;

    }

    public static int[] runRound() {
        Scanner scan = new Scanner(System.in);

        // Doesn't subtract rerolls if it's initial roll
        System.out.println(yWin.currentDice[0]);
        if(yWin.currentDice[0] != 0) {
            yWin.rerollsLeft--;
            yWin.updateRerolls();
        }

        // If out of rerolls reset
        if(yWin.rerollsLeft < 0) {
            yWin.rerollsLeft = 2;
            yWin.updateRerolls();

            yahtzeeArray.generateYahtzeeArray();
            yWin.updateButtons(yahtzeeArray.array);

            YahtzeeCounter yahtzeeCounter = new YahtzeeCounter();
            yahtzeeCounter.calculateValues(yahtzeeArray.array);

            yWin.updateTable(yahtzeeCounter.deserialize());
            yWin.resetButtons();
        }

        // Roll first set of dice and print it
        if(yWin.rerollsLeft >= 0) {
            yahtzeeArray.generateYahtzeeArray(yWin.skippedDice);
            yWin.updateButtons(yahtzeeArray.array);

            YahtzeeCounter yahtzeeCounter = new YahtzeeCounter();
            yahtzeeCounter.calculateValues(yahtzeeArray.array);

            yWin.updateTable(yahtzeeCounter.deserialize());
        }

        yWin.skippedDice = new int[] {0, 0, 0, 0, 0}; // Reset skipped
        return yahtzeeArray.array;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Yahtzee");

        Scanner scan = new Scanner(System.in);

        int[] roundResult;

        // If debug take first round result from input
        // Else run a round using runRound() command and roll dice, set aside etc.

        yWin.rollButton.addActionListener(e -> runRound());




    }
}