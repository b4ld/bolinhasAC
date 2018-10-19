package org.academiadecodigo.invictus.bolinhas;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Board {


    //----------------------------------->

    public int TOTAL_COLUMNS = 10;
    public int TOTAL_ROWS = 8;
    public static final int PADDING = 10;
    public static final int CELL_SIZE = 100;

    private static Rectangle screen;//fundo opcional

    private Picture[][] picturesArray;


    public Board() {


    }


    public void init() {

        // create the GUI and grid size
        this.screen = new Rectangle(PADDING, PADDING, TOTAL_COLUMNS * CELL_SIZE, TOTAL_ROWS * CELL_SIZE);

        // display the background
        this.screen.setColor(Color.LIGHT_GRAY);//board edge color
        this.screen.fill();


    }


    public void drawInitialPictures() {

        //DRAW ALL THE PICTURES

        int inPosX = PADDING;
        int inPosY = PADDING;

        int random;

        picturesArray = new Picture[TOTAL_ROWS][TOTAL_COLUMNS];
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLUMNS; j++) {

                random = (int) Math.floor(Math.random() * 3);

                //Create a new Image;
                if (random == 0) {
                    picturesArray[i][j] = new Picture(inPosX, inPosY, "assets/_img/blue.png");
                }
                if (random == 1) {
                    picturesArray[i][j] = new Picture(inPosX, inPosY, "assets/_img/red.png");
                }
                if (random == 2) {
                    picturesArray[i][j] = new Picture(inPosX, inPosY, "assets/_img/yellow.png");
                }

                inPosX = inPosX + CELL_SIZE;
                if (j == 9) {
                    inPosY = inPosY + CELL_SIZE;
                    inPosX = PADDING;
                }

                picturesArray[i][j].draw();

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



    /*

//Codigo que verifica de so meche uma casa - Rui-----------------


public boolean canSwap(int XprimeiroClick,int YprimeiroClick,int XsegundoClick,int YsegundoClick){

        int moveX=Math.abs(XprimeiroClick-XsegundoClick);
        int moveY=Math.abs(YprimeiroClick-YsegundoClick);


        return(moveX==0&&moveY==1)||(moveX==10&&moveY==0);
        }







  */


}
