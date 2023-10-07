import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static int[] parseNumbersToSkip(String unparsed) {
        if(unparsed == ""){
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
            int[] toSkip = parseNumbersToSkip(scan.nextLine());
            yahtzeeArray.generateYahtzeeArray(toSkip);

            yahtzeeArray.printDice();
        }
        return yahtzeeArray.array;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Yahtzee");

        // Wait for enter
        System.in.read();

        int[] roundResult = runRound();
        roundResult = new int[]{1, 2, 3, 4, 6};

        System.out.println("\n\nFirst round result");
        System.out.println(Arrays.toString(roundResult));

        YatzeeCounter counter = new YatzeeCounter();
        counter.calculateValues(roundResult);
        counter.printValues();

    }
}