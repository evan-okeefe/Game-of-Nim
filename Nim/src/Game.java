public class Game {
    private Player p1;
    private Player p2;
    private int playerTurn;
    private boolean computerOpponent;
    private NimGUI gui;

    public Game(NimGUI gui) {
        this.gui = gui;
    }

    public void setComputerOpponent(boolean computerOpponent) {
        this.computerOpponent = computerOpponent;
    }

    public void setPlayerNames(String p1Name, String p2Name) {
        p1 = new Player(p1Name);
        if (computerOpponent) {
            p2 = new Computer(p2Name);
        } else {
            p2 = new Player(p2Name);
        }

        playerTurn = (int)(Math.random()*2) + 1;
        gui.showGameScreen();
        startTurn();
    }

    public void startTurn() {
        if (playerTurn == 1) {
            gui.updateGameDisplay(p1.getName() + "'s turn!", Board.getNumPieces());
        } else {
            if (p2 instanceof Computer) {
                ((Computer)p2).takeTurn();
                endTurn();
            } else {
                gui.updateGameDisplay(p2.getName() + "'s turn!", Board.getNumPieces());
            }
        }
    }

    public void endTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;

        if (Board.getNumPieces() <= 0) {
            endGame();
        } else {
            startTurn();
        }
    }

    private void endGame() {
        Player winner = (playerTurn == 1) ? p1 : p2;
        winner.win();
        gui.showResultScreen(winner);
    }

    public void playerTakesPieces(int num) {
        Player currentPlayer = (playerTurn == 1) ? p1 : p2;
        currentPlayer.nim(num);
        endTurn();
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }
}