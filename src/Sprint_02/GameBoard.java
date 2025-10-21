package Sprint_02;

public class GameBoard {

    public enum activeTurn { Player_One, Player_Two }

    private BoardTile[][] grid;
    private activeTurn turn;

    public GameBoard() {
        grid = new BoardTile[3][3];
        turn = activeTurn.Player_One;
        initializeBoard();
    }

    public GameBoard(int boardsize, activeTurn turn) {
        grid = new BoardTile[boardsize][boardsize];
        this.turn = turn;
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                grid[i][j] = new BoardTile();
            }
        }
    }

    public BoardTile getCell(int row, int column) {
        return grid[row][column];
    }

    public void setCell(int row, int column, BoardTile tile) {
        grid[row][column] = tile;
    }

    public activeTurn getTurn() {
        return turn;
    }

    public void switchTurn() {
        if (turn == activeTurn.Player_One) {
            turn = activeTurn.Player_Two;
        } else {
            turn = activeTurn.Player_One;
        }
    }

}