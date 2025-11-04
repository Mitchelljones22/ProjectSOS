package Sprint_03.product;

public class SimpleGame extends SOSGame{
    public SimpleGame(int boardSize){
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
        System.out.println("Row" + row + " Col" + col);

        GameLogic.SOSCheckWithS(gameBoard, row, col, letter);

        gameInformation.switchTurn();
        if(GameLogic.isBoardFull(gameBoard)){
            getGameInformation().setGameOver(true);
            System.out.println("Game Over");
        }
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
