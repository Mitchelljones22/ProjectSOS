import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

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
        HBox headerHBox = new HBox();

        RadioButton playerOneSButton = new RadioButton("S");
        RadioButton playerOneOButton = new RadioButton("O");
        playerOneSButton.setStyle("-fx-text-fill: #E1AD01;");
        playerOneOButton.setStyle("-fx-text-fill: #E1AD01;");

        playerOneSButton.setOnAction(e -> {
            if (playerOneSButton.isSelected()) {
                playerOneOButton.setSelected(false);
                System.out.println("S selected"); // Optional: do something when S is selected
            }
        });

        playerOneOButton.setOnAction(e -> {
            if (playerOneOButton.isSelected()) {
                playerOneSButton.setSelected(false);
                System.out.println("S selected"); // Optional: do something when S is selected
            }
        });



        CheckBox recordCheckBox = new CheckBox("Record Game");
        recordCheckBox.setStyle("-fx-text-fill: #E1AD01;");

        Line newLine = new Line();
        newLine.setStartX(centerWidth / 2);
        newLine.setStartY(centerHeight / 2);
        newLine.setEndX(50);
        newLine.setEndY(50);
        newLine.setStroke(Color.BLUE);

        Line secondLine = new Line();
        secondLine.setStartX(screenWidth - (centerWidth / 2));
        secondLine.setStartY(centerHeight / 2);
        secondLine.setEndX(screenWidth - 50);
        secondLine.setEndY(50);
        newLine.setStroke(Color.RED);


        leftPanelVBox.getChildren().addAll(playerOneSButton, playerOneOButton, recordCheckBox);
        leftPanelVBox.setAlignment(Pos.CENTER_LEFT);
        leftPanelVBox.setSpacing(10);
        leftPanelVBox.layoutYProperty().bind(
                rootPane.heightProperty().divide(2).subtract(leftPanelVBox.widthProperty().divide(2))
        );
        leftPanelVBox.setLayoutX(25);

        headerHBox.getChildren().add(mainTextField);
        headerHBox.setAlignment(Pos.CENTER);

        //double widthHeaderHBox = headerHBox.getBoundsInLocal().getWidth();

        //binds to center of screen dynamically
        headerHBox.layoutXProperty().bind(
                rootPane.widthProperty().divide(2).subtract(headerHBox.widthProperty().divide(2))
        );
        headerHBox.setLayoutY(25);

        rootPane.getChildren().addAll(headerHBox, leftPanelVBox, newLine, secondLine);

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