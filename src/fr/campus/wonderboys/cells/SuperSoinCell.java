package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.SuperSoin;
import fr.campus.wonderboys.game.Cell;

public class SuperSoinCell extends Cell {

    private final SuperSoin superSoin = new SuperSoin();

    @Override
    public void interact(Character perso) {
        System.out.println("Super Trésor trouvé !");
        superSoin.use(perso);
    }

    @Override
    public String toString() {
        return "Trésor: " + superSoin.toString();
    }
}
