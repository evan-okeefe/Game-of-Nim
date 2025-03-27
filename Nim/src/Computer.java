public class Computer extends Player {
    public Computer(String _name) {
        super(_name);
    }


    public void takeTurn() {
        int piecesToTake = calculateMove();
        nim(piecesToTake);
    }

    private int calculateMove() {
        if (Board.getNumPieces() == 1) {
            return 1;
        }

        int losingPosition = 1;
        while ((losingPosition * 2 + 1) <= Board.getNumPieces()) {
            losingPosition = losingPosition * 2 + 1;
        }

        for (int i = 1; i <= Board.getNumPieces() / 2; i++) {
            if ((Board.getNumPieces() - i) == losingPosition) {
                return i;
            }
        }

        return 1;
    }
}