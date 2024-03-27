import java.util.Scanner;

public class Game {
    private Scanner sc = new Scanner(System.in); // Scanner to read the words using the keyboard
    private Player player1 = new Player("Player1");
    private Player player2 = new Player("Player2");
    private Player currentPlayer; // The current player's turn
    private String firstLetters = ""; // Letters for the next word
    private boolean initialLetterPhase = true; // Check to see if the game is in the first stage

    public void start() {
        currentPlayer = player2; // Player2 starts the game
        while (!player1.hasLost() && !player2.hasLost()) {
            if (initialLetterPhase) {
                promptForInitialLetter(); // The game starts with the first stage
            } else {
                promptForWord(); // The player has to input a word
            }
            switchPlayers(); // Swap the current player
        }
        // Show the winner
        System.out.println(getWinner().getName() + " has won the game!");
    }

    private void promptForInitialLetter() {
        // Starting player has to insert a letter in order for the game to start
        System.out.println(currentPlayer.getName() + ", insert a letter to start the game: ");
        firstLetters = sc.nextLine().toLowerCase();
        while (firstLetters.length() != 1 || !Character.isLetter(firstLetters.charAt(0))) {
            System.out.println("Insert a letter to start the game: ");
            firstLetters = sc.nextLine().toLowerCase();
        }
        initialLetterPhase = false; // End of first stage
    }

    private void promptForWord() {
        System.out.println(currentPlayer.getName() + ", insert a word starting with " + firstLetters + ": ");
        String word = sc.nextLine();
        if (word.isEmpty()) {
            currentPlayer.addLetterToLife();
            if (currentPlayer.getLife().length() < 4) {
                initialLetterPhase = true; // Go back to first stack
                firstLetters = ""; // Reset the first letters
            }
            return;
        }

        while (word.length() <= 4 || !word.startsWith(firstLetters)) {
            if (word.length() <= 4) {
                System.out.println("The word has to be longer than 4 characters. Try again: ");
            } else if (!word.startsWith(firstLetters)) {
                System.out.println("The word has to start with " + firstLetters + ". Try again: ");
            }
            word = sc.nextLine();
            if (word.isEmpty()) {
                currentPlayer.addLetterToLife();
                if (currentPlayer.getLife().length() < 4) {
                    initialLetterPhase = true; // Reset to first stage
                    firstLetters = ""; // Reset the first letters
                }
                return;
            }
        }
        firstLetters = word.substring(word.length() - 2); // Update the first 2 letters for the next player
    }

    private void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private Player getWinner() {
        if (player1.hasLost()) {
            return player2;
        }
        return player1;
    }
}
