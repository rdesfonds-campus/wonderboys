package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Classe de base pour tous les ennemis.
 * Hérite de Character avec type="Enemy".
 */
public abstract class Enemy extends Character {

    public Enemy(String name, int lifeLevel, int attackLevel,
                 OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Enemy", name, lifeLevel, attackLevel, weapon, defense);
    }

    /**
     * À surcharger dans les ennemis concrets.
     * Appelé quand le héros bat cet ennemi.
     */
    public void onDefeated() {
        System.out.println(getName() + " est vaincu !");
        // Plus tard : XP, or, loot...
    }
}
