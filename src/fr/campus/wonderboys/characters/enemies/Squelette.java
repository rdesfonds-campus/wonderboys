package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Squelette extends Enemy {

    public Squelette() {
        super("Squelette", 9, 6, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Squelette (PV: " + getLifeLevel() + ", dégâts: 1d6)";
    }
}
