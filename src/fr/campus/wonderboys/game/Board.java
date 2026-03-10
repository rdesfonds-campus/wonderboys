package fr.campus.wonderboys.game;

import fr.campus.wonderboys.cells.*;
import fr.campus.wonderboys.pieges.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Cell> cells;
    private int totalSquares;

    public Board() { // ← public Board() pas SortUltimaCell
        this.totalSquares = 64;
        this.cells = new ArrayList<>();

        // 64 cases vides d'abord
        for (int i = 0; i < 64; i++) {
            cells.add(new EmptyCell());
        }

        // Place 5 pièges random
        placerPiges(5);

        // Place 10 trésors random (taux %)
        placerTresor(10);

        // Boss case 63
        cells.set(63, new EnemyCell()); // temporaire

        // Monstres commentés pour l'instant
        // placerMonstres(30);
    }

    private void placerPiges(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int pos = (int)(Math.random() * 64);
            Cell piege = piegeAleatoire();
            cells.set(pos, piege);
        }
    }

    private Cell piegeAleatoire() {
        int choix = (int)(Math.random() * 5);
        switch(choix) {
            case 0: return new Trou();
            case 1: return new Mine();
            case 2: return new NuagePoison();
            case 3: return new Eboulement();
            case 4: return new Flammes();
            default: return new Trou();
        }
    }

    private void placerTresor(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int pos = (int)(Math.random() * 64);
            Cell tresor = tresorAleatoire();
            cells.set(pos, tresor);
        }
    }

    private Cell tresorAleatoire() {
        double rand = Math.random();
        if (rand < 0.25) return new PotionSoinCell();
        else if (rand < 0.50) return new FragmentPuissanceCell();
        else if (rand < 0.65) return new SuperSoinCell();
        else if (rand < 0.80) return new AmuletteProtectionCell();
        else if (rand < 0.85) return new BazookaCell();
        else if (rand < 0.90) return new SortUltimaCell();
        else if (rand < 0.95) return new LameVorpaleCell();
        else return new EtoileMarioCell();
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
