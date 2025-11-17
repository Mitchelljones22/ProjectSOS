package Sprint_04.product;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.GREEN;

public class MainUI extends Application {
    public static final int screenWidth = 500;
    public static final int screenHeight = 425;
    public static final int centerWidth = screenWidth / 2;
    public static final int centerHeight = screenHeight / 2;

    private GameController gameController;

    @Override
    public void start(Stage mainStage) {
        Pane rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: #006400;");

        Text mainTextField = new Text("Project SOS");
        mainTextField.setStyle(
                "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 24px;"
                        + "-fx-fill: #E1AD01;"
        );

        gameController = new GameController();
        GridPane boardGridPane = new GridPane();

        //VBox and HBox creation
        VBox centerVBox = new VBox();
        VBox leftPanelVBox = new VBox();
        VBox rightPanelVBox = new VBox();
        VBox headerVBox = new VBox();
        VBox buttonVBox = new VBox();
        HBox bottomHBox = new HBox();
        HBox topPanelHBox = new HBox();

        //Dropdown box for board size
        ComboBox<Integer> boardSizeComboBox = new ComboBox<Integer>();
        boardSizeComboBox.getItems().addAll(3, 4, 5, 6, 7, 8, 9, 10);
        boardSizeComboBox.setStyle(
                "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 14px;"
                        + "-fx-fill: #E1AD01;"
        );
        boardSizeComboBox.setTranslateY(-3);
        boardSizeComboBox.setValue(3);

        //Text label next to dropdown
        Text boardSizeLabel = new Text("Board Size");
        boardSizeLabel.setStyle(
                "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 16px;"
                        + "-fx-fill: #E1AD01;"
        );
        boardSizeLabel.setTranslateY(3);

        // Top Panel HBox contains Title and Game settings
        topPanelHBox.setAlignment(Pos.TOP_CENTER);
        topPanelHBox.setPadding(new Insets(15));
        topPanelHBox.setSpacing(15);

        //Text at the bottom of the screen displaying turn and game mode
        Text statusTXT = new Text("Status: ");
        updateStatusText(statusTXT);
        statusTXT.setStyle(
                "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 16px;"
                        + "-fx-fill: #E1AD01;");

        bottomHBox.getChildren().addAll(statusTXT);
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);

        RadioButton simpleGameButton = new RadioButton("Simple Game");
        RadioButton generalGameButton = new RadioButton("General Game");
        simpleGameButton.setStyle(
                "-fx-text-fill: #E1AD01;"
                        + "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 14px;"
        );
        generalGameButton.setStyle(
                "-fx-text-fill: #E1AD01;"
                        + "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 14px;"
        );
        simpleGameButton.setSelected(true);

        ToggleGroup gameModeToggleGroup = new ToggleGroup();
        simpleGameButton.setToggleGroup(gameModeToggleGroup);
        generalGameButton.setToggleGroup(gameModeToggleGroup);


        topPanelHBox.getChildren().addAll(simpleGameButton, generalGameButton, boardSizeLabel, boardSizeComboBox);

        //Player One label
        Text playerOneLabel = new Text("Blue Player");
        playerOneLabel.setStyle(
                "-fx-fill: #0137f8;"
                        + "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 16px;"
        );

        //Player Two label
        Text playerTwoLabel = new Text("Red Player");
        playerTwoLabel.setStyle(
                "-fx-fill: #8e0d0d;"
                        + "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 16px;"
        );

        //Player One Human or Computer buttons
        ToggleGroup playerOneComputerToggleGroup = new ToggleGroup();
        RadioButton playerOneHumanRadioButton = new RadioButton("Human");
        RadioButton playerOneComputerRadioButton = new RadioButton("Computer");
        playerOneHumanRadioButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneComputerRadioButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneHumanRadioButton.setToggleGroup(playerOneComputerToggleGroup);
        playerOneComputerRadioButton.setToggleGroup(playerOneComputerToggleGroup);
        playerOneHumanRadioButton.setSelected(true);

        //Player Two Human or Computer buttons
        ToggleGroup playerTwoComputerToggleGroup = new ToggleGroup();
        RadioButton playerTwoHumanRadioButton = new RadioButton("Human");
        RadioButton playerTwoComputerRadioButton = new RadioButton("Computer");
        playerTwoHumanRadioButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoComputerRadioButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoHumanRadioButton.setToggleGroup(playerTwoComputerToggleGroup);
        playerTwoComputerRadioButton.setToggleGroup(playerTwoComputerToggleGroup);
        playerTwoHumanRadioButton.setSelected(true);
        playerTwoComputerRadioButton.setTranslateX(15);


        //Player One radio buttons
        ToggleGroup playerOneGroup = new ToggleGroup();
        RadioButton playerOneSButton = new RadioButton("S");
        RadioButton playerOneOButton = new RadioButton("O");
        playerOneSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneOButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneSButton.setToggleGroup(playerOneGroup);
        playerOneOButton.setToggleGroup(playerOneGroup);
        playerOneSButton.setSelected(true);
        playerOneSButton.setTranslateX(20);
        playerOneOButton.setTranslateX(20);

