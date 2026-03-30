package characters.enemies;

import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

public class Dragon extends Enemy {

    public Dragon() {
        super("Dragon", "Dragon", 20, 8,
                new OffensiveEquipment("Weapon", 8, "Souffle de feu"),
                new DefensiveEquipment("Shield", 6, "Écailles"));
        setScoreValue(50);

    }
}