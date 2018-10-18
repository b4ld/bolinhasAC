package org.academiadecodigo.pcdev.beje.newP;

public class Main {

    public static void main(String[] args) {
        //initilize the grid

        Grid grid = new Grid();
        mouseUse m = new mouseUse(grid);

        grid.drawCell();
        grid.drawImages();
        m.mouseUse();


        //initialize the mouse



    }
}
