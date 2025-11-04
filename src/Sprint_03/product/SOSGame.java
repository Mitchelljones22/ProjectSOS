package Sprint_03.product;

public abstract class SOSGame {
    protected GameBoard gameBoard;
    protected GameInformation gameInformation;

    public SOSGame(int boardSize) {
        this.gameBoard = new GameBoard(boardSize);
        this.gameInformation = new GameInformation();
    }

    public abstract boolean makeMove(int row, int col, String letter);

    public abstract boolean isGameOver();

    public abstract GameBoard.activeTurn getWinner();

    public void reset() {
        gameBoard.reset();
        gameInformation.reset();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameInformation getGameInformation() {
        return gameInformation;
    }

    public GameBoard.activeTurn getCurrentTurn() {
        return gameInformation.getCurrentTurn();
    }

    public void switchTurn() {
        gameInformation.switchTurn();
    }


}
