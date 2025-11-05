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

        GameLogic.SOSCheckResults result= new GameLogic.SOSCheckResults();

        if(letter.equals("S")){
            result = (GameLogic.SOSCheckWithS(gameBoard, row, col, gameInformation.getCurrentTurn()));

        }
        else if(letter.equals("O")){
            result = (GameLogic.SOSCheckWithO(gameBoard, row, col, gameInformation.getCurrentTurn()));
        }

        // If there is one SOS the Simple game is over
        if(result.sosCount > 0) {
            gameInformation.addSOSLines(result.sosLines);
            gameInformation.setWinner(gameInformation.getCurrentTurn());
            getGameInformation().setGameOver(true);
            System.out.println("Game Over Winner is " + gameInformation.getWinner());
            gameInformation.switchTurn();
            return true;
        }

        // No SOS detected then switch turns
        gameInformation.switchTurn();
        if(GameLogic.isBoardFull(gameBoard)){
            getGameInformation().setGameOver(true);
            System.out.println("Game Over");
        }
        return true; // Move is complete
    }

    @Override
    public boolean isGameOver() {
        return gameInformation.isGameOver();
    }

    @Override
    public GameBoard.activeTurn getWinner() {
        return(gameInformation.getWinner());
    }
}
