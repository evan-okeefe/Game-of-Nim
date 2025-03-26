class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public void nim(int num) {
        if (num > 0 && num <= Board.getNumPieces() / 2) {
            Board.takePieces(num);
        }
    }
}
