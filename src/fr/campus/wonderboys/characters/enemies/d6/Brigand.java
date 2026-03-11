package fr.campus.wonderboys.characters.enemies.d6;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Brigand : ennemi moyen du jeu Wonderboys.
 * 6 PV, dégâts 1d6, rusé mais fragile.
 */
public class Brigand extends Enemy {

    /**
     * Crée un Brigand avec ses caractéristiques par défaut.
     * 6PV | S3 | A1 | R30
     */
    public Brigand() {
        super("Brigand", 6, 6, null, null, new Dice(6), 30);

        // Valeurs spécifiques au Brigand
        setSkill(3);           // S3 selon tes specs
        setArmorModifier(1);   // A1 selon tes specs
        setThac0(20);
        setCa(6);

    }

    /**
     * Affichage du Brigand avec toutes ses caractéristiques.
     */
    @Override
    public String toString() {
        return "Brigand (PV: " + getLifeLevel()
                + ", dégâts: 1d6, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    /**
     * Message quand le Brigand est vaincu.
     */
    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Brigand rapporte 30 points de score !");
    }
}
