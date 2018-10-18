package org.academiadecodigo.invictus.bolinhas;

public enum GamePiece {

    AZUL("Azul"),
    VERDE("Verde"),
    AMARELO("Amarelo"),
    VERMELHO("Vermelho");

    private String nameGamePiece;

    GamePiece(String nameGamePiece) {
        this.nameGamePiece = nameGamePiece;
    }
}
