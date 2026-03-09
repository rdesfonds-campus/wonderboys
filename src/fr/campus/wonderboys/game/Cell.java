package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;

/**
 * Représente une case du plateau de jeu.
 *
 * Types possibles : case vide, ennemi, arme, potion, etc.
 * Toutes les cases spécifiques héritent de cette classe.
 */
public abstract class Cell {

    /**
     * Méthode appelée quand un personnage arrive sur cette case.
     */
    public abstract void interact(Character hero);

    /**
     * Retourne une description textuelle de la case.
     *
     * @return chaîne de caractères décrivant la case (ex: "Case vide")
     */
    @Override
    public abstract String toString();
}
