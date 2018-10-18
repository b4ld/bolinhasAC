package org.academiadecodigo.pcdev.beje.newP;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Grid {

    private int cols = 10;
    private int rows = 8;
    private int cellSize = 100;
    private int random;
    private Rectangle[][] cellArray;
    private Picture[][] picArray;


    private final int PADDING = 10;

    // private Rectangle grid = new Rectangle(PADDING, PADDING, cols*cellSize,rows*cellSize);

    //private Rectangle cell = new Rectangle(PADDING,PADDING,cellSize,cellSize);


    public Grid() {

    }

    public void drawCell(){

        //DRAW ALL THE CELLS

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



    public void drawImages(){

        //DRAW ALL THE CELLS

        int inPosX = PADDING;
        int inPosY = PADDING;

                picArray = new Picture[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                random=(int)Math.floor(Math.random()*3);

                //Create a new Image;
                if(random==0) {
                    picArray[i][j] = new Picture(inPosX, inPosY, "assets/blue.png");
                }
                if (random==1){
                    picArray[i][j] = new Picture(inPosX, inPosY, "assets/red.png");
                }
                if (random==2){
                    picArray[i][j] = new Picture(inPosX, inPosY, "assets/yellow.png");
                }

                inPosX=inPosX+cellSize;
                if (j==9){
                    inPosY=inPosY+cellSize;
                    inPosX=PADDING;
                }

                picArray[i][j].draw();


            }

                }


    }


    //Methods SWAP


    public void fillCell(int X, int Y) {
        System.out.println(X + "..." + Y);
        cellArray[Y][X].setColor(Color.BLUE);
        cellArray[Y][X].fill();

    }


    public void SwapPiece(int Xp1,int Yp1, int Xp2, int Yp2){



        //Falta implementar a canSwap - falar com rui

        //must be animated

        picArray[Yp1][Xp1].translate((Xp2 - Xp1) * cellSize,(Yp2 - Yp1) * cellSize);
        picArray[Yp2][Xp2].translate((Xp1 - Xp2) * cellSize,(Yp1 - Yp2) * cellSize);

        Picture aux = picArray[Yp1][Xp1];
        picArray[Yp1][Xp1] = picArray[Yp2][Xp2];
        picArray[Yp2][Xp2] = aux;

    }











    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getPADDING() {
        return PADDING;
    }

    public Picture[][] getPicArray() {
        return picArray;
    }
}





