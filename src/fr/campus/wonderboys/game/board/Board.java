package fr.campus.wonderboys.game.board;

import fr.campus.wonderboys.game.cells.*;
import fr.campus.wonderboys.game.cells.Cell;
import fr.campus.wonderboys.game.cells.EmptyCell;
import fr.campus.wonderboys.game.cells.EnemyCell;
import fr.campus.wonderboys.pieges.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Plateau de jeu de 64 cases.
 * Gère la distribution aléatoire des pièges, trésors et monstres.
 */
public class Board {

    private final List<Cell> cells;
    private final int totalSquares = 64;
    private int oubliettesCount = 0;

    public Board() {
        this.cells = new ArrayList<>();
        initializeBoard();
    }

    /**
     * Initialise le plateau avec un mélange de cases.
     */
    private void initializeBoard() {
        // 1. Remplir le plateau de cases vides par défaut
        for (int i = 0; i < totalSquares; i++) {
            cells.add(new EmptyCell());
        }

        // 2. Placer les éléments spéciaux
        placerPiges(7);    // Un peu plus de pièges pour le challenge
        placerTresor(12);  // Distribution de butin
        placerMonstres(15); // Distribution d'ennemis

        // 3. Sécuriser la case de départ (Case 0)
        cells.set(0, new EmptyCell());

        // 4. Placer le Boss final sur la dernière case (Case 63)
        cells.set(63, new EnemyCell());
    }

    /**
     * Place des pièges de manière aléatoire.
     */
    private void placerPiges(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int pos = (int) (Math.random() * (totalSquares - 1));
            if (cells.get(pos) instanceof EmptyCell) {
                Cell piege;
                // Limite stricte à 1 oubliette pour ne pas frustrer le joueur
                if (oubliettesCount < 1 && Math.random() < 0.2) {
                    piege = new Oubliette();
                    oubliettesCount++;
                } else {
                    piege = piegeAleatoireNonOubliette();
                }
                cells.set(pos, piege);
            }
        }
    }

    private Cell piegeAleatoireNonOubliette() {
        int choix = (int) (Math.random() * 4);
        return switch (choix) {
            case 0 -> new Mine();
            case 1 -> new NuagePoison();
            case 2 -> new Eboulement();
            case 3 -> new Flammes();
            default -> new Mine();
        };
    }

    /**
     * Place des trésors et des cases de fouille.
     */
    private void placerTresor(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int pos = (int) (Math.random() * (totalSquares - 1));
            if (cells.get(pos) instanceof EmptyCell) {
                // Alternance entre trésor direct et case de fouille aléatoire
                cells.set(pos, Math.random() > 0.4 ? tresorAleatoire() : new FouilleCell());
            }
        }
    }

    private Cell tresorAleatoire() {
        double rand = Math.random();
        if (rand < 0.25) return new PotionSoinCell();
        if (rand < 0.50) return new FragmentPuissanceCell();
        if (rand < 0.65) return new SuperSoinCell();
        if (rand < 0.80) return new AmuletteProtectionCell();
        if (rand < 0.85) return new BazookaCell();
        if (rand < 0.90) return new SortUltimaCell();
        if (rand < 0.95) return new LameVorpaleCell();
        return new EtoileMarioCell();
    }

    /**
     * Place des ennemis sur les cases encore vides.
     */
    private void placerMonstres(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int pos = (int) (Math.random() * (totalSquares - 1));
            if (cells.get(pos) instanceof EmptyCell) {
                cells.set(pos, new EnemyCell());
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
        return "Plateau de " + totalSquares + " cases prêt (Oubliettes: " + oubliettesCount + ")";
    }
}