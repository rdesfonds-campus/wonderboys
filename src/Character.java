public class Character {
    private String type; //"warrior" "wizard" "knight"
    private String name; //nom du personnage
    private int lifeLevel; //nombre de pv
    private int attackLevel; //niveau d'attaque
    private OffensiveEquipment weapon; //equipement offensif par defaut
    private DefensiveEquipment defense; // équipement défensif (bouclier, potion, etc.)


    // Constructeur : sert à créer le personnage avec ses premières valeurs
    public Character(String type, String name, int lifeLevel, int attackLevel,
                     OffensiveEquipment weapon, DefensiveEquipment defense) {
        this.type = type;
        this.name = name;
        this.lifeLevel = lifeLevel;
        this.attackLevel = attackLevel;
        this.weapon = weapon;
        this.defense = defense;
    }

    // Getters : permet de lire les infos sur le personnage
    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLifeLevel() {
        return lifeLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public OffensiveEquipment getWeapon() {
        return weapon;
    }

    public DefensiveEquipment getDefense() {
        return defense;
    }

    // Setters : permettent de MODIFIER les infos du personnage
    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public void setWeapon(OffensiveEquipment weapon) {
        this.weapon = weapon;
    }

    public void setDefense(DefensiveEquipment defense) {
        this.defense = defense;
    }

    // toString : permet d'afficher le personnage proprement en texte
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
