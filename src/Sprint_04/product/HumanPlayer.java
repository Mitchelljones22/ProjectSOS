package Sprint_04.product;


public class HumanPlayer extends Player{
    public HumanPlayer(GameBoard gameBoard, GameInformation gameInformation) {
        super(gameBoard, gameInformation);
    }
    @Override
    public BoardTile makeMove(GameBoard gameBoard, GameInformation gameInformation) {
        return null; // Humans will calculate their move and select it on their own
    }
}
