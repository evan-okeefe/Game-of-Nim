import javax.swing.*;
import java.awt.*;

public class NimGameGUI extends JFrame {
    private JTextField player1Field;
    private JTextField player2Field;
    private JComboBox<String> opponentSelection;

    public NimGameGUI() {
        // Initialize components
        player1Field = new JTextField(20);
        player2Field = new JTextField(20);
        opponentSelection = new JComboBox<>(new String[] {"Player", "Computer"});

        // Layout setup
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Player 1's name:"));
        inputPanel.add(player1Field);
        inputPanel.add(new JLabel("Enter Player 2's name:"));
        inputPanel.add(player2Field);
        inputPanel.add(new JLabel("Choose Opponent:"));
        inputPanel.add(opponentSelection);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());

        add(inputPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public String getPlayer1Name() {
        return player1Field.getText();
    }

    public String getPlayer2Name() {
        return player2Field.getText();
    }

    public int getOpponentChoice() {
        return opponentSelection.getSelectedIndex();
    }

    public void updateBoardDisplay() {
        // Update board UI (you'll need to implement this method)
    }

    private void startGame() {
        String player1Name = getPlayer1Name();
        String player2Name = getPlayer2Name();
        int opponentChoice = getOpponentChoice();

        // You can pass this data to your Game class
        Game game = new Game(this);  // Assuming you pass the GUI to Game class
    }

    public static void main(String[] args) {
        new NimGameGUI();
    }
}
