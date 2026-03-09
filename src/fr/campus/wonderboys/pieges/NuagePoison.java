package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class NuagePoison extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("☠️ PIÈGE POISON ! Tu tousses -2 PV");
        System.out.println("(Dégâts 2 PV, à brancher plus tard)");
    }

    @Override
    public String toString() {
        return "Nuage Poison (2 PV)";
    }
}
