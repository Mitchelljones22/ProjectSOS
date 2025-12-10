package Sprint_05.product;

import java.util.ArrayList;
import java.util.List;


public class GameRecorder {
    private List<Move> moves;
    private int moveSequence;
    private boolean isRecording;

    public GameRecorder() {
        this.moves = new ArrayList<>();
        this.moveSequence = 0;
        this.isRecording = true;
    }

    public void recordMove(int row, int col, String letter, GameBoard.activeTurn player) {
        if (!isRecording) {
            return;
        }

        Move move = new Move(row, col, letter, player, moveSequence);
        moves.add(move);
        moveSequence++;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getMoveSequence() {
        return moveSequence;
    }

    public int getMoveCount() {
        return moves.size();
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void stopRecording() {
        this.isRecording = false;
    }

    public void clear() {
        moves.clear();
        moveSequence = 0;
    }



}
