package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.game.cells.Cell;

public class Eboulement extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("🪨 PIÈGE ÉBOULEMENT ! Pierres -5 PV");
        System.out.println("(Dégâts 5 PV, à brancher plus tard)");
    }

    @Override
    public String toString() {
        return "Éboulement (5 PV)";
    }
}
