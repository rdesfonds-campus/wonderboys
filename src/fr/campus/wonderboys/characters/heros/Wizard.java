package fr.campus.wonderboys.characters.heros;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Magicien du jeu Wonderboys spécialisé dans les sorts.
 * Hérite de Character mais privilégie l'attaque magique.
 */
public class Wizard extends Character {

    /**
     * Crée un nouveau Wizard avec ses sorts et potions de départ.
     * Initialise aussi son habileté, son armure et son score.
     *
     * @param name        nom du magicien
     * @param lifeLevel   niveau de vie de départ (plus faible que le guerrier)
     * @param attackLevel niveau d'attaque magique de départ (élevé)
     * @param weapon      sort offensif équipé (boule de feu, éclair...)
     * @param defense     potion de soin ou protection magique équipée
     */
    public Wizard(String name, int lifeLevel, int attackLevel,
                  OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Wizard", name, lifeLevel, attackLevel, weapon, defense);

        // Valeurs par défaut pour le système de combat
        setSkill(11);          // Magicien un peu plus habile mais fragile
        setArmorModifier(0);   // Très peu de protection physique
        setScore(0);           // Le magicien commence aussi avec 0 point de score
        setThac0(20);     // THAC0 débutant
        setCa(8);         // CA haute = fragile

    }

    /**
     * Affiche les informations principales du magicien.
     *
     * @return chaîne lisible avec type, nom, vie, attaque, habileté, armure et score
     */
    @Override
    public String toString() {
        return "Wizard : " + getName()
                + ", Vie : " + getLifeLevel()
                + ", Attaque : " + getAttackLevel()
                + ", Habileté : " + getSkill()
                + ", Armure : " + getArmorModifier()
                + ", Score : " + getScore()
                + ", TACO : " + getThac0()
                + ", CA : " + getCa();
    }
}
