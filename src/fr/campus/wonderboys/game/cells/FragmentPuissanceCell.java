package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.equipment.FragmentPuissance;

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
