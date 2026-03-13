package fr.campus.wonderboys.characters.heros;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.*;

public class HeroFactory {

    public static Character createHero(int choice, String name) {

        if (choice == 1) {

            return new Warrior(
                    name,
                    10,
                    10,
                    new Weapon(3, "Épée rouillée"),
                    new Shield()
            );

        } else {

            return new Wizard(
                    name,
                    6,
                    8,
                    new OffensiveEquipment("Spell", 4, "Boule de feu"),
                    new Potion(3, "Potion")
            );
        }
    }
}