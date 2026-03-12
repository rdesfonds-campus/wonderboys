package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.heros.Character;

public class SuperSoin extends DefensiveEquipment {

    public SuperSoin() {
        super("Super Soin", 999, "supersoin"); // 999 = PV max
    }

    @Override
    public void use(Character hero) {
        System.out.println("Super Soin ! PV à MAX (20)");
        hero.setLifeLevel(20); // PV max fixe pour l'instant
    }


    @Override
    public String toString() {
        return "Super Soin (PV max)";
    }
}
