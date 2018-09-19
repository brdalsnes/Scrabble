package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;



public class Controller {

    @FXML
    private BorderPane pane;
    @FXML
    private GridPane gridView;
    @FXML
    private HBox letterRow;
    @FXML
    private Button playButton;

    private Board board = new Board();

    public void initialize(){
        letterRow.setStyle("-fx-border-color: black");
        Square[][] grid = board.getGrid();

        //Places board in view
        for(int x = 0; x < Board.X_TILES; x++){
            for(int y = 0; y < Board.Y_TILES; y++){
                gridView.add(grid[x][y], x, y);
            }
        }

        //Fill letter row
        board.fillRow();
        setRowView();

        //Play button
        playButton.setMinWidth(4*Board.SQUARE_SIZE);
        playButton.setMinHeight(Board.SQUARE_SIZE);


    }

    @FXML
    public void handleDragDroppedOnGrid(DragEvent event){
        board.removeTileFromRow((Tile)event.getGestureSource());
        board.setNeighborsAsPlayable();
        event.consume();
    }

    @FXML
    public void handleDragDroppedOnRow(DragEvent event){
        board.addTileToRow((Tile)event.getGestureSource());
        event.consume();
        setRowView();
    }

    @FXML
    public void handleDragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.ANY);
        event.consume();
    }

    @FXML
    public void handlePlayClick(){
        board.fillRow();
        setRowView();
        board.lockTiles();
    }

    private void setRowView(){
        //Sets tiles from the row model into the view
        for(Tile tile : board.getRow()){
            if(!letterRow.getChildren().contains(tile)) { //Only adds new tiles
                letterRow.getChildren().add(tile);
            }
        }
    }



}
