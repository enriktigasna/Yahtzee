import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    // Returns a random array with 5 numbers
    public static int[] generateYahtzyArray() {
        Random rd = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(6) + 1;
        }
        return arr;
    }

    public static int[] generateYahtzyArray(int[] originalArray, int[] skipNumbers) {
        Random rd = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {

            // Check if i is in numbers to skip, puts original number in it
            int currentIteration = i;
            if(IntStream.of(skipNumbers).anyMatch(n -> n == currentIteration)){
                arr[i] = originalArray[i];
                continue;
            }
            arr[i] = rd.nextInt(6) + 1;
        }
        return arr;
    }

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
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Yahtzee");

        // Wait for enter
        System.in.read();

        System.out.println("Your dice:");
        int[] originalYahtzyArray = generateYahtzyArray();
        System.out.println(Arrays.toString(originalYahtzyArray));

        System.out.print("Do you want to skip any numbers? ");
        String unparsedNumbersToSkip = scan.nextLine();


        int[] toSkip = parseNumbersToSkip(unparsedNumbersToSkip);
        int[] skippedYahtzyArray = generateYahtzyArray(originalYahtzyArray, toSkip);

        System.out.println(Arrays.toString(skippedYahtzyArray));
    }
}