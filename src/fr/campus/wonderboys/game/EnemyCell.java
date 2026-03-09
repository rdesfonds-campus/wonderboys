package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;

public class EnemyCell extends Cell {

    @Override
    public void interact(Character hero) {
        System.out.println("Un ennemi t'attaque !");
        // Plus tard : combat
    }

    @Override
    public String toString() {  // ← AJOUTE ÇA
        return "Case ennemi";
    }
}
