package Sprint_04.test;

import Sprint_02.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for User Story 3: Start a new game of the chosen board size and game mode
 * Tests the ability to start a new game with specified settings
 */
public class NewGameTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard();
    }

    // ===================================================================
    // User Story 3: Start a new game of the chosen board size and game mode
    // Acceptance Criterion 3.1: Initialize game with valid board size and Simple mode
    // ===================================================================

    /**
     * Test for AC 3.1: Initialize game with valid board size and Simple mode
     * Input: Create new game with board size 6 and Simple mode
     * Expected: Board is cleared, size is 6x6, and Simple mode rules are applied
     */
    @Test
    public void testNewGameWithSimpleMode() {
        GameBoard newGame = new GameBoard(6, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);

        assertEquals(6, newGame.getBoardSize(),
                "New game board size should be 6x6");
        assertEquals(GameBoard.gameMode.Simple, newGame.getGameMode(),
                "New game mode should be Simple");
        assertEquals(GameBoard.activeTurn.Player_One, newGame.getTurn(),
                "New game should start with Player_One turn");

        // Verify board is empty (all cells are clear)
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertTrue(newGame.getCell(i, j).isEmpty(),
                        "Cell [" + i + "," + j + "] should be empty in new game");
            }
        }
    }

    // ===================================================================
    // User Story 3: Start a new game of the chosen board size and game mode
    // Acceptance Criterion 3.2: Initialize game with valid board size and General mode
    // ===================================================================

    /**
     * Test for AC 3.2: Initialize game with valid board size and General mode
     * Input: Create new game with custom board size and General mode
     * Expected: Board is cleared, size updates, and General mode rules are applied
     */
    @Test
    public void testNewGameWithGeneralMode() {
        GameBoard newGame = new GameBoard(7, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);

        assertEquals(7, newGame.getBoardSize(),
                "New game board size should be 7x7");
        assertEquals(GameBoard.gameMode.General, newGame.getGameMode(),
                "New game mode should be General");
        assertEquals(GameBoard.activeTurn.Player_One, newGame.getTurn(),
                "New game should start with Player_One turn");

        // Verify board is empty
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                assertTrue(newGame.getCell(i, j).isEmpty(),
                        "Cell [" + i + "," + j + "] should be empty in new game");
            }
        }
    }

    // ===================================================================
    // User Story 3: Start a new game of the chosen board size and game mode
    // Acceptance Criterion 3.3: Reset existing game state
    // ===================================================================

    /**
     * Test for AC 3.3: Reset existing game state
     * Input: Make moves on a board, then create a new game
     * Expected: Previous moves are cleared, board resets to initial configuration
     */
    @Test
    public void testResetExistingGameState() {
        // Create initial game and make some moves
        GameBoard oldGame = new GameBoard(3, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        oldGame.getCell(0, 0).handleMouseClick("S", GameBoard.activeTurn.Player_One);
        oldGame.getCell(1, 1).handleMouseClick("O", GameBoard.activeTurn.Player_Two);
        oldGame.getCell(2, 2).handleMouseClick("S", GameBoard.activeTurn.Player_One);

        // Verify moves were made
        assertFalse(oldGame.getCell(0, 0).isEmpty(), "Cell should have a move");
        assertFalse(oldGame.getCell(1, 1).isEmpty(), "Cell should have a move");
        assertFalse(oldGame.getCell(2, 2).isEmpty(), "Cell should have a move");

        // Create new game (simulating "New Game" button click)
        GameBoard newGame = new GameBoard(4, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);

        // Verify new game is properly reset
        assertEquals(4, newGame.getBoardSize(), "Board size should be updated to 4x4");
        assertEquals(GameBoard.gameMode.General, newGame.getGameMode(),
                "Game mode should be updated to General");
        assertEquals(GameBoard.activeTurn.Player_One, newGame.getTurn(),
                "Turn should be reset to Player_One");

        // Verify all cells in new game are empty
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertTrue(newGame.getCell(i, j).isEmpty(),
                        "All cells should be empty in new game");
            }
        }
    }
}