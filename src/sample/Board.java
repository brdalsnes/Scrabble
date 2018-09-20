package sample;

import java.util.*;

public class Board{
    public final static int X_TILES = 15;
    public final static int Y_TILES = 15;
    public final static int ROW_LETTERS = 7;
    public final static int SQUARE_SIZE = 50;

    private Map<Character, Integer> letters = new HashMap<>();
    private char[] letterDistribution = "AAAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ".toCharArray();
    private List<Tile> row = new ArrayList<>();
    private Square[][] grid = new Square[X_TILES][Y_TILES];
    private List<String> words = new ArrayList<>();

    public Board(){
        //Sets the points of each letter
        initializeLetters();

        //Creates board
        for(int col = 0; col < Y_TILES; col++){
            for(int row = 0; row < X_TILES; row++){
                grid[col][row] = new Square(col,row);
            }
        }

        //Sets square where play has to start from
        grid[(X_TILES-1)/2][(Y_TILES-1)/2].startSquare();
    }

    public void fillRow(){
        int emptySlots = ROW_LETTERS - row.size();
        for(int i = 0; i < emptySlots; i++){
            row.add(drawTile());
        }
    }

    private Tile drawTile(){
        int rnd = new Random().nextInt(letterDistribution.length);
        char letter = letterDistribution[rnd];
        return new Tile(letter, letters.get(letter));
    }

    public void setNeighborsAsPlayable(){
        for(int col = 0; col < Y_TILES; col++){
            for(int row = 0; row < X_TILES; row++){
                if(grid[col][row].getTile() != null){ //If square has tile
                    if(grid[col][row-1].getTile() == null && grid[col][row+1].getTile() == null){
                        grid[col-1][row].setPlayable(true);
                        grid[col+1][row].setPlayable(true);
                    }
                    if(grid[col-1][row].getTile() == null && grid[col+1][row].getTile() == null){
                        grid[col][row-1].setPlayable(true);
                        grid[col][row+1].setPlayable(true);
                    }
                }
            }
        }
    }

    public void findWords(){
        String word = "";
        boolean activeTile = false;
        //Finds horizontal words
        for(int col = 0; col < Y_TILES; col++){
            for(int row = 0; row < X_TILES; row++){
                Tile tile = grid[col][row].getTile();
                if(tile != null) { //If square has tile
                    word += tile.getLetter();
                    if(tile.isMovable()){ //Tile has been played this round
                       activeTile = true;
                    }
                }else{
                    if(activeTile && word.length() > 1){
                        words.add(word);
                        activeTile = false;
                        System.out.println(word);
                    }
                    word = "";
                }
            }
        }
        //Finds vertical words
        for(int row = 0; row < X_TILES; row++){
            for(int col = 0; col < Y_TILES; col++){
                Tile tile = grid[col][row].getTile();
                if(tile != null) { //If square has tile
                    word += tile.getLetter();
                    if(tile.isMovable()){ //Tile has been played this round
                        activeTile = true;
                    }
                }else{
                    if(activeTile && word.length() > 1){
                        words.add(word);
                        activeTile = false;
                        System.out.println(word);
                    }
                    word = "";
                }
            }
        }
    }


    //Sets all tiles currently on grid as not movable
    public void lockTiles(){
        for(int col = 0; col < Y_TILES; col++){
            for(int row = 0; row < X_TILES; row++){
                if(grid[col][row].getTile() != null){
                    grid[col][row].getTile().setMovable(false);
                }
            }
        }

    }

    public void removeTileFromRow(Tile tile){
        row.remove(tile);
    }

    public void addTileToRow(Tile tile){
        row.add(tile);
    }

    public Square[][] getGrid() {
        return grid;
    }

    public List<Tile> getRow() {
        return row;
    }

    private void initializeLetters(){
        letters.put('A', 1);
        letters.put('B', 3);
        letters.put('C', 3);
        letters.put('D', 2);
        letters.put('E', 1);
        letters.put('F', 4);
        letters.put('G', 1);
        letters.put('H', 4);
        letters.put('I', 1);
        letters.put('J', 8);
        letters.put('K', 5);
        letters.put('L', 1);
        letters.put('M', 3);
        letters.put('N', 1);
        letters.put('O', 1);
        letters.put('P', 3);
        letters.put('Q', 10);
        letters.put('R', 1);
        letters.put('S', 1);
        letters.put('T', 1);
        letters.put('U', 1);
        letters.put('V', 4);
        letters.put('W', 4);
        letters.put('X', 8);
        letters.put('Y', 4);
        letters.put('Z', 10);
    }
}
