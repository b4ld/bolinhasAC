package org.academiadecodigo.invictus.bolinhas;

public class GamePieceFactory {

    public static GamePiece getNewPiece() {

        GamePiece newPiece = GamePiece.values()[GamePiece.randomPieceValue()];

        return newPiece;

    }

}