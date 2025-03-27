public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void nim(int num) {
        Board.removePieces(num);
    }

    public void win() {
        score++;
    }

    public int getScore() {
        return score;
    }
}
