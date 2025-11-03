package Sprint_03.test;

import Sprint_02.BoardTile;
import Sprint_02.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for User Story 2: Choose the game mode of a chosen board
 * Tests the ability to select Simple or General game mode
 */
public class GameModeTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard();
    }

    // ===================================================================
    // User Story 2: Choose the game mode of a chosen board
    // Acceptance Criterion 2.1: Initialize game with Simple mode
    // ===================================================================

    /**
     * Test for AC 2.1: Initialize game with valid board size and Simple mode
     * Input: Board size 4 and Simple game mode
     * Expected: Game is created with Simple mode and proper initialization
     */
    @Test
    public void testSimpleGameModeInitialization() {
        GameBoard simpleBoard = new GameBoard(4, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);

        assertEquals(GameBoard.gameMode.Simple, simpleBoard.getGameMode(),
                "Game mode should be Simple");
        assertEquals(4, simpleBoard.getBoardSize(),
                "Board size should be 4x4");
        assertEquals(GameBoard.activeTurn.Player_One, simpleBoard.getTurn(),
                "Initial turn should be Player_One");
    }

    /**
     * Test for AC 2.1: Verify all cells are empty in Simple mode
     * Input: Create Simple game with board size 4
     * Expected: All cells on the board are empty
     */
    @Test
    public void testSimpleGameAllCellsEmpty() {
        GameBoard simpleBoard = new GameBoard(4, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);

        // Verify all cells are empty
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertTrue(simpleBoard.getCell(i, j).isEmpty(),
                        "Cell [" + i + "," + j + "] should be empty in new Simple game");
            }
        }
    }

    /**
     * Test for AC 2.1: Simple game with different board sizes
     * Input: Create Simple games with sizes 3, 5, and 7
     * Expected: All games are properly initialized with Simple mode
     */
    @Test
    public void testSimpleGameWithVariousBoardSizes() {
        GameBoard simple3 = new GameBoard(3, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(GameBoard.gameMode.Simple, simple3.getGameMode(),
                "3x3 board should have Simple mode");
        assertEquals(3, simple3.getBoardSize(), "Board should be 3x3");

        GameBoard simple5 = new GameBoard(5, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(GameBoard.gameMode.Simple, simple5.getGameMode(),
                "5x5 board should have Simple mode");
        assertEquals(5, simple5.getBoardSize(), "Board should be 5x5");

        GameBoard simple7 = new GameBoard(7, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(GameBoard.gameMode.Simple, simple7.getGameMode(),
                "7x7 board should have Simple mode");
        assertEquals(7, simple7.getBoardSize(), "Board should be 7x7");
    }

    // ===================================================================
    // User Story 2: Choose the game mode of a chosen board
    // Acceptance Criterion 2.2: Initialize game with General mode
    // ===================================================================

    /**
     * Test for AC 2.2: Initialize game with valid board size and General mode
     * Input: Board size 5 and General game mode
     * Expected: Game is created with General mode and proper initialization
     */
    @Test
    public void testGeneralGameModeInitialization() {
        GameBoard generalBoard = new GameBoard(5, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);

        assertEquals(GameBoard.gameMode.General, generalBoard.getGameMode(),
                "Game mode should be General");
        assertEquals(5, generalBoard.getBoardSize(),
                "Board size should be 5x5");
        assertEquals(GameBoard.activeTurn.Player_One, generalBoard.getTurn(),
                "Initial turn should be Player_One");
    }

    /**
     * Test for AC 2.2: Verify all cells are empty in General mode
     * Input: Create General game with board size 5
     * Expected: All cells on the board are empty and unoccupied
     */
    @Test
    public void testGeneralGameAllCellsEmpty() {
        GameBoard generalBoard = new GameBoard(5, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);

        // Verify all cells are empty
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertTrue(generalBoard.getCell(i, j).isEmpty(),
                        "Cell [" + i + "," + j + "] should be empty in new General game");
            }
        }
    }

    /**
     * Test for AC 2.2: General game with different board sizes
     * Input: Create General games with sizes 4, 6, and 8
     * Expected: All games are properly initialized with General mode
     */
    @Test
    public void testGeneralGameWithVariousBoardSizes() {
        GameBoard general4 = new GameBoard(4, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);
        assertEquals(GameBoard.gameMode.General, general4.getGameMode(),
                "4x4 board should have General mode");
        assertEquals(4, general4.getBoardSize(), "Board should be 4x4");

        GameBoard general6 = new GameBoard(6, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);
        assertEquals(GameBoard.gameMode.General, general6.getGameMode(),
                "6x6 board should have General mode");
        assertEquals(6, general6.getBoardSize(), "Board should be 6x6");

        GameBoard general8 = new GameBoard(8, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);
        assertEquals(GameBoard.gameMode.General, general8.getGameMode(),
                "8x8 board should have General mode");
        assertEquals(8, general8.getBoardSize(), "Board should be 8x8");
    }

    // ===================================================================
    // User Story 2: Additional tests
    // Acceptance Criterion 2.3: Reset existing game state
    // ===================================================================

    /**
     * Test for AC 2.3: Verify default game mode is Simple
     * Input: Create board with default constructor
     * Expected: Game mode defaults to Simple
     */
    @Test
    public void testDefaultGameModeIsSimple() {
        GameBoard defaultBoard = new GameBoard();
        assertEquals(GameBoard.gameMode.Simple, defaultBoard.getGameMode(),
                "Default game mode should be Simple");
    }

    /**
     * Additional test: Verify turn is always Player_One at start
     * Input: Create multiple boards with different modes
     * Expected: All boards start with Player_One turn
     */
    @Test
    public void testInitialTurnIsAlwaysPlayerOne() {
        GameBoard simpleBoard = new GameBoard(3, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
        assertEquals(GameBoard.activeTurn.Player_One, simpleBoard.getTurn(),
                "Simple game should start with Player_One");

        GameBoard generalBoard = new GameBoard(5, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);
        assertEquals(GameBoard.activeTurn.Player_One, generalBoard.getTurn(),
                "General game should start with Player_One");
    }

    /**
     * Additional test: Verify cells are properly initialized as BoardTile objects
     * Input: Create game with Simple mode
     * Expected: Each cell is a non-null BoardTile instance
     */
    @Test
    public void testCellsAreProperlyInitializedBoardTiles() {
        GameBoard board = new GameBoard(3, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                BoardTile tile = board.getCell(i, j);
                assertNotNull(tile, "Cell should not be null");
                assertInstanceOf(BoardTile.class, tile, "Cell should be instance of BoardTile");
            }
        }
    }
}