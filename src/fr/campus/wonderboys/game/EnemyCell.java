package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.enemies.BestiaireFactory;
import fr.campus.wonderboys.characters.enemies.Enemy;

public class EnemyCell extends Cell {

    @Override
    public void interact(Character hero) {

        System.out.println("⚔️ Un ennemi apparaît !");

        Enemy monster = BestiaireFactory.creerMonstre(hero.getBoardPosition());

        if (monster == null) {
            System.out.println("Erreur génération monstre.");
            return;
        }

        Menu menu = new Menu();
        CombatSystem combat = new CombatSystem(menu);

        combat.fight(hero, monster);
    }

    @Override
    public String toString() {
        return "Case ennemi";
    }
}