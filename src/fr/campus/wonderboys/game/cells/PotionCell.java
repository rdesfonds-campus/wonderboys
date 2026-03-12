package fr.campus.wonderboys.game.cells;
import fr.campus.wonderboys.characters.heros.Character;

/**
 * Case contenant une potion de soin.
 *
 * Le joueur peut boire la potion pour récupérer de la vie.
 *
 * @author Romain D
 * @version 1.0
 */
public class PotionCell extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("Tu trouves une potion !");
        // Plus tard : gain de vie
    }

    @Override
    public String toString() {
        return "Case potion";
    }
}
