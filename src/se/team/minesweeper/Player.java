package se.team.minesweeper;

import java.util.Scanner;
//Player handles player input(which cell to reveal)
public class Player {
    private final Scanner scanner = new Scanner(System.in);

    //Asking the player after row & column and returns them as an array
    public int[] makeMove() {
        System.out.print("Ange rad och kolumn (t.ex. '2 3'): ");
        //Row
        int r = scanner.nextInt();
        //Column
        int c = scanner.nextInt();
        return new int[]{r, c};
    }
}
