package Sprint_03.test;

import Sprint_03.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for User Story 7: Winning Simple Game
 * Tests that game ends when a player makes an SOS
 */
public class SimpleGameOverTest {

    private SimpleGame game;
    private GameBoard board;

    @BeforeEach
    public void setUp() {
        game = new SimpleGame(3);
        board = game.getGameBoard();
    }

    /**
     * AC 7.1 & 7.2: Game detects and ends when horizontal SOS is formed
     */
    @Test
    public void testHorizontalSOSWin() {
        // Arrange - Create S-O-S horizontally
        game.makeMove(0, 0, "S"); // Player 1
        game.makeMove(1, 0, "O"); // Player 2
        game.makeMove(0, 1, "O"); // Player 1
        game.makeMove(1, 1, "S"); // Player 2

        // Act - Complete the SOS
        boolean result = game.makeMove(0, 2, "S"); // Player 1 completes S-O-S

        // Assert
        assertTrue(result, "Move should succeed");
        assertTrue(game.isGameOver(), "Game should be over after SOS");
    }

    /**
     * AC 7.1 & 7.2: Game detects vertical SOS
     */
    @Test
    public void testVerticalSOSWin() {
        // Arrange - Create S-O-S vertically
        game.makeMove(0, 0, "S"); // Player 1
        game.makeMove(0, 1, "O"); // Player 2
        game.makeMove(1, 0, "O"); // Player 1
        game.makeMove(1, 1, "S"); // Player 2

        // Act - Complete vertical SOS
        boolean result = game.makeMove(2, 0, "S"); // Player 1 completes S-O-S

        // Assert
        assertTrue(result, "Move should succeed");
        assertTrue(game.isGameOver(), "Game should be over after vertical SOS");
    }

    /**
     * AC 7.1 & 7.2: Game detects diagonal SOS
     */
    @Test
    public void testDiagonalSOSWin() {
        // Arrange - Create diagonal S-O-S
        game.makeMove(0, 0, "S"); // Player 1
        game.makeMove(0, 1, "O"); // Player 2
        game.makeMove(1, 1, "O"); // Player 1
        game.makeMove(1, 0, "S"); // Player 2

        // Act - Complete diagonal SOS
        boolean result = game.makeMove(2, 2, "S"); // Player 1 completes diagonal S-O-S

        // Assert
        assertTrue(result, "Move should succeed");
        assertTrue(game.isGameOver(), "Game should be over after diagonal SOS");
    }

    /**
     * AC 7.3: Player who made SOS is declared winner
     */
    @Test
    public void testWinnerIsCurrentPlayer() {
        // Arrange
        GameBoard.activeTurn startingPlayer = game.getCurrentTurn();

        // Create winning position (S-O-? in row 0)
        game.makeMove(0, 0, "S"); // Player 1
        game.makeMove(1, 0, "O"); // Player 2
        game.makeMove(0, 1, "O"); // Player 1
        game.makeMove(1, 1, "S"); // Player 2

        GameBoard.activeTurn winningPlayer = game.getCurrentTurn();

        // Act - Winning move
        game.makeMove(0, 2, "S"); // Complete SOS

        // Assert
        assertEquals(winningPlayer, game.getWinner(),
                "Winner should be the player who made the SOS");
        assertNotNull(game.getWinner(), "Winner should not be null");
    }

    /**
     * AC 7.1: Test SOS with O in the middle
     */
    @Test
    public void testSOSWithOInMiddle() {
        // Arrange - Create S-O-S with O placed in middle
        game.makeMove(0, 0, "S"); // Player 1
        game.makeMove(1, 0, "S"); // Player 2
        game.makeMove(0, 2, "S"); // Player 1
        game.makeMove(1, 1, "S"); // Player 2

        // Act - Place O in middle to complete SOS
        boolean result = game.makeMove(0, 1, "O"); // Player 1 completes S-O-S

        // Assert
        assertTrue(result, "Move should succeed");
        assertTrue(game.isGameOver(), "Game should be over after SOS formed by O");
        assertNotNull(game.getWinner(), "Should have a winner");
    }

    /**
     * Edge case: Game starts with neither player having won
     */
    @Test
    public void testGameStartsWithoutWinner() {
        // Assert
        assertFalse(game.isGameOver(), "New game should not be over");
        assertNull(game.getWinner(), "New game should have no winner");
        assertTrue(game.getGameInformation().getSOSLines().isEmpty(),
                "New game should have no SOS lines");
    }

}