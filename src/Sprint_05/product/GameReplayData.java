package Sprint_05.product;

import java.util.List;

public  class GameReplayData {
    public final int gameID;
    public final String gameMode;
    public final int boardSize;
    public final GameBoard.activeTurn winner;
    public final List<Move> moves;

    public GameReplayData(int gameID, String gameMode, int boardSize,
                          GameBoard.activeTurn winner, List<Move> moves) {
        this.gameID = gameID;
        this.gameMode = gameMode;
        this.boardSize = boardSize;
        this.winner = winner;
        this.moves = moves;
    }
}
