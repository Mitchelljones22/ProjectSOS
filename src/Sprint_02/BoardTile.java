package Sprint_02;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class BoardTile extends StackPane {
    private Text letter;
    private boolean isEmpty;
    public BoardTile(){
        setStyle("-fx-border-color: white");
        this.setPrefSize(2000, 2000);
        this.setPrefSize(60, 60);
        this.setAlignment(Pos.CENTER);

        letter = new Text("");
        letter.setFont(Font.font("Arial", 30));
        letter.setStyle("-fx-fill: white;");

        isEmpty = true;
        this.getChildren().add(letter);
    }

    public void handleMouseClick(String letterToPlace, GameBoard.activeTurn currentTurn) {
        if (isEmpty) {
            setLetter(letterToPlace);

            if (currentTurn == GameBoard.activeTurn.Player_One) {
                setStyle("-fx-border-color: white; -fx-background-color: Blue;");
            } else {
                setStyle("-fx-border-color: white; -fx-background-color: Red;");
            }
        }
    }
    public void setLetter(String letterText) {
        letter.setText(letterText);
        isEmpty = false;
    }

    public String getLetter() {
        return letter.getText();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void clear() {
        letter.setText("");
        isEmpty = true;
        setStyle("-fx-border-color: white; -fx-background-color: transparent;");
    }
}
