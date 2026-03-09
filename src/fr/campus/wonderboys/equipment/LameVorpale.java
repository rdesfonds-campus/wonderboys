package fr.campus.wonderboys.equipment;

public class LameVorpale extends OffensiveEquipment {

    public LameVorpale() {
        super("Lame Vorpale", 6, "lame"); // 1d6 spécial
    }

    public void testUse() {
        System.out.println("Lame Vorpale ! 1d6 (5-6 = mort instant)");
        int jet = (int)(Math.random() * 6) + 1;
        System.out.println("Jet: " + jet);
        if (jet >= 5) {
            System.out.println("POUF ! Ennemi tué instantanément !");
        } else {
            System.out.println("Dégâts normaux: " + jet);
        }
    }

    @Override
    public String toString() {
        return "Lame Vorpale (1d6 mort 5-6)";
    }
}
