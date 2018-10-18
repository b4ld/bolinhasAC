package org.academiadecodigo.GroupProject1;

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
