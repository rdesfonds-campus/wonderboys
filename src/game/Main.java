package game;

import characters.Warrior;
import characters.Wizard;
import equipment.OffensiveEquipment;
import equipment.DefensiveEquipment;

public class Main {

    public static void main(String[] args) {

        OffensiveEquipment epee = new OffensiveEquipment("Weapon", 3, "Épée rouillée");
        DefensiveEquipment bouclier = new DefensiveEquipment("Shield", 2, "Petit bouclier");
        Warrior guerrier = new Warrior("Thor", 10, 5, epee, bouclier);

        OffensiveEquipment sort = new OffensiveEquipment("Spell", 4, "Boule de feu");
        DefensiveEquipment potion = new DefensiveEquipment("Potion", 3, "Petite potion");
        Wizard magicien = new Wizard("Merlin", 6, 8, sort, potion);

        System.out.println(guerrier);
        System.out.println(magicien);
    }
}