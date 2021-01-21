package pieces;

import java.awt.*;

public class Guard {

    private int row;
    private int col;
    private char colour;

    public Guard(int row , int col, char colour) {
        this.row = row;
        this.col = col;
        this.colour = colour;

    }

    public void render(Graphics g) {

        if(this.colour == 'Y')
            g.setColor(Color.yellow);
        else
            g.setColor(Color.GREEN);


        g.fillOval(this.col * 100 + 25,this.row * 100 + 25,50,50);

    }
    public void move(int row, int col){
        this.row = row;
        this.col = col;
    }
}
