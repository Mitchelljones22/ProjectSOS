package Sprint_03.product;

public class GameLogic {

    public static int HorizontalSOSCheck(GameBoard gameBoard, int row, int col, String letter) {
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
