package sample;

import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends BorderPane {

    private char letter;
    private int points;
    private boolean movable = true;

    private Text letterText = new Text();
    private Text pointsText = new Text();

    public Tile(char letter, int points){
        this.letter = letter;
        this.points = points;

        letterText.setText(String.valueOf(letter));
        pointsText.setText(String.valueOf(points));
        letterText.setFont(Font.font(32));

        setCenter(letterText);
        setRight(pointsText);
        setPadding(new Insets(0,2,0,2));
        setMinHeight(Board.SQUARE_SIZE);
        setMinWidth(Board.SQUARE_SIZE);
        setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        setOnDragDetected(event -> handleDragDetected(event));
    }

    private void handleDragDetected(MouseEvent event){
        if(movable) {
            Image tileImage = this.snapshot(new SnapshotParameters(), null);
            Dragboard db = this.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(tileImage);
            db.setContent(content);
            event.consume();
        }
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isMovable() {
        return movable;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
