public class Player {
    private final String name;
    private int score;

    public Player(String _name) {
        name = _name;
        score = 0;
    }

    public void nim(int num) {
        if (Board.getNumPieces() == 1) {
            if (num == 1) {
                Board.takePieces(num);
            }
        } else {
            if (num <= Board.getNumPieces()/2) {
                Board.takePieces(num);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void win() {
        score++;
    }

    public int getScore() {
        return score;
    }
}