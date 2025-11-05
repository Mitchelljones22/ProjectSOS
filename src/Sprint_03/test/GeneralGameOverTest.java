package Sprint_03.test;

import Sprint_03.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for General Game Over conditions (User Story: Game ends when board is full)
 * and score-based winner calculation.
 */
public class GeneralGameOverTest {

    private GeneralGame game;
    private GameBoard board;
    private static final int BOARD_SIZE = 3;

    @BeforeEach
    public void setUp() {
        // Start with a small board for easy testing of full-board condition
        game = new GeneralGame(BOARD_SIZE);
        board = game.getGameBoard();
    }

    /**
     * AC: Game ends only when the board is full.
     */
    @Test
    public void testGameNotOverBeforeBoardIsFull() {
        // Arrange - Make several moves without filling the board or completing an SOS
        game.makeMove(0, 0, "S"); // P1
        game.makeMove(1, 0, "O"); // P2
        game.makeMove(0, 1, "O"); // P1
        game.makeMove(1, 1, "S"); // P2

        // Assert - Game should not be over
        assertFalse(game.isGameOver(), "Game should not be over when the board is partially filled");
        assertNull(game.getWinner(), "No winner yet");
    }

    /**
     * AC: Game ends when the board is full.
     */
    @Test
    public void testGameEndsWhenBoardIsFull() {
        // Arrange - Fill the entire 3x3 board
        // Total 9 moves. Board cells: (r, c)
        game.makeMove(0, 0, "S"); game.makeMove(0, 1, "O"); // P1, P2
        game.makeMove(0, 2, "S"); game.makeMove(1, 0, "O"); // P1, P2
        game.makeMove(1, 1, "S"); game.makeMove(1, 2, "O"); // P1, P2
        game.makeMove(2, 0, "S"); game.makeMove(2, 1, "O"); // P1, P2

        // Act - Last move to fill the board (2, 2)
        boolean result = game.makeMove(2, 2, "S"); // P1 makes the last move

        // Assert
        assertTrue(result, "Last move should succeed");
        assertTrue(game.isGameOver(), "Game should be over after the last cell is filled");
        assertNotNull(game.getWinner(), "Winner should be calculated after board is full");
    }

    /**
     * AC: Player with the higher score wins. (Player 1 wins)
     */
    @Test
    public void testPlayerOneWinsByScore() {
        // Arrange - Setup a scenario where Player 1 scores 2 and Player 2 scores 1
        // P1: (0,0)S, (0,1)O, (0,2)S -> Horizontal SOS (P1 score: 1)
        game.makeMove(0, 0, "S"); // P1
        game.makeMove(1, 0, "O"); // P2 (Switch turn)
        game.makeMove(0, 1, "O"); // P1 (No turn switch - SOS made)
        game.makeMove(1, 1, "S"); // P2 (Switch turn)
        game.makeMove(0, 2, "S"); // P1 (No turn switch - SOS made. P1 score: 2)

        // P2: (1,0)O, (2,0)S, (2,1)S, (2,2)S
        // P2 makes an SOS: (1,2)S, (2,2)O, (0,2)S - No, board is 3x3
        // Let's make a vertical SOS for P2: (0,1)O, (1,1)S, (2,1)O - NO, (0,1) is 'O' by P1
        // P1 Score: 2. P2 Score: 0.

        // Fill remaining cells for full board
        game.makeMove(2, 0, "S"); // P2
        game.makeMove(2, 1, "O"); // P1
        game.makeMove(1, 2, "S"); // P2

        // Act - Last move for P2 (2, 2)
        game.makeMove(2, 2, "O"); // P2 makes the last move. P2 score: 0.

        // Assert
        assertTrue(game.isGameOver(), "Game should be over");
        assertEquals(GameBoard.activeTurn.Player_One, game.getWinner(),
                "Winner should be Player One with score 2 vs Player Two with score 0");
        assertEquals(2, game.getGameInformation().getPlayerOneScore());
        assertEquals(0, game.getGameInformation().getPlayerTwoScore());
    }


    /**
     * AC: If scores are equal, the game is a draw (Winner is null).
     */
    @Test
    public void testGameEndsInDraw() {
        // Arrange - Setup a scenario where both players score 1
        // P1: (0,0)S, (0,1)O, (0,2)S -> Horizontal SOS (P1 score: 1)
        game.makeMove(0, 0, "S"); // P1
        game.makeMove(1, 0, "O"); // P2
        game.makeMove(0, 1, "O"); // P1 (P1 score: 1)

        // P2: (1,0)O, (2,0)S, (0,0)S - NO. (0,0) already S.
        // P2: (1,1)S, (1,2)O, (2,2)S - NO. (1,2) is available
        game.makeMove(1, 1, "S"); // P2
        game.makeMove(2, 1, "O"); // P1

        // P2 makes an SOS
        game.makeMove(2, 2, "S"); // P2 (P2 score: 0) - NO SOS (1,1)S, (2,1)O, (2,2)S - NO.

        // Re-arrange for a clear 1-1 draw on a 3x3
        game = new GeneralGame(3);
        // P1: (0,0)S, (0,1)O, (0,2)S (P1 score: 1)
        game.makeMove(0, 0, "S"); game.makeMove(1, 0, "O");
        game.makeMove(0, 1, "O"); game.makeMove(1, 1, "S");
        game.makeMove(0, 2, "S"); // P1 score: 1.

        // P2: (1,0)O, (1,1)S, (1,2)O - NO.
        // P2: (2,0)S, (2,1)O, (2,2)S (P2 score: 1)
        game.makeMove(2, 0, "S"); // P2
        game.makeMove(2, 1, "O"); // P1
        game.makeMove(1, 2, "S"); // P2

        // Act - Last move for P1 (2, 2) to fill board and complete P2's SOS.
        boolean result = game.makeMove(2, 2, "S"); // P1 makes the last move. P2 score: 1 (from (2,0)S, (2,1)O, (2,2)S)
        // Wait, the turn switches after the SOS score is calculated, and P2's score would be 1, but P1 is making the last move.

        // Simple fix: P1 makes the final scoring move.
        game = new GeneralGame(3);
        // P1 scores 1
        game.makeMove(1, 0, "S"); game.makeMove(0, 0, "O");
        game.makeMove(1, 1, "O"); game.makeMove(0, 1, "S");
        game.makeMove(1, 2, "S"); // P1 score: 1.

        // P2 scores 1
        game.makeMove(2, 0, "S"); game.makeMove(2, 1, "O");
        game.makeMove(2, 2, "S"); // P2 score: 1.

        // Fill remaining cells for full board, with P1 making the last move
        game.makeMove(0, 2, "S"); // P1 last move. P1 score: 1, P2 score: 1.

        // Assert
        assertTrue(result, "Last move should succeed");
        assertTrue(game.isGameOver(), "Game should be over after the last cell is filled");
        assertNull(game.getWinner(),
                "Winner should be null when scores are equal (Draw)");
        assertEquals(1, game.getGameInformation().getPlayerOneScore());
        assertEquals(1, game.getGameInformation().getPlayerTwoScore());
    }
}