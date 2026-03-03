package fr.campus.wonderboys.characters;

import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;

/**
 * Personnage abstrait de base pour Wonderboy.
 * Toutes les classes Warrior/Wizard doivent en hériter.
 *
 * @author Romain D
 * @version 1.0
 */
public abstract class Character {
    /**
     * Crée un nouveau personnage avec tous ses attributs.
     *
     * @param type "Warrior" ou "Wizard"
     * @param name nom unique
     * @param lifeLevel points de vie
     * @param attackLevel niveau d'attaque
     * @param weapon arme ou sort
     * @param defense bouclier ou potion
     * @see Warrior
     * @see Wizard
     */

    private String type;
    private String name;
    private int lifeLevel;
    private int attackLevel;
    private OffensiveEquipment weapon;
    private DefensiveEquipment defense;

    public Character(String type, String name, int lifeLevel, int attackLevel,
                     OffensiveEquipment weapon, DefensiveEquipment defense) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;
        this.defense = defense;
    }

    public String getType() { return type; }
    public String getName() { return name; }
    public int getLifeLevel() { return lifeLevel; }
    public int getAttackLevel() { return attackLevel; }
    public OffensiveEquipment getWeapon() { return weapon; }
    public DefensiveEquipment getDefense() { return defense; }

    public void setType(String type) { this.type = type; }
    public void setName(String name) { this.name = name; }
    public void setLifeLevel(int lifeLevel) { this.lifeLevel = lifeLevel; }
    public void setAttackLevel(int attackLevel) { this.attackLevel = attackLevel; }
    public void setWeapon(OffensiveEquipment weapon) { this.weapon = weapon; }
    public void setDefense(DefensiveEquipment defense) { this.defense = defense; }

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
                '}';
    }
}
