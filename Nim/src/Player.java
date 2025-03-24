import java.util.Scanner;

public class Player {

    private final String name;
    private int score;

    private final Scanner scanner = new Scanner(System.in);

    public Player(String _name) {
        name = _name;
    }

    public void takeTurn(){
        if (Board.getNumTiles() == 1)
            System.out.println("Choose a number of tiles to remove (up to " + 1 + "):");
        else
            System.out.println("Choose a number of tiles to remove (up to " + Board.getNumTiles()/2 + "):");
        int num = scanner.nextInt();

        nim(num);
    }

    public void nim(int num){
        if (Board.getNumTiles() == 1){
            if (num == 1){
                System.out.println("You take " + num + " tiles");
                Board.takeTiles(num);
            }
            else{
                System.out.println("You cannot take that many tiles");
                takeTurn();
            }
        }
        else{
            if (num <= Board.getNumTiles()/2){
                System.out.println(name + " takes " + num + " tiles");
                Board.takeTiles(num);
            }
            else{
                System.out.println("That is too many tiles");
                takeTurn();
            }
        }

    }

    public String getName() {
        return name;
    }

    public void win(){
        score++;
    }

    public int getScore() {
        return score;
    }
}
