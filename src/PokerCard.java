/**
* Created with IntelliJ IDEA.
* User: stpyang
* Date: 2/6/14
* Time: 1:53 PM
* To change this template use File | Settings | File Templates.
*/
class PokerCard implements Comparable<PokerCard>{

    enum Suit {
        CLUB(3), DIAMOND(2), HEART(1), SPADE(0);

        final int rank;

        Suit(int rank) {
            this.rank = rank;
        }
    }

    private final Suit suit;
    private final int rank;
    private final String inputString;

    PokerCard(String inputString) {
        this.inputString = inputString;
        char c;

        c = inputString.charAt(0);
        if (Character.isDigit(c)) {
            this.rank = Integer.parseInt("" + c);
        } else if (c == 'T') {
            this.rank = 10;
        } else if (c == 'J') {
            this.rank = 11;
        } else if (c == 'Q') {
            this.rank = 12;
        } else if (c == 'K') {
            this.rank = 13;
        } else if (c == 'A') {
            this.rank = 14;
        } else {
            throw new IllegalArgumentException("Cannot determine card rank: " + inputString);
        }

        c = inputString.charAt(1);
        if (c == 'C') {
            this.suit = Suit.CLUB;
        } else if (c == 'D') {
            this.suit = Suit.DIAMOND;
        } else if (c == 'H') {
            this.suit = Suit.HEART;
        } else if (c == 'S') {
            this.suit = Suit.SPADE;
        } else {
            throw new IllegalArgumentException("Cannot determine card suit: " + inputString);
        }
    }

    public int getValue() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return inputString;
    }

    @Override
    public int compareTo(PokerCard o) {
        if (rank != o.rank) {
            return Integer.compare(rank, o.rank);
        } else {
            return suit.compareTo(o.suit);
        }
    }
}
