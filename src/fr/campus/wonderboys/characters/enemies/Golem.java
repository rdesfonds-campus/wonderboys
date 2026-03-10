package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Golem : boss du jeu Wonderboys.
 * 16 PV, dégâts 1d8, très résistant.
 */
public class Golem extends Enemy {

    public Golem() {
        super("Golem", 16, 8, null, null, new Dice(8), 150);
        setSkill(4);     // S4
        setArmorModifier(8); // A8
    }

    @Override
    public String toString() {
        return "Golem BOSS (PV: " + getLifeLevel() + ", dégâts: 1d8, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Golem rapporte 150 points de score !");
    }
}
