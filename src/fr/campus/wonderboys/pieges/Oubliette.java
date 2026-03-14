package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class Oubliette extends Cell {

    @Override
    public void interact(Character hero) {

        System.out.println("💀 OUBLIETTE !");
        System.out.println("Tu tombes dans un trou et retournes au départ.");

        hero.setBoardPosition(1);
    }

    @Override
    public String toString() {
        return "Oubliette (retour départ)";
    }
}