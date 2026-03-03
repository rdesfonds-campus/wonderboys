package fr.campus.wonderboys.equipment;

public class Spell extends OffensiveEquipment {

    public Spell(int attackLevel, String name) {
        super("fr.campus.wonderboys.equipment.Spell", attackLevel, name);
    }

    @Override
    public String toString() {
        return "fr.campus.wonderboys.equipment.Spell : " + getName() + " (Attaque : " + getAttackLevel() + ")";
    }
}
