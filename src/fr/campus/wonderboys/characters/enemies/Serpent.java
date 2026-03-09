package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Serpent extends Enemy {

    public Serpent() {
        super("Serpent", 4, 4, (OffensiveEquipment) null, (DefensiveEquipment) null);
    }

    @Override
    public String toString() {
        return "Serpent (PV: " + getLifeLevel() + ", dégâts: 1d4)";
    }
}
