package fr.campus.wonderboys.game;

public class Board {

    private int totalSquares;

    public Board() {
        this.totalSquares = 64;
    }

    public int getTotalSquares() {
        return totalSquares;
    }

    @Override
    public String toString() {
        return "fr.campus.wonderboys.game.Board{totalSquares=" + totalSquares + "}";
    }
}
