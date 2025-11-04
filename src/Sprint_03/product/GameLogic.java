package Sprint_03.product;

public class GameLogic {

    public static int SOSCheckWithS(GameBoard gameBoard, int row, int col, String letter) {
        int totalPoints = 0;

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
                    System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOW");
                    totalPoints += 1;
                }
            }
        }

        return totalPoints;
    }

    /**
     * Checks if either row or col is outside range of board
     * @param gameBoard current game
     * @param row row of the tile you want to check
     * @param col column of the tile you want to check
     * @return true if tile is valid false else
     */
    public static boolean posOnBoard(GameBoard gameBoard, int row, int col){
        if(row >= gameBoard.getBoardSize() - 1 || row < 0 || col >= gameBoard.getBoardSize() - 1 || col < 0){
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
