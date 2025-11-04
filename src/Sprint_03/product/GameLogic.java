package Sprint_03.product;

public class GameLogic {

    public static int SOSCheckWithS(GameBoard gameBoard, int row, int col, String letter) {
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

        }

        if(gameBoard.getCell(row + 1 , col).isEmpty() && gameBoard.getCell(row - 1 , col).isEmpty()) {
            return 0;
        }
        if(!gameBoard.getCell(row + 1, col).getLetter().equals(letter)){
            if(gameBoard.getCell(row + 2, col).getLetter().equals(letter)){
                System.out.println("ONE SOS POINT");
                return 1;
            }
        }

        if(!gameBoard.getCell(row - 1, col).getLetter().equals(letter)){
            if(gameBoard.getCell(row - 2, col).getLetter().equals(letter)){
                System.out.println("ONE SOS POINT");
                return 1;
            }
        }
        return 0;
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
