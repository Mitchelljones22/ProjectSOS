package Sprint_03.product;

public class GameBoard {
    public enum activeTurn { Player_One, Player_Two }
    public enum gameMode { Simple, General }

    private BoardTile[][] grid;
    private int boardSize;

    public GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.grid = new BoardTile[boardSize][boardSize];
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

    public void reset() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].clear();
            }
        }
    }

    public int getBoardSize() {
        return grid.length;
    }

}