package se.team.minesweeper;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    private static final Pattern MOVE_PATTERN = Pattern.compile("^(\\d{1,3})\\s*([A-Za-z])$");


    public int[] makeMove(int rows, int cols) {
        while (true) {
            System.out.print("Ange drag (t.ex. 3 C): ");
            String line = scanner.nextLine();
            if (line == null) continue;

            line = line.trim()
                    .replace(',', ' ')
                    .replaceAll("\\s+", " ")
                    .toUpperCase(Locale.ROOT);

            Matcher m = MOVE_PATTERN.matcher(line);
            if (!m.matches()) {
                System.out.println("Fel format. Skriv t.ex. 3 C");
                continue;
            }

            int rowHuman = Integer.parseInt(m.group(1)); // 1..rows
            char colChar  = m.group(2).charAt(0);        // A..Z
            int row = rowHuman - 1;                      // 0..rows-1
            int col = colChar - 'A';                     // 0..cols-1

            if (row < 0 || row >= rows) {
                System.out.println("Ogiltig rad. Tillåtna rader: 1.." + rows);
                continue;
            }
            if (col < 0 || col >= cols) {
                System.out.println("Ogiltig kolumn. Tillåtna kolumner: A.." + (char)('A' + cols - 1));
                continue;
            }

            return new int[]{row, col};
        }
    }
}