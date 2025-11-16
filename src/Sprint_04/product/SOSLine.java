package Sprint_04.product;

import javafx.scene.paint.Color;

public class SOSLine {
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private GameBoard.activeTurn player;

    public SOSLine(int startRow, int startCol, int endRow, int endCol,  GameBoard.activeTurn player) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.player = player;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }
    public int getEndCol() {
        return endCol;
    }
    public GameBoard.activeTurn getPlayer() {
        return player;
    }

    public Color getColor() {
        if(player == GameBoard.activeTurn.Player_One){
            return Color.BLUE;
        }
        else{
            return Color.RED;
        }
    }

}
