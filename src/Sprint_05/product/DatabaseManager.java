package Sprint_05.product;

import java.sql.*;
import java.util.List;

public class DatabaseManager {
    private static final String DB_PATH = "jdbc:sqlite:sos_db.sqlite";

    public static int saveGame(String gameMode, int boardSize, GameBoard.activeTurn winner, List<Move> moves) {
        Connection conn = null;
        Statement sqlStatement = null;

        try {
            conn = DriverManager.getConnection(DB_PATH);
            sqlStatement = conn.createStatement();
            conn.setAutoCommit(false);

            String winnerStr = "NULL";
            if (winner != null) {
                winnerStr = "'" + winner.toString() + "'";
            }

            String insertGameSQL = "INSERT INTO GameReplays (gameMode, boardSize, winner, timestamp) VALUES ('"
                    + gameMode + "', " + boardSize + ", " + winnerStr + ", datetime('now'))";
            sqlStatement.executeUpdate(insertGameSQL);

            ResultSet rs = sqlStatement.executeQuery("SELECT last_insert_rowid()");
            int gameID = 0;
            if (rs.next()) {
                gameID = rs.getInt(1);
            }
            rs.close();

            sqlStatement.executeUpdate("INSERT INTO GamePlayers (gameID, username, playerNumber) VALUES ("
                    + gameID + ", 'Player_One', 1)");
            sqlStatement.executeUpdate("INSERT INTO GamePlayers (gameID, username, playerNumber) VALUES ("
                    + gameID + ", 'Player_Two', 2)");

            for (Move move : moves) {
                String insertMoveSQL = "INSERT INTO Moves (gameID, username, moveSequence, row, col, letter) VALUES ("
                        + gameID + ", '" + move.getPlayer().toString() + "', " + move.getSequence() + ", "
                        + move.getRow() + ", " + move.getCol() + ", '" + move.getLetter() + "')";
                sqlStatement.executeUpdate(insertMoveSQL);
            }

            conn.commit();
            System.out.println("Game saved successfully with ID: " + gameID);
            return gameID;

        } catch (SQLException e) {
            System.err.println("Failed to save game: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return -1;
        } finally {
            if (sqlStatement != null) {
                try {
                    sqlStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static GameReplayData loadMostRecentGame() {
        Connection conn = null;
        Statement sqlStatement = null;

        try {
            conn = DriverManager.getConnection(DB_PATH);
            sqlStatement = conn.createStatement();

            ResultSet resultSet = sqlStatement.executeQuery("SELECT gameID FROM GameReplays ORDER BY gameID DESC LIMIT 1");

            if (resultSet.next()) {
                int gameID = resultSet.getInt("gameID");
                resultSet.close();
                return loadGameByID(conn, sqlStatement, gameID);
            } else {
                System.out.println("No games found in database");
                resultSet.close();
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Failed to load most recent game: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (sqlStatement != null) {
                try {
                    sqlStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static GameReplayData loadGameByID(Connection conn, Statement sqlStatement, int gameID) throws SQLException {
        ResultSet resultSet = sqlStatement.executeQuery("SELECT gameMode, boardSize, winner FROM GameReplays WHERE gameID = " + gameID);

        if (resultSet.next() == false) {
            System.err.println("Game not found: " + gameID);
            resultSet.close();
            return null;
        }

        String gameMode = resultSet.getString("gameMode");
        int boardSize = resultSet.getInt("boardSize");
        String winnerStr = resultSet.getString("winner");
        resultSet.close();

        GameBoard.activeTurn winner = null;
        if (winnerStr != null) {
            winner = GameBoard.activeTurn.valueOf(winnerStr);
        }

        List<Move> moves = loadMoves(conn, sqlStatement, gameID);

        return new GameReplayData(gameID, gameMode, boardSize, winner, moves);
    }

    private static List<Move> loadMoves(Connection conn, Statement sqlStatement, int gameID) throws SQLException {
        List<Move> moves = new java.util.ArrayList<Move>();

        ResultSet resultSet = sqlStatement.executeQuery("SELECT moveSequence, row, col, letter, username FROM Moves WHERE gameID = "
                + gameID + " ORDER BY moveSequence");

        while (resultSet.next()) {
            int sequence = resultSet.getInt("moveSequence");
            int row = resultSet.getInt("row");
            int col = resultSet.getInt("col");
            String letter = resultSet.getString("letter");
            String username = resultSet.getString("username");

            GameBoard.activeTurn player = GameBoard.activeTurn.valueOf(username);

            moves.add(new Move(row, col, letter, player, sequence));
        }
        resultSet.close();

        return moves;
    }

    public static class GameReplayData {
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
}