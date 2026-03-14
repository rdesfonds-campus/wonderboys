package fr.campus.wonderboys.pieges;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.game.Cell;

public class NuagePoison extends Cell {

    private static final int DAMAGE = 2;

    @Override
    public void interact(Character hero) {

        System.out.println("☠️ NUAGE DE POISON !");
        hero.setLifeLevel(hero.getLifeLevel() - DAMAGE);

        System.out.println("-" + DAMAGE + " PV");
        System.out.println("PV restants : " + hero.getLifeLevel());
    }

    @Override
    public String toString() {
        return "Poison (-2 PV)";
    }
}