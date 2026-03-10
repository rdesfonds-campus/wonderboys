package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Goblin : ennemi faible du jeu Wonderboys.
 * 8 PV, attaque 1d4, malin mais fragile.
 */
public class Goblin extends Enemy {

    public Goblin() {
        super("Gobelin", 8, 4, null, null, new Dice(4), 15);
        setSkill(2);     // S2
        setArmorModifier(1); // A1
    }

    @Override
    public String toString() {
        return "Gobelin (PV: " + getLifeLevel() + ", dégâts: 1d4, Habileté: " + getSkill() + ", Armure: " + getArmorModifier() + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Gobelin rapporte 15 points de score !");
    }
}
