package org.academiadecodigo.invictus.bolinhas;




import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Game {


    Mouse mouseBoard;
    MouseHandler mouseHandler;
    MouseEvent mouseEvent;

    int mouseCooX;
    int mouseCooY;

    int primeiroX;
    int primeiroY;

    int segundoX;
    int segundoY;

    private boolean isFirst = true;

    private Board gameBoard;

    private GamePiece[][] gameArray;

    public Game()
    {

        mouseHandler = new MouseInputHandler();
        mouseBoard = new Mouse(mouseHandler);
        mouseBoard.addEventListener(MouseEventType.MOUSE_CLICKED);

        gameBoard = new Board();
    }


    public void gameInit() {

        gameBoard.init();
        gameBoard.drawInitialPictures();

    }

    public void gameStart() {


    }


    public void swapPieceLogic(int Xp1,int Yp1, int Xp2, int Yp2){

        //Falta implementar a canSwap - falar com rui
        //testes logicos

        //Actualiza gameArray
        GamePiece aux = gameArray[Yp1][Xp1];
        gameArray[Yp1][Xp1] = gameArray[Yp2][Xp2];
        gameArray[Yp2][Xp2] = aux;

        gameBoard.swapPieceDraw(Xp1, Yp1, Xp2, Yp2); //faz draw das mudancas

    }


    class MouseInputHandler implements MouseHandler {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

            mouseCooX = ((int) mouseEvent.getX() - Board.PADDING) / Board.CELL_SIZE;
            mouseCooY = ((int) mouseEvent.getY() - Board.PADDING) / Board.CELL_SIZE;
            System.out.println(mouseCooX + "X------Y" + mouseCooY);

            //myGrid.removeImg(mouseCooX,mouseCooY);

            if (isFirst) {
                primeiroX = mouseCooX;
                primeiroY = mouseCooY;
                System.out.println("First Click");
                isFirst = false;


                //myGrid.fillCell(primeiroX,primeiroY);


            } else {
                segundoX = mouseCooX;
                segundoY = mouseCooY;
                System.out.println("Second click");

                isFirst = true;

                //testes entram aqui


               // game.SwapPiece(primeiroX, primeiroY, segundoX, segundoY);

            }

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
        }


    }


}
