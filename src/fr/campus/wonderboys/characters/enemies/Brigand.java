package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Brigand extends Enemy {

    public Brigand() {
        super("Brigand", 6, 6, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Brigand (PV: " + getLifeLevel() + ", dégâts: 1d6)";
    }
}
