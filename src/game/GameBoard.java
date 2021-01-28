package game;

import UI.Modal;
import pieces.Guard;
import pieces.Leader;
import pieces.Piece;
import pieces.Tortoise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {

    private Piece[][]  piecesColector;
    private Piece selectedPiece;
    private int lastIndexRow;
    private int lastIndexCol;
    public static final int TILE_SIDE_COUNT = 8;
    private int firstTortoiseCol;
    private int secondTortoiseCol;



    public GameBoard(){

        this.piecesColector =new Piece[TILE_SIDE_COUNT][TILE_SIDE_COUNT];


        definitionOfTortuisePosition();
        this.piecesColector[2][firstTortoiseCol] = (new Tortoise(2,firstTortoiseCol,'W'));
        this.piecesColector[2][secondTortoiseCol] = (new Tortoise(2,secondTortoiseCol,'W'));

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

    /**
    define the coll for the tortoises.
     */
    private void definitionOfTortuisePosition() {
        firstTortoiseCol = randomGenerater();
        secondTortoiseCol = randomGenerater();

        while(firstTortoiseCol == 2){
            firstTortoiseCol = randomGenerater();
        }

        while(firstTortoiseCol == secondTortoiseCol || secondTortoiseCol == 2) {
            secondTortoiseCol = randomGenerater();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY() / 100;
        int col = e.getX() / 100;


        if (selectedPiece != null && this.selectedPiece instanceof Leader) {
            moveLeader(row, col);
            this.repaint();
            if(row == 2 && col == 2){
                if(this.selectedPiece.getColour() == 'Y'){
                    new Modal(this, "Game Over", "yellow won");
                } else {
                    new Modal(this, "Game Over", "green won");
                }
            }
            this.selectedPiece = null;
            return;
        }

        if (selectedPiece != null && this.selectedPiece instanceof Guard) {
            moveGuard(row, col);
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

    /**
    filling the gray squares
    */
    private void greyPaint(Graphics g){
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

    /**
    filling the orange and black squares
     */
    private void orangeAndBlackPaint (Graphics g){
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

    /**
    filling the white squares
     */
    private void whitePaint (Graphics g){
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

    /**
     rendering the game pieces
     */
    private void renderGamePiece(Graphics g, int row, int col){

            if(hasBoardPiece(row, col)){
                Piece G1 = this.piecesColector[row][col];
                G1.render(g);
            }

    }

    /**
     checking if there is a piece on the board in a specific square
     */
    private boolean hasBoardPiece(int row, int col){
        return this.piecesColector[row][col] != null;
    }

    /**
    moving the leader
    */
    private void moveLeader(int row, int col) {
        if(row != lastIndexRow && col == lastIndexCol){
            if(row < lastIndexRow){
                row = lastIndexRow;
                while(true){
                    row--;
                    if(this.hasBoardPiece(row, col)){
                        if(piecesColector[row][col] instanceof Tortoise){
                            piecesColector[row][col] = null;
                            piecesColector[lastIndexRow][lastIndexCol] = null;
                            break;
                        }
                        row++;
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                    if(row == 0){
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                }
            } else{
                row = lastIndexRow;
                while(true){
                    row++;

                    if(this.hasBoardPiece(row, col)){
                        if(piecesColector[row][col] instanceof Tortoise) {
                            piecesColector[row][col] = null;
                            piecesColector[lastIndexRow][lastIndexCol] = null;
                            break;
                        }
                        row--;
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                    if(row == 4){
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                }
            }

        } else if(col != lastIndexCol && row == lastIndexRow){
            if(col < lastIndexCol){
                col = lastIndexCol;
                while(true){
                    col--;
                    if(this.hasBoardPiece(row, col)){
                        if(piecesColector[row][col] instanceof Tortoise) {
                            piecesColector[row][col] = null;
                            piecesColector[lastIndexRow][lastIndexCol] = null;
                            break;
                        }
                        col++;
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                    if(col == 0){
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                }
            } else{
                col = lastIndexCol;
                while(true){
                    col++;
                    if(this.hasBoardPiece(row, col)){
                        if(piecesColector[row][col] instanceof Tortoise) {
                            piecesColector[row][col] = null;
                            piecesColector[lastIndexRow][lastIndexCol] = null;
                            break;
                        }
                        col--;
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                    if(col == 4){
                        Piece L1 = this.selectedPiece;
                        L1.move(row, col);
                        piecesColector[row][col] = selectedPiece;
                        piecesColector[lastIndexRow][lastIndexCol] = null;
                        break;
                    }
                }
            }
        }
    }
    /**
     moving the guard
     */
    private void moveGuard(int row, int col) {
        if( (((row + 1 ==  lastIndexRow) || (row - 1 ==  lastIndexRow)) && col == lastIndexCol) || ((col + 1 ==  lastIndexCol) || (col - 1 ==  lastIndexCol)) && row == lastIndexRow) {
            if(this.hasBoardPiece(row, col) && piecesColector[row][col] instanceof Tortoise){
                piecesColector[row][col] = null;
                piecesColector[lastIndexRow][lastIndexCol] = null;
                return;
            }
            if(this.hasBoardPiece(row, col) && (piecesColector[row][col] instanceof Leader || piecesColector[row][col] instanceof Guard)){
                return;
            }
            Piece G1 = this.selectedPiece;
            G1.move(row, col);
            piecesColector[row][col] = selectedPiece;
            piecesColector[lastIndexRow][lastIndexCol] = null;
        }
    }

    /**
    generate a random number
     */
    private int randomGenerater(){
        Random rand = new Random();
        int random = rand.nextInt(5);
        return random;
    }

}
