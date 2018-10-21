package org.academiadecodigo.invictus.bolinhas;

public class Main {

    public static void main(String[] args) {

        Game game= new Game();
        //game.startMenuInit(); //This is initialized in startMenu
        //game.gameStart();





        String[][] testarray = new String[][]{
                {"x", "i", "i", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "i"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},
                {"x", "x", "x", "x", "x"},




        };



        Game.Drop(testarray);





    }

}
