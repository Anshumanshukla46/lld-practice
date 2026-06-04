package q7_wip_snakeNladder;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;
    Deque<Player> playerList = new LinkedList<>(); // say p1 turn then take it, play and put at last
    Player winner;

    public Game() {
        initializeGame();
    }

    private void initializeGame() {
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;

        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playerList.add(player1);
        playerList.add(player2);
    }

    public void startGame() {
        while (winner == null) { // can change once got the winner

            // check whose turn now
            Player playerTurn = findPlayerTurn();
            System.out.println("Player turn of: " + playerTurn.id + " current position is: " + playerTurn.currentPosition);

            // roll the dice
            int diceNumber = dice.rollDice();

            // get the new position
            int playerNewPosition = playerTurn.currentPosition + diceNumber;
            playerNewPosition = jumpCheck(playerNewPosition);  // check if snake/ladder is their
            playerTurn.currentPosition = playerNewPosition;

            System.out.println("Player turn of: " + playerTurn.id + " done, new position is: " + playerNewPosition);

            // check for winning condition
            if (playerNewPosition >= board.cells.length * board.cells.length - 1) {
                winner = playerTurn;
            }
        }

        System.out.println("WINNER is: " + winner.id);
    }

    private int jumpCheck(int playerNewPosition) {
        if (playerNewPosition > board.cells.length * board.cells.length - 1) {
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if (cell.jump != null && cell.jump.start == playerNewPosition) {
            String jumpBy = (cell.jump.start < cell.jump.end) ? "Ladder" : "Snake";
            System.out.println("Jump due to: " + jumpBy);
            return cell.jump.end;
        }
        return playerNewPosition;
    }

    private Player findPlayerTurn() {
        Player playerTurns = playerList.removeFirst();
        playerList.addLast(playerTurns);
        return playerTurns;
    }
}
