import javax.swing.*;
import java.awt.*;

public class Board {
    private static int numPieces;
    private static int columns = 4;

    public static void populate(){
        numPieces = (int)(Math.random() * 40) + 10;
    }

    public static void takePieces(int num){
        numPieces -= num;
    }

    public static void display() {
        System.out.println("╔══════════════════╗");
        System.out.println("║   Game of Nim    ║");
        System.out.println("╚══════════════════╝");

        // Display pieces in stacks (rows & columns)
        for (int i = 0; i < numPieces; i++) {
            System.out.print("($) ");
            if ((i + 1) % columns == 0 || i == numPieces - 1) {
                System.out.println(); // New row when column limit is reached
            }
        }

        System.out.println("\nTotal Pieces: " + numPieces);
    }

    public static int getNumPieces(){
        return numPieces;
    }
}
