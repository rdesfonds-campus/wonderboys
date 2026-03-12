package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.game.cells.Cell;

public class Oubliette extends Cell { // Hérite de Cell (abstraite)

    @Override
    public void interact(Character hero) {
        System.out.println("💀 OUBLIETTE ! Tu tombes et retournes à la case 1.");
        hero.setBoardPosition(1);  // Remet à la case départ
        // Pas de dégâts, tu gardes tes PV, score et objets !
    }

    @Override
    public String toString() {
        return "Oubliette (retour case 1)";
    }
}

