package org.academiadecodigo.invictus.bolinhas;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Game {

    public static int TOTAL_COLUMNS = 10;
    public static int TOTAL_ROWS = 8;

    Mouse mouseBoard;
    MouseHandler mouseHandler;
    MouseEvent mouseEvent;

    private Picture startMenuBackground = new Picture(Board.PADDING, Board.PADDING, "assets/logobolinhas.png");
    private Board gameBoard;
    private Boolean outOfBackground = false;
    private GamePiece[][] gameArray;
    private Rectangle rect;


    //Sounds
    private Sound initMenu = new Sound("assets/sounds/menu.wav");

    private Sound explosion = new Sound("assets/sounds/slap.wav");


    public Game() {
        mouseHandler = new MouseInputHandler();
        mouseBoard = new Mouse(mouseHandler);
        mouseBoard.addEventListener(MouseEventType.MOUSE_CLICKED);

        gameBoard = new Board();

        //Draw the background
        startMenuBackground.draw();
        initMenu.loop();
    }

    public void startMenuInit() {
        initMenu.close();
        startMenuBackground.delete();
        System.out.println("deleted image background");
        gameInit();
        System.out.println("Game initiated");


    }

    public void gameInit() {

        gameBoard.init();

        gameArray = new GamePiece[TOTAL_ROWS][TOTAL_COLUMNS];

        for (int row = 0; row < TOTAL_ROWS; row++) {
            for (int col = 0; col < TOTAL_COLUMNS; col++) {
                gameArray[row][col] = GamePieceFactory.getNewPiece();
            }
        }

        gameCheckInitialDetonations();

        gameBoard.drawInitPieces(gameArray);

    }


    public boolean gameCheckInitialDetonations() {

        HashSet<Point> matches = new HashSet<>();

        for (int row = 0; row < TOTAL_ROWS; row++) {
            for (int col = 0; col < TOTAL_COLUMNS; col++) {

                while (true) {

                    matches.clear();

                    locateNeighbors(row, col, pointAt(row, col), matches);

                    if (matches.size() < 3)
                        break;

                    if (matches.size() >= 3) {
                        Iterator<Point> piecesToRegen = matches.iterator();

                        while (piecesToRegen.hasNext()) {
                            Point point = piecesToRegen.next();
                            gameArray[point.x][point.y] = GamePieceFactory.getNewPiece();
                        }
                    }

                }

            }
        }

        return true;
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

        //conta pecas similares aquelas que foram swapped
        if (matchesFirst.size() >= 3) {
            //   System.out.println("first");
            detonate(matchesFirst);
        }

        if (matchesSecond.size() >= 3) {
            //   System.out.println("second");
            detonate(matchesSecond);
        }

        gameBoard.redrawAllPieces(gameArray);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        drop();

        refill();

        gameBoard.redrawAllPieces(gameArray);

        //Testar explosoes subsequente?

        autoDetonations();

        gameBoard.redrawAllPieces(gameArray);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        drop();

        refill();

        gameBoard.redrawAllPieces(gameArray);

        //     }

    }


    public boolean autoDetonations() {

        HashSet<Point> matchesAll = new HashSet<>();

        HashSet<Point> matches = new HashSet<>();

        for (int row = 0; row < TOTAL_ROWS; row++) {
            for (int col = 0; col < TOTAL_COLUMNS; col++) {

                while (true) {

                    matches.clear();

                    locateNeighbors(row, col, pointAt(row, col), matches);

                    if (matches.size() < 3)
                        break;

                    if (matches.size() >= 3) {
                        matchesAll.addAll(matches);
                        break;
                    }

                }

            }
        }
        detonate(matchesAll);
        return true;
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
            gameArray[point.x][point.y] = GamePiece.DETONATION;
            gameArray[point.x][point.y] = GamePiece.DETONATION;

        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                explosion.open();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                explosion.close();
            }
        }).start();


    }


    public void drop() {

        //   int nRows = gam.length;
        //   int nCols = arrayPic[0].length;
        //   Picture pi = new Picture();

        for (int col = 0; col < TOTAL_COLUMNS; col++) {

            LinkedList<GamePiece> list = new LinkedList<>();

            for (int row = 0; row < TOTAL_ROWS; row++) {
                if (gameArray[row][col] != GamePiece.DETONATION) {
                    list.add(gameArray[row][col]);
                }
            }


            int listSize = list.size();

            for (int row = 0; row < (TOTAL_ROWS - listSize); row++) {
                gameArray[row][col] = GamePiece.DETONATION;
            }

            for (int row = (TOTAL_ROWS - listSize); row < TOTAL_ROWS; row++) {
                gameArray[row][col] = list.removeFirst();
            }

        }

    }

    public void refill() {

        for (int row = 0; row < TOTAL_ROWS; row++) {
            for (int col = 0; col < TOTAL_COLUMNS; col++) {
                if (gameArray[row][col] == GamePiece.DETONATION) {
                    gameArray[row][col] = GamePieceFactory.getNewPiece();
                }
            }
        }
        //   gameBoard.redrawAllPieces(gameArray);

    }


//---------


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

            Sound clickSound = new Sound("assets/sounds/click.wav");
            clickSound.open();
            //myGrid.removeImg(X_mouse,Y_mouse);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Flag to exit of StartBackgroundImage after click START
            clickSound.close();

            if (!outOfBackground) {
                if ((X_mouse == 4 || X_mouse == 5) && (Y_mouse == 3 || Y_mouse == 4)) {
                    System.out.println("startMenuInit");
                    startMenuInit();
                    outOfBackground = true;
                }
            } else {
                if (isFirstClick) {
                    col_FirstClick = X_mouse;
                    row_FirstClick = Y_mouse;
                    System.out.println("First Click");
                    isFirstClick = false;

                    System.out.println(col_FirstClick + "  " + row_FirstClick);
                    //Quadradinho para seleçao do cenas------------------------------
                    //rect = new Rectangle(mouseEvent.getX()-43,mouseEvent.getY()-50,100,100);
                    //rect.setColor(Color.BLUE);
                    //rect.drawi();

                    //myGrid.fillCell(col_FirstClick,row_FirstClick);

                } else {
                    col_SecondClick = X_mouse;
                    row_SecondClick = Y_mouse;
                    System.out.println("Second click");
                    isFirstClick = true;

                    //testes entram aqui
                    if (testSwap(col_FirstClick, row_FirstClick, col_SecondClick, row_SecondClick)) {//se jogada valida (distancia de clicks = 1)
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                swapPiece(col_FirstClick, row_FirstClick, col_SecondClick, row_SecondClick);
                            }
                        }).start();

                    }

                }
            }

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
        }


    }


}



