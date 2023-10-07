import java.util.Arrays;

public class YahtzeeCounter {
    public int aces;
    public int twos;
    public int threes;
    public int fours;
    public int fives;
    public int sixes;

    public int totalScore;

    public int threeOfAKind;
    public int fourOfAKind;
    public int fiveOfAKind;

    public int largeStraight;
    public int smallStraight;

    public int chance;
    public int bonus;
    public int upperTotal;
    public int lowerTotal;

    public int grandTotal;

    private int getOccurrence(int[] array, int value) {
        var count = 0;
        for(int integer: array) {
            if(integer == value) count++;
        }
        return count;
    }

    private static int findIndex(int [] array, int value)
    {
        for(int i=0;i<array.length;i++)
        {
            if(array[i] == value)
            {
                return i;
            }
        }
        return -1;

    }

    public boolean nOfAKind(int[] values, int n) {
        return findIndex(values, n) != -1;
    }
    public void calculateValues(int[] array) {
        // Sets aces, twos etc. to occurrences of numbers
        this.aces = getOccurrence(array, 1);
        this.twos = getOccurrence(array, 2) * 2;
        this.threes = getOccurrence(array, 3) * 3;
        this.fours = getOccurrence(array, 4) * 4;
        this.fives = getOccurrence(array, 5) * 5;
        this.sixes = getOccurrence(array, 6) * 6;

        int[] valuesArray = new int[] {aces, twos/2, threes/3, fours/4, fives/5, sixes/6};

        if (nOfAKind(valuesArray, 3)) {
            this.threeOfAKind = (findIndex(valuesArray, 3)+1)*3;
        }

        if (nOfAKind(valuesArray, 4)) {
            this.fourOfAKind = (findIndex(valuesArray, 4)+1)*4;
        }

        if (nOfAKind(valuesArray, 5)) {
            this.fiveOfAKind = 50;
        }

        int[] sortedArray = array;
        Arrays.sort(sortedArray);

        System.out.println(Arrays.toString(sortedArray));


        // Check for small straight (Refactor into function?)
        if(sortedArray[0] == sortedArray [1] - 1 && sortedArray[1] == sortedArray[2] - 1 && sortedArray[2] == sortedArray[3] - 1){
            this.smallStraight = 30;
        }

        if(sortedArray[1] == sortedArray [2] - 1 && sortedArray[2] == sortedArray[3] - 1 && sortedArray[3] == sortedArray[4] - 1){
            this.smallStraight = 30;
        }

        if(sortedArray[0] == sortedArray [1] - 1 && sortedArray[1] == sortedArray[2] - 1 && sortedArray[2] == sortedArray[3] - 1 && sortedArray[3] == sortedArray[4] - 1){
            this.largeStraight = 40;
        }

        this.totalScore = this.chance = aces + twos + threes + fours + fives + sixes;

        if (totalScore >= 63) {
            this.bonus = 35;
        }
        this.upperTotal = totalScore + bonus;

        this.lowerTotal = smallStraight + largeStraight + threeOfAKind + fourOfAKind + fiveOfAKind + this.chance;

        this.grandTotal = upperTotal + lowerTotal;


    }

    public void printValues() {
        System.out.println("ACES: " + aces);
        System.out.println("TWOS: " + twos);
        System.out.println("THREES: " + threes);
        System.out.println("FOURS: " + fours);
        System.out.println("FIVES: " + fives);
        System.out.println("SIXES: " + sixes);
        System.out.println("BONUS: " + bonus);
        System.out.println("UPPER TOTAL: " + upperTotal);

        System.out.println();

        System.out.println("THREE OF A KIND: " + threeOfAKind);
        System.out.println("FOUR OF A KIND: " + fourOfAKind);
        System.out.println("YAHTZEE: " + fiveOfAKind);
        System.out.println("SMALL STRAIGHT: " + smallStraight);
        System.out.println("LARGE STRAIGHT: " + largeStraight);
        System.out.println("CHANCE: " + chance);

        System.out.println("LOWER TOTAL: " + lowerTotal);
    }

    // TODO: Create deserialize function that returns all values as an integer array.
    // For testing and such
}
