package characters.enemies;

import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

public class Orc extends Enemy {

    public Orc() {
        super("Orc", "Orc", 8, 4,
                new OffensiveEquipment("Weapon", 4, "Massue"),
                new DefensiveEquipment("Shield", 2, "Armure de cuir"));
        setScoreValue(25);
    }
}