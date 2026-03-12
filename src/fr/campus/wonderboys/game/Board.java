package fr.campus.wonderboys.game;

import fr.campus.wonderboys.cells.*;
import fr.campus.wonderboys.characters.Character;
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
     * Constructeur : 64 cases vides + pièges + trésors + boss.
     */
    public Board() {

        this.totalSquares = 64;
        this.cells = new ArrayList<>();

        // 64 cases vides
        for (int i = 0; i < 64; i++) {
            cells.add(new EmptyCell());
        }

        // Place 5 pièges
        placerPiges(5);

        // Place 10 trésors
        placerTresor(10);

        // Boss final
        cells.set(63, new EnemyCell());
    }

    /**
     * Place 'nombre' pièges random (max 1 oubliette)
     */
    private void placerPiges(int nombre) {

        for (int i = 0; i < nombre; i++) {

            int pos = (int)(Math.random() * 64);

            Cell piege;

            if (oubliettesCount < 1 && Math.random() < 0.3) {
                piege = new Oubliette();
                oubliettesCount++;
            }
            else {
                piege = piegeAleatoireNonOubliette();
            }

            cells.set(pos, piege);
        }
    }

    /**
     * Piège aléatoire sans oubliette
     */
    private Cell piegeAleatoireNonOubliette() {

        int choix = (int)(Math.random() * 4);

        switch(choix) {

            case 0: return new Mine();
            case 1: return new NuagePoison();
            case 2: return new Eboulement();
            case 3: return new Flammes();

            default: return new Mine();
        }
    }

    /**
     * Place les trésors sur le plateau
     */
    private void placerTresor(int nombre) {

        for (int i = 0; i < nombre; i++) {

            int pos = (int)(Math.random() * 64);

            Cell tresor = tresorAleatoire();

            cells.set(pos, tresor);
        }
    }

    /**
     * Génération d'un trésor aléatoire
     */
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

    /**
     * Récupère la cellule à une position
     */
    public Cell getCell(int index) {

        if (index < 0 || index >= cells.size()) {
            return new EmptyCell();
        }

        return cells.get(index);
    }

    /**
     * Résout l'effet de la case quand un joueur arrive dessus.
     * (Code extrait de Game.java)
     */
    public void resolveCell(Character player, int position) {

        Cell caseActuelle = getCell(position - 1);

        if (caseActuelle instanceof EmptyCell) {

            System.out.println("La salle est vide.");

        }

        else if (caseActuelle instanceof EnemyCell) {

            System.out.println("Un monstre garde cette salle !");

            // Le combat sera géré ailleurs (CombatSystem)

        }

        else if (caseActuelle instanceof FouilleCell) {

            System.out.println("Tu trouves un trésor !");

            caseActuelle.interact(player);

        }

        else {

            System.out.println("La case contient un piège ou un objet.");

            caseActuelle.interact(player);

        }
    }

    /**
     * Nombre total de cases
     */
    public int getTotalSquares() {

        return totalSquares;

    }

    @Override
    public String toString() {

        return "Board{totalSquares=" + totalSquares +
                ", cells=" + cells.size() +
                ", oubliettes=" + oubliettesCount + "}";

    }
}