package org.academiadecodigo.invictus.bolinhas;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.HashSet;


public class Board {


    //----------------------------------->

    public static final int PADDING = 10;
    public static final int CELL_SIZE = 100;

    private static Rectangle screen;//fundo opcional

    private Picture[][] picturesArray = null;


    public Board() {
        picturesArray = new Picture[Game.TOTAL_ROWS][Game.TOTAL_COLUMNS];
    }

    public void init() {

        // create the GUI and grid size
        this.screen = new Rectangle(PADDING, PADDING, Game.TOTAL_COLUMNS * CELL_SIZE, Game.TOTAL_ROWS * CELL_SIZE);

        // display the background
        this.screen.setColor(Color.LIGHT_GRAY);//board edge color
        this.screen.fill();

    }

    public void drawPiece(int row, int col, GamePiece piece) {

   //     System.out.println(row + " - " + col + " : " + piece.getPieceAsset());
        picturesArray[row][col] = new Picture(col * CELL_SIZE + PADDING, row * CELL_SIZE + PADDING, piece.getPieceAsset());
        picturesArray[row][col].draw();
    }

    public void drawInitPieces(GamePiece[][] gameArray){

        for (int row = 0; row < gameArray.length; row++) {
            for (int col = 0; col < gameArray[0].length; col++) {
                drawPiece(row,col, gameArray[row][col]);
            }
        }
    }

    public void redrawAllPieces(GamePiece[][] gameArray){

        for (int row = 0; row < gameArray.length; row++) {
            for (int col = 0; col < gameArray[0].length; col++) {
                picturesArray[row][col].delete();
            }
        }

        for (int row = 0; row < gameArray.length; row++) {
            for (int col = 0; col < gameArray[0].length; col++) {
                drawPiece(row,col, gameArray[row][col]);
            }
        }

        System.out.println("redrawn pieces");
    }


    public void drawDetonations(GamePiece[][] gameArray){

        for (int row = 0; row < gameArray.length; row++) {
            for (int col = 0; col < gameArray[0].length; col++) {
                if(gameArray[row][col]==GamePiece.DETONATION){
                    drawPiece(row,col, gameArray[row][col]);
                }

            }
        }

    }



    public void swapPieceDraw(int Xp1, int Yp1, int Xp2, int Yp2) {

        picturesArray[Yp1][Xp1].translate((Xp2 - Xp1) * Board.CELL_SIZE, (Yp2 - Yp1) * Board.CELL_SIZE);
        picturesArray[Yp2][Xp2].translate((Xp1 - Xp2) * Board.CELL_SIZE, (Yp1 - Yp2) * Board.CELL_SIZE);

        Picture aux = picturesArray[Yp1][Xp1];

        picturesArray[Yp1][Xp1] = picturesArray[Yp2][Xp2];
        picturesArray[Yp2][Xp2] = aux;

    }


}
