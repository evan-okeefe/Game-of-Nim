import java.util.Scanner;

public class Game {

    Player p1;
    Player p2;

    int playerTurn;

    Scanner scanner = new Scanner(System.in);

    public void play() {
        String response = "";
        while (!(response.equalsIgnoreCase("p") || response.equalsIgnoreCase("c"))){
            System.out.println("Would you like to play against another [p]layer, or against a [c]omputer?");
            response = scanner.nextLine();
        }

        if (response.equalsIgnoreCase("p")){
            System.out.println("Enter Player 1's name:");
            p1 = new Player(scanner.nextLine());

            System.out.println("Enter Player 2's name:");
            p2 = new Player(scanner.nextLine());
        }
        else if (response.equalsIgnoreCase("c")){
            System.out.println("Enter Player's name:");
            p1 = new Player(scanner.nextLine());

            System.out.println("Would you like to name the Computer?");
            System.out.println("[Y]es / [n]o");
            String nameComp = scanner.nextLine();

            if (nameComp.equalsIgnoreCase("n")){
                p2 = new Computer("[Computer] Computer");
            }
            else{
                System.out.println("Enter Computer's name:");
                p2 = new Computer("[Computer]" + scanner.nextLine());
            }

        }

        int playerTurn = (int)(Math.random()*2) + 1;

        System.out.println("Player " + playerTurn + " goes first: " + p1.getName() + " vs " + p2.getName());

    }

    public void loop(){

    }

    public void restart(){

    }
}
