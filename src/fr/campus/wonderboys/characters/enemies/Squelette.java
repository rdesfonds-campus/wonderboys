package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Squelette : ennemi moyen du jeu Wonderboys.
 * 9 PV, dégâts 1d6, protégé par ses os.
 */
public class Squelette extends Enemy {

    public Squelette() {
        super("Squelette", 9, 6, null, null, new Dice(6), 25);
        setSkill(1);     // S1
        setArmorModifier(3); // A3
    }

    @Override
    public String toString() {
        return "Squelette (PV: " + getLifeLevel() + ", dégâts: 1d6, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Squelette rapporte 25 points de score !");
    }
}
