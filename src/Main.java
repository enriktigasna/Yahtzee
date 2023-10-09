import javax.swing.*;
import java.util.*;

public class Main {

    static YahtzeeArray yahtzeeArray = new YahtzeeArray();
    static YahtzeeCounter yahtzeeCounter = new YahtzeeCounter();
    static YahtzeeWindow yWin = new YahtzeeWindow();

    static int roundsLeft = 13;
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
        if(yWin.currentDice[0] != 0) {
            yWin.rerollsLeft--;
            yWin.updateRerolls();
        }

        // If out of rerolls reset
        if(yWin.rerollsLeft < 0) {
            // TODO: Create function that locks a value
            String toLock = yWin.createLockingPopup();
            lock(toLock);

            yWin.rerollsLeft = 2;
            yWin.updateRerolls();

            yahtzeeArray.generateYahtzeeArray();
            yWin.updateButtons(yahtzeeArray.array);

            yahtzeeCounter.calculateValues(yahtzeeArray.array);

            yWin.updateTable(yahtzeeCounter.deserialize());
            yWin.resetButtons();

            roundsLeft--;
            yWin.updateRounds(roundsLeft);
        }

        // Roll first set of dice and print it
        if(yWin.rerollsLeft >= 0) {
            yahtzeeArray.generateYahtzeeArray(yWin.skippedDice);
            yWin.updateButtons(yahtzeeArray.array);
            yahtzeeCounter.calculateValues(yahtzeeArray.array);

            yWin.updateTable(yahtzeeCounter.deserialize());
        }

        if(roundsLeft == 0){
            yWin.remove(yWin.rollButton);
            yWin.rollButton.setVisible(false);
        }
        yWin.skippedDice = new int[] {0, 0, 0, 0, 0}; // Reset skipped
        return yahtzeeArray.array;
    }

    private static void lock(String toLock) {
        Dictionary<String, Integer> lockingIndexTable = new Hashtable<>();

        lockingIndexTable.put("ACES", 0);
        lockingIndexTable.put("TWOS", 1);
        lockingIndexTable.put("THREES", 2);
        lockingIndexTable.put("FOURS", 3);
        lockingIndexTable.put("FIVES", 4);
        lockingIndexTable.put("SIXES", 5);
        lockingIndexTable.put("BONUS", 6);
        lockingIndexTable.put("THREE OF A KIND", 7);
        lockingIndexTable.put("FOUR OF A KIND", 8);
        lockingIndexTable.put("YAHTZEE", 9);
        lockingIndexTable.put("LARGE STRAIGHT", 10);
        lockingIndexTable.put("SMALL STRAIGHT", 11);
        lockingIndexTable.put("LOWER TOTAL", 12);
        lockingIndexTable.put("GRAND TOTAL", 13);

        System.out.println(toLock);


        yWin.lockedYCounter.serialize(lockingIndexTable.get(toLock), yahtzeeCounter.deserialize()[lockingIndexTable.get(toLock)]);
        yWin.updateLockedTable(yWin.lockedYCounter.deserialize());
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