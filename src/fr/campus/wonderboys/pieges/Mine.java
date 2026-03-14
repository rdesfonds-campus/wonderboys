package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class Mine extends Cell {

    private static final int DAMAGE = 6;

    @Override
    public void interact(Character hero) {

        System.out.println("💥 BOOM ! Tu marches sur une mine !");
        hero.setLifeLevel(hero.getLifeLevel() - DAMAGE);

        System.out.println("-" + DAMAGE + " PV");
        System.out.println("PV restants : " + hero.getLifeLevel());
    }

    @Override
    public String toString() {
        return "Mine (-6 PV)";
    }
}