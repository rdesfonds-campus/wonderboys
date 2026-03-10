package fr.campus.wonderboys.characters;

import fr.campus.wonderboys.equipment.FragmentPuissance;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Personnage abstrait de base pour Wonderboy.
 * Toutes les classes Warrior/Wizard doivent en hériter.
 */
public abstract class Character {

    // --- Attributs de base du personnage ---
    private String type;              // Type de personnage : "Warrior" ou "Wizard"
    private String name;              // Nom du personnage
    private int lifeLevel;            // Points de vie actuels
    private int attackLevel;          // Niveau d'attaque de base
    private OffensiveEquipment weapon;   // Arme ou sort équipé
    private DefensiveEquipment defense;  // Bouclier ou potion équipé
    private int id;                   // Identifiant venant de la base de données

    // --- Attributs pour le système de combat ---
    private int skill;          // Habileté (sert aux jets d'attaque/défense)
    private int armorModifier;  // Bonus d'armure / défense naturelle

    // --- Attribut pour le score du joueur ---
    private int score;          // Score total du personnage

    // --- Attribut pour la gestion des PV max ---
    private int maxLifeLevel = 20; // Points de vie maximum (par défaut)

    /**
     * Constructeur principal du personnage.
     *
     * @param type        type du personnage ("Warrior" ou "Wizard")
     * @param name        nom du personnage
     * @param lifeLevel   points de vie de départ
     * @param attackLevel niveau d'attaque de départ
     * @param weapon      arme ou sort équipé
     * @param defense     bouclier ou potion équipée
     */
    public Character(String type, String name, int lifeLevel, int attackLevel,
                     OffensiveEquipment weapon, DefensiveEquipment defense) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;
        this.defense = defense;
        // skill, armorModifier et score seront initialisés dans Warrior/Wizard
    }

    // --- Getters (accès en lecture) ---
    public String getType() { return type; }
    public String getName() { return name; }
    public int getLifeLevel() { return lifeLevel; }
    public int getAttackLevel() { return attackLevel; }
    public OffensiveEquipment getWeapon() { return weapon; }
    public DefensiveEquipment getDefense() { return defense; }
    public int getId() { return id; }

    public int getSkill() { return skill; }
    public int getArmorModifier() { return armorModifier; }
    public int getScore() { return score; }

    public int getMaxLifeLevel() { return maxLifeLevel; }

    // --- Setters (accès en écriture / modification) ---
    public void setType(String type) { this.type = type; }
    public void setName(String name) { this.name = name; }
    public void setLifeLevel(int lifeLevel) { this.lifeLevel = lifeLevel; }
    public void setAttackLevel(int attackLevel) { this.attackLevel = attackLevel; }
    public void setWeapon(OffensiveEquipment weapon) { this.weapon = weapon; }
    public void setDefense(DefensiveEquipment defense) { this.defense = defense; }
    public void setId(int id) { this.id = id; }

    public void setSkill(int skill) { this.skill = skill; }
    public void setArmorModifier(int armorModifier) { this.armorModifier = armorModifier; }
    public void setScore(int score) { this.score = score; }

    public void setMaxLifeLevel(int max) { this.maxLifeLevel = max; }

    /**
     * Méthode spéciale pour gérer un fragment de puissance.
     * TODO : implémenter réellement cet effet si besoin.
     */
    public void setOffensiveEquipment(FragmentPuissance fragmentPuissance) {
        // Pour l'instant vide
    }

    // --- Représentation texte pour le debug / affichage ---
    @Override
    public String toString() {
        String weaponName = (weapon != null) ? weapon.getName() : "aucune arme";
        String defenseName = (defense != null) ? defense.getName() : "aucune défense";

        return "Character{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", lifeLevel=" + lifeLevel +
                ", attackLevel=" + attackLevel +
                ", weapon='" + weaponName + '\'' +
                ", defense='" + defenseName + '\'' +
                ", skill=" + skill +
                ", armorModifier=" + armorModifier +
                ", score=" + score +
                '}';
    }
}
