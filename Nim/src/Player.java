public class Player {

    private String name;
    private int score;

    public Player(String _name) {
        name = _name;
    }

    public void takeTurn(){

    }

    public void nim(int num){
        if (num > Board.getNumTiles()/2){
            score += num;
            Board.takeTiles(num);
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
