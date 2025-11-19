package Sprint_04.test;

import Sprint_04.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class SelectPlayerTypesTest {
    private GameController gameController;
    private GameBoard gameBoard;
    private GameInformation gameInformation;


    @BeforeEach
    public void setup() {
        gameController = new GameController();
        gameBoard = gameController.getBoard();
        gameInformation = gameController.getGameInformation();
    }

    @Test // The UI will set both players to Human by default
    public void testBothHumanPlayersSelected(){
        gameController.getCurrentGame().setPlayerOne(new HumanPlayer(gameBoard, gameInformation));
        gameController.getCurrentGame().setPlayerTwo(new HumanPlayer(gameBoard, gameInformation));
        assertInstanceOf(HumanPlayer.class, gameController.getCurrentGame().getPlayerOne());
        assertInstanceOf(HumanPlayer.class, gameController.getCurrentGame().getPlayerTwo());
    }

    @Test
    public void testBothComputerPlayersSelected(){
        gameController.getCurrentGame().setPlayerOne(new ComputerPlayer(gameBoard, gameInformation));
        gameController.getCurrentGame().setPlayerTwo(new ComputerPlayer(gameBoard, gameInformation));
        assertInstanceOf(ComputerPlayer.class, gameController.getCurrentGame().getPlayerOne());
        assertInstanceOf(ComputerPlayer.class, gameController.getCurrentGame().getPlayerTwo());
    }

    @Test
    public void testHumanComputerPlayersSelected(){
        gameController.getCurrentGame().setPlayerOne(new HumanPlayer(gameBoard, gameInformation));
        gameController.getCurrentGame().setPlayerTwo(new ComputerPlayer(gameBoard, gameInformation));
        assertInstanceOf(HumanPlayer.class, gameController.getCurrentGame().getPlayerOne());
        assertInstanceOf(ComputerPlayer.class, gameController.getCurrentGame().getPlayerTwo());
    }

    @Test
    public void testComputerHumanPlayersSelected(){
        gameController.getCurrentGame().setPlayerOne(new ComputerPlayer(gameBoard, gameInformation));
        gameController.getCurrentGame().setPlayerTwo(new HumanPlayer(gameBoard, gameInformation));
        assertInstanceOf(ComputerPlayer.class, gameController.getCurrentGame().getPlayerOne());
        assertInstanceOf(HumanPlayer.class, gameController.getCurrentGame().getPlayerTwo());
    }
}
