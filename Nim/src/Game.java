import java.util.Scanner;

public class Game {

    Player p1;
    Player p2;

    int playerTurn;

    Scanner scanner = new Scanner(System.in);

    public void play() {
        String response;
        System.out.println("Would you like to play against another [P]layer, or against a [c]omputer?");
        response = scanner.nextLine();


        if (response.equalsIgnoreCase("c")){
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
                p2 = new Computer("[Computer] " + scanner.nextLine());
            }

        }
        else{
            System.out.println("Enter Player 1's name:");
            p1 = new Player(scanner.nextLine());

            System.out.println("Enter Player 2's name:");
            p2 = new Player(scanner.nextLine());
        }

        int playerTurn = (int)(Math.random()*2) + 1;

        System.out.println("Player " + playerTurn + " goes first: " + p1.getName() + " vs " + p2.getName());

        loop();

    }

    public void loop(){
        while(Board.getNumTiles() > 0){
            if (playerTurn == 1){
                System.out.println(p1.getName() + "'s turn!");
                Board.display();
                p1.takeTurn();
                playerTurn = 2;
            }
            else{
                System.out.println(p2.getName() + "'s turn!");
                Board.display();
                p2.takeTurn();
                playerTurn = 1;
            }
        }

        if (Board.getNumTiles() == 0){
            if (playerTurn == 1){
                System.out.println(p2.getName() + " loses");
                restart();
            }
            else{
                System.out.println(p1.getName() + " loses");
                restart();
            }
        }

    }

    public void restart(){
        System.out.println("Would you like to play again?");
        System.out.println("[Y]es / [n]o");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("n")){
            System.out.println("Goodbye!");
        }
        else{
            Board.populate();
            play();
        }
    }
}
