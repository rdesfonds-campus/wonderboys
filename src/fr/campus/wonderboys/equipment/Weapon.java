package fr.campus.wonderboys.equipment;

public class Weapon extends OffensiveEquipment {

    public Weapon(int attackLevel, String name) {
        super("fr.campus.wonderboys.equipment.Weapon", attackLevel, name);
    }

    @Override
    public String toString() {
        return "fr.campus.wonderboys.equipment.Weapon : " + getName() + " (Attaque : " + getAttackLevel() + ")";
    }
}
