package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;

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

    @Override
    public void enEntrant(){
        System.out.println("C'est vide");
    }
}
