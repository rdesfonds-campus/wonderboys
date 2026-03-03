package fr.campus.wonderboys.equipment;

public class OffensiveEquipment {

    // Attributs : infos que l'objet garde en mémoire
    private String type;
    private int attackLevel;
    private String name;


    // Constructeur : sert à créer l'objet avec ses premières valeurs
    public OffensiveEquipment(String type, int attackLevel, String name) {
        this.type = type;
        this.attackLevel = attackLevel;
        this.name = name;
    }

    // Getters : permettent de LIRE les valeurs des attributs
    public String getType() {
        return type;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public String getName() {
        return name;
    }

    // Setters : permettent de MODIFIER les valeurs des attributs
    public void setType(String type) {
        this.type = type;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public void setName(String name) {
        this.name = name;
    }
// toString : permet d'afficher l'objet proprement en texte
    @Override
    public String toString() {
        return "fr.campus.wonderboys.equipment.OffensiveEquipment{" +
                "type='" + type + '\'' +
                ", attackLevel=" + attackLevel +
                ", name='" + name + '\'' +
                '}';
    }

}
