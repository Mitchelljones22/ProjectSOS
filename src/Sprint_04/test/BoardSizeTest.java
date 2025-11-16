package Sprint_04.test;

import Sprint_02.BoardTile;
import Sprint_02.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for User Story 1: Choose a board size
 * Tests the ability to select and create boards of different sizes
 */
public class BoardSizeTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard();
    }

    // ===================================================================
    // User Story 1: Choose a board size
    // Acceptance Criterion 1.1: Valid board size selection
    // ===================================================================

    /**
     * Test for AC 1.1: Valid board size selection
     * Input: Board size between 3 and 10
     * Expected: GameBoard is created with the specified size
     */
    @Test
    public void testValidBoardSizeSelection() {
        // Test board size 3
        GameBoard board3 = new GameBoard(3, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(3, board3.getBoardSize(), "Board size should be 3x3");

        // Test board size 5
        GameBoard board5 = new GameBoard(5, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(5, board5.getBoardSize(), "Board size should be 5x5");

        // Test board size 10
        GameBoard board10 = new GameBoard(10, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(10, board10.getBoardSize(), "Board size should be 10x10");
    }

    /**
     * Test for AC 1.1: Additional test for mid-range board sizes
     * Input: Board sizes 4, 6, 7, 8, 9
     * Expected: GameBoard is created with each specified size
     */
    @Test
    public void testMidRangeBoardSizes() {
        GameBoard board4 = new GameBoard(4, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(4, board4.getBoardSize(), "Board size should be 4x4");

        GameBoard board6 = new GameBoard(6, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(6, board6.getBoardSize(), "Board size should be 6x6");

        GameBoard board7 = new GameBoard(7, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(7, board7.getBoardSize(), "Board size should be 7x7");

        GameBoard board8 = new GameBoard(8, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(8, board8.getBoardSize(), "Board size should be 8x8");

        GameBoard board9 = new GameBoard(9, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(9, board9.getBoardSize(), "Board size should be 9x9");
    }

    // ===================================================================
    // User Story 1: Choose a board size
    // Acceptance Criterion 1.2: Default board size
    // ===================================================================

    /**
     * Test for AC 1.2: Default board size
     * Input: No board size specified (default constructor)
     * Expected: Board defaults to 3x3
     */
    @Test
    public void testDefaultBoardSize() {
        GameBoard defaultBoard = new GameBoard();
        assertEquals(3, defaultBoard.getBoardSize(), "Default board size should be 3x3");
    }

    /**
     * Test for AC 1.2: Verify default board has correct number of cells
     * Input: Create board with default constructor
     * Expected: Board has 3 rows and 3 columns (9 total cells)
     */
    @Test
    public void testDefaultBoardCellCount() {
        GameBoard defaultBoard = new GameBoard();

        // Verify we can access all 3x3 cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertNotNull(defaultBoard.getCell(i, j),
                        "Cell at position [" + i + "," + j + "] should not be null");
            }
        }
    }

    /**
     * Additional test: Verify all cells in custom board are initialized
     * Input: Create board of size 5x5
     * Expected: All 25 cells are not null and are empty
     */
    @Test
    public void testAllCellsInitializedInCustomBoard() {
        GameBoard board5 = new GameBoard(5, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);

        // Verify all cells exist and are empty
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                BoardTile tile = board5.getCell(i, j);
                assertNotNull(tile, "Cell at [" + i + "," + j + "] should not be null");
                assertTrue(tile.isEmpty(), "Cell at [" + i + "," + j + "] should be empty");
            }
        }
    }
}