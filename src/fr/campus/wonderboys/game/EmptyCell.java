package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;

/**
 * Case vide du plateau de jeu.
 */
public class EmptyCell extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("Case vide. Rien ne se passe.");
    }

    @Override
    public String toString() {
        return "Case vide";
    }
}
