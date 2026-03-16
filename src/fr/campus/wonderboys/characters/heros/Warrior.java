package fr.campus.wonderboys.characters.heros;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Warrior extends Character {

    /**
     * Constructeur simplifié (utilisé par DAO et menus)
     */
    public Warrior(String name) {
        super("Warrior", name, 20, 5, null, null);

        setSkill(10);
        setArmorModifier(5);
        setThac0(19);
        setCa(5);
    }

    /**
     * Constructeur complet (si un jour tu charges tout depuis la BDD)
     */
    public Warrior(String name,
                   int lifeLevel,
                   int attackLevel,
                   OffensiveEquipment weapon,
                   DefensiveEquipment defense) {

        super("Warrior", name, lifeLevel, attackLevel, weapon, defense);

        setSkill(10);
        setArmorModifier(5);
        setThac0(19);
        setCa(5);
    }
}