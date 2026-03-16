package fr.campus.wonderboys.characters.heros;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

public class Wizard extends Character {

    /**
     * Constructeur simplifié
     */
    public Wizard(String name) {
        super("Wizard", name, 15, 8, null, null);

        setSkill(12);
        setArmorModifier(2);
        setThac0(18);
        setCa(8);
    }

    /**
     * Constructeur complet
     */
    public Wizard(String name,
                  int lifeLevel,
                  int attackLevel,
                  OffensiveEquipment weapon,
                  DefensiveEquipment defense) {

        super("Wizard", name, lifeLevel, attackLevel, weapon, defense);

        setSkill(12);
        setArmorModifier(2);
        setThac0(18);
        setCa(8);
    }
}