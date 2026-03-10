package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Champignon : ennemi faible du jeu Wonderboys.
 * 5 PV, attaque 1d4, très faible habileté.
 */
public class Champignon extends Enemy {

    /**
     * Crée un Champignon avec ses caractéristiques par défaut.
     * 5PV | S1 | A0 | R10
     */
    public Champignon() {
        super("Champignon", 5, 4, null, null, new Dice(4), 10);

        // Valeurs spécifiques au Champignon
        setSkill(1);           // S1 selon tes specs
        setArmorModifier(0);   // A0 selon tes specs
    }

    /**
     * Affichage du Champignon avec toutes ses caractéristiques.
     */
    @Override
    public String toString() {
        return "Champignon (PV: " + getLifeLevel()
                + ", dégâts: 1d4, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    /**
     * Message quand le Champignon est vaincu.
     */
    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Champignon rapporte 10 points de score !");
    }
}
