package fr.campus.wonderboys.characters.enemies.d4;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Singe : ennemi agile du jeu Wonderboys.
 * 5 PV, attaque 1d4, un peu malin.
 */
public class Singe extends Enemy {

    public Singe() {
        super("Singe", 5, 4, null, null, new Dice(4), 15);
        setSkill(2);     // S2
        setArmorModifier(1); // A1
    }

    @Override
    public String toString() {
        return "Singe (PV: " + getLifeLevel() + ", dégâts: 1d4, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Singe rapporte 15 points de score !");
    }
}
