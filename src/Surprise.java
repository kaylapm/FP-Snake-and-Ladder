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
public class Surprise {
    private int position;

    public Surprise(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinningPosition(int playerPosition) {
        return playerPosition == position;
    }
}