package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.LameVorpale;
import fr.campus.wonderboys.game.Cell;

public class LameVorpaleCell extends Cell {

    private final LameVorpale lame = new LameVorpale();

    @Override
    public void interact(Character perso) {
        System.out.println("Lame Vorpale trouvée !");
        lame.testUse();
    }

    @Override
    public String toString() {
        return "Trésor: " + lame.toString();
    }
}
