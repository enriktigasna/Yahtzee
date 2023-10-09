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
                upperTotal,
                threeOfAKind,
                fourOfAKind,
                fiveOfAKind,
                largeStraight,
                smallStraight,
                lowerTotal,
                grandTotal
        };
    }

    @Override public void calculateValues(int[] array) {
        this.upperTotal = totalScore + bonus;

        this.lowerTotal = smallStraight + largeStraight + threeOfAKind + fourOfAKind + fiveOfAKind + this.chance;

        this.grandTotal = upperTotal + lowerTotal;
    }
}
