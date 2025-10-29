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

    //Getters & Setters,

    public boolean isMine() { return mine; }
    public boolean isRevealed() { return revealed; }
    public int getAdjacentMines() { return adjacentMines; }

    public void setMine(boolean mine) { this.mine = mine; }
    public void setRevealed(boolean revealed) { this.revealed = revealed; }
    public void setAdjacentMines(int count) { this.adjacentMines = count; }

    //How the cell should be displayed when the board is printed

    @Override
    public String toString() {
        //Dot is equal to hidden box
        if (!revealed) return ".";
        //Mine
        if (mine) return "*";
        //Number or blank
        return adjacentMines == 0 ? " " : String.valueOf(adjacentMines);
    }


}
