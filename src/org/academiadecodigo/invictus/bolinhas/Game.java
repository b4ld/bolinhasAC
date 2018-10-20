package org.academiadecodigo.invictus.bolinhas;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

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
            }
        }

        gameBoard.drawInitPieces(gameArray);

    }


    public void gameStart() {

    }


    public void swapPiece(int col_FirstClick, int row_FirstClick, int col_SecondClick, int row_SecondClick) {

        //actualiza array logico - gameArray

        GamePiece temp = gameArray[row_FirstClick][col_FirstClick];
        gameArray[row_FirstClick][col_FirstClick] = gameArray[row_SecondClick][col_SecondClick];
        gameArray[row_SecondClick][col_SecondClick] = temp;

        HashSet<Point> matchesFirst = new HashSet<>();
        HashSet<Point> matchesSecond = new HashSet<>();

        locateNeighbors(row_FirstClick, col_FirstClick, pointAt(row_FirstClick, col_FirstClick), matchesFirst);
        locateNeighbors(row_SecondClick, col_SecondClick, pointAt(row_SecondClick, col_SecondClick), matchesSecond);

        //     System.out.println(matchesFirst.size());
        //     System.out.println(matchesSecond.size());

        //conta pecas similares aquelas que foram swapped
        if (matchesFirst.size() >= 3) {
            System.out.println("first");
            detonate(matchesFirst);
        }

        if (matchesSecond.size() >= 3) {
            System.out.println("second");
            detonate(matchesSecond);
        }

        //testes explosoes e etc

        //desenha peças trocadas se nada explodiu e tal
//        gameBoard.drawPiece(row_FirstClick, col_FirstClick, gameArray[row_FirstClick][col_FirstClick]);
//        gameBoard.drawPiece(row_SecondClick, col_SecondClick, gameArray[row_SecondClick][col_SecondClick]);

        gameBoard.drawAllPieces(gameArray);


    }


    //testa se so anda uma casa

    public boolean testSwap(int col_FirstClick, int row_FirstClick, int col_SecondClick, int row_SecondClick) {

        int moveX = Math.abs(col_FirstClick - col_SecondClick);
        int moveY = Math.abs(row_FirstClick - row_SecondClick);

        return (moveX == 0 && moveY == 1) || (moveX == 1 && moveY == 0);
    }


    public GamePiece pointAt(int row, int column) {

        return gameArray[row][column];

    }

    private void locateNeighbors(int row, int col, GamePiece color, HashSet<Point> matches) {

        Point p = new Point(row, col);

        if (matches.contains(p)) {
            return;
        } else {
            matches.add(p);
        }

        //Check east
        if (col + 1 < gameArray[0].length) {
            if (pointAt(row, col + 1) == color)
                locateNeighbors(row, col + 1, color, matches);
        }

        //Check south
        if (row + 1 < gameArray.length) {
            if (pointAt(row + 1, col) == color)
                locateNeighbors(row + 1, col, color, matches);
        }

        //Check west
        if (col - 1 >= 0) {
            if (pointAt(row, col - 1) == color)
                locateNeighbors(row, col - 1, color, matches);
        }

        //Check north
        if (row - 1 >= 0) {
            if (pointAt(row - 1, col) == color)
                locateNeighbors(row - 1, col, color, matches);
        }

    }


    public void detonate(HashSet<Point> matches) {//marca com null os estouros

        Iterator<Point> detonations = matches.iterator();

        while (detonations.hasNext()) {
            Point point = detonations.next();
            gameArray[point.x][point.y]= GamePiece.DETONATION;
            gameArray[point.x][point.y]= GamePiece.DETONATION;
        }

    }



    /*

    //find similar pieces to the one in a(a),b(b)
    public int findConnectedPieces(int a, int b, GamePiece[][] array) {

        //wall tests
        boolean canUp = (a - 1 >= 0);
        boolean canDown = (a + 1 < array.length);
        boolean canRight = (b + 1 < array[0].length);
        boolean canLeft = (b - 1 >= 0);

        GamePiece testValue = array[a][b];

        if(testValue==GamePiece.LARANJA) return 0; //a explosao ja passou por aqui, salta fora

        int up = 0;
        int down = 0;
        int right = 0;
        int left = 0;

        array[a][b] = GamePiece.LARANJA;

        if (canUp && array[a - 1][b] == testValue) {
            up = findConnectedPieces(a - 1, b, array);
        }
        if (canDown && array[a + 1][b] == testValue) {
            down = findConnectedPieces(a + 1, b, array);
        }
        if (canLeft && array[a][b - 1] == testValue) {
            left = findConnectedPieces(a, b - 1, array);
        }
        if (canRight && array[a][b + 1] == testValue) {
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

*/



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



