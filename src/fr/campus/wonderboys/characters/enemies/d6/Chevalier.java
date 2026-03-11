package fr.campus.wonderboys.characters.enemies.d6;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Chevalier : ennemi moyen du jeu Wonderboys.
 * 12 PV, dégâts 1d6, bien protégé.
 */
public class Chevalier extends Enemy {

    /**
     * Crée un Chevalier avec ses caractéristiques par défaut.
     * 12PV | S3 | A4 | R40
     */
    public Chevalier() {
        super("Chevalier", 12, 6, null, null, new Dice(6), 40);

        // Valeurs spécifiques au Chevalier
        setSkill(3);          // S3 selon tes specs
        setArmorModifier(4);  // A4 selon tes specs
    }

    /**
     * Affichage du Chevalier avec toutes ses caractéristiques.
     */
    @Override
    public String toString() {
        return "Chevalier (PV: " + getLifeLevel()
                + ", dégâts: 1d6, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    /**
     * Message quand le Chevalier est vaincu.
     */
    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Chevalier rapporte 40 points de score !");
    }
}
