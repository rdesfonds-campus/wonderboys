package characters.enemies;

import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

public class Goblin extends Enemy {

    public Goblin() {
        super("Goblin", "Gobelin", 4, 2,
                new OffensiveEquipment("Weapon", 2, "Couteau rouillé"),
                new DefensiveEquipment("Shield", 1, "Peau de bête"));

        setScoreValue(10);
    }
}