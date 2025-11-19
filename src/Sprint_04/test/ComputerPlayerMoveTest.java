package Sprint_04.test;

import Sprint_04.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ComputerPlayerMoveTest {
    private GameController gameController;
    private GameBoard gameBoard;
    private GameInformation gameInformation;


    @BeforeEach
    public void setup() {
        gameController = new GameController();
        gameBoard = gameController.getBoard();
        gameInformation = gameController.getGameInformation();
    }

    @Test
    public void testComputerOnlySelectsEmptyTiles() {

        gameController.startNewGame(GameBoard.gameMode.Simple, 3);

        ComputerPlayer computerPlayer = new ComputerPlayer(gameBoard, gameInformation);
        gameController.getCurrentGame().setPlayerOne(computerPlayer);

        gameBoard.getCell(0, 0).setLetter("S");
        gameBoard.getCell(0, 1).setLetter("S");
        gameBoard.getCell(0, 2).setLetter("S");
        gameBoard.getCell(1, 0).setLetter("S");
        gameBoard.getCell(1, 1).setLetter("S");
        gameBoard.getCell(1, 2).setLetter("S");
        gameBoard.getCell(2, 0).setLetter("S");
        gameBoard.getCell(2, 1).setLetter("S");
        // (2,2) is the only empty tile left empty

        BoardTile selectedTile = computerPlayer.makeMove(gameBoard, gameInformation);

        assertNotNull(selectedTile, "Computer should select a tile");
        assertTrue(selectedTile.isEmpty(), "Selected tile should be empty before placing letter");
    }

    @Test
    public void testComputerPlacesValidLetter() {
        ComputerPlayer computerPlayer = new ComputerPlayer(gameBoard, gameInformation);
        gameController.getCurrentGame().setPlayerOne(computerPlayer);
        gameController.startNewGame(GameBoard.gameMode.Simple, 4);
        gameBoard = gameController.getBoard();
        gameInformation = gameController.getGameInformation();

        BoardTile selectedTile = computerPlayer.makeMove(gameBoard, gameInformation);
        assertNotNull(selectedTile, "Computer should select a tile");

        // random letter as done in MainUI
        Random random = new Random();
        String randomLetter;
        if(random.nextBoolean()) {
            randomLetter = "S";
        } else {
            randomLetter = "O";
        }

        boolean moveSuccessful = gameController.handleMove(
                selectedTile.getXPos(),
                selectedTile.getYPos(),
                randomLetter
        );

        assertTrue(moveSuccessful, "Computer's move should be successful");
        assertFalse(selectedTile.isEmpty(), "Tile should no longer be empty");
        assertTrue(selectedTile.getLetter().equals("S") || selectedTile.getLetter().equals("O"),
                "Placed letter should be either 'S' or 'O', but was: " + selectedTile.getLetter());
    }

    @Test
    public void testComputerPlaysCompleteSimpleGame() {
        gameController.startNewGame(GameBoard.gameMode.Simple, 3);
        gameBoard = gameController.getBoard();
        gameInformation = gameController.getGameInformation();

        ComputerPlayer player1 = new ComputerPlayer(gameBoard, gameInformation);
        ComputerPlayer player2 = new ComputerPlayer(gameBoard, gameInformation);
        gameController.getCurrentGame().setPlayerOne(player1);
        gameController.getCurrentGame().setPlayerTwo(player2);

        int maxMoves = 9; // 3x3 board
        int moveCount = 0;

        while (!gameInformation.isGameOver() && moveCount < maxMoves) {
            ComputerPlayer currentPlayer = (ComputerPlayer) gameInformation.getCurrentPlayer();
            BoardTile tile = currentPlayer.makeMove(gameBoard, gameInformation);

            if (tile != null) {
                // Randomly place S or O
                String letter;
                if (moveCount % 2 == 0) {
                    letter = "S";
                } else {
                    letter = "O";
                }
                gameController.handleMove(tile.getXPos(), tile.getYPos(), letter);
                moveCount++;
            } else {
                break; // No more moves available
            }
        }

        assertTrue(gameInformation.isGameOver() || GameLogic.isBoardFull(gameBoard),
                "Game should end either by winner or full board");
    }


    @Test
    public void testComputerPlaysCompleteGeneralGame() {
        gameController.startNewGame(GameBoard.gameMode.General, 3);
        gameBoard = gameController.getBoard();
        gameInformation = gameController.getGameInformation();

        ComputerPlayer player1 = new ComputerPlayer(gameBoard, gameInformation);
        ComputerPlayer player2 = new ComputerPlayer(gameBoard, gameInformation);
        gameController.getCurrentGame().setPlayerOne(player1);
        gameController.getCurrentGame().setPlayerTwo(player2);

        int maxMoves = 9; // 3x3 board
        int moveCount = 0;

        while (!gameInformation.isGameOver() && moveCount < maxMoves) {
            ComputerPlayer currentPlayer = (ComputerPlayer) gameInformation.getCurrentPlayer();
            BoardTile tile = currentPlayer.makeMove(gameBoard, gameInformation);

            if (tile != null) {
                // Randomly place S or O
                String letter;
                if (moveCount % 2 == 0) {
                    letter = "S";
                } else {
                    letter = "O";
                }
                gameController.handleMove(tile.getXPos(), tile.getYPos(), letter);
                moveCount++;
            } else {
                break; // No more moves available
            }
        }

        assertTrue(gameInformation.isGameOver(), "General game should end when board is full");
        assertTrue(GameLogic.isBoardFull(gameBoard), "Board should be full");
    }


}
