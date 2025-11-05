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

        GameLogic.SOSCheckResults result= new GameLogic.SOSCheckResults();

        if(letter.equals("S")){
            result = (GameLogic.SOSCheckWithS(gameBoard, row, col, gameInformation.getCurrentTurn()));

        }
        else if(letter.equals("O")){
            result = (GameLogic.SOSCheckWithO(gameBoard, row, col, gameInformation.getCurrentTurn()));
        }

        // If there is one SOS the Simple game is over
        if(result.sosCount > 0) {
            for(int i = 0; i < result.sosCount; i++){
                gameInformation.incrementScore(gameInformation.getCurrentTurn());
            }
            gameInformation.addSOSLines(result.sosLines);
            gameInformation.switchTurn();
        }
        else{
            gameInformation.switchTurn();
        }

        if(GameLogic.isBoardFull(gameBoard)){
            getGameInformation().setGameOver(true);
            System.out.println("Game Over");
            calculateWinner();
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

    /*
     * sets the winner in gameInformation
     */
    private void calculateWinner(){
        int playerOneScore = gameInformation.getPlayerOneScore();
        int playerTwoScore = gameInformation.getPlayerTwoScore();

        if(playerOneScore > playerTwoScore){
            gameInformation.setWinner(GameBoard.activeTurn.Player_One);
        }
        else if(playerOneScore < playerTwoScore){
            gameInformation.setWinner(GameBoard.activeTurn.Player_Two);
        }
    }
}
