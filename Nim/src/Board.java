public class Board {
    private static int numPieces;

    public static void populate() {
        numPieces = 15;  // Default number of pieces
    }

    public static int getNumPieces() {
        return numPieces;
    }

    public static void removePieces(int num) {
        if (numPieces >= num) {
            numPieces -= num;
        }
    }
}
