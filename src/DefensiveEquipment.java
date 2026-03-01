public class DefensiveEquipment {

    // Attributs : infos que l'objet garde en mémoire
    private String type;        // ex : "Shield" ou "Potion"
    private int defenseLevel;   // niveau de défense
    private String name;        // nom de l'objet

    // Constructeur : sert à créer l'objet avec ses premières valeurs
    public DefensiveEquipment(String type, int defenseLevel, String name) {
        this.type = type;
        this.defenseLevel = defenseLevel;
        this.name = name;
    }

    // Getters : permettent de LIRE les valeurs des attributs
    public String getType() {
        return type;
    }

    public int getDefenseLevel() {
        return defenseLevel;
    }

    public String getName() {
        return name;
    }

    // Setters : permettent de MODIFIER les valeurs des attributs
    public void setType(String type) {
        this.type = type;
    }

    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString : permet d'afficher l'objet proprement en texte
    @Override
    public String toString() {
        return "DefensiveEquipment{" +
                "type='" + type + '\'' +
                ", defenseLevel=" + defenseLevel +
                ", name='" + name + '\'' +
                '}';
    }
}
