import java.util.Scanner;

public class Game {

    Player p1;
    Player p2;

    int playerTurn;

    boolean newGame = true;

    Scanner scanner = new Scanner(System.in);

    public void play() {
        if (newGame){
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
            newGame = false;
        }

        playerTurn = (int)(Math.random()*2) + 1;

        System.out.println("Player " + playerTurn + " goes first: " + p1.getName() + " vs " + p2.getName());

        loop();

    }

    public void loop(){
        while(Board.getNumPieces() > 0){
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

        if (Board.getNumPieces() == 0){
            if (playerTurn == 1){
                System.out.println(p2.getName() + " loses");
                System.out.println(p1.getName() + " scores one point");
                p1.win();
            }
            else{
                System.out.println(p1.getName() + " loses");
                System.out.println(p2.getName() + " scores one point");
                p2.win();
            }
            System.out.println(p1.getName() + " has " + p1.getScore() + " points");
            System.out.println(p2.getName() + " has " + p2.getScore() + " points");
            restart();
        }

    }

    public void restart(){
        System.out.println("Would you like to play again?");
        System.out.println("[Y]es / [n]o");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("n")){
            if (p1.getScore() != p2.getScore()){
                if (p1.getScore() > p2.getScore()){
                    System.out.println(p1.getName() + " won! (" + p1.getScore() + ":" + p2.getScore() + ")");
                }
                if (p2.getScore() > p1.getScore()){
                    System.out.println(p2.getName() + " won! (" + p2.getScore() + ":" + p1.getScore() + ")");
                }
            }
            else {
                System.out.println(p1.getName() + " and " + p2.getName() + " tied!");
            }
            System.out.println("Goodbye!");
        }
        else{
            Board.populate();
            for (int i = 0; i < 100; i++) {
                System.out.println();
            }
            play();
        }
    }
}
