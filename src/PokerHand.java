import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 2/6/14
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class PokerHand implements Comparable<PokerHand> {

    enum RANK {
        HIGH_CARD,
        PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH,
    }

    private PokerCard[] cards;
    private RANK rank;
    private int value;

    public PokerHand(String[] inputStrings) {
        if (inputStrings.length != 5) {
            throw new IllegalArgumentException("Poker hand must have five cards!");
        }

        this.cards = new PokerCard[5];
        for (int i = 0; i < 5; ++i) {
            this.cards[i] = new PokerCard(inputStrings[i]);
        }

        Arrays.sort(cards);

        if (isRoyalFlush()) {
            this.rank = RANK.ROYAL_FLUSH;
        } else if (isStraightFlush()) {
            this.rank = RANK.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            this.rank = RANK.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            this.rank = RANK.FULL_HOUSE;
        } else if (isFlush()) {
            this.rank = RANK.FLUSH;
        } else if (isStraight()) {
            this.rank = RANK.STRAIGHT;
        } else if (isThreeOfAKind()) {
            this.rank = RANK.THREE_OF_A_KIND;
        } else if (isTwoPairs()) {
            this.rank = RANK.TWO_PAIRS;
        } else if (isPair()) {
            this.rank = RANK.PAIR;
        } else {
            this.rank = RANK.HIGH_CARD;
            this.value = getHighestValue();
        }
    }

    int getHighestValue() {
        return cards[4].getValue();
    }

    int[] getValueCount() {
        int[] result = new int[15];
        for (PokerCard card : cards) {
            ++result[card.getValue()];
        }
        return result;
    }

    boolean isRoyalFlush() {
        if (!isStraightFlush()) {
            return false;
        }
        if (getHighestValue() == 14) {
            this.value = 14;
            return true;
        }
        return false;
    }

    boolean isStraightFlush() {
        if (isFlush() && isStraight()) {
            this.value = getHighestValue();
            return true;
        }

        return false;
    }

    boolean isFourOfAKind() {
        int[] values = getValueCount();

        for (int i = 0; i < 15; ++i) {
            if (values[i] == 4) {
                this.value = i;
                return true;
            }
        }
        return false;
    }

    boolean isFullHouse() {
        int[] values = getValueCount();

        boolean hasTwo = false;
        boolean hasThree = false;
        int valueOfThree = 0;
        for (int i = 0; i < 15; ++i) {
            if (values[i] == 2) {
                hasTwo = true;
            } else if (values[i] == 3) {
                hasThree = true;
                valueOfThree = i;
            }
        }

        if (hasTwo && hasThree) {
            this.value = valueOfThree;
            return true;
        }

        return false;
    }

    boolean isFlush() {
        PokerCard.Suit s = cards[0].getSuit();
        for (int i = 1; i < 5; ++i) {
            if (s != cards[i].getSuit()) {
                return false;
            }
        }
        this.value = getHighestValue();
        return true;
    }

    boolean isStraight() {
        int[] values = getValueCount();

        // i is the index of the lowest value
        int i = 0;
        while (values[i] == 0) { ++i; }

        if (values[i + 1] == 1 && values[i + 2] == 1 && values[i + 3] == 1 && values[i + 4] == 1) {
            this.value = i + 4;
            return true;
        }

        return false;
    }

    boolean isThreeOfAKind() {
        int[] values = getValueCount();

        for(int i = 0; i < 15; ++i) {
            if (values[i] == 3) {
                this.value = i;
                return true;
            }
        }

        return false;
    }

    boolean isTwoPairs() {
        int[] values = getValueCount();

        int numberOfPairs = 0;
        for(int i = 0; i < 15; ++i) {
            if (values[i] == 2) {
                this.value = Math.max(this.value, i);
                ++numberOfPairs;
            }
        }

        return numberOfPairs == 2;
    }

    boolean isPair() {
        int[] values = getValueCount();

        for (int i = 0; i < 15; ++i) {
            if (values[i] == 2) {
                this.value = i;
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "cards=" + Arrays.toString(cards) +
                ", rank=" + rank +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(PokerHand o) {
        if (rank != o.rank) {
            return -rank.compareTo(o.rank);
        } else if (value != o.value) {
            return -Integer.compare(value, o.value);
        } else if (!Arrays.equals(cards, o.cards)) {
            int[] values = this.getValueCount();
            int[] o_values = o.getValueCount();
            for (int i = 14; i > 0; --i) {
                if (values[i] != o_values[i]) {
                    return -Integer.compare(values[i], o_values[i]);
                }
            }
        }
        throw new IllegalArgumentException("Don't know how to compare these two poker hands! " + this + " " + o);
    }
}
