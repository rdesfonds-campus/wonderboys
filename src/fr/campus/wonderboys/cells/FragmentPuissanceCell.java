package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.FragmentPuissance;
import fr.campus.wonderboys.game.Cell;

public class FragmentPuissanceCell extends Cell {

    private final FragmentPuissance fragment = new FragmentPuissance();

    @Override
    public void interact(Character perso) {
        System.out.println("Tu trouves un Fragment de Puissance !");
        // fragment.use(perso); ← à décommenter quand setOffensiveEquipment OK
    }

    @Override
    public String toString() {
        return "Trésor: " + fragment.toString();
    }
}
