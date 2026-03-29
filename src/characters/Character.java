package characters;

public class Character {

    // Les informations du personnage
    private String type;       // "Warrior" ou "Wizard"
    private String name;       // son prénom
    private int lifeLevel;     // ses points de vie
    private int attackLevel;   // sa force d'attaque

    // Le constructeur : sert à créer un personnage avec ses valeurs de départ
    public Character(String type, String name, int lifeLevel, int attackLevel) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
    }

    // Getters : pour LIRE les valeurs depuis l'extérieur
    public String getType()      { return type; }
    public String getName()      { return name; }
    public int getLifeLevel()    { return lifeLevel; }
    public int getAttackLevel()  { return attackLevel; }

    // Setters : pour MODIFIER les valeurs depuis l'extérieur
    public void setName(String name)           { this.name = name; }
    public void setLifeLevel(int lifeLevel)    { this.lifeLevel = lifeLevel; }
    public void setAttackLevel(int attackLevel){ this.attackLevel = attackLevel; }

    // toString : pour afficher le personnage proprement dans la console
    @Override
    public String toString() {
        return type + " : " + name
                + " | PV : " + lifeLevel
                + " | Attaque : " + attackLevel;
    }
}