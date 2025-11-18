package Sprint_04.product;

import java.util.Random;

public class ComputerPlayer extends Player{
    public ComputerPlayer(GameBoard gameBoard, GameInformation gameInformation) {
        super(gameBoard, gameInformation);
    }

    @Override
    public BoardTile makeMove(GameBoard gameBoard, GameInformation gameInformation) {
        if(gameInformation.isGameOver()){ // Base case, check if game is over
            return null;
        }

        int boardSize = (gameBoard.getBoardSize());
        Random random = new Random();

        while(true){
            int x = random.nextInt(boardSize);
            int y = random.nextInt(boardSize);

            if(gameBoard.getCell(x, y).isEmpty()){
                return gameBoard.getCell(x, y);
            }

            if(GameLogic.isBoardFull(gameBoard)){
                return null;
            }
        }



        /* Place in first tile available
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                if(gameBoard.getCell(x, y).isEmpty()){
                    return gameBoard.getCell(x, y);
                }
            }
        }
        */

    }
}
