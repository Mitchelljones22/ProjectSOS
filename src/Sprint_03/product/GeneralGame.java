package Sprint_03.product;

public class GeneralGame extends SOSGame {
    public GeneralGame(int boardSize){
        super(boardSize);
    }
    @Override
    public boolean makeMove(int row, int col, String letter){
        BoardTile tile = gameBoard.getCell(row, col);

        if (!tile.isEmpty()) {
            return false;
        }

        tile.setLetter(letter);
        tile.setPlayerColor(gameInformation.getCurrentTurn());

        gameInformation.switchTurn();
        return true;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
    @Override
    public GameBoard.activeTurn getWinner() {
        return(gameInformation.getWinner());
    }
}
