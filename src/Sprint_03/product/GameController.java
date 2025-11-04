package Sprint_03.product;

public class GameController {
    private SOSGame currentGame;
    
    public GameController() {
        // Start with default simple game
        this.currentGame = new SimpleGame(3);
    }

    public void startNewGame(GameBoard.gameMode mode, int boardSize) {
        if (mode == GameBoard.gameMode.Simple) {
            currentGame = new SimpleGame(boardSize);
        } else {
            currentGame = new GeneralGame(boardSize);
        }
    }

    public boolean handleMove(int row, int col, String letter) {
        return currentGame.makeMove(row, col, letter);
    }

    public SOSGame getCurrentGame() {
        return currentGame;
    }

    public boolean isGameOver() {
        return currentGame.isGameOver();
    }
    
    public GameBoard.activeTurn getWinner() {
        return currentGame.getWinner();
    }

    public GameBoard.activeTurn getCurrentTurn() {
        return currentGame.getCurrentTurn();
    }
    
    public GameBoard getBoard() {

        return currentGame.getGameBoard();
    }

    public GameInformation getGameInformation() {
        return currentGame.getGameInformation();
    }

    public void resetGame() {
        currentGame.reset();
    }

    public String getGameModeString() {
        if (currentGame instanceof SimpleGame) {
            return "Simple";
        } else if (currentGame instanceof GeneralGame) {
            return "General";
        }
        return "Unknown";
    }

    public int getBoardSize() {
        return currentGame.getGameBoard().getBoardSize();
    }
}