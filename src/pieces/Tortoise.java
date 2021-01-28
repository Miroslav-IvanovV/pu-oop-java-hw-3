package pieces;

import java.awt.*;

public class Tortoise extends Piece {

    public Tortoise(int row , int col, char colour) {
        this.row = row;
        this.col = col;
        this.colour = colour;

    }

    public void render(Graphics g) {

        g.setColor(Color.RED);
        g.fillOval(this.col * 100 + 12,this.row * 100 + 13,75,75);

        g.setColor(Color.WHITE);
        g.fillOval(this.col * 100 + 25,this.row * 100 + 25,50,50);

    }

}
