package characters;

import equipment.OffensiveEquipment;
import equipment.DefensiveEquipment;

public class Wizard extends Character {

    public Wizard(String name, int lifeLevel, int attackLevel,
                  OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Wizard", name, lifeLevel, attackLevel, weapon, defense);
    }

    @Override
    public String toString() {
        return "Wizard : " + getName()
                + " | PV : " + getLifeLevel()
                + " | Attaque : " + getAttackLevel()
                + " | Arme : " + getWeapon()
                + " | Défense : " + getDefense();
    }
}