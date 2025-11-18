package Sprint_04.product;

public abstract class Player {
    protected GameBoard gameBoard;
    protected GameInformation gameInformation;

    public Player(GameBoard gameBoard, GameInformation gameInformation) {
        this.gameBoard = gameBoard;
        this.gameInformation = gameInformation;
    }

    public abstract BoardTile makeMove(GameBoard gameBoard, GameInformation gameInformation);
}
