package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.equipment.AmuletteProtection;

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
