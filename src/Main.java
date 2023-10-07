import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static final boolean DEBUG = true;

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

        // Creates YahtzeeArray object
        YahtzeeArray yahtzeeArray = new YahtzeeArray();

        // Roll first set of dice and print it
        yahtzeeArray.generateYahtzeeArray();
        yahtzeeArray.printDice();

        final int REROLLS = 2;

        for(int i = 0; i < REROLLS; i++) {
            System.out.print("Do you want to set aside any numbers? blank to reroll entire dice. ");
            int[] toSkip = parseNumbers(scan.nextLine());
            yahtzeeArray.generateYahtzeeArray(toSkip);

            yahtzeeArray.printDice();
        }
        return yahtzeeArray.array;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Yahtzee");

        Scanner scan = new Scanner(System.in);

        int[] roundResult;

        // If debug take first round result from input
        // Else run a round using runRound() command and roll dice, set aside etc.
        if(!DEBUG) {
            roundResult = runRound();
        } else {

            // Debug Mode
            System.out.println("Enter Yahtzee numbers to evaluate");
            roundResult = parseNumbers(scan.nextLine());

            // parseNumbers subtracts one from each integer in the array, to make indexes work, have to add one to each for the debug.
            // A bit hacky will have to refactor this later
            for(int i = 0; i < roundResult.length; i++) {
                roundResult[i]++;
            }
        }


        System.out.println("\n\nFirst round result");
        System.out.println(Arrays.toString(roundResult));

        YahtzeeCounter counter = new YahtzeeCounter();
        counter.calculateValues(roundResult);
        counter.printValues();

    }
}