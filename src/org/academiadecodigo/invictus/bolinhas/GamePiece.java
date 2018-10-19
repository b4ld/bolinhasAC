package org.academiadecodigo.invictus.bolinhas;

public enum GamePiece {

    AZUL("assets/blue.png"),
    VERDE("assets/green.png"),
    AMARELO("assets/yellow.png"),
    VERMELHO("assets/red.png"),
    ROSA("assets/pink.png"),
    LARANJA("assets/orange.png");

    private String pieceAsset;

    GamePiece(String pieceAsset) {
        this.pieceAsset = pieceAsset;
    }

    public String getPieceAsset() {
        return this.pieceAsset;
    }
}
