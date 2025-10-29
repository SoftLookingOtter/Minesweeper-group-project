package se.team.minesweeper;

//Game is responsible for the game's logic and order(game-loop)
public class Game {
    //The game board
    private final Board board;
    //Player
    private final Player player;
    //True if game ends
    private boolean gameOver;

    //Creates a new game with the specified dimensions & number of mines
    public Game(int rows, int cols, int mines) {
        board = new Board(rows, cols, mines);
        player = new Player();
        gameOver = false;
    }

    //Main-loop for Game
    public void start() {
        System.out.println("💣 Välkommen till Minröj!!");
        while (!gameOver) {
            //Shows actual board without mines
            board.printBoard(false);
            //Let the player make a move
            int[] move = player.makeMove(board.getRows(), board.getCols());
            //Try to reveal the cell if false is returned, the player hit a mine
            boolean safe = board.revealCell(move[0], move[1]);

            //If it's mine

            if (!safe) {
                System.out.println("💥 Du träffade en mina! Spelet över!");
                //Show all mines
                board.printBoard(true);
                gameOver = true;

            }
            //If Player wins
            else if (board.allSafeCellsRevealed()) {
                System.out.println("🎉 Grattis, du har rensat brädet!");
                board.printBoard(true);
                gameOver = true;
            }
        }
    }
}