        //Player Two radio buttons
        ToggleGroup playerTwoGroup = new ToggleGroup();
        RadioButton playerTwoSButton = new RadioButton("S");
        RadioButton playerTwoOButton = new RadioButton("O");
        playerTwoSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoOButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoSButton.setToggleGroup(playerTwoGroup);
        playerTwoOButton.setToggleGroup(playerTwoGroup);
        playerTwoSButton.setSelected(true);
        playerTwoSButton.setTranslateX(-10);
        playerTwoOButton.setTranslateX(-8);


        CheckBox recordCheckBox = new CheckBox("Record Game");
        recordCheckBox.setStyle("-fx-text-fill: #E1AD01;");

        //Button Creation
        Button newGameButton = new Button("New Game");
        Button replayButton = new Button("Replay");
        newGameButton.setStyle(
                "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 14px;"
        );
        newGameButton.setPrefSize(90,10);

        replayButton.setStyle(
                "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 14px;"
        );
        replayButton.setPrefSize(90,10);

        newGameButton.setOnAction(event -> {
            int boardSize = boardSizeComboBox.getValue();
            GameBoard.gameMode gameMode;
            if (simpleGameButton.isSelected()) {
                gameMode = GameBoard.gameMode.Simple;
            } else {
                gameMode = GameBoard.gameMode.General;
            }
            gameController.startNewGame(gameMode, boardSize);

            buildBoard(boardGridPane, playerOneSButton, playerOneOButton,  playerTwoSButton, playerTwoOButton,
                    playerOneHumanRadioButton, playerOneComputerRadioButton,
                    playerTwoHumanRadioButton, playerTwoComputerRadioButton,
                    playerOneLabel, playerTwoLabel, statusTXT, rootPane);

        });

        buttonVBox.getChildren().addAll(replayButton, newGameButton);
        buttonVBox.setAlignment(Pos.CENTER);
        buttonVBox.setSpacing(10);
        buttonVBox.layoutXProperty().bind(
                rootPane.widthProperty().subtract(100)
        );
        buttonVBox.layoutYProperty().bind(
                rootPane.heightProperty().subtract(100)
        );


