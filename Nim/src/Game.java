public class Game {
    private Player p1;
    private Player p2;
    private int playerTurn;
    private NimGameGUI gui;

    public Game(NimGameGUI gui) {
        this.gui = gui;
    }

    public void setupPlayers(String p1Name, String p2Name, String opponentChoice) {
        p1 = new Player(p1Name);
        if (opponentChoice.equals("Computer")) {
            p2 = new Computer("Computer");
        } else {
            p2 = new Player(p2Name);
        }

        startNewGame();
    }

    private void startNewGame() {
        Board.populate();
        playerTurn = (int) (Math.random() * 2) + 1;
        gui.updateBoardDisplay();
        takeTurn();
    }

    private void takeTurn() {
        if (Board.getNumPieces() > 0) {
            if (playerTurn == 1) {
                gui.promptMove(p1);
            } else {
                gui.promptMove(p2);
            }
        } else {
            checkGameOver();
        }
    }

    public void playerMove(int num) {
        if (playerTurn == 1) {
            p1.nim(num);
            playerTurn = 2;
        } else {
            p2.nim(num);
            playerTurn = 1;
        }

        gui.updateBoardDisplay();
        takeTurn();
    }

    private void checkGameOver() {
        if (Board.getNumPieces() == 0) {
            if (playerTurn == 1) {
                gui.showGameOver(p2.getName() + " loses! " + p1.getName() + " wins!");
            } else {
                gui.showGameOver(p1.getName() + " loses! " + p2.getName() + " wins!");
            }
        }
    }

    public void askPlayAgain(boolean replay) {
        if (replay) {
            startNewGame();
        } else {
            gui.closeGame();
        }
    }
}
