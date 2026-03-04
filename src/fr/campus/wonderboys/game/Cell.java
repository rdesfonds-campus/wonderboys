package fr.campus.wonderboys.game;

/**
 * Représente une case du plateau de jeu.
 *
 * Types possibles : case vide, ennemi, arme, potion, etc.
 * Toutes les cases spécifiques héritent de cette classe.
 *
 * @author Romain D
 * @version 1.0
 */
public abstract class Cell {

    /**
     * Retourne une description textuelle de la case.
     *
     * @return chaîne de caractères décrivant la case (ex: "Case vide")
     */
    @Override
    public abstract String toString();
}
