package Sprint_03.product;

public class GameLogic {
    //public static int SOSverticleCheck(Gameboard gameBoard){

    //}








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
