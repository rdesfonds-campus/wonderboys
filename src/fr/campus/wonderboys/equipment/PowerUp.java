package fr.campus.wonderboys.equipment;

public class PowerUp extends OffensiveEquipment {

    public PowerUp() {
        super("PowerUp", 2, "power");
    }

    @Override
    public String toString() {
        return "Power up (+2 dégâts)";
    }
}
