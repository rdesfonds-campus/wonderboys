package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Classe de base pour tous les ennemis.
 * Hérite de Character avec type="Enemy".
 */
public abstract class Enemy extends Character {

    private Dice damageDice;

    // Constructeur 5 paramètres (pour Main)
    public Enemy(String name, int lifeLevel, int attackLevel,
                 OffensiveEquipment weapon, DefensiveEquipment defense) {
        this(name, lifeLevel, attackLevel, weapon, defense, new Dice(6));
    }

    // Constructeur 6 paramètres (pour nouveaux monstres)
    public Enemy(String name, int lifeLevel, int attackLevel,
                 OffensiveEquipment weapon, DefensiveEquipment defense, Dice damageDice) {
        super("Enemy", name, lifeLevel, attackLevel, weapon, defense);
        this.damageDice = damageDice;
    }

    public void onDefeated() {
        System.out.println(getName() + " est vaincu !");
    }

    public int calculateDamage() {
        return damageDice.roll();
    }
}
