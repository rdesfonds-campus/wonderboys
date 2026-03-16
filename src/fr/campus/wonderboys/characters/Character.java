package fr.campus.wonderboys.characters;

import fr.campus.wonderboys.equipment.FragmentPuissance;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Classe abstraite représentant un personnage du jeu.
 * Warrior et Wizard héritent de cette classe.
 */
public abstract class Character {

    // --- Informations générales ---
    private int id;
    private String type;
    private String name;

    // --- Statistiques ---
    private int lifeLevel;
    private int maxLifeLevel = 20;
    private int attackLevel;

    // --- Combat ---
    private int skill;
    private int armorModifier;
    private int thac0;
    private int ca;

    // --- Score ---
    private int score = 0;

    // --- Équipement ---
    private OffensiveEquipment weapon;
    private DefensiveEquipment defense;

    // --- Position sur le plateau ---
    private int boardPosition = 1;

    /**
     * Constructeur principal
     */
    public Character(String type,
                     String name,
                     int lifeLevel,
                     int attackLevel,
                     OffensiveEquipment weapon,
                     DefensiveEquipment defense) {

        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;
        this.defense = defense;
    }

    // -------------------------
    // GETTERS
    // -------------------------

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLifeLevel() {
        return lifeLevel;
    }

    public int getMaxLifeLevel() {
        return maxLifeLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public int getSkill() {
        return skill;
    }

    public int getArmorModifier() {
        return armorModifier;
    }

    public int getThac0() {
        return thac0;
    }

    public int getCa() {
        return ca;
    }

    public int getScore() {
        return score;
    }

    public OffensiveEquipment getWeapon() {
        return weapon;
    }

    public DefensiveEquipment getDefense() {
        return defense;
    }

    public int getBoardPosition() {
        return boardPosition;
    }

    // -------------------------
    // SETTERS
    // -------------------------

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public void setMaxLifeLevel(int maxLifeLevel) {
        this.maxLifeLevel = maxLifeLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public void setArmorModifier(int armorModifier) {
        this.armorModifier = armorModifier;
    }

    public void setThac0(int thac0) {
        this.thac0 = thac0;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWeapon(OffensiveEquipment weapon) {
        this.weapon = weapon;
    }

    public void setDefense(DefensiveEquipment defense) {
        this.defense = defense;
    }

    public void setBoardPosition(int boardPosition) {
        this.boardPosition = boardPosition;
    }

    // -------------------------
    // MÉTHODES GAMEPLAY
    // -------------------------

    public void gainScore(int points) {
        score += points;
    }

    public void takeDamage(int damage) {
        lifeLevel -= damage;

        if (lifeLevel < 0) {
            lifeLevel = 0;
        }
    }

    public void heal(int points) {
        lifeLevel += points;

        if (lifeLevel > maxLifeLevel) {
            lifeLevel = maxLifeLevel;
        }
    }

    /**
     * Effet spécial pour fragment de puissance
     * (à implémenter plus tard si nécessaire)
     */
    public void setOffensiveEquipment(FragmentPuissance fragmentPuissance) {
        // TODO implémentation future
    }

    // -------------------------
    // AFFICHAGE
    // -------------------------

    @Override
    public String toString() {

        String weaponName = (weapon != null) ? weapon.getName() : "aucune arme";
        String defenseName = (defense != null) ? defense.getName() : "aucune défense";

        return
                "\n--- HERO ---" +
                        "\nNom : " + name +
                        "\nClasse : " + type +
                        "\nPV : " + lifeLevel + "/" + maxLifeLevel +
                        "\nAttaque : " + attackLevel +
                        "\nSkill : " + skill +
                        "\nArmure : " + ca +
                        "\nArme : " + weaponName +
                        "\nDéfense : " + defenseName +
                        "\nScore : " + score +
                        "\nPosition : " + boardPosition +
                        "\n";
    }
}