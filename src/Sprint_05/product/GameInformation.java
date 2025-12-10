package Sprint_05.product;

import java.util.ArrayList;
import java.util.List;

public class GameInformation {
    private GameBoard.activeTurn currentTurn;
    private int playerOneScore;
    private int playerTwoScore;
    private boolean isGameOver;
    private GameBoard.activeTurn winner;
    private Player playerOne;
    private Player playerTwo;


    private List<SOSLine> sosLines; // Stores all SOS lines to be displayed by UI

    public GameInformation() {
        sosLines = new ArrayList<>();
        reset();
    }

    public void reset() {
        this.currentTurn = GameBoard.activeTurn.Player_One;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.isGameOver = false;
        this.winner = null;
    }

    public GameBoard.activeTurn getCurrentTurn() {
        return currentTurn;
    }

    public void switchTurn() {
        if (currentTurn == GameBoard.activeTurn.Player_One) {
            currentTurn = GameBoard.activeTurn.Player_Two;
        } else {
            currentTurn = GameBoard.activeTurn.Player_One;
        }
    }

    public void incrementScore(GameBoard.activeTurn player) {
        if (player == GameBoard.activeTurn.Player_One) {
            playerOneScore++;
        } else {
            playerTwoScore++;
        }
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getCurrentPlayer() {
        if (currentTurn == GameBoard.activeTurn.Player_One) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public GameBoard.activeTurn getWinner() {
        return winner;
    }

    public void setWinner(GameBoard.activeTurn winner) {
        this.winner = winner;
    }

    public void addSOSLines(List<SOSLine> sosLine) {
        sosLines.addAll(sosLine);
    }

    public List<SOSLine> getSOSLines() {
        return sosLines;
    }
}
