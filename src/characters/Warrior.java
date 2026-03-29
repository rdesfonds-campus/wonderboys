package characters;

import equipment.OffensiveEquipment;
import equipment.DefensiveEquipment;

public class Warrior extends Character {

    // Warrior appelle le constructeur de Character avec "Warrior" comme type
    public Warrior(String name, int lifeLevel, int attackLevel,
                   OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Warrior", name, lifeLevel, attackLevel, weapon, defense);
    }


    @Override
    public String toString() {
        return "Warrior : " + getName()
                + " | PV : " + getLifeLevel()
                + " | Attaque : " + getAttackLevel()
                + " | Arme : " + getWeapon()
                + " | Défense : " + getDefense();
    }
}