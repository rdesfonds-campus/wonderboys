package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.Character;

public class Bazooka extends OffensiveEquipment {

    public Bazooka() {
        super("Bazooka légendaire", 20, "bazooka"); // 1d20
    }

    /* @Override
    public void use(Character hero) {
        System.out.println("BOOM ! Bazooka 1d20 dégâts !");
        int degats = (int)(Math.random() * 20) + 1; // 1 à 20
        System.out.println("Dégâts infligés: " + degats);
    }*/


    @Override
    public String toString() {
        return "Bazooka (1d20 dégâts)";
    }
    public void testUse() {
        System.out.println("BOOM ! Bazooka 1d20 dégâts !");
        int degats = (int)(Math.random() * 20) + 1;
        System.out.println("Dégâts infligés: " + degats);
    }

}
