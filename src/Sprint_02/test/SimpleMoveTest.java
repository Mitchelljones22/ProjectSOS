package Sprint_02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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

    // ===================================================================
    // User Story 4: Make a move in a simple game
    // Acceptance Criterion 4.3: Human Player Winning move
    // (NOT IMPLEMENTED IN THIS SPRINT - Placeholder for future sprint)
    // ===================================================================

    /**
     * Test for AC 4.3: Human Player Winning move
     * NOTE: This feature is NOT implemented in Sprint 2
     * This test is a placeholder for future implementation
     *
     * Input: Player places move that creates SOS
     * Expected: Game recognizes SOS and declares winner
     */
    @Test
    public void testWinningMoveNotImplementedYet() {
        // This acceptance criterion is not part of Sprint 2
        // Test will be implemented in a future sprint when
        // SOS detection and winner determination are added

        // For now, we just verify moves can be placed
        simpleGame.getCell(0, 0).handleMouseClick("S", GameBoard.activeTurn.Player_One);
        simpleGame.getCell(0, 1).handleMouseClick("O", GameBoard.activeTurn.Player_Two);
        simpleGame.getCell(0, 2).handleMouseClick("S", GameBoard.activeTurn.Player_One);

        // Verify all moves were placed (no winner detection yet)
        assertEquals("S", simpleGame.getCell(0, 0).getLetter());
        assertEquals("O", simpleGame.getCell(0, 1).getLetter());
        assertEquals("S", simpleGame.getCell(0, 2).getLetter());

        // Future implementation will check for SOS sequence and winner
    }
}