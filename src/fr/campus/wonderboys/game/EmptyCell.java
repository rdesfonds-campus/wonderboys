package fr.campus.wonderboys.game;

/**
 * Case vide du plateau de jeu.
 *
 * Le joueur peut passer dessus sans interaction spéciale.
 *
 * @author Romain D
 * @version 1.0
 */
public class EmptyCell extends Cell {

    @Override
    public String toString() {
        return "Case vide";
    }
}
