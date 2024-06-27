/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : C
 * Group    : XX
 * Members  :
 * 1. Student ID - Full Name
 * 2. Student ID - Full Name
 * 3. Student ID - Full Name
 * ------------------------------------------------------
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeAndLadderGUI extends JFrame {

    JLabel currentPlayer1Label;
    JLabel currentPlayer2Label;
    JLabel firstScoreLabel;
    JLabel secondScoreLabel;
    JLabel currentPlayerInfoLabel;
    JLabel playerTurnLabel;
    JLabel firstDiceLabel;
    JLabel secondDiceLabel;
    CustomSnakeAndLadder game;

    public SnakeAndLadderGUI() {
        setTitle("Snake and Ladder");
        setSize(640, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null); 

        JLabel titleLabel = new JLabel("Snake and Ladder");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(207, 26, 300, 40);
        mainPanel.add(titleLabel);

        JTextField firstPlayerField = new JTextField();
        firstPlayerField.setBounds(100, 108, 150, 26);
        mainPanel.add(firstPlayerField);

        JLabel player1Label = new JLabel("Player 1");
        player1Label.setFont(new Font("Arial", Font.BOLD, 14));
        player1Label.setHorizontalAlignment(SwingConstants.CENTER);
        player1Label.setBounds(155, 85, 80, 16);
        mainPanel.add(player1Label);

        JLabel player2Label = new JLabel("Player 2");
        player2Label.setFont(new Font("Arial", Font.BOLD, 14));
        player2Label.setHorizontalAlignment(SwingConstants.CENTER);
        player2Label.setBounds(439, 85, 80, 16);
        mainPanel.add(player2Label);

        JTextField secondPlayerField = new JTextField();
        secondPlayerField.setBounds(386, 108, 150, 26);
        mainPanel.add(secondPlayerField);

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(98, 156, 445, 26);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String player1Name = firstPlayerField.getText();
                String player2Name = secondPlayerField.getText();

                currentPlayer1Label.setText(player1Name);
                currentPlayer2Label.setText(player2Name);

                firstPlayerField.setText("");
                secondPlayerField.setText("");

                game = new CustomSnakeAndLadder(100, SnakeAndLadderGUI.this);
                game.startGame(player1Name, player2Name);
            }
        });
        mainPanel.add(startButton);

        currentPlayer1Label = new JLabel("Player 1");
        currentPlayer1Label.setFont(new Font("Arial", Font.BOLD, 14));
        currentPlayer1Label.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayer1Label.setBounds(54, 229, 80, 16);
        mainPanel.add(currentPlayer1Label);

        currentPlayer2Label = new JLabel("Player 2");
        currentPlayer2Label.setFont(new Font("Arial", Font.BOLD, 14));
        currentPlayer2Label.setHorizontalAlignment(SwingConstants.CENTER);
        currentPlayer2Label.setBounds(153, 229, 80, 16);
        mainPanel.add(currentPlayer2Label);

        JLabel currentPositionLabel = new JLabel("Current Position");
        currentPositionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        currentPositionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentPositionLabel.setBounds(54, 200, 150, 16);
        mainPanel.add(currentPositionLabel);

        firstScoreLabel = new JLabel("0");
        firstScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        firstScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstScoreLabel.setBounds(64, 251, 80, 30);
        mainPanel.add(firstScoreLabel);

        secondScoreLabel = new JLabel("0");
        secondScoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        secondScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondScoreLabel.setBounds(163, 251, 80, 30);
        mainPanel.add(secondScoreLabel);

        currentPlayerInfoLabel = new JLabel("-");
        currentPlayerInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentPlayerInfoLabel.setVerticalAlignment(SwingConstants.TOP);
        currentPlayerInfoLabel.setBounds(54, 288, 150, 83);
        mainPanel.add(currentPlayerInfoLabel);

        playerTurnLabel = new JLabel("Player Turn");
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTurnLabel.setBounds(391, 200, 150, 16);
        mainPanel.add(playerTurnLabel);

        firstDiceLabel = new JLabel();
        firstDiceLabel.setBounds(330, 225, 112, 107);
        mainPanel.add(firstDiceLabel);

        secondDiceLabel = new JLabel();
        secondDiceLabel.setBounds(487, 225, 112, 107);
        mainPanel.add(secondDiceLabel);

        JButton rollButton = new JButton("Roll Dice");
        rollButton.setBounds(371, 340, 190, 26);

        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.rollDice();
            }
        });
        mainPanel.add(rollButton);

        add(mainPanel);
    }

    private ImageIcon resizeImageIcon(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = imageIcon.getImage(); 
        Image newImg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);  
    }

    public void updateTurnLabel(String text) {
        playerTurnLabel.setText(text);
    }

    public void updateProgress(String text) {
        currentPlayerInfoLabel.setText("<html>" + text.replace("\n", "<br>") + "</html>");
    }

    public void updateDice(int dice1, int dice2) {
        firstDiceLabel.setIcon(resizeImageIcon("/" + dice1 + ".png", 107, 107));
        secondDiceLabel.setIcon(resizeImageIcon("/" + dice2 + ".png", 107, 107));
    }

    public void updateScore(int player, int position) {
        if (player == 1) {
            firstScoreLabel.setText(String.valueOf(position));
        } else if (player == 2) {
            secondScoreLabel.setText(String.valueOf(position));
        }
    }

}
