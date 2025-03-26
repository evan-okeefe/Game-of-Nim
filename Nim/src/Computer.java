class Computer extends Player {
    public Computer(String name) {
        super(name);
    }

    @Override
    public void nim(int num) {
        int move = Math.max(1, Math.min(Board.getNumPieces() / 2, num));
        Board.takePieces(move);
    }
}