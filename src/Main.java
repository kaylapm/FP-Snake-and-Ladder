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
import javax.swing.SwingUtilities;
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeAndLadderGUI gui = new SnakeAndLadderGUI();
            gui.setVisible(true);
        });
    }
}