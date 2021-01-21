package game;

import pieces.Guard;
import pieces.Leader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JFrame implements MouseListener {

    private Object[][]  piecesColector;
    private Object selectedPiece;
    private int lastIndexRow;
    private int lastIndexCol;
    public static final int TILE_SIDE_COUNT = 8;

    public GameBoard(){

        this.piecesColector =new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];

        //Leaders adding
        this.piecesColector[0][4] = (new Leader(0,4,'Y'));
        this.piecesColector[4][0] = (new Leader(4,0,'G'));

        //Guards adding
        this.piecesColector[4][4] =(new Guard(4,4,'G'));
        this.piecesColector[4][1] =(new Guard(4,1,'G'));
        this.piecesColector[4][2] =(new Guard(4,2,'G'));
        this.piecesColector[4][3] =(new Guard(4,3,'G'));
        this.piecesColector[0][0] =(new Guard(0,0,'Y'));
        this.piecesColector[0][1] =(new Guard(0,1,'Y'));
        this.piecesColector[0][2] =(new Guard(0,2,'Y'));
        this.piecesColector[0][3] =(new Guard(0,3,'Y'));




        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY() / 100;
        int col = e.getX() / 100;


        if (selectedPiece != null && this.selectedPiece instanceof Leader) {
            Leader L1 = (Leader) this.selectedPiece;
            L1.move(row, col);
            this.selectedPiece = null;
            this.repaint();
        }

        if (selectedPiece != null && this.selectedPiece instanceof Guard) {
            if( (((row + 1 ==  lastIndexRow) || (row - 1 ==  lastIndexRow)) || ((col + 1 ==  lastIndexCol) || (col - 1 ==  lastIndexCol))) && !this.hasBoardPiece(row,col)) {
                Guard G1 = (Guard) this.selectedPiece;
                G1.move(row, col);
                piecesColector[row][col] = selectedPiece;
                piecesColector[lastIndexRow][lastIndexCol] = null;
            }
            this.selectedPiece = null;
            this.repaint();
            return;
        }
            if(this.hasBoardPiece(row,col)) {
                this.selectedPiece = this.piecesColector[row][col];
                lastIndexRow = row;
                lastIndexCol = col;
            }
        }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {

        //filling the gray squares
        greyPaint(g);

        //filling the orange and black squares
        orangeAndBlackPaint(g);

        //filling the white squares
        whitePaint(g);


        //filling the gray circle
        g.setColor(Color.GRAY);
        g.fillOval(225,225,50,50);

        //rendering game pieces
        for(int x = 0; x < TILE_SIDE_COUNT; x++) {
            for (int y = 0; y < TILE_SIDE_COUNT; y++) {
                renderGamePiece(g, x, y);
            }
        }

    }

    //filling the gray squares
    public void greyPaint(Graphics g){
        //filling the gray squares
        g.setColor(Color.GRAY);
        g.fill3DRect(0,100,100,100,true);
        g.fill3DRect(100,100,100,100,true);
        g.fill3DRect(300,100,100,100,true);
        g.fill3DRect(400,100,100,100,true);
        g.fill3DRect(0,300,100,100,true);
        g.fill3DRect(100,300,100,100,true);
        g.fill3DRect(300,300,100,100,true);
        g.fill3DRect(400,300,100,100,true);

    }
    //filling the orange and black squares
    public void orangeAndBlackPaint (Graphics g){
        //filling the orange squares
        g.setColor(Color.ORANGE);
        g.fill3DRect(0,0,100,100,true);
        g.fill3DRect(400,0,100,100,true);
        g.fill3DRect(100,400,100,100,true);
        g.fill3DRect(300,400,100,100,true);

        //filling the black squares
        g.setColor(Color.BLACK);
        g.fill3DRect(100,0,100,100,true);
        g.fill3DRect(300,0,100,100,true);
        g.fill3DRect(0,400,100,100,true);
        g.fill3DRect(400,400,100,100,true);

    }

    //filling the white squares
    public void whitePaint (Graphics g){
        //filling the white squares
        g.setColor(Color.white);
        g.fill3DRect(200,0,100,100,true);
        g.fill3DRect(200,100,100,100,true);
        g.fill3DRect(200,200,100,100,true);
        g.fill3DRect(200,300,100,100,true);
        g.fill3DRect(200,400,100,100,true);
        g.fill3DRect(0,200,100,100,true);
        g.fill3DRect(100,200,100,100,true);
        g.fill3DRect(300,200,100,100,true);
        g.fill3DRect(400,200,100,100,true);

    }

    public void renderGamePiece(Graphics g, int row, int col){

        if (this.piecesColector[row][col] instanceof Leader) {
            Leader L1 = (Leader) this.piecesColector[row][col];
            L1.render(g);
        }

        if (this.piecesColector[row][col] instanceof Guard) {
            Guard G1 = (Guard) this.piecesColector[row][col];
            G1.render(g);
        }

    }
    private boolean hasBoardPiece(int row, int col){
        return this.piecesColector[row][col] != null;
    }

}
