package fr.campus.wonderboys.equipment;

public class Potion extends DefensiveEquipment {

    public Potion(int defenseLevel, String name) {
        super("fr.campus.wonderboys.equipment.Potion", defenseLevel, name);
    }

    @Override
    public String toString() {
        return "fr.campus.wonderboys.equipment.Potion : " + getName() + " (Défense : " + getDefenseLevel() + ")";
    }
}
