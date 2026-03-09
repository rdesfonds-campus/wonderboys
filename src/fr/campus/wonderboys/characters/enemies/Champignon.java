package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

public class Champignon extends Enemy {

    public Champignon() {
        super("Champignon", 5, 4, (OffensiveEquipment) null, (DefensiveEquipment) null, new Dice(4));
    }


    @Override
    public String toString() {
        return "Champignon (PV: " + getLifeLevel() + ", dégâts: 1d4)";
    }
}
