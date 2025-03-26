public class Game {
    private Player p1;
    private Player p2;
    private int playerTurn;
    private NimGameGUI gui;

    public Game(NimGameGUI gui) {
        this.gui = gui;
        setupGame();
    }

    private void setupGame() {
        // Get Player 1's name from GUI
        p1 = new Player(gui.getPlayer1Name());

        // Choose opponent: Player or Computer
        int choice = gui.getOpponentChoice();  // GUI method to get choice (Player/Computer)

        if (choice == 1) {
            p2 = new Computer("Computer");
        } else {
            p2 = new Player(gui.getPlayer2Name());
        }

        Board.populate();
        playerTurn = (int) (Math.random() * 2) + 1;
        gui.updateBoardDisplay();  // Initial board update
    }

    public void loop(int num) {
        if (Board.getNumPieces() > 0) {
            if (playerTurn == 1) {
                p1.nim(num);
                playerTurn = 2;
            } else {
                p2.nim(num);
                playerTurn = 1;
            }
        }
        gui.updateBoardDisplay();  // Update the board after each turn
    }
}
