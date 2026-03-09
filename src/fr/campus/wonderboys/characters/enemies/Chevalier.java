package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Chevalier extends Enemy {

    public Chevalier() {
        super("Chevalier", 12, 6, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Chevalier (PV: " + getLifeLevel() + ", dégâts: 1d6)";
    }
}
