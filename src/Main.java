import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class Main extends Application {
    public static final int screenWidth = 400;
    public static final int screenHeight = 300;
    public static final int centerWidth = screenWidth / 2;
    public static final int centerHeight = screenHeight / 2;

    @Override
    public void start(Stage mainStage) {
        Pane rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: #006400;");

        Text mainTextField = new Text("Project SOS");
        mainTextField.setStyle("-fx-font-family: 'High Tower Text';"
        + "-fx-font-size: 18px;"
        + "-fx-fill: #E1AD01;");

        VBox centerVBox = new VBox();
        VBox leftPanelVBox = new VBox();
        VBox rightPanelVBox = new VBox();
        HBox headerHBox = new HBox();


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


        headerHBox.getChildren().add(mainTextField);
        headerHBox.setAlignment(Pos.CENTER);

        //binds to center of screen dynamically
        headerHBox.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(headerHBox.widthProperty().divide(2))
        );
        headerHBox.setLayoutY(25);


        GameBoard boardMain = new GameBoard();
        GridPane boardGridPane = new GridPane();
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

        rootPane.getChildren().addAll(headerHBox, leftPanelVBox, rightPanelVBox, boardGridPane);

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