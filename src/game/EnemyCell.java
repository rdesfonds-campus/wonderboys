package game;

import characters.enemies.Dragon;
import characters.enemies.Enemy;
import characters.enemies.Goblin;
import characters.enemies.Orc;

public class EnemyCell extends Cell {

    private Enemy ennemi;
    private CombatResult dernierResultat;

    public EnemyCell(Menu menu, int position) {
        super(menu);

        if (position >= 59) {
            // Les 6 dernières cases : uniquement le Dragon
            this.ennemi = new Dragon();
        } else {
            // Le reste du plateau : Goblin ou Orc
            int tirage = (int)(Math.random() * 2);
            if (tirage == 0) {
                this.ennemi = new Goblin();
            } else {
                this.ennemi = new Orc();
            }
        }
    }

    @Override
    public void interact(characters.Character hero) {
        CombatSystem combat = new CombatSystem(menu);
        dernierResultat = combat.combattre(hero, ennemi);
    }

    public CombatResult getDernierResultat() {
        return dernierResultat;
    }

    @Override
    public String toString() {
        return "Case ennemi (" + ennemi.getName() + ")";
    }
}