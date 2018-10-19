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


    public void swapPiece(int col_FirstClick, int row_FirstClick, int col_SecondClick, int row_SecondClick) {

        //actualiza array logico - gameArray

        GamePiece temp = gameArray[row_FirstClick][col_FirstClick];
        gameArray[row_FirstClick][col_FirstClick] = gameArray[row_SecondClick][col_SecondClick];
        gameArray[row_SecondClick][col_SecondClick] = temp;


        System.out.println(findConnectedPieces(col_FirstClick, row_FirstClick, gameArray));
    //    System.out.println(findConnectedPieces(r§ow_SecondClick, col_SecondClick, gameArray));


        //conta pecas similares aquelas que foram swapped
     //   if (findConnectedPieces(row_FirstClick, col_FirstClick, gameArray) >= 3) {
    //        detonate(row_FirstClick, col_FirstClick, gameArray);
     //   }

      //  if (findConnectedPieces(row_SecondClick, col_SecondClick, gameArray) >= 3) {
    //        detonate(row_SecondClick, col_SecondClick, gameArray);
     //   }

        //testes explosoes e etc


        //desenha peças trocadas se nada explodiu e tal
        gameBoard.drawPiece(row_FirstClick, col_FirstClick, gameArray[row_FirstClick][col_FirstClick]);
        gameBoard.drawPiece(row_SecondClick, col_SecondClick, gameArray[row_SecondClick][col_SecondClick]);

    }


    //testa se so anda uma casa

    public boolean testSwap(int col_FirstClick, int row_FirstClick, int col_SecondClick, int row_SecondClick) {

        int moveX = Math.abs(col_FirstClick - col_SecondClick);
        int moveY = Math.abs(row_FirstClick - row_SecondClick);

        return (moveX == 0 && moveY == 1) || (moveX == 1 && moveY == 0);
    }


    //find similar pieces to the one in a(a),b(b)
    public int findConnectedPieces(int a, int b, GamePiece[][] array) {

        //wall tests
        boolean canUp = (a - 1 >= 0);
        boolean canDown = (a + 1 < array.length);
        boolean canRight = (b + 1 < array[0].length);
        boolean canLeft = (b - 1 >= 0);

        GamePiece value = array[a][b];

        int up = 0;
        int down = 0;
        int right = 0;
        int left = 0;

        //isto nao funciona pq ele nao chega a marcar com valores 2 os que quer ver encontrados

        if (canUp && array[a - 1][b] == value) {
            up = findConnectedPieces(a - 1, b, array);
        }
        if (canDown && array[a + 1][b] == value) {
            down = findConnectedPieces(a + 1, b, array);
        }
        if (canLeft && array[a][b - 1] == value) {
            left = findConnectedPieces(a, b - 1, array);
        }
        if (canRight && array[a][b + 1] == value) {
            right = findConnectedPieces(a, b + 1, array);
        }

        return up + left + right + down + 1;
    }


    public void detonate(int row, int col, GamePiece[][] array) {//marca com null os estouros

        //wall tests
        boolean canUp = (row - 1 >= 0);
        boolean canDown = (row + 1 < array.length);
        boolean canRight = (col + 1 < array[0].length);
        boolean canLeft = (col - 1 >= 0);

        GamePiece value = array[row][col];

        array[row][col] = null;//make boom

        if (canUp && array[row - 1][col] == value) {
            detonate(row - 1, col, array);
        }
        if (canDown && array[row + 1][col] == value) {
            detonate(row + 1, col, array);
        }
        if (canLeft && array[row][col - 1] == value) {
            detonate(row, col - 1, array);
        }
        if (canRight && array[row][col + 1] == value) {
            detonate(row, col + 1, array);
        }


    }


    class MouseInputHandler implements MouseHandler {

        int col_FirstClick;
        int row_FirstClick;

        int col_SecondClick;
        int row_SecondClick;

        int X_mouse;
        int Y_mouse;

        private boolean isFirstClick = true;

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            X_mouse = ((int) mouseEvent.getX() - Board.PADDING) / Board.CELL_SIZE;//convert pixel column
            Y_mouse = ((int) mouseEvent.getY() - Board.PADDING) / Board.CELL_SIZE;//convert pixel row
            System.out.println(X_mouse + " < X --- Y > " + Y_mouse);

            //myGrid.removeImg(X_mouse,Y_mouse);

            if (isFirstClick) {
                col_FirstClick = X_mouse;
                row_FirstClick = Y_mouse;
                System.out.println("First Click");
                isFirstClick = false;

                //myGrid.fillCell(col_FirstClick,row_FirstClick);

            } else {
                col_SecondClick = X_mouse;
                row_SecondClick = Y_mouse;
                System.out.println("Second click");

                isFirstClick = true;

                //testes entram aqui

                if (testSwap(col_FirstClick, row_FirstClick, col_SecondClick, row_SecondClick)) {//se jogada valida (distancia de clicks = 1)
                    swapPiece(col_FirstClick, row_FirstClick, col_SecondClick, row_SecondClick);
                }

            }

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
        }


    }


}
