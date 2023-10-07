import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class YahtzeeArray {
    public int[] array = new int[5];

    // Generates random 5 dice rolls, and puts them into the this.array property
    public int[] generateYahtzeeArray() {
        Random rd = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(6) + 1;
        }
        array = arr;
        return arr;
    }

    /*
        If an integer array is provided, it skips changing the array on the skipped numbers
        Example [0, 0, 0, 0, 0] array becomes [2, 0, 5, 0, 2] given [2, 4] as an input

        Note: 0 is an impossible number in the this.array, since you can't roll 0 on dice
     */
    public int[] generateYahtzeeArray(int[] skipNumbers) {
        Random rd = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {

            // Check if I is in numbers to skip, puts original number in it
            if(skipNumbers[i] == 1){
                arr[i] = array[i];
                continue;
            }
            arr[i] = rd.nextInt(6) + 1;
        }
        this.array = arr;
        return arr;
    }

    public void printDice() {
        System.out.println("Your current dice:");
        System.out.println(Arrays.toString(this.array));
    }

}
