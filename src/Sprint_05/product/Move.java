package Sprint_05.product;

public class Move {
    private final int row;
    private final int col;
    private final String letter;
    private final GameBoard.activeTurn player;
    private final int sequence;

    public Move(int row, int col, String letter, GameBoard.activeTurn player, int sequence) {
        this.row = row;
        this.col = col;
        this.letter = letter;
        this.player = player;
        this.sequence = sequence;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getLetter() {
        return letter;
    }

    public GameBoard.activeTurn getPlayer() {
        return player;
    }

    public int getSequence() {
        return sequence;
    }

}