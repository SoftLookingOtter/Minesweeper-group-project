package se.team.minesweeper;

//Cell represents a single square on the board
//It knows if it is a mine, if it is revealed and how many mines are adjacent to it
public class Cell {
    //True if the box contains a mine
    private boolean mine;
    //True if the box is revealed
    private boolean revealed;
    //Number of mines around the box
    private int adjacentMines;

    //Contructing that sets the default values
    public Cell() {
        this.mine = false;
        this.revealed = false;
        this.adjacentMines = 0;
    }


}
