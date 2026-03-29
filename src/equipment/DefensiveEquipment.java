package equipment;

public class DefensiveEquipment {

    private String type;       // "Shield" ou "Potion"
    private int defenseLevel;  // niveau de défense
    private String name;       // nom de l'équipement

    public DefensiveEquipment(String type, int defenseLevel, String name) {
        this.type = type;
        this.defenseLevel = defenseLevel;
        this.name = name;
    }

    public String getType()       { return type; }
    public int getDefenseLevel()  { return defenseLevel; }
    public String getName()       { return name; }

    public void setType(String type)              { this.type = type; }
    public void setDefenseLevel(int defenseLevel) { this.defenseLevel = defenseLevel; }
    public void setName(String name)              { this.name = name; }

    @Override
    public String toString() {
        return type + " : " + name + " (Défense : " + defenseLevel + ")";
    }
}