package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.AmuletteProtection;
import fr.campus.wonderboys.game.Cell;

public class AmuletteProtectionCell extends Cell {
    private final AmuletteProtection amulette = new AmuletteProtection();

    @Override
    public void interact(Character perso) {
        System.out.println("Amulette trouvée !");
        amulette.use(perso);
    }

    @Override
    public String toString() {
        return "Trésor: " + amulette.toString();
    }
}
