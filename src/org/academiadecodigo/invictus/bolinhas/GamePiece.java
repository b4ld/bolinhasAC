package org.academiadecodigo.invictus.bolinhas;

public enum GamePiece {

    AZUL("assets/blue.png"),
    VERDE("assets/green.png"),
    AMARELO("assets/yellow.png"),
    VERMELHO("assets/red.png"),
    ROSA("assets/pink.png"),
    LARANJA("assets/orange.png"),
    CINZA("assets/grey.png"),
    BROWN("assets/brown.png"),
    DETONATION("assets/explosion.png");

    private String pieceAsset;

    GamePiece(String pieceAsset) {
        this.pieceAsset = pieceAsset;
    }

    public String getPieceAsset() {
        return this.pieceAsset;
    }

    public static int randomPieceValue(){
        return (int) (Math.random() * (GamePiece.values().length-1)); // o ultimo caso é explosao, por isso nao se inclui
    }

    public static int randomPieceValueExcept(GamePiece piece){
        int randomPiece;

        do {
            randomPiece=(int) (Math.random() * (GamePiece.values().length-1));
        } while(randomPiece==piece.ordinal());

        return randomPiece;
    }

}
