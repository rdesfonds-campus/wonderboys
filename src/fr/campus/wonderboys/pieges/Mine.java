package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class Mine extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("💥 PIÈGE MINE ! BOOM 6 PV !");
        System.out.println("(Dégâts 6 PV, à brancher plus tard)");
    }

    @Override
    public String toString() {
        return "Mine (6 PV)";
    }
}
