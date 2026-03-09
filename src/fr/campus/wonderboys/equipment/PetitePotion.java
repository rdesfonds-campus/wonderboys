package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.Character;

/**
 * Potion qui soigne +5 PV
 */
public class PetitePotion extends DefensiveEquipment {

    public PetitePotion() {
        super("Petite potion", 5, "potion");  // ← CONSTRUCTEUR EXPLICITE
    }

    @Override
    public void use(Character hero) {
        System.out.println("Tu bois la petite potion ! +5 PV");
        int nouveauxPV = hero.getLifeLevel() + 5;
        hero.setLifeLevel(nouveauxPV);
    }

    @Override
    public String toString() {
        return "Petite potion (+5 PV)";
    }
}
