package fr.campus.wonderboys.equipment;

public class FragmentPuissance extends OffensiveEquipment {

    public FragmentPuissance() {
        super("Fragment de puissance", 2, "fragment");
    }

    @Override
    public String toString() {
        return "Fragment de puissance (+2 dégâts)";
    }
}
