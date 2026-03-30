package characters;

import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

public abstract class Character {

    // Les informations du personnage
    private String type;       // "Warrior" ou "Wizard"
    private String name;       // son prénom
    private int lifeLevel;     // ses points de vie
    private int attackLevel;   // sa force d'attaque
    private OffensiveEquipment weapon;    // arme ou sort
    private DefensiveEquipment defense;   // bouclier ou potion
    private int id;
    private int score;

    // Le constructeur : sert à créer un personnage avec ses valeurs de départ
    public Character(String type, String name, int lifeLevel, int attackLevel, OffensiveEquipment weapon, DefensiveEquipment defense) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;
        this.defense = defense;
    }

    // Getters : pour LIRE les valeurs depuis l'extérieur
    public String getType()      { return type; }
    public String getName()      { return name; }
    public int getLifeLevel()    { return lifeLevel; }
    public int getAttackLevel()  { return attackLevel; }
    public OffensiveEquipment getWeapon()      { return weapon; }
    public DefensiveEquipment getDefense()     { return defense; }
        public int getId()            { return id; }
    public int getScore()         { return score; }

    // Setters : pour MODIFIER les valeurs depuis l'extérieur
    public void setName(String name)           { this.name = name; }
    public void setLifeLevel(int lifeLevel)    { this.lifeLevel = lifeLevel; }
    public void setAttackLevel(int attackLevel){ this.attackLevel = attackLevel; }
    public void setWeapon(OffensiveEquipment weapon)       { this.weapon = weapon; }
    public void setDefense(DefensiveEquipment defense)     { this.defense = defense; }
    public void setId(int id)     { this.id = id; }
    public void setScore(int score) { this.score = score; }



    // toString : pour afficher le personnage proprement dans la console
    @Override
    public String toString() {
        return type + " : " + name
                + " | PV : " + lifeLevel
                + " | Attaque : " + attackLevel
                + " | Arme : " + weapon
                + " | Défense : " + defense;
    }
}