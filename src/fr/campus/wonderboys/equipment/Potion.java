package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.heros.Character;  // ← AJOUTE ÇA

public class Potion extends DefensiveEquipment {

    public Potion(int defenseLevel, String name) {
        super("Potion", defenseLevel, name);  // ← "Potion" au lieu du package
    }

    @Override
    public void use(Character hero) {
        System.out.println("Potion utilisée ! +" + getDefenseLevel() + " PV");
    }

    @Override
    public String toString() {
        return "Potion(" + getDefenseLevel() + ")";
    }
}
