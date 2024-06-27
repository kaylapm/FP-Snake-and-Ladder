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