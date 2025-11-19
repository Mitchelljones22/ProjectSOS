package Sprint_04.product;

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

    public void setPlayerOne(Player player) {
        gameInformation.setPlayerOne(player);
    }

    public void setPlayerTwo(Player player) {
        gameInformation.setPlayerTwo(player);
    }

    public Player getPlayerOne() {
        return gameInformation.getPlayerOne();
    }

    public Player getPlayerTwo() {
        return gameInformation.getPlayerTwo();
    }

    public Player getCurrentPlayer() {
        return gameInformation.getCurrentPlayer();
    }
}
