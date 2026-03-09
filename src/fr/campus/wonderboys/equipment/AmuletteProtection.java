package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.Character;

public class AmuletteProtection extends DefensiveEquipment {

    public AmuletteProtection() {
        super("Amulette protection", 3, "amulette"); // -3 dégâts
    }

    @Override
    public void use(Character hero) {
        System.out.println("Tu trouves une amulette de protection ! (-3 dégâts subis, à gérer plus tard)");
        // Pour l'instant, pas de logique sur hero pour ne pas casser Character
    }

    @Override
    public String toString() {
        return "Amulette (-3 dégâts subis)";
    }
}
