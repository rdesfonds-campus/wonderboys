package fr.campus.wonderboys.equipment;

public class SortUltima extends OffensiveEquipment {

    public SortUltima() {
        super("Sort Ultima", 20, "sort"); // 1d20
    }

    public void testUse() {
        System.out.println("ULTIMA ! Sort 1d20 dégâts !");
        int degats = (int)(Math.random() * 20) + 1;
        System.out.println("Dégâts magiques: " + degats);
    }

    @Override
    public String toString() {
        return "Sort Ultima (1d20)";
    }
}
