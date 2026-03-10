package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.PetitePotion;
import fr.campus.wonderboys.game.Cell;

public class PotionSoinCell extends Cell {
    private PetitePotion potion = new PetitePotion();

    @Override
    public void interact(Character perso) {
        potion.use(perso);
    }

    @Override
    public String toString() {
        return "Trésor: " + potion.toString();
    }
}
