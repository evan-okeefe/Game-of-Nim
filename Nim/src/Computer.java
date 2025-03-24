public class Computer extends Player{

    public Computer(String _name) {
        super(_name);
    }

    @Override
    public void takeTurn(){
        if (Board.getNumTiles() == 1) {
            nim(1);
            return;
        }

        int losingPosition = 1;
        while ((losingPosition * 2 + 1) <= Board.getNumTiles()) {
            losingPosition = losingPosition * 2 + 1;
        }

        for (int i = 1; i <= Board.getNumTiles() / 2; i++) {
            if ((Board.getNumTiles() - i) == losingPosition) {
                nim(i);
                return;
            }
        }

        nim(1);
    }
}
