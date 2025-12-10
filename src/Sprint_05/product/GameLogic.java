package Sprint_05.product;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    public static class SOSCheckResults{
        public int sosCount;
        public List<SOSLine> sosLines;

        public SOSCheckResults(){
            this.sosCount = 0;
            this.sosLines = new ArrayList<>();
        }
    }

    public static SOSCheckResults SOSCheckWithS(GameBoard gameBoard, int row, int col, GameBoard.activeTurn currentPlayer) {
        SOSCheckResults results = new SOSCheckResults();

        int[][] surroundingTiles = {
                {0,-1}, //left
                {0, 1}, //right
                {-1,0}, //up
                {1, 0}, //down
                {1, 1}, //bottom right - diagonal
                {-1,-1}, //top left - diagonal
                {1, -1}, //bottom left - diagonal
                {-1, 1} //top right - diagonal

        };

        for(int i = 0; i < surroundingTiles.length; i++){
            int[] currentDire = surroundingTiles[i];
            int Orow = row + currentDire[0];
            int Ocol = col + currentDire[1]; // selects tile touching
            int Srow = row + (currentDire[0] * 2); // selects tile 2 spaces away
            int Scol = col + (currentDire[1] * 2); // selects tile 2 spaces away

            if(posOnBoard(gameBoard, Srow, Scol) && posOnBoard(gameBoard, Orow, Ocol)){
                if(gameBoard.getCell(Orow, Ocol).getLetter().equals("O") && gameBoard.getCell(Srow, Scol).getLetter().equals("S")){
                    System.out.println("Scored point by placing S");
                    results.sosCount++;
                    results.sosLines.add(new SOSLine(row, col,Srow, Scol, currentPlayer));
                }
            }
        }
        return results;
    }

    public static SOSCheckResults SOSCheckWithO(GameBoard gameBoard, int row, int col, GameBoard.activeTurn currentPlayer) {
        SOSCheckResults results = new SOSCheckResults();

        int[][] surroundingTiles = {
                {0,-1}, //left
                {0, 1}, //right
                {-1,0}, //up
                {1, 0}, //down
                {1, 1}, //bottom right - diagonal
                {-1,-1}, //top left - diagonal
                {1, -1}, //bottom left - diagonal
                {-1, 1} //top right - diagonal

        };

        for(int i = 0; i < surroundingTiles.length; i+= 2){
            int[] currentDire = surroundingTiles[i];
            int[] currentDire2 = surroundingTiles[i+1];
            int OrowFront = row + currentDire[0];
            int OcolFront = col + currentDire[1]; // selects tile touching
            int OrowEnd = row + currentDire2[0];
            int OcolEnd  = col + currentDire2[1]; // selects tile touching

            if(posOnBoard(gameBoard, OrowEnd, OcolEnd) && posOnBoard(gameBoard, OrowFront, OcolFront)){
                if(gameBoard.getCell(OrowFront, OcolFront).getLetter().equals("S") && gameBoard.getCell(OrowEnd, OcolEnd).getLetter().equals("S")){
                    System.out.println("Scored point by placing O");
                    results.sosCount++;
                    results.sosLines.add(new SOSLine(OrowFront, OcolFront, OrowEnd, OcolEnd, currentPlayer));
                }
            }
        }
        return results;
    }



    /**
     * Checks if either row or col is outside range of board
     * @param gameBoard current game
     * @param row row of the tile you want to check
     * @param col column of the tile you want to check
     * @return true if tile is valid false else
     */
    public static boolean posOnBoard(GameBoard gameBoard, int row, int col){
        if(row > gameBoard.getBoardSize() - 1 || row < 0 || col > gameBoard.getBoardSize() - 1 || col < 0){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isBoardFull(GameBoard gameBoard) {
        for(int x = 0; x < gameBoard.getBoardSize(); x++){
            for(int y = 0; y < gameBoard.getBoardSize(); y++){
                if (gameBoard.getCell(x, y).isEmpty()) {
                    System.out.println("Cell Empty");
                    return false;
                }
            }
        }
        System.out.println("Board is full");
        return true;
    }



}
