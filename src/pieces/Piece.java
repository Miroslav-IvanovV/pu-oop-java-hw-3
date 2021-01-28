package pieces;

import java.awt.*;

public abstract class Piece {

    protected int row;
    protected int col;
    protected char colour;

    public void move(int row, int col){
        this.row = row;
        this.col = col;
    }
    public char getColour(){
        return colour;
    }

    public abstract void render(Graphics g);
}
