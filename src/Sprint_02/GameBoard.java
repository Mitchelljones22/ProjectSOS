package Sprint_02;

public class GameBoard {

    public enum activeTurn { Player_One, Player_Two };
    public enum gameMode {Simple, General};

    private BoardTile[][] grid;
    private activeTurn turn;
    private gameMode mode;

    public GameBoard() {
        this.grid = new BoardTile[3][3];
        this.turn = activeTurn.Player_One;
        this.mode = gameMode.Simple;
        initializeBoard();
    }

    public GameBoard(int boardsize, activeTurn turn, gameMode mode) {
        this.grid = new BoardTile[boardsize][boardsize];
        this.turn = turn;
        this.mode = mode;
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

    public int getBoardSize() {
        return grid.length;
    }

    public gameMode getGameMode() {
        return mode;
    }

}