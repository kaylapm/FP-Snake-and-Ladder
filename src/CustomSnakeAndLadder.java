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

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CustomSnakeAndLadder extends SnakeAndLadder {

    private String customFeature;
    private SnakeAndLadderGUI gui;

    public CustomSnakeAndLadder(int boardSize, SnakeAndLadderGUI gui) {
        super(boardSize);
        this.gui = gui;
    }

    public void startGame(String player1Name, String player2Name) {
        Player p1 = new Player(player1Name);
        Player p2 = new Player(player2Name);

        initiateGame();
        addPlayer(p1);
        addPlayer(p2);

        setStatus(1);
        setPlayerInTurn(getTurn());

        updateTurnLabel();
    }

    public void rollDice() {
        if (getStatus() == 2) {
            return;
        }

        Player playerInTurn = getPlayers().get(getPlayerInTurn());
        int dice1 = playerInTurn.rollDice();
        int dice2 = playerInTurn.rollDice();

        gui.updateDice(dice1, dice2);

        movePlayer(playerInTurn, dice1 + dice2);

        if (dice1 == 6 && dice2 == 6) {
            JOptionPane.showMessageDialog(null, playerInTurn.getUserName() + " got double six! Roll again.");
        } else {
            setPlayerInTurn(getTurn());
        }

        updateTurnLabel();
    }

    @Override
    public void movePlayer(Player p, int x) {
        p.moveAround(x, this.boardSize);

        StringBuilder progress = new StringBuilder();

        for (int i = 0; i < this.ladders.size(); i++) {
            Ladder l = this.ladders.get(i);
            if (p.getPosition() == l.getFromPosition()) {
                p.setPosition(l.getToPosition());
                progress.append(p.getUserName()).append(" got ladder from ").append(l.getFromPosition()).append(" climb to ").append(l.getToPosition()).append("\n");
            }
        }

        for (int i = 0; i < this.snakes.size(); i++) {
            Snake s = this.snakes.get(i);
            if (p.getPosition() == s.getFromPosition()) {
                p.setPosition(s.getToPosition());
                progress.append(p.getUserName()).append(" got snake from ").append(s.getFromPosition()).append(" slide down to ").append(s.getToPosition()).append("\n");
            }
        }
        progress.append(p.getUserName()).append(" new position is ").append(p.getPosition());
        gui.updateProgress(progress.toString());


        if (p == getPlayers().get(0)) {
            gui.updateScore(1, p.getPosition());
        } else {
            gui.updateScore(2, p.getPosition());
        }

        if (p.getPosition() == this.boardSize) {
            this.status = 2;
            gui.updateProgress("The winner is: " + p.getUserName());
            JOptionPane.showMessageDialog(null, "The winner is: " + p.getUserName());
        }
    }

    private void updateTurnLabel() {
        Player playerInTurn = getPlayers().get(getPlayerInTurn());
        gui.updateTurnLabel(playerInTurn.getUserName() + "'s turn");
    }
}
