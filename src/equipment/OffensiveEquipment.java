package equipment;

public class OffensiveEquipment {

    private String type;       // "Weapon" ou "Spell"
    private int attackLevel;   // niveau d'attaque
    private String name;       // nom de l'équipement

    public OffensiveEquipment(String type, int attackLevel, String name) {
        this.type = type;
        this.attackLevel = attackLevel;
        this.name = name;
    }

    public String getType()       { return type; }
    public int getAttackLevel()   { return attackLevel; }
    public String getName()       { return name; }

    public void setType(String type)            { this.type = type; }
    public void setAttackLevel(int attackLevel) { this.attackLevel = attackLevel; }
    public void setName(String name)            { this.name = name; }

    @Override
    public String toString() {
        return type + " : " + name + " (Attaque : " + attackLevel + ")";
    }
}