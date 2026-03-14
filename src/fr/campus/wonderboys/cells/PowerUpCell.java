package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.PowerUp;
import fr.campus.wonderboys.game.Cell;

public class PowerUpCell extends Cell {

    private final PowerUp fragment = new PowerUp();

    @Override
    public void interact(Character perso) {
        System.out.println("POWER-UP ! Tu détiens la force toute puissante");
        // power.use(perso); ← à décommenter quand setOffensiveEquipment OK
    }

    @Override
    public String toString() {
        return "Trésor: " + fragment.toString();
    }
}
