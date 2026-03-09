package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class Flammes extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("🔥 PIÈGE FLAMMES ! Brûlures -3 PV");
        System.out.println("(Dégâts 3 PV, à brancher plus tard)");
    }

    @Override
    public String toString() {
        return "Flammes (3 PV)";
    }
}
