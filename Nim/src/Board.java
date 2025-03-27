public class Board {
    private static int numPieces;

    public static void populate() {
        numPieces = (int)(Math.random() * 40) + 10;
    }

    public static void takePieces(int num) {
        numPieces -= num;
    }

    public static int getNumPieces() {
        return numPieces;
    }
}