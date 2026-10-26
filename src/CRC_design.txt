Class Card
    - Property: Card number and suit as Strings
        - Number: A, 2-10, J, Q, K
        - Suit: H, D, C, S

    - Methods:
        get number and suit
        display card as a String (e.g. KH, 3D)


Class Deck
    - Has 52 unique cards
    - Draw 1 card each time

    - Methods:
        drawCard
            - remove 1 card from the deck
            - return the drawn card

        shuffle
            - shuffle all cards in the deck


Class Discard
    - Can store up to 4 cards
    - Positions: 17-20
    - Each position can only store 1 card
    - Discarded cards do not count toward the score

    - Methods:
        discardCard
            - check if the position is valid (17-20)
            - check if the position is already filled
            - put the card in the selected position
            - raise errors if invalid or occupied

        getRemainingDiscards
            - return the number of empty discard positions


Class BlackjackSolitaire (it is the playboard and game controller)
    - Has a Deck and Discard
    - Can store 16 cards on the playboard
    - Positions: 1-16
    - Each position can only store 1 card
    - Keep track of the currently drawn card
    - End the game when all 16 playboard positions are filled
        - Discard positions do not have to be filled
    - Calculate the score when the game ends

    - Methods:
        initiate
            - create the deck and discard
            - create the empty playboard
            - shuffle the deck

        show the state of game
            - show the current playboard with cards and position numbers
            - empty positions show position numbers
            - filled positions show cards instead of numbers
            - show the number of remaining discards
            - show the currently drawn card

        placeCard
            - input: position number (1-20)
                - 1-16: Playboard
                - 17-20: Discard

            - if Playboard:
                - check if position is valid and empty
                - cover the position number and put the card
                - update the playboard

            - if Discard:
                - check if position is valid and empty
                - put the card in Discard
                - update the remaining discards

            - raise errors if:
                - position is already filled
                - position is out of range
                - input is invalid
            - ask for input again if there is an error
            - only draw the next card after successful placement

        calculateScore
            - calculate the score of 4 rows and 5 columns
            - each row and column is a separate hand
            - total of 9 hands
            - ignore discarded cards

            - Card values:
                - 2-10: face value
                - J, Q, K: 10
                - A: 1 or 11
                    - choose the highest value without exceeding 21
                    - handle multiple Aces

            - Hand scores:
                - Blackjack (2 cards totaling 21): 10
                - 21 (3-5 cards): 7
                - 20: 5
                - 19: 4
                - 18: 3
                - 17: 2
                - 16 or less: 1
                - 22 or more: 0

            - add scores from all 9 hands
            - return total score

        isGameOver
            - check if all 16 playboard positions are filled

        play
            - initiate the game
            - draw the first card
            - show the state of game
            - prompt where the user would put the card
            - validate input and place the card
            - repeat until the game is over
            - calculate the final score
            - display "Game over!" and the total score


Class BlackjackSolitaireRunner
    - Contains main method

    - Methods:
        main
            - create a BlackjackSolitaire object
            - call play()