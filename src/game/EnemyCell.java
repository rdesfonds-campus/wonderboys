package game;

import characters.enemies.Dragon;
import characters.enemies.Enemy;
import characters.enemies.Goblin;
import characters.enemies.Orc;

public class EnemyCell extends Cell {

    private Enemy ennemi;

    public EnemyCell(Menu menu) {
        super(menu);
        int tirage = (int)(Math.random() * 3);
        switch (tirage) {
            case 0:  this.ennemi = new Goblin(); break;
            case 1:  this.ennemi = new Orc();    break;
            default: this.ennemi = new Dragon(); break;
        }
    }

    @Override
    public void interact(characters.Character hero) {
        CombatSystem combat = new CombatSystem(menu);
        combat.combattre(hero, ennemi);
    }

    @Override
    public String toString() {
        return "Case ennemi (" + ennemi.getName() + ")";
    }
}