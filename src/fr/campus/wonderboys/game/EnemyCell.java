package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.enemies.*;  // Pour tous
import fr.campus.wonderboys.characters.enemies.d4.*;
import fr.campus.wonderboys.characters.enemies.d6.*;
import fr.campus.wonderboys.characters.enemies.boss.*;

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
