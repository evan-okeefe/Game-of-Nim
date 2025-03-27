public class Computer extends Player {
    public Computer(String name) {
        super(name);
    }

    @Override
    public void nim(int num) {
        int move = (Board.getNumPieces() > 1) ? (int) (Math.random() * 3) + 1 : 1;
        Board.removePieces(move);
        System.out.println(getName() + " removes " + move + " pieces.");
    }
}
