package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Crabe extends Enemy {

    public Crabe() {
        super("Crabe", 6, 4, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Crabe (PV: " + getLifeLevel() + ", dégâts: 1d4)";
    }
}
