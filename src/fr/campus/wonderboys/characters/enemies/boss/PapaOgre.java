package fr.campus.wonderboys.characters.enemies.boss;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Papa Ogre : boss ultime du jeu Wonderboys.
 * 25 PV, dégâts 1d8, très puissant.
 */
public class PapaOgre extends Enemy {

    public PapaOgre() {
        super("Papa ogre", 25, 8, null, null, new Dice(8), 500);
        setSkill(10);    // S10
        setArmorModifier(10); // A10
    }

    @Override
    public String toString() {
        return "Papa ogre BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Papa Ogre rapporte 500 points de score !");
    }
}
