package org.academiadecodigo.invictus.bolinhas;

public class GamePieceFactory {

    public static GamePiece getNewPiece() {

        int random = (int) (Math.random() * (GamePiece.values().length)); //remover exploded e free spot
        GamePiece newPiece = GamePiece.values()[random];

        return newPiece;

    }

}