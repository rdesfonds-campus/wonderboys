package fr.campus.wonderboys.game;

import fr.campus.wonderboys.cells.*;
import fr.campus.wonderboys.pieges.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Plateau de jeu 64 cases avec génération random de pièges/trésors.
 * Max 1 Oubliette pour raréfaction.
 */
public class Board {

    private List<Cell> cells;
    private int totalSquares;
    /**
     * Compteur oubliettes placées (max 1).
     */
    private int oubliettesCount = 0;

    /**
     * Constructeur : 64 cases vides + 5 pièges + 10 trésors + boss case 63.
     */
    public Board() {
        this.totalSquares = 64;
        this.cells = new ArrayList<>();

        // 64 cases vides d'abord
        for (int i = 0; i < 64; i++) {
            cells.add(new EmptyCell());
        }

        // Place 5 pièges random (max 1 Oubliette)
        placerPiges(5);

        // Place 10 trésors random
        placerTresor(10);

        // Boss case 63
        cells.set(63, new EnemyCell()); // temporaire

        // Monstres commentés pour l'instant
        placerMonstres(30);
    }

    /**
     * Place 'nombre' pièges random, max 1 Oubliette.
     * @param nombre nombre de pièges à placer
     */
    private void placerPiges(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int pos = (int)(Math.random() * 64);
            Cell piege;
            if (oubliettesCount < 1 && Math.random() < 0.3) { // 30% chance si <1
                piege = new Oubliette();
                oubliettesCount++;
            } else {
                piege = piegeAleatoireNonOubliette();
            }
            cells.set(pos, piege);
        }
    }

    /**
     * Piège aléatoire SANS oubliette (pour raréfaction).
     * @return Cell piège non-oubliette
     */
    private Cell piegeAleatoireNonOubliette() {
        int choix = (int)(Math.random() * 4); // 4 types
        switch(choix) {
            case 0: return new Mine();
            case 1: return new NuagePoison();
            case 2: return new Eboulement();
            case 3: return new Flammes();
            default: return new Mine();
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
    private void placerMonstres(int nombre) {

        for (int i = 0; i < nombre; i++) {

            int pos = (int)(Math.random() * 63) + 1;

            if (!(cells.get(pos) instanceof EnemyCell)) {
                cells.set(pos, new EnemyCell());
            }
        }
    }
    /**
     * Récupère la Cell à l'index (0-based, safe).
     * @param index position sur plateau (1-64 → 0-63)
     * @return Cell ou EmptyCell si hors limites
     */
    public Cell getCell(int index) {
        if (index < 0 || index >= cells.size()) {
            return new EmptyCell();
        }
        return cells.get(index);
    }

    /**
     * Nombre total de cases (64).
     * @return int 64
     */
    public int getTotalSquares() {
        return totalSquares;
    }

    @Override
    public String toString() {
        return "Board{totalSquares=" + totalSquares + ", cells=" + cells.size() +
                ", oubliettes=" + oubliettesCount + "}";
    }
}
