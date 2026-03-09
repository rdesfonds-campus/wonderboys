package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

public class ChauveSouris extends Enemy {

    public ChauveSouris() {
        super("Chauve-souris", 3, 4, (OffensiveEquipment) null, (DefensiveEquipment) null, new Dice(4));
    }


    @Override
    public String toString() {
        return "Chauve-souris (PV: " + getLifeLevel() + ", dégâts: 1d4)";
    }
}