        leftPanelVBox.getChildren().addAll(playerOneLabel,playerOneHumanRadioButton, playerOneSButton, playerOneOButton, playerOneComputerRadioButton, recordCheckBox);
        leftPanelVBox.setAlignment(Pos.CENTER_LEFT);
        leftPanelVBox.setSpacing(10);
        leftPanelVBox.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(leftPanelVBox.heightProperty().divide(2))
        );
        leftPanelVBox.setLayoutX(25);

        rightPanelVBox.getChildren().addAll(playerTwoLabel, playerTwoHumanRadioButton, playerTwoSButton, playerTwoOButton, playerTwoComputerRadioButton);
        rightPanelVBox.setAlignment(Pos.CENTER_RIGHT);
        rightPanelVBox.setSpacing(10);
        rightPanelVBox.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(rightPanelVBox.heightProperty().divide(2)).subtract(20)
        );
        rightPanelVBox.layoutXProperty().bind(
                rootPane.widthProperty().subtract(rightPanelVBox.widthProperty()).subtract(50)
        );

        headerVBox.getChildren().addAll(mainTextField, topPanelHBox);
        headerVBox.setAlignment(Pos.CENTER);

        //binds to center of screen dynamically
        headerVBox.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(headerVBox.widthProperty().divide(2))
        );
        headerVBox.setLayoutY(25);

        bottomHBox.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(bottomHBox.widthProperty().divide(2))
        );
        bottomHBox.layoutYProperty().bind(rootPane.heightProperty().subtract(50));

        boardGridPane.setAlignment(Pos.CENTER);
        boardGridPane.setHgap(2);
        boardGridPane.setVgap(2);

        // Build the initial board
        buildBoard(boardGridPane, playerOneSButton, playerOneOButton,  playerTwoSButton, playerTwoOButton,
                playerOneHumanRadioButton, playerOneComputerRadioButton,
                playerTwoHumanRadioButton, playerTwoComputerRadioButton,
                playerOneLabel, playerTwoLabel, statusTXT, rootPane);


        // Position the board in the center
        boardGridPane.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(boardGridPane.widthProperty().divide(2))
        );
        boardGridPane.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(boardGridPane.heightProperty().divide(2))
        );

        rootPane.getChildren().addAll(headerVBox, leftPanelVBox, rightPanelVBox, boardGridPane, bottomHBox, buttonVBox);

        Scene mainScene = new Scene(rootPane, screenWidth, screenHeight);
        mainScene.setFill(GREEN);

        mainStage.setTitle("Project SOS");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    private void buildBoard(GridPane gridPane,
                            RadioButton p1S, RadioButton p1O,
                            RadioButton p2S, RadioButton p2O,
                            RadioButton p1Human, RadioButton p1Computer,
                            RadioButton p2Human, RadioButton p2Computer,
                            Text playerOneLabel, Text playerTwoLabel,
                            Text statusText,Pane rootPane) {

        gridPane.getChildren().clear();

        GameBoard board = gameController.getBoard();
        int boardSize = board.getBoardSize();

        playerOneLabel.setStyle(
                "-fx-fill: #0137f8; "
                        + "-fx-font-family: 'High Tower Text'; "
                        + "-fx-font-size: 16px;"
        );

        playerTwoLabel.setStyle(
                "-fx-fill: #8e0d0d;"
                        + "-fx-font-family: 'High Tower Text';"
                        + "-fx-font-size: 16px;"
        );

        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                final int row = x;
                final int col = y;

                BoardTile tile = board.getCell(row, col);
                tile.setPrefSize(60, 60);

                tile.setOnMouseClicked(e -> {
                    if (gameController.isGameOver()) {
                        return;
                    }

                    String selectedLetter;

                    if (gameController.getCurrentTurn() == GameBoard.activeTurn.Player_One) {
                        playerTwoLabel.setStyle(
                                "-fx-fill: #ff1a1a; "
                                        + "-fx-font-family: 'High Tower Text'; "
                                        + "-fx-font-size: 16px;"
                        );

                        playerOneLabel.setStyle(
                                "-fx-fill: rgba(12,7,165,0.85);"
                                        + "-fx-font-family: 'High Tower Text';"
                                        + "-fx-font-size: 16px;"
                        );

                        if (p1S.isSelected()) {
                            selectedLetter = "S";
                        } else {
                            selectedLetter = "O";
                        }
                    } else {
                        playerOneLabel.setStyle(
                                "-fx-fill: #0137f8; "
                                        + "-fx-font-family: 'High Tower Text'; "
                                        + "-fx-font-size: 16px;"
                        );

                        playerTwoLabel.setStyle(
                                "-fx-fill: #8e0d0d;"
                                        + "-fx-font-family: 'High Tower Text';"
                                        + "-fx-font-size: 16px;"
                        );

                        if (p2S.isSelected()) {
                            selectedLetter = "S";
                        } else {
                            selectedLetter = "O";
                        }
                    }

                    boolean moveIsSuccess = gameController.handleMove(row, col, selectedLetter);

                    if (moveIsSuccess) {
                        updateStatusText(statusText);
                        drawSOSLines(gridPane, rootPane);  // Pass rootPane
                    }
                });
                gridPane.add(tile, col, row);
            }
        }
        drawSOSLines(gridPane, rootPane);  // Pass rootPane
        updateStatusText(statusText);
    }


    private void updateStatusText(Text statusText) {
        GameInformation state = gameController.getGameInformation();
        SOSGame currentGame = gameController.getCurrentGame();

        StringBuilder status = new StringBuilder();

        if (gameController.isGameOver()) {
            GameBoard.activeTurn winner = gameController.getWinner();
            if (winner == null) {
                status.append("Game Over - Draw!");
            } else {
                status.append("Game Over - ").append(winner).append(" Wins!");
            }
        }
        else // Game is still in progress
        {
            status.append("Status: ").append(gameController.getCurrentTurn());

            if (currentGame instanceof SimpleGame) {
                status.append("   Mode: Simple");
            } else if (currentGame instanceof GeneralGame) {
                status.append("   Mode: General");
                status.append("   P1: ").append(state.getPlayerOneScore());
                status.append("   P2: ").append(state.getPlayerTwoScore());
            }
        }

        statusText.setText(status.toString());
    }


    public void drawSOSLines(GridPane gridPane, Pane rootPane) {
        // Remove old lines from rootPane
        rootPane.getChildren().removeIf(node -> node instanceof Line);

        List<SOSLine> lineList = gameController.getGameInformation().getSOSLines();

        // Get GridPane's position on screen
        double gridX = gridPane.getLayoutX();
        double gridY = gridPane.getLayoutY();

        for (SOSLine sosLine : lineList) {
            int tileSize = 60;
            int gap = 2;

            // Calculate line positions relative to GridPane
            double startX = sosLine.getStartCol() * (tileSize + gap) + tileSize / 2;
            double startY = sosLine.getStartRow() * (tileSize + gap) + tileSize / 2;
            double endX = sosLine.getEndCol() * (tileSize + gap) + tileSize / 2;
            double endY = sosLine.getEndRow() * (tileSize + gap) + tileSize / 2;

            // Add GridPane offset to draw in correct position on rootPane
            Line line = new Line(
                    startX + gridX,
                    startY + gridY,
                    endX + gridX,
                    endY + gridY
            );
            line.setStroke(sosLine.getColor());
            line.setStrokeWidth(3);

            // Add to rootPane, NOT gridPane!
            rootPane.getChildren().add(line);
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}


