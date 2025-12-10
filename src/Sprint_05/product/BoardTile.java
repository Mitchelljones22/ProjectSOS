package Sprint_05.product;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardTile extends StackPane {
    private Text letter;
    private boolean isEmpty;
    private int xPos;
    private int yPos;

    public BoardTile(int xPos, int yPos) {

        setStyle("-fx-border-color: white");
        this.setPrefSize(60, 60);
        this.setAlignment(Pos.CENTER);

        letter = new Text("");
        letter.setFont(Font.font("Arial", 30));
        letter.setStyle("-fx-fill: white;");

        isEmpty = true;

        this.xPos = xPos;
        this.yPos = yPos;

        this.getChildren().add(letter);
    }


    /**
     * Set the letter on this tile
     * @param letterText "S" or "O"
     */
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

    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }

    public void clear() {
        letter.setText("");
        isEmpty = true;
        setStyle("-fx-border-color: white; -fx-background-color: transparent;");
    }
}
