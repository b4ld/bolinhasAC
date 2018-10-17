package org.academiadecodigo.invictus.bolinhas.Draw;
import org.academiadecodigo.simplegraphics.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {

    //Class rsponable for making the grid appear



    private int cols = 10;
    private int rows = 8;
    private int cellSize = 100;
    private final int PADDING = 10;
    private int random;
    private Rectangle[][] cellArray;
    private Picture[][] picturesArray;

    public Grid() {



    }


    public void drawCell(){

        int inPosX = PADDING;
        int inPosY = PADDING;
        cellArray = new Rectangle[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                random=(int)Math.floor(Math.random()*3);

                cellArray[i][j] = new Rectangle(inPosX,inPosY,cellSize,cellSize);

                inPosX=inPosX+cellSize;
                if (j==9){
                    inPosY=inPosY+cellSize;
                    inPosX=PADDING;
                }
                cellArray[i][j].setColor(Color.LIGHT_GRAY);
                cellArray[i][j].draw();

            }

        }







    }





}
