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
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CustomSnakeAndLadder extends SnakeAndLadder {

    private SnakeAndLadderGUI gui;
    private ArrayList<Bomb> bombs;
    private ArrayList<Surprise> surprises;

    public CustomSnakeAndLadder(int boardSize, SnakeAndLadderGUI gui) {
        super(boardSize);
        this.gui = gui;
        this.bombs = new ArrayList<>();
        this.surprises = new ArrayList<>();
        initializeBombsAndSurprises();
    }

    private void initializeBombsAndSurprises() {
        bombs.add(new Bomb(55));
        bombs.add(new Bomb(73));
        bombs.add(new Bomb(98));

        surprises.add(new Surprise(63));
        surprises.add(new Surprise(39));
    }

    public void startGame(ArrayList<String> playerNames) {
        initiateGame();
        clearPlayers(); // Clear existing players
        for (String name : playerNames) {
            addPlayer(new Player(name));
        }

        setStatus(1);
        setPlayerInTurn(getTurn());

        updateTurnLabel();
        restartScoreLabels();
        gui.updateProgress("Game started.");
        gui.disableStartButton();
        gui.emptyPlayerNameFields();
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
            setPlayerInTurn((getPlayerInTurn() + 1) % getPlayers().size());  // Change turn to the next player
        }

        updateTurnLabel();
    }

    @Override
    public void movePlayer(Player p, int x) {
        p.moveAround(x, this.boardSize);

        StringBuilder progress = new StringBuilder();

        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getFromPosition()) {
                p.setPosition(l.getToPosition());
                progress.append(p.getUserName()).append(" got ladder from ").append(l.getFromPosition()).append(" climb to ").append(l.getToPosition()).append("\n");
            }
        }

        for (Snake s : this.snakes) {
            if (p.getPosition() == s.getFromPosition()) {
                p.setPosition(s.getToPosition());
                progress.append(p.getUserName()).append(" got snake from ").append(s.getFromPosition()).append(" slide down to ").append(s.getToPosition()).append("\n");
            }
        }

        for (Bomb b : this.bombs) {
            if (p.getPosition() == b.getPosition()) {
                p.setPosition(Math.max(1, p.getPosition() - b.getPenalty())); // Ensure position doesn't go below 1
                progress.append(p.getUserName()).append(" hit a bomb at ").append(b.getPosition()).append(" and moved back by ").append(b.getPenalty()).append(" positions.\n");
            }
        }

        for (Surprise s : this.surprises) {
            if (s.isWinningPosition(p.getPosition())) {
                this.status = 2;
                gui.updateProgress("The winner is: " + p.getUserName());
                JOptionPane.showMessageDialog(null, p.getUserName() + " got a surprise at " + p.getPosition() +". The winner is: " + p.getUserName());
                gui.enableStartButton();
                return;
            }
        }

        progress.append(p.getUserName()).append(" new position is ").append(p.getPosition());
        gui.updateProgress(progress.toString());

        int playerIndex = getPlayers().indexOf(p);
        gui.updateScore(playerIndex + 1, p.getPosition());

        if (p.getPosition() == this.boardSize) {
            this.status = 2;
            gui.updateProgress("The winner is: " + p.getUserName());
            JOptionPane.showMessageDialog(null, "The winner is: " + p.getUserName());
            gui.enableStartButton();
        }
    }

    private void updateTurnLabel() {
        Player playerInTurn = getPlayers().get(getPlayerInTurn());
        gui.updateTurnLabel(playerInTurn.getUserName() + "'s turn");
    }

    private void restartScoreLabels() {
        for (int i = 0; i < getPlayers().size(); i++) {
            gui.updateScore(i + 1, 0);
        }
    }

    private void clearPlayers() {
        getPlayers().clear();
    }
}
