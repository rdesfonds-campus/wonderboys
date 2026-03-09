package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Champignon extends Enemy {

    public Champignon() {
        super("Champignon", 5, 4, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Champignon (PV: " + getLifeLevel() + ", dégâts: 1d4)";
    }
}
