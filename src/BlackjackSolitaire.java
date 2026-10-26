import java.util.Scanner;

public class BlackjackSolitaire {
    private Deck deck;
    private Discard discard;
    private Card[] board;
    private Card currentCard;
    private int filledPositions;

    public void initiate() {
        deck = new Deck();
        deck.shuffle();
        discard = new Discard();
        board = new Card[16];
        currentCard = null;
        filledPositions = 0;
    }

    private String showPosition(int position) {
        if (board[position - 1] == null) {
            return Integer.toString(position);
        }
        return board[position - 1].toString();
    }

    private void showRow(int start, int end, boolean indent) {
        if (indent) {
            System.out.print("\t");
        }

        for (int position = start; position <= end; position++) {
            System.out.print(showPosition(position));

            if (position < end) {
                System.out.print("\t");
            }
        }

        System.out.println();
    }

    public void showGameState() {
        showRow(1, 5, false);
        showRow(6, 10, false);
        showRow(11, 13, true);
        showRow(14, 16, true);

        System.out.println("Discards remaining: " + discard.getRemainingDiscards());

        if (currentCard != null) {
            System.out.println("Current card: " + currentCard);
        }
    }

    public void placeCard(Card card, int position) {
        if (position < 1 || position > 20) {
            throw new IllegalArgumentException("Position must be between 1 and 20.");
        }

        if (position >= 17) {
            discard.discardCard(card, position);
        } else {
            int index = position - 1;

            if (board[index] != null) {
                throw new IllegalStateException("That position is already filled.");
            }

            board[index] = card;
            filledPositions++;
        }
    }

    public boolean isGameOver() {
        return filledPositions == 16;
    }

    private int scoreHand(int[] positions) {
        int total = 0;
        boolean hasAce = false;

        for (int i = 0; i < positions.length; i++) {
            Card card = board[positions[i] - 1];
            total += card.getValue();

            if (card.getRank().equals("A")) {
                hasAce = true;
            }
        }

        // Count one Ace as 11 if it does not exceed 21.
        if (hasAce && total + 10 <= 21) {
            total += 10;
        }

        if (total == 21) {
            if (positions.length == 2) {
                return 10;
            }
            return 7;
        }

        if (total == 20) {
            return 5;
        }

        if (total == 19) {
            return 4;
        }

        if (total == 18) {
            return 3;
        }

        if (total == 17) {
            return 2;
        }

        if (total <= 16) {
            return 1;
        }

        return 0;
    }

    public int calculateScore() {
        if (!isGameOver()) {
            throw new IllegalStateException("All 16 playboard positions must be filled first.");
        }

        int[][] hands = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13},
                {14, 15, 16},
                {1, 6},
                {2, 7, 11, 14},
                {3, 8, 12, 15},
                {4, 9, 13, 16},
                {5, 10}
        };

        int totalScore = 0;

        for (int i = 0; i < hands.length; i++) {
            totalScore += scoreHand(hands[i]);
        }

        return totalScore;
    }

    public void play() {
        initiate();
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            currentCard = deck.drawCard();

            System.out.println();
            showGameState();

            boolean placed = false;

            while (!placed) {
                System.out.print("Choose a position (1-20, 17-20 to discard): ");
                String input = scanner.nextLine().trim();

                try {
                    int position = Integer.parseInt(input);

                    placeCard(currentCard, position);

                    placed = true;
                    currentCard = null;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a whole number from 1 to 20.");

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid move: " + e.getMessage());

                } catch (IllegalStateException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                }
            }
        }

        System.out.println();
        System.out.println("Final playboard:");
        showGameState();

        System.out.println("Game over! You scored " + calculateScore() + " points.");
    }
}