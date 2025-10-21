package Sprint_02;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

        GameBoard boardMain = new GameBoard();
        GridPane boardGridPane = new GridPane();

        //VBox and HBox creation
        VBox centerVBox = new VBox();
        VBox leftPanelVBox = new VBox();
        VBox rightPanelVBox = new VBox();
        VBox headerVBox = new VBox();
        VBox bottomVBox = new VBox();
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

        bottomVBox.getChildren().addAll(statusTXT);
        bottomVBox.setAlignment(Pos.BOTTOM_CENTER);


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

        simpleGameButton.setOnAction(e -> {
            if(simpleGameButton.isSelected()) {
                generalGameButton.setSelected(false);
            }
            else{
                simpleGameButton.setSelected(true);
            }
        });

        generalGameButton.setOnAction(e -> {
            if(generalGameButton.isSelected()) {
                simpleGameButton.setSelected(false);
            }
            else{
                generalGameButton.setSelected(true);
            }
        });
        
        topPanelHBox.getChildren().addAll(simpleGameButton, generalGameButton, boardSizeLabel, boardSizeComboBox);




        RadioButton playerOneSButton = new RadioButton("S");
        RadioButton playerOneOButton = new RadioButton("O");
        playerOneSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneOButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneSButton.setSelected(true);

        playerOneSButton.setOnAction(e -> {
            if (playerOneSButton.isSelected()) {
                playerOneOButton.setSelected(false);
            }
            else{
                playerOneSButton.setSelected(true);
            }
        });

        playerOneOButton.setOnAction(e -> {
            if (playerOneOButton.isSelected()) {
                playerOneSButton.setSelected(false);
            }
            else{
                playerOneOButton.setSelected(true);
            }
        });

        RadioButton playerTwoSButton = new RadioButton("S");
        RadioButton playerTwoOButton = new RadioButton("O");
        playerTwoSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoOButton.setStyle("-fx-text-fill: #E1AD01;");
        playerTwoSButton.setSelected(true);

        playerTwoSButton.setOnAction(e -> {
            if (playerTwoSButton.isSelected()) {
                playerTwoOButton.setSelected(false);
            }
            else{
                playerTwoSButton.setSelected(true);
            }
        });

        playerTwoOButton.setOnAction(e -> {
            if (playerTwoOButton.isSelected()) {
                playerTwoSButton.setSelected(false);
            }
            else{
                playerTwoOButton.setSelected(true);
            }
        });

        CheckBox recordCheckBox = new CheckBox("Record Game");
        recordCheckBox.setStyle("-fx-text-fill: #E1AD01;");

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

        bottomVBox.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(bottomVBox.widthProperty().divide(2))
        );
        bottomVBox.layoutYProperty().bind(rootPane.heightProperty().subtract(50));

        boardGridPane.setAlignment(Pos.CENTER);
        boardGridPane.setHgap(2);
        boardGridPane.setVgap(2);

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                BoardTile tile = boardMain.getCell(x, y);
                tile.setPrefSize(60, 60);

                tile.setOnMouseClicked(e -> {
                    String selectedLetter = playerOneSButton.isSelected() ? "S" : "O";
                    GameBoard.activeTurn currentTurn = boardMain.getTurn();

                    tile.handleMouseClick(selectedLetter, currentTurn);
                    boardMain.switchTurn();
                });

                boardGridPane.add(tile, y, x);
            }
        }

        // Position the board in the center
        boardGridPane.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(boardGridPane.widthProperty().divide(2))
        );
        boardGridPane.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(boardGridPane.heightProperty().divide(2))
        );

        rootPane.getChildren().addAll(headerVBox, leftPanelVBox, rightPanelVBox, boardGridPane, bottomVBox);

        Scene mainScene = new Scene(rootPane, screenWidth, screenHeight);
        mainScene.setFill(GREEN);

        mainStage.setTitle("Project SOS");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}