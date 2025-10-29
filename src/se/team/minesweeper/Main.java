package se.team.minesweeper;

//Creating a new Game-object & starts the game.
public class Main {
    public static void main(String[] args) {
        //Creating a new game : 8x8-board with 10 mines
        Game game = new Game(6, 6, 8);

        //Start the game
        game.start();
    }
}
