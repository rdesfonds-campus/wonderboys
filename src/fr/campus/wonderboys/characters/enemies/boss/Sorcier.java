package fr.campus.wonderboys.characters.enemies.boss;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Sorcier : boss du jeu Wonderboys.
 * 15 PV, dégâts 1d8, puissant en magie.
 */
public class Sorcier extends Enemy {

    public Sorcier() {
        super("Sorcier", 15, 8, null, null, new Dice(8), 100);
        setSkill(7);     // S7
        setArmorModifier(5); // A5
    }

    @Override
    public String toString() {
        return "Sorcier BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Sorcier rapporte 100 points de score !");
    }
}
