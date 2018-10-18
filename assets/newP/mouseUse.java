package org.academiadecodigo.pcdev.beje.newP;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class mouseUse implements MouseHandler {

    Grid myGrid;
    int mouseCooX;
    int mouseCooY;

    int primeiroX;
    int primeiroY;

    int segundoX;
    int segundoY;

    private boolean isFirst = true;


    public mouseUse(Grid myGrid) {
        this.myGrid = myGrid;
    }

    public void mouseUse(){
        Mouse newMouse = new Mouse(this);
        newMouse.addEventListener(MouseEventType.MOUSE_CLICKED);

    }


    public int getMouseCooX() {
        return mouseCooX;
    }

    public int getMouseCooY() {
        return mouseCooY;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        mouseCooX = ((int)mouseEvent.getX()-myGrid.getPADDING())/myGrid.getCellSize();
        mouseCooY = ((int)mouseEvent.getY()-myGrid.getPADDING())/myGrid.getCellSize();
        System.out.println(mouseCooX+"X------Y"+mouseCooY);

        //myGrid.removeImg(mouseCooX,mouseCooY);

        if (isFirst){
            primeiroX = mouseCooX;
            primeiroY = mouseCooY;
            System.out.println("First Click");
            isFirst=false;


            myGrid.fillCell(primeiroX,primeiroY);


        }else{
            segundoX = mouseCooX;
            segundoY = mouseCooY;
            System.out.println("Secund click");


            isFirst=true;



                //testes entram aqui
            myGrid.SwapPiece(primeiroX,primeiroY,segundoX,segundoY);
        }

       // myGrid.picArray[mouseCooX][mouseCooX].translate(0,0);






    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
