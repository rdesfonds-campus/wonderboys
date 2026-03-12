package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.equipment.LameVorpale;

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
