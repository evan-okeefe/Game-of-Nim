import javax.swing.*;
import java.awt.*;

public class NimGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Game game;

    public NimGUI() {
        frame = new JFrame("Game of Nim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        game = new Game(this);
        Board.populate();

        // Create and add panels
        cardPanel.add(createWelcomePanel(), "welcome");
        cardPanel.add(createPlayerSelectPanel(), "playerSelect");
        cardPanel.add(createPlayerNamePanel(), "playerName");
        cardPanel.add(createComputerNamePanel(), "computerName");
        cardPanel.add(createGamePanel(), "game");
        cardPanel.add(createResultPanel(), "result");

        frame.add(cardPanel);
        cardLayout.show(cardPanel, "welcome");
        frame.setVisible(true);
    }

    // Panel creation methods
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Game of Nim!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "playerSelect"));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createPlayerSelectPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel question = new JLabel("Who would you like to play against?", SwingConstants.CENTER);
        question.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton playerButton = new JButton("Another Player");
        playerButton.addActionListener(e -> {
            game.setComputerOpponent(false);
            cardLayout.show(cardPanel, "playerName");
        });

        JButton computerButton = new JButton("Computer");
        computerButton.addActionListener(e -> {
            game.setComputerOpponent(true);
            cardLayout.show(cardPanel, "computerName");
        });

        panel.add(question);
        panel.add(playerButton);
        panel.add(computerButton);
        return panel;
    }

    private JPanel createPlayerNamePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel p1Label = new JLabel("Player 1 name:");
        JTextField p1Field = new JTextField();

        JLabel p2Label = new JLabel("Player 2 name:");
        JTextField p2Field = new JTextField();

        JButton submitButton = new JButton("Start Game");
        submitButton.addActionListener(e -> {
            if (!p1Field.getText().isEmpty() && !p2Field.getText().isEmpty()) {
                game.setPlayerNames(p1Field.getText(), p2Field.getText());
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter names for both players");
            }
        });

        panel.add(p1Label);
        panel.add(p1Field);
        panel.add(p2Label);
        panel.add(p2Field);
        panel.add(submitButton);
        return panel;
    }

    private JPanel createComputerNamePanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel p1Label = new JLabel("Your name:");
        JTextField p1Field = new JTextField();

        JLabel compLabel = new JLabel("Computer name (leave blank for default):");
        JTextField compField = new JTextField();

        JButton submitButton = new JButton("Start Game");
        submitButton.addActionListener(e -> {
            if (!p1Field.getText().isEmpty()) {
                String compName = compField.getText().isEmpty() ? "[Computer] Computer" : "[Computer] " + compField.getText();
                game.setPlayerNames(p1Field.getText(), compName);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter your name");
            }
        });

        panel.add(p1Label);
        panel.add(p1Field);
        panel.add(compLabel);
        panel.add(compField);
        panel.add(submitButton);
        return panel;
    }

    private JPanel createGamePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel turnLabel = new JLabel("", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea piecesArea = new JTextArea();
        piecesArea.setEditable(false);
        piecesArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("Take (1-");
        JTextField inputField = new JTextField(3);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            try {
                int num = Integer.parseInt(inputField.getText());
                int max = Board.getNumPieces() == 1 ? 1 : Board.getNumPieces() / 2;
                if (num >= 1 && num <= max) {
                    game.playerTakesPieces(num);
                    inputField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a number between 1 and " + max);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number");
            }
        });

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        panel.add(turnLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(piecesArea), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel scorePanel = new JPanel(new GridLayout(2, 1));
        JLabel p1Score = new JLabel("", SwingConstants.CENTER);
        JLabel p2Score = new JLabel("", SwingConstants.CENTER);
        scorePanel.add(p1Score);
        scorePanel.add(p2Score);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
            Board.populate();
            game = new Game(this);
            cardLayout.show(cardPanel, "playerSelect");
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);

        panel.add(resultLabel, BorderLayout.NORTH);
        panel.add(scorePanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public void showGameScreen() {
        cardLayout.show(cardPanel, "game");
    }

    public void updateGameDisplay(String turnInfo, int numPieces) {
        JPanel gamePanel = (JPanel)cardPanel.getComponent(4);
        JLabel turnLabel = (JLabel)((BorderLayout)gamePanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        JTextArea piecesArea = (JTextArea)((JScrollPane)((BorderLayout)gamePanel.getLayout()).getLayoutComponent(BorderLayout.CENTER)).getViewport().getView();

        turnLabel.setText(turnInfo);

        StringBuilder sb = new StringBuilder();
        sb.append("Pieces remaining: ").append(numPieces).append("\n\n");
        for (int i = 0; i < numPieces; i++) {
            sb.append("($) ");
            if ((i + 1) % 4 == 0) sb.append("\n");
        }
        piecesArea.setText(sb.toString());

        JPanel inputPanel = (JPanel)((BorderLayout)gamePanel.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
        JLabel inputLabel = (JLabel)inputPanel.getComponent(0);
        int max = numPieces == 1 ? 1 : numPieces / 2;
        inputLabel.setText("Take (1-" + max + "):");
    }

    public void showResultScreen(Player winner) {
        JPanel resultPanel = (JPanel)cardPanel.getComponent(5);
        JLabel resultLabel = (JLabel)((BorderLayout)resultPanel.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        JPanel scorePanel = (JPanel)((BorderLayout)resultPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        JLabel p1Score = (JLabel)scorePanel.getComponent(0);
        JLabel p2Score = (JLabel)scorePanel.getComponent(1);

        resultLabel.setText(winner.getName() + " wins!");
        p1Score.setText(game.getPlayer1().getName() + ": " + game.getPlayer1().getScore() + " points");
        p2Score.setText(game.getPlayer2().getName() + ": " + game.getPlayer2().getScore() + " points");

        cardLayout.show(cardPanel, "result");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NimGUI());
    }
}