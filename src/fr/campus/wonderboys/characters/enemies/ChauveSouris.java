package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Chauve-souris : ennemi très faible et fragile du jeu Wonderboys.
 * 3 PV, attaque 1d4, voleuse mais faible.
 */
public class ChauveSouris extends Enemy {

    /**
     * Crée une Chauve-souris avec ses caractéristiques par défaut.
     * 3PV | S1 | A1 | R10
     */
    public ChauveSouris() {
        super("Chauve-souris", 3, 4, null, null, new Dice(4), 10);

        // Valeurs spécifiques à la Chauve-souris
        setSkill(1);           // S1 selon tes specs
        setArmorModifier(1);   // A1 selon tes specs
    }

    /**
     * Affichage de la Chauve-souris avec toutes ses caractéristiques.
     */
    @Override
    public String toString() {
        return "Chauve-souris (PV: " + getLifeLevel()
                + ", dégâts: 1d4, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    /**
     * Message quand la Chauve-souris est vaincue.
     */
    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("La Chauve-souris rapporte 10 points de score !");
    }
}
