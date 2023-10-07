import java.util.Arrays;

public class YatzeeCounter {
    public int aces;
    public int twos;
    public int threes;
    public int fours;
    public int fives;
    public int sixes;

    public int threeOfAKind;
    public int fourOfAKind;
    public int fiveOfAKind;

    public int largeStraight;
    public int smallStraight;

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
    public void calculateValues(int[] array) {
        // Sets aces, twos etc. to occurences of numbers
        this.aces = getOccurrence(array, 1);
        this.twos = getOccurrence(array, 2) * 2;
        this.threes = getOccurrence(array, 3) * 3;
        this.fours = getOccurrence(array, 4) * 4;
        this.fives = getOccurrence(array, 5) * 5;
        this.sixes = getOccurrence(array, 6) * 6;

        int[] valuesArray = new int[] {aces, twos/2, threes/3, fours/4, fives/5, sixes/6};

        if (findIndex(valuesArray, 3) != -1) {
            this.threeOfAKind = (findIndex(valuesArray, 3)+1)*3;
        }

        if (findIndex(valuesArray, 4) != -1) {
            this.fourOfAKind = (findIndex(valuesArray, 4)+1)*4;
        }

        if (findIndex(valuesArray, 5) != -1) {
            this.fiveOfAKind = 50;
        }

        Arrays.sort(valuesArray);

        if(valuesArray[0] == valuesArray [1] - 1 && valuesArray[1] == valuesArray[2] - 1 && valuesArray[2] == valuesArray[3] - 1){
            this.smallStraight = 40;
        }

        if(valuesArray[1] == valuesArray [2] - 1 && valuesArray[2] == valuesArray[3] - 1 && valuesArray[3] == valuesArray[4] - 1){
            this.smallStraight = 40;
        }


    }

    public void printValues() {
        System.out.println("ACES: " + aces);
        System.out.println("TWOS: " + twos);
        System.out.println("THREES: " + threes);
        System.out.println("FOURS: " + fours);
        System.out.println("FIVES: " + fives);
        System.out.println("SIXES: " + sixes);
        System.out.println("THREE OF A KIND: " + threeOfAKind);
        System.out.println("FOUR OF A KIND: " + fourOfAKind);
        System.out.println("YATZEE: " + fiveOfAKind);
        System.out.println("LARGE STRAIGHT: " + largeStraight);
        System.out.println("SMALL STRAIGHT: " + smallStraight);
    }
}
