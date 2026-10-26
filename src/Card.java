public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        if (rank.equals("A")) {
            return 1;
        }
        if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
            return 10;
        }
        return Integer.parseInt(rank);
    }

    public String toString() {
        return rank + suit;
    }
}