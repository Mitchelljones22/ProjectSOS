package Sprint_02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for User Story 6: Make a move in a general game
 * Tests the ability for human players to place S and O in general game mode
 */
public class GeneralMoveTest {

    private GameBoard generalGame;

    @BeforeEach
    public void setUp() {
        generalGame = new GameBoard(4, GameBoard.activeTurn.Player_One, GameBoard.gameMode.General);
    }

    // ===================================================================
    // User Story 6: Make a move in a general game
    // Acceptance Criterion 6.1: Human Player S move
    // ===================================================================

    /**
     * Test for AC 6.1: Human Player S move in general game
     * Input: Player selects 'S' and clicks empty cell in general game
     * Expected: 'S' is placed in the cell
     */
    @Test
    public void testPlaceSMoveInGeneralGame() {
        BoardTile tile = generalGame.getCell(2, 2);

        // Verify tile is initially empty
        assertTrue(tile.isEmpty(), "Tile should initially be empty");
        assertEquals("", tile.getLetter(), "Tile should have no letter initially");

        // Place 'S' on the tile
        tile.handleMouseClick("S", GameBoard.activeTurn.Player_One);

        // Verify 'S' was placed
        assertEquals("S", tile.getLetter(), "Tile should contain 'S'");
        assertFalse(tile.isEmpty(), "Tile should no longer be empty");
    }

}