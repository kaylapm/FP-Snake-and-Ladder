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

public class Snake{
    private int fromPosition;
    private int toPosition;

    public Snake(int from, int to) {
        this.fromPosition = from;
        this.toPosition = to;
    }
    public void setFromPosition(int fromPosition){
        this.fromPosition = fromPosition;
    }
    public void setToPosition(int toPosition){
        this.toPosition = toPosition;
    }
    public int getFromPosition(){
        return fromPosition;
    }
    public int getToPosition(){
        return toPosition;
    }
}