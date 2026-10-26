public class Discard {
    private Card[] cards;

    public Discard() {
        cards = new Card[4];
    }

    public void discardCard(Card card, int position) {
        if (position < 17 || position > 20) {
            throw new IllegalArgumentException("Discard position must be 17-20.");
        }

        int index = position - 17;

        if (cards[index] != null) {
            throw new IllegalStateException("That discard position is already filled.");
        }

        cards[index] = card;
    }

    public int getRemainingDiscards() {
        int remaining = 0;

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                remaining++;
            }
        }

        return remaining;
    }
}