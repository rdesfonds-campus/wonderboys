package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

public class PapaOgre extends Enemy {

    public PapaOgre() {
        super("Papa ogre", 25, 8, (OffensiveEquipment) null, (DefensiveEquipment) null, new Dice(8));
    }

    @Override
    public String toString() {
        return "Papa ogre BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8)";
    }
}
