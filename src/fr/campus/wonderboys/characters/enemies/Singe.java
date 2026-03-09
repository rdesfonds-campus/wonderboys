package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Singe extends Enemy {

    public Singe() {
        super("Singe", 5, 4, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Singe (PV: " + getLifeLevel() + ", dégâts: 1d4)";
    }
}
