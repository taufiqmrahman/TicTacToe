import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int height = 650;
    int width = 600;

    JFrame frame = new JFrame("TICTACTOE ©TAUFIQ MUSTAFIZUR RAHMAN");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JButton startButton = new JButton("Start Game");
    JButton playAgainButton = new JButton("Play Again");

    JButton[][] board = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;
    boolean gameOver = false;
    int turns = 0;


    public TicTacToe() {
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Background, foreground color and font
        textLabel.setBackground(new Color(56, 80, 92));
        textLabel.setForeground(Color.gray);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC TAC TOE");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(56, 80, 92));
        frame.add(boardPanel, BorderLayout.CENTER);

        controlPanel.setLayout(new FlowLayout());
        
        // Set button colors
        startButton.setBackground(new Color(243, 193, 78));
        playAgainButton.setBackground(new Color(243, 193, 78));

        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        playAgainButton.setFont(new Font("Arial", Font.BOLD, 30));
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        showStartButton();
    }

    private void showStartButton() {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridBagLayout());
        boardPanel.add(startButton);
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void showPlayAgainButton() {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridBagLayout());
        boardPanel.add(playAgainButton);
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void startGame() {
        textLabel.setText(currentPlayer + "'s turn.");
        initializeBoard();
    }

    private void initializeBoard() {
        boardPanel.removeAll();
        boardPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);
                tile.setBackground(new Color(56, 80, 92));
                tile.setForeground(new Color(243, 193, 78));
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            return;
                        }
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                if(currentPlayer.equals(player1)){
                                    currentPlayer = player2;
                                } 
                                else{
                                    currentPlayer = player1;
                                }
                                textLabel.setText(currentPlayer + "'s turn.");

                            }
                        }
                    }
                });
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void checkWinner() {
        // Horizontal check
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals("")) continue;
            if (board[i][0].getText().equals(board[i][1].getText()) &&
                board[i][1].getText().equals(board[i][2].getText())) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
            }
        }

        // Vertical check
        for (int x = 0; x < 3; x++) {
            if (board[0][x].getText().equals("")) continue;
            if (board[0][x].getText().equals(board[1][x].getText()) &&
                board[1][x].getText().equals(board[2][x].getText())) {
                for (int y = 0; y < 3; y++) {
                    setWinner(board[y][x]);
                }
                gameOver = true;
            }
        }

        // Diagonal check
        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            !board[0][0].getText().equals("")) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
        }

        // Opposite diagonal check
        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            !board[0][2].getText().equals("")) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
        }

        // Tie check
        if (turns == 9 && !gameOver) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }

        if (gameOver) {
            showPlayAgainButton();
        }
    }

    private void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.GRAY);
        textLabel.setText(currentPlayer + " is the Winner!");
        
    }

    private void setTie(JButton tile) {
        tile.setForeground(Color.YELLOW);
        tile.setBackground(Color.GRAY);
        textLabel.setText("!!!TIE!!!");
    }


    private void resetBoard() {
        gameOver = false;
        turns = 0;
        currentPlayer = player1;
        textLabel.setText("TIC TAC TOE");
        initializeBoard();
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}