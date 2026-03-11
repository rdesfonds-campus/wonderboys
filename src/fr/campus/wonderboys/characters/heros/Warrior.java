package fr.campus.wonderboys.characters.heros;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Guerrier du jeu Wonderboys avec beaucoup de PV et une forte attaque physique.
 * Hérite de Character pour partager nom, vie, attaque et équipements.
 */
public class Warrior extends Character {

    /**
     * Crée un nouveau Warrior avec ses équipements de départ.
     * Initialise aussi son habileté, son armure et son score.
     *
     * @param name        nom du guerrier
     * @param lifeLevel   niveau de vie de départ (généralement élevé)
     * @param attackLevel niveau d'attaque physique de départ
     * @param weapon      arme offensive équipée (épée, hache...)
     * @param defense     bouclier ou armure équipée
     */
    public Warrior(String name, int lifeLevel, int attackLevel,
                   OffensiveEquipment weapon, DefensiveEquipment defense) {
        super("Warrior", name, lifeLevel, attackLevel, weapon, defense);

        // Valeurs par défaut pour le système de combat
        setSkill(10);          // Habileté de base du guerrier
        setArmorModifier(1);   // Léger bonus d'armure
        setScore(0);           // Le guerrier commence avec 0 point de score
        setThac0(19);     // Bon THAC0 pour guerrier
        setCa(5);         // CA moyenne (armure)

    }

    /**
     * Affiche les informations principales du guerrier.
     *
     * @return chaîne lisible avec type, nom, vie, attaque et score
     */
    @Override
    public String toString() {
        return "Warrior : " + getName()
                + ", Vie : " + getLifeLevel()
                + ", Attaque : " + getAttackLevel()
                + ", Habileté : " + getSkill()
                + ", Armure : " + getArmorModifier()
                + ", Score : " + getScore()
                + ", TACO : " + getThac0()
                + ", CA : " + getCa();

    }
}
