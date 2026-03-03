package fr.campus.wonderboys.characters;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Magicien du jeu Wonderboy spécialisé dans les sorts.
 * Hérite de Character mais privilégie l'attaque magique.
 *
 * @author Romain D
 * @version 1.0
 */
public class Wizard extends Character {

    /**
     * Crée un nouveau Wizard avec ses sorts et potions.
     *
     * @param name nom du magicien
     * @param lifeLevel niveau de vie (plus faible que guerrier)
     * @param attackLevel niveau d'attaque magique (élevé)
     * @param weapon sort offensif (boule de feu, éclair...)
     * @param defense potion de soin ou protection magique
     */
    public Wizard(String name, int lifeLevel, int attackLevel,
                  OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Wizard", name, lifeLevel, attackLevel, weapon, defense);
    }

    /**
     * Affiche les infos du magicien de façon lisible.
     *
     * @return chaîne avec nom, vie et attaque magique
     */
    @Override
    public String toString() {
        return "Wizard : " + getName() + ", Vie : " + getLifeLevel() + ", Attaque : " + getAttackLevel();
    }
}
