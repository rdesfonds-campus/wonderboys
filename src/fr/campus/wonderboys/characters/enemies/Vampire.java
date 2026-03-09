package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

public class Vampire extends Enemy {

    public Vampire() {
        super("Vampire", 20, 8, (OffensiveEquipment) null, (DefensiveEquipment) null, new Dice(8));
    }

    @Override
    public String toString() {
        return "Vampire BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8)";
    }
}
