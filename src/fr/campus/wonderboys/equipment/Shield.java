package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.heros.Character;

public class Shield extends DefensiveEquipment {

    private String name;
    private int defense;

    public Shield() {
        super("Bouclier", 2, "shield");  // ← AJOUTE ÇA
    }

    @Override
    public void use(Character hero) {
        System.out.println("Bouclier activé !");
    }

    @Override
    public String toString() {
        return "Bouclier (défense: 2)";
    }
}
