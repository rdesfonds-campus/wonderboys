package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;  // ← AJOUTE CET IMPORT

/**
 * Case contenant une arme à ramasser.
 *
 * Le joueur peut s'équiper de l'arme pour augmenter sa force.
 *
 * @author Romain D
 * @version 1.0
 */
public class WeaponCell extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("Tu trouves une arme !");
    }

    @Override
    public String toString() {  // ← AJOUTE CETTE MÉTHODE
        return "Case arme";
    }
}
