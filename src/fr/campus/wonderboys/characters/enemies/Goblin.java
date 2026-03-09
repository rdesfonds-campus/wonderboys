package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Gobelin : ennemi faible.
 */
public class Goblin extends Enemy {

    public Goblin(String name, int lifeLevel, int attackLevel,
                  OffensiveEquipment weapon, DefensiveEquipment defense) {
        super(name, lifeLevel, attackLevel, weapon, defense);
    }

    @Override
    public void onDefeated() {
        System.out.println("Le " + getName() + " lâche 10 XP !");
        super.onDefeated();
    }

    @Override
    public String toString() {
        return "Goblin " + getName() + " (PV:" + getLifeLevel() + ", ATK:" + getAttackLevel() + ")";
    }
}
