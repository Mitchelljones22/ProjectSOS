package Sprint_02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class Main extends Application {
    public static final int screenWidth = 500;
    public static final int screenHeight = 425;
    public static final int centerWidth = screenWidth / 2;
    public static final int centerHeight = screenHeight / 2;
    private GameBoard boardMain;

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

        boardMain = new GameBoard();
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
        Text statusTXT = new Text("Status: " + boardMain.getTurn());
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

        //Player One radio buttons
        ToggleGroup playerOneGroup = new ToggleGroup();
        RadioButton playerOneSButton = new RadioButton("S");
        RadioButton playerOneOButton = new RadioButton("O");
        playerOneSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneOButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneSButton.setToggleGroup(playerOneGroup);
        playerOneOButton.setToggleGroup(playerOneGroup);
        playerOneSButton.setSelected(true);

        //Player Two radio buttons
        ToggleGroup playerTwoGroup = new ToggleGroup();
        RadioButton playerTwoSButton = new RadioButton("S");
        RadioButton playerTwoOButton = new RadioButton("O");
        playerTwoSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoOButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoSButton.setToggleGroup(playerTwoGroup);
        playerTwoOButton.setToggleGroup(playerTwoGroup);
        playerTwoSButton.setSelected(true);

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
            //Get selected settings from UI
            int boardSize = boardSizeComboBox.getValue();
            GameBoard.gameMode mode;
            if (simpleGameButton.isSelected()) {
                mode = GameBoard.gameMode.Simple;
            } else {
                mode = GameBoard.gameMode.General;
            }

            // Create new board with selected settings
            boardMain = new GameBoard(boardSize, GameBoard.activeTurn.Player_One, mode);

            // Rebuild the UI with new board
            buildBoard(boardMain, boardGridPane, playerOneSButton, playerOneOButton,
                    playerTwoSButton, playerTwoOButton, statusTXT);
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


        leftPanelVBox.getChildren().addAll(playerOneSButton, playerOneOButton, recordCheckBox);
        leftPanelVBox.setAlignment(Pos.CENTER_LEFT);
        leftPanelVBox.setSpacing(10);
        leftPanelVBox.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(leftPanelVBox.heightProperty().divide(2))
        );
        leftPanelVBox.setLayoutX(25);

        rightPanelVBox.getChildren().addAll(playerTwoSButton, playerTwoOButton);
        rightPanelVBox.setAlignment(Pos.CENTER_RIGHT);
        rightPanelVBox.setSpacing(10);
        rightPanelVBox.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(rightPanelVBox.heightProperty().divide(2)).subtract(20)
        );
        rightPanelVBox.layoutXProperty().bind(
                rootPane.widthProperty().subtract(rightPanelVBox.widthProperty()).subtract(25)
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
        buildBoard(boardMain, boardGridPane, playerOneSButton, playerOneOButton,
                playerTwoSButton, playerTwoOButton, statusTXT);


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

    /**
     *  Used to initialize board upon new game
     * @param board boardMain - the game one screen
     * @param gridPane boardGridPane - the pane that contains the tiles
     * @param p1S playerOneSButton - Radio Button
     * @param p1O playerOneOButton - Radio Button
     * @param p2S playerTwoSButton - Radio Button
     * @param p2O playerOneTwoButton - Radio Button
     * @param statusText displays the turn
     */
    private void buildBoard(GameBoard board, GridPane gridPane,
                            RadioButton p1S, RadioButton p1O,
                            RadioButton p2S, RadioButton p2O,
                            Text statusText) {
        gridPane.getChildren().clear();
        int boardSize = board.getBoardSize();

        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                BoardTile tile = board.getCell(x, y);
                tile.setPrefSize(60, 60);

                tile.setOnMouseClicked(e -> {
                    String selectedLetter;
                    if (board.getTurn() == GameBoard.activeTurn.Player_One) {
                        //Player One's turn
                        if (p1S.isSelected()) {
                            selectedLetter = "S";
                        } else {
                            selectedLetter = "O";
                        }
                    }
                    else { //Player Two's turn
                        if (p2S.isSelected()) {
                            selectedLetter = "S";
                        } else {
                            selectedLetter = "O";
                        }
                    }
                    GameBoard.activeTurn currentTurn = board.getTurn();

                    tile.handleMouseClick(selectedLetter, currentTurn);
                    board.switchTurn();
                    statusText.setText("Status: " + board.getTurn() + "   Mode: "+ board.getGameMode());
                });

                gridPane.add(tile, y, x);
            }
        }
        statusText.setText("Status: " + board.getTurn() + "   Game Mode: "+ board.getGameMode());
    }

    public static void main(String[] args) {
        launch(args);

    }
}