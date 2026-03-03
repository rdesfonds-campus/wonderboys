package fr.campus.wonderboys.equipment;

public class Shield extends DefensiveEquipment {

    public Shield(int defenseLevel, String name) {
        super("fr.campus.wonderboys.equipment.Shield", defenseLevel, name);
    }

    @Override
    public String toString() {
        return "fr.campus.wonderboys.equipment.Shield : " + getName() + " (Défense : " + getDefenseLevel() + ")";
    }
}
