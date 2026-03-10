package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Dragon : boss majeur du jeu Wonderboys (case 64).
 * 20 PV, dégâts 1d8, très puissant et protégé.
 */
public class Dragon extends Enemy {

    /**
     * Crée un Dragon avec ses caractéristiques de boss.
     * 20PV | S8 | A8 | R200
     */
    public Dragon() {
        super("Dragon", 20, 8, null, null, new Dice(8), 200);

        // Valeurs spécifiques au Dragon BOSS
        setSkill(8);           // S8 selon tes specs
        setArmorModifier(8);   // A8 selon tes specs
    }

    /**
     * Affichage du Dragon avec toutes ses caractéristiques.
     */
    @Override
    public String toString() {
        return "Dragon BOSS (PV: " + getLifeLevel()
                + ", dégâts: 1d8, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    /**
     * Message quand le Dragon est vaincu (fin du jeu !).
     */
    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("LE DRAGON EST VAINCU ! Félicitations, tu gagnes 200 points ! 🎉");
    }
}
