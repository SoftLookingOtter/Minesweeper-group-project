package se.team.minesweeper;

//Board represents entire game board with rows, column & cell
public class Board {
    //Number of rows
    private final int rows;
    //Number of columns
    private final int cols;
    //Number of mines
    private final int mineCount;
    //2-D array with all cells
    private final Cell[][] grid;

//Creates a board with the specified dimensions and number of mines
    public Board(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        grid = new Cell[rows][cols];
        //Initialize the board directly upon creation
        init();
    }
//Initialize all cells and place mines randomly
    private void init() {
        //Creating empty cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell();
            }
        }
        //Lay out mines
        placeMines();
        //Count how many mines are next to each square
        calculateAdjacents();
    }

    //Random placement of mines on the board
    private void placeMines() {
        Random rand = new Random();
        int placed = 0;
        while (placed < mineCount) {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);
            if (!grid[r][c].isMine()) {
                //Only if there is not already a mine
                grid[r][c].setMine(true);
                placed++;
            }
        }
    }

    //Count mines around each cell
    private void calculateAdjacents() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!grid[r][c].isMine()) {
                    grid[r][c].setAdjacentMines(countAdjacentMines(r, c));
                }
            }
        }
    }

    //Helping method for counting mines around a cell
    private int countAdjacentMines(int r, int c) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nr = r + i, nc = c + j;
                //Check that we don't go outside the board
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    //Reveal a cell - returns false if it was a mine
    public boolean revealCell(int r, int c) {
        //If the coordinates are outside the board do nothing
        if (r < 0 || r >= rows || c < 0 || c >= cols) return false;
        Cell cell = grid[r][c];
        //Already revealed, skip
        if (cell.isRevealed()) return true;
        //Mark as revealed
        cell.setRevealed(true);

        //If we hit a mine, Game Over!!!

        if (cell.isMine()) return false;

        if (cell.getAdjacentMines() == 0) {
          //Reveal empty cells
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revealCell(r + i, c + j);
                }
            }
        }
        return true;
    }

    //Checking if all all safe(non-mine) cells are revealed
    public boolean allSafeCellsRevealed() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!grid[r][c].isMine() && !grid[r][c].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    //Printing the board terminal
    //Show-mines = true Show all mines (Used at Game Over)

    public void printBoard(boolean showMines) {
        //Print Column number
        System.out.print("   ");
        for (int c = 0; c < cols; c++) System.out.print(c + " ");
        System.out.println();
        //Print out all rows
        for (int r = 0; r < rows; r++) {
            //Row number
            System.out.printf("%2d ", r);
            for (int c = 0; c < cols; c++) {
                if (showMines && grid[r][c].isMine()) {
                    System.out.print("* ");
                } else {
                    System.out.print(grid[r][c] + " ");
                }
            }
            System.out.println();
        }
    }
}
