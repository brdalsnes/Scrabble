package sample;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Square extends StackPane {
    //Location in grid
    private int x;
    private int y;

    private Bonus bonus;
    private Tile tile = null;
    private boolean playable = false;
    private Rectangle view = new Rectangle(Board.SQUARE_SIZE, Board.SQUARE_SIZE);
    private Text text = new Text();

    public Square(int x, int y){
        this.x = x;
        this.y = y;

        //Sets random bonus
        double bonusDecider = Math.random();
        if(bonusDecider < 0.82){
            bonus = Bonus.N;
            view.setFill(Color.GAINSBORO);
        }else if(bonusDecider >= 0.82 && bonusDecider < 0.88){
            bonus = Bonus.DL;
            view.setFill(Color.DODGERBLUE);
            text.setText("2L");
        }else if(bonusDecider >= 0.88 && bonusDecider < 0.94) {
            bonus = Bonus.DW;
            view.setFill(Color.FIREBRICK);
            text.setText("2W");
        }else if(bonusDecider >= 0.94 && bonusDecider < 0.97){
            bonus = Bonus.TL;
            view.setFill(Color.FORESTGREEN);
            text.setText("3L");
        }else if(bonusDecider >= 0.97){
            bonus = Bonus.TW;
            view.setFill(Color.GOLDENROD);
            text.setText("3W");
        }

        getChildren().addAll(view, text);

        setOnDragOver(event -> handleDragOver(event));
        setOnDragDropped(event -> handleDragDropped(event));
    }

    private void handleDragOver(DragEvent event){
        if(playable) {
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        }
    }

    private void handleDragDropped(DragEvent event){
        Tile tile = (Tile) event.getGestureSource();
        setTile(tile);
        this.getChildren().add(tile);
    }

    public void startSquare(){
        playable = true;
        bonus = Bonus.N;
        view.setFill(Color.DARKRED);
        text.setText("START");
        text.setFill(Color.GAINSBORO);
        text.setFont(Font.font(14));
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        this.playable = false;
    }

    public Tile getTile() {
        return tile;
    }

    public void setPlayable(boolean playable) {
        if(tile == null) {
            this.playable = playable;
        }
    }

    public Bonus getBonus() {
        return bonus;
    }

    private enum Bonus{
        N, DL, TL, DW, TW
    }

    private class ObservableTile{

    }
}


