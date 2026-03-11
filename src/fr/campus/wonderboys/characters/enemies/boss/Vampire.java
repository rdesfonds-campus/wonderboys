package fr.campus.wonderboys.characters.enemies.boss;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Vampire : boss du jeu Wonderboys.
 * 20 PV, dégâts 1d8, très rapide et protégé.
 */
public class Vampire extends Enemy {

    public Vampire() {
        super("Vampire", 20, 8, null, null, new Dice(8), 150);
        setSkill(7);     // S7
        setArmorModifier(7); // A7
    }

    @Override
    public String toString() {
        return "Vampire BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Vampire rapporte 150 points de score !");
    }
}
