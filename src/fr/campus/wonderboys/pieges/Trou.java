package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class Trou extends Cell { // Hérite de Cell (abstraite)

    @Override
    public void interact(Character hero) {
        System.out.println("💀 PIÈGE TROU ! Tu tombes et perds 4 PV");
        // hero.setLifeLevel(hero.getLifeLevel() - 4); ← on commente pour pas casser
        System.out.println("(Dégâts 4 PV, à brancher plus tard)");
    }

    @Override
    public String toString() {
        return "Trou (4 PV)";
    }
}
