package Sprint_05.test;

import Sprint_02.BoardTile;
import Sprint_02.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for User Story 4: Make a move in a simple game
 * Tests the ability for human players to place S and O on the board
 */
public class SimpleMoveTest {

    private GameBoard simpleGame;

    @BeforeEach
    public void setUp() {
        simpleGame = new GameBoard(3, GameBoard.activeTurn.Player_One, GameBoard.gameMode.Simple);
    }

    // ===================================================================
    // User Story 4: Make a move in a simple game
    // Acceptance Criterion 4.1: Human Player S move
    // ===================================================================

    /**
     * Test for AC 4.1: Human Player S move
     * Input: Player selects 'S' and clicks empty cell
     * Expected: 'S' is placed in the cell
     */
    @Test
    public void testPlaceSMoveInSimpleGame() {
        BoardTile tile = simpleGame.getCell(0, 0);

        // Verify tile is initially empty
        assertTrue(tile.isEmpty(), "Tile should initially be empty");
        assertEquals("", tile.getLetter(), "Tile should have no letter initially");

        // Place 'S' on the tile
        tile.handleMouseClick("S", GameBoard.activeTurn.Player_One);

        // Verify 'S' was placed
        assertEquals("S", tile.getLetter(), "Tile should contain 'S'");
        assertFalse(tile.isEmpty(), "Tile should no longer be empty");
    }

    // ===================================================================
    // User Story 4: Make a move in a simple game
    // Acceptance Criterion 4.2: Human Player O move
    // ===================================================================

    /**
     * Test for AC 4.2: Human Player O move
     * Input: Player selects 'O' and clicks empty cell
     * Expected: 'O' is placed in the cell
     */
    @Test
    public void testPlaceOMoveInSimpleGame() {
        BoardTile tile = simpleGame.getCell(1, 1);

        // Verify tile is initially empty
        assertTrue(tile.isEmpty(), "Tile should initially be empty");
        assertEquals("", tile.getLetter(), "Tile should have no letter initially");

        // Place 'O' on the tile
        tile.handleMouseClick("O", GameBoard.activeTurn.Player_Two);

        // Verify 'O' was placed
        assertEquals("O", tile.getLetter(), "Tile should contain 'O'");
        assertFalse(tile.isEmpty(), "Tile should no longer be empty");
    }
}