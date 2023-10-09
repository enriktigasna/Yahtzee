import java.util.Arrays;

public class LockedYahtzeeCounter extends YahtzeeCounter {

    @Override public int[] deserialize() {
        return new int[] {
                aces,
                twos,
                threes,
                fours,
                fives,
                sixes,
                bonus,
                threeOfAKind,
                fourOfAKind,
                fiveOfAKind,
                largeStraight,
                smallStraight,
                upperTotal,
                lowerTotal,
                grandTotal
        };
    }

    public int[] serialize(int index, int value) {
        switch (index) {
            case 0:
                aces = value;
                break;
            case 1:
                twos = value;
                break;
            case 2:
                threes = value;
                break;
            case 3:
                fours = value;
                break;
            case 4:
                fives = value;
                break;
            case 5:
                sixes = value;
                break;
            case 6:
                bonus = value;
                break;
            case 7:
                threeOfAKind = value;
                break;
            case 8:
                fourOfAKind = value;
                break;
            case 9:
                fiveOfAKind = value;
                break;
            case 10:
                largeStraight = value;
                break;
            case 11:
                smallStraight = value;
                break;
            case 12:
                upperTotal = value;
            case 13:
                lowerTotal = value;
                break;
            case 14:
                grandTotal = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid index: " + index);
        }

        return deserialize();
    }

    @Override public void calculateValues(int[] array) {
        this.upperTotal = totalScore + bonus;

        this.lowerTotal = smallStraight + largeStraight + threeOfAKind + fourOfAKind + fiveOfAKind + this.chance;

        this.grandTotal = upperTotal + lowerTotal;
    }
}
