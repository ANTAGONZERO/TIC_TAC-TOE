package JAVA_LEETCODE.projects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private String currentPlayer;
    private JLabel resultLabel;
    private JLabel turnLabel;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        currentPlayer = "O"; // Player 1 starts with "O"

        // Define the background color for the buttons
        Color buttonBackgroundColor = new Color(220, 220, 220); // Light gray

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(buttonBackgroundColor); // Set background color
                int row = i;
                int col = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(row, col);
                    }
                });

                boardPanel.add(buttons[i][j]);
            }
        }

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));

        turnLabel = new JLabel("Player " + currentPlayer + "'s Turn", JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(turnLabel);
        topPanel.add(resultLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("") && !isGameOver()) {
            buttons[row][col].setText(currentPlayer);
            buttons[row][col].setEnabled(false);

            String winner = determineWinner();
            if (!winner.equals("None")) {
                if (winner.equals("Draw")) {
                    resultLabel.setText("It's a Draw!");
                } else {
                    resultLabel.setText("Player " + winner + " wins!");
                }
                disableAllButtons();
            } else {
                // Switch to the next player's turn
                currentPlayer = (currentPlayer.equals("O")) ? "X" : "O";
                // Update the turn label
                turnLabel.setText("Player " + currentPlayer + "'s Turn");
            }
        }
    }

    private boolean isGameOver() {
        // Check for a draw
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // There are empty spaces, game is not over
                }
            }
        }
        return true; // All cells are filled, it's a draw
    }

    private String determineWinner() {
        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }

        // Check for a winner
        if (player1check(board)) {
            return "O";
        }
        if (player2check(board)) {
            return "X";
        }

        if (isDraw(board)) {
            return "Draw"; // It's a draw
        }

        return "None"; // If no winner is found, continue the game
    }

    private boolean player1check(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals("O") && board[i][1].equals("O") && board[i][2].equals("O")) {
                return true; // Horizontal win
            }
            if (board[0][i].equals("O") && board[1][i].equals("O") && board[2][i].equals("O")) {
                return true; // Vertical win
            }
        }

        if (board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O")) {
            return true; // Main diagonal win
        }
        if (board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O")) {
            return true; // Secondary diagonal win
        }

        return false; // No win for "O"
    }

    private boolean player2check(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals("X") && board[i][1].equals("X") && board[i][2].equals("X")) {
                return true; // Horizontal win
            }
            if (board[0][i].equals("X") && board[1][i].equals("X") && board[2][i].equals("X")) {
                return true; // Vertical win
            }
        }

        if (board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X")) {
            return true; // Main diagonal win
        }
        if (board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X")) {
            return true; // Secondary diagonal win
        }

        return false; // No win for "X"
    }

    private boolean isDraw(String[][] board) {
        // Check if the board is full and no player has won
        return isGameOver() && !player1check(board) && !player2check(board);
    }

    private void disableAllButtons() {
        // Disable all buttons when the game is over
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI();
            }
        });
    }
}
