package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.equipment.PetitePotion;

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
