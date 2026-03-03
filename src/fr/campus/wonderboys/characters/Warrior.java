package fr.campus.wonderboys.characters;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Guerrier du jeu Wonderboy avec beaucoups PV et attaque physique.
 * Hérite de Character pour partager nom, vie, attaque et équipements.
 *
 * @author Romain D
 * @version 1.0
 */
public class Warrior extends Character {

    /**
     * Crée un nouveau Warrior avec ses équipements par défaut.
     *
     * @param name nom du guerrier
     * @param lifeLevel niveau de vie (généralement élevé)
     * @param attackLevel niveau d'attaque physique
     * @param weapon arme offensive (épée, hache...)
     * @param defense bouclier ou armure
     */
    public Warrior(String name, int lifeLevel, int attackLevel,
                   OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Warrior", name, lifeLevel, attackLevel, weapon, defense);
    }

    /**
     * Affiche les infos du guerrier de façon lisible.
     *
     * @return chaîne avec nom, vie et attaque
     */
    @Override
    public String toString() {
        return "Warrior : " + getName() + ", Vie : " + getLifeLevel() + ", Attaque : " + getAttackLevel();
    }
}
