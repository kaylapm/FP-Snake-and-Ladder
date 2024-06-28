/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : C
 * Group    : 01
 * Members  :
 * 1. 5026231158 - Kayla Putri Maharani
 * 2. 5026231173 - Naura Salsabila
 * 3. 5026231139 - Amandea Chandiki Larasati
 * 4. 5026231226 - Vivi Alvianita
 * ------------------------------------------------------
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class SnakeAndLadderGUI extends JFrame {

    JTextField[] playerFields;
    JLabel[] playerLabels;
    JLabel[] scoreLabels;
    JLabel currentPlayerInfoLabel;
    JLabel playerTurnLabel;
    JLabel firstDiceLabel;
    JLabel secondDiceLabel;
    JButton startButton;
    CustomSnakeAndLadder game;

    public SnakeAndLadderGUI() {
        setTitle("Snake and Ladder");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);

        JLabel titleLabel = new JLabel("Snake and Ladder");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(307, 26, 300, 40);
        mainPanel.add(titleLabel);

        playerFields = new JTextField[4];
        playerLabels = new JLabel[4];
        scoreLabels = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            playerFields[i] = new JTextField();
            playerFields[i].setBounds(250, 108 + i * 40, 150, 26);
            mainPanel.add(playerFields[i]);

            playerLabels[i] = new JLabel("Player " + (i + 1));
            playerLabels[i].setFont(new Font("Arial", Font.BOLD, 14));
            playerLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerLabels[i].setBounds(460, 108 + i * 40, 80, 26);
            mainPanel.add(playerLabels[i]);

            scoreLabels[i] = new JLabel("0");
            scoreLabels[i].setFont(new Font("Arial", Font.PLAIN, 20));
            scoreLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabels[i].setBounds(540, 108 + i * 40, 80, 26);
            mainPanel.add(scoreLabels[i]);
        }

        startButton = new JButton("Start Game");
        startButton.setBounds(200, 300, 445, 26);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetPlayerLabels();

                ArrayList<String> playerNames = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    if (!playerFields[i].getText().trim().isEmpty()) {
                        playerNames.add(playerFields[i].getText().trim());
                    }
                }

                if (playerNames.size() < 2) {
                    JOptionPane.showMessageDialog(null, "Please enter at least two players.");
                    return;
                }

                for (int i = 0; i < playerNames.size(); i++) {
                    playerLabels[i].setText(playerNames.get(i));
                    playerLabels[i].setForeground(Color.BLUE);
                    scoreLabels[i].setForeground(Color.BLUE);
                }

                game = new CustomSnakeAndLadder(100, SnakeAndLadderGUI.this);
                game.startGame(playerNames);
            }
        });
        mainPanel.add(startButton);

        currentPlayerInfoLabel = new JLabel("-");
        currentPlayerInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentPlayerInfoLabel.setVerticalAlignment(SwingConstants.TOP);
        currentPlayerInfoLabel.setBounds(150, 400, 150, 83);
        mainPanel.add(currentPlayerInfoLabel);

        playerTurnLabel = new JLabel("Player Turn");
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerTurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTurnLabel.setBounds(500, 350, 150, 16);
        mainPanel.add(playerTurnLabel);

        firstDiceLabel = new JLabel();
        firstDiceLabel.setBounds(450, 380, 112, 107);
        mainPanel.add(firstDiceLabel);

        secondDiceLabel = new JLabel();
        secondDiceLabel.setBounds(600, 380, 112, 107);
        mainPanel.add(secondDiceLabel);

        JButton rollButton = new JButton("Roll Dice");
        rollButton.setBounds(500, 520, 190, 26);

        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (game != null) {
                    game.rollDice();
                }
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
        if (player >= 1 && player <= 4) {
            scoreLabels[player - 1].setText(String.valueOf(position));
        }
    }

    public void disableStartButton() {
        startButton.setEnabled(false);
    }

    public void enableStartButton() {
        startButton.setEnabled(true);
    }

    public void emptyPlayerNameFields() {
        for (int i = 0; i < 4; i++) {
            playerFields[i].setText("");
        }
    }

    private void resetPlayerLabels() {
        for (int i = 0; i < 4; i++) {
            playerLabels[i].setText("Player " + (i + 1));
            playerLabels[i].setForeground(Color.BLACK);
            scoreLabels[i].setForeground(Color.BLACK);
            scoreLabels[i].setText("0");
        }
    }
}
