package fr.campus.wonderboys.characters.enemies.d4;

import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;

/**
 * Crabe : ennemi faible mais résistant du jeu Wonderboys.
 * 6 PV, attaque 1d4, bonne armure naturelle.
 */
public class Crabe extends Enemy {

    /**
     * Crée un Crabe avec ses caractéristiques par défaut.
     * 6PV | S1 | A2 | R20
     */
    public Crabe() {
        super("Crabe", 6, 4, null, null, new Dice(4), 20);

        // Valeurs spécifiques au Crabe
        setSkill(1);           // S1 selon tes specs
        setArmorModifier(2);   // A2 selon tes specs
    }

    /**
     * Affichage du Crabe avec toutes ses caractéristiques.
     */
    @Override
    public String toString() {
        return "Crabe (PV: " + getLifeLevel()
                + ", dégâts: 1d4, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    /**
     * Message quand le Crabe est vaincu.
     */
    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Crabe rapporte 20 points de score !");
    }
}
