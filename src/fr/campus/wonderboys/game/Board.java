package fr.campus.wonderboys.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Plateau de jeu avec 64 cases.
 */
public class Board {

    private List<Cell> cells;
    private int totalSquares;

    public Board() {
        this.totalSquares = 64;
        this.cells = new ArrayList<>();

        // Mini‑plateau de test pour commencer
        for (int i = 0; i < 64; i++) {
            if (i % 4 == 0) {
                cells.add(new EmptyCell());
            } else if (i % 4 == 1) {
                cells.add(new EnemyCell());
            } else if (i % 4 == 2) {
                cells.add(new WeaponCell());
            } else {
                cells.add(new PotionCell());
            }
        }
    }

    public Cell getCell(int index) {
        if (index < 0 || index >= cells.size()) {
            return new EmptyCell();
        }
        return cells.get(index);
    }

    public int getTotalSquares() {
        return totalSquares;
    }

    @Override
    public String toString() {
        return "Board{totalSquares=" + totalSquares + ", cells=" + cells.size() + "}";
    }
}
