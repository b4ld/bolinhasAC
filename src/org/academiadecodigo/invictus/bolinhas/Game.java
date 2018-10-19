package org.academiadecodigo.invictus.bolinhas;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Game {

    public static int TOTAL_COLUMNS = 10;
    public static int TOTAL_ROWS = 8;

    Mouse mouseBoard;
    MouseHandler mouseHandler;
    MouseEvent mouseEvent;


    private Board gameBoard;

    private GamePiece[][] gameArray;


    public Game() {
        mouseHandler = new MouseInputHandler();
        mouseBoard = new Mouse(mouseHandler);
        mouseBoard.addEventListener(MouseEventType.MOUSE_CLICKED);

        gameBoard = new Board();
    }


    public void gameInit() {

        gameBoard.init();

        gameArray = new GamePiece[TOTAL_ROWS][TOTAL_COLUMNS];

        for (int row = 0; row < TOTAL_ROWS; row++) {
            for (int col = 0; col < TOTAL_COLUMNS; col++) {
                gameArray[row][col] = GamePieceFactory.getNewPiece();
                gameBoard.drawPiece(row, col, gameArray[row][col]);
            }
        }

    }


    public void gameStart() {

    }


    public void swapPiece(int X_Row_First, int Y_Col_First, int X_Row_Second, int Y_Col_Second) {

        //actualiza array logico - gameArray

        GamePiece temp = gameArray[Y_Col_First][X_Row_First];
        gameArray[Y_Col_First][X_Row_First] = gameArray[Y_Col_Second][X_Row_Second];
        gameArray[Y_Col_Second][X_Row_Second] = temp;


        //testes explosoes e etc

        //desenha peças trocadas se nada explodiu e tal
        gameBoard.drawPiece(Y_Col_First, X_Row_First, gameArray[Y_Col_First][X_Row_First]);
        gameBoard.drawPiece(Y_Col_Second, X_Row_Second, gameArray[Y_Col_Second][X_Row_Second]);

    }


    //testa se so anda uma casa

    public boolean testSwap(int X_FirstClick, int Y_FirstClick, int X_SecondClick, int Y_SecondClick) {

        int moveX = Math.abs(X_FirstClick - X_SecondClick);
        int moveY = Math.abs(Y_FirstClick - Y_SecondClick);

        return (moveX == 0 && moveY == 1) || (moveX == 1 && moveY == 0);
    }


    class MouseInputHandler implements MouseHandler {

        int X_FirstClick;
        int Y_FirstClick;

        int X_SecondClick;
        int Y_SecondClick;

        int X_mouse;
        int Y_mouse;

        private boolean isFirstClick = true;

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            X_mouse = ((int) mouseEvent.getX() - Board.PADDING) / Board.CELL_SIZE;
            Y_mouse = ((int) mouseEvent.getY() - Board.PADDING) / Board.CELL_SIZE;
            System.out.println(X_mouse + " < X --- Y > " + Y_mouse);

            //myGrid.removeImg(X_mouse,Y_mouse);

            if (isFirstClick) {
                X_FirstClick = X_mouse;
                Y_FirstClick = Y_mouse;
                System.out.println("First Click");
                isFirstClick = false;

                //myGrid.fillCell(X_FirstClick,Y_FirstClick);

            } else {
                X_SecondClick = X_mouse;
                Y_SecondClick = Y_mouse;
                System.out.println("Second click");

                isFirstClick = true;

                //testes entram aqui

                if (testSwap(X_FirstClick, Y_FirstClick, X_SecondClick, Y_SecondClick)) {//se jogada valida (distancia de clicks = 1)
                    swapPiece(X_FirstClick, Y_FirstClick, X_SecondClick, Y_SecondClick);
                }

            }

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
        }


    }


}
