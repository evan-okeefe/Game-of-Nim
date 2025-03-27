import javax.swing.*;
import java.awt.*;

public class NimGameGUI extends JFrame {
    private Game game;
    private JButton replayButton;
    private JPanel inputPanel;
    private JTextField player1NameField;
    private JTextField player2NameField;
    private JComboBox<String> opponentChoiceComboBox;
    private JButton startGameButton;
    private JLabel boardDisplayLabel;

    public NimGameGUI() {
        this.setTitle("Nim Game");
        this.setLayout(new BorderLayout());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        player1NameField = new JTextField();
        player2NameField = new JTextField();
        opponentChoiceComboBox = new JComboBox<>(new String[]{"Player", "Computer"});
        startGameButton = new JButton("Start Game");

        inputPanel.add(new JLabel("Player 1 Name:"));
        inputPanel.add(player1NameField);
        inputPanel.add(new JLabel("Player 2 Name:"));
        inputPanel.add(player2NameField);
        inputPanel.add(new JLabel("Choose Opponent:"));
        inputPanel.add(opponentChoiceComboBox);
        inputPanel.add(startGameButton);

        boardDisplayLabel = new JLabel("Board has 15 pieces", SwingConstants.CENTER);
        boardDisplayLabel.setPreferredSize(new Dimension(400, 100));

        replayButton = new JButton("Play Again");
        replayButton.setVisible(false);
        replayButton.addActionListener(e -> game.askPlayAgain(true));

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(boardDisplayLabel, BorderLayout.CENTER);
        this.add(replayButton, BorderLayout.SOUTH);

        startGameButton.addActionListener(e -> startGame());

        this.setVisible(true);
    }

    private void startGame() {
        String player1Name = player1NameField.getText();
        String player2Name = player2NameField.getText();
        String opponentChoice = (String) opponentChoiceComboBox.getSelectedItem();

        if (player1Name.isEmpty() || (opponentChoice.equals("Player") && player2Name.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Please enter all required names!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        game = new Game(this);
        game.setupPlayers(player1Name, player2Name, opponentChoice);
        inputPanel.setVisible(false);
        replayButton.setVisible(false);
        updateBoardDisplay();
    }

    public void promptMove(Player player) {
        String input = JOptionPane.showInputDialog(this, player.getName() + ", enter how many pieces to remove (1-3):");

        if (input != null) {
            try {
                int num = Integer.parseInt(input);
                if (num >= 1 && num <= 3 && num <= Board.getNumPieces()) {
                    game.playerMove(num);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid move. Try again.");
                    promptMove(player);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                promptMove(player);
            }
        }
    }

    public void updateBoardDisplay() {
        if (game != null) {
            boardDisplayLabel.setText("Number of pieces remaining: " + Board.getNumPieces());
        }
    }

    public void showGameOver(String message) {
        JOptionPane.showMessageDialog(this, message);
        showReplayPrompt();
    }

    public void showReplayPrompt() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            game.askPlayAgain(true);
        } else {
            game.askPlayAgain(false);
        }
    }

    public void closeGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NimGameGUI::new);
    }
}
