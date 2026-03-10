package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.Bazooka;
import fr.campus.wonderboys.game.Cell;

public class BazookaCell extends Cell {
    private final Bazooka bazooka = new Bazooka();

    @Override
    public void interact(Character perso) {
        System.out.println("Bazooka trouvé !");
        bazooka.testUse();
    }

    @Override
    public String toString() {
        return "Trésor: " + bazooka.toString();
    }
}
