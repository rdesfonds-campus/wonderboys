package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

public class Golem extends Enemy {

    public Golem() {
        super("Golem", 16, 8, (OffensiveEquipment) null, (DefensiveEquipment) null, new Dice(8));
    }

    @Override
    public String toString() {
        return "Golem BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8)";
    }
}
