package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.equipment.SuperSoin;

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
