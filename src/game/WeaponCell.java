package game;

import equipment.OffensiveEquipment;

public class WeaponCell extends Cell {

    private OffensiveEquipment arme;

    public WeaponCell(Menu menu) {
        super(menu);
        this.arme = genererArme();
    }

    private OffensiveEquipment genererArme() {
        int tirage = (int)(Math.random() * 3);
        switch (tirage) {
            case 0:  return new OffensiveEquipment("Weapon", 3, "Épée courte");
            case 1:  return new OffensiveEquipment("Weapon", 5, "Hache de bataille");
            default: return new OffensiveEquipment("Weapon", 4, "Épieu");
        }
    }

    @Override
    public void interact(characters.Character hero) {
        menu.showMessage("Tu trouves : " + arme);
        menu.showMessage("Ton arme actuelle : " + hero.getWeapon());
        menu.showMessage("1 - Prendre l'arme");
        menu.showMessage("2 - Laisser");
        int choix = menu.askInt("Ton choix :");
        if (choix == 1) {
            hero.setWeapon(arme);
            menu.showMessage("Tu équipes : " + arme.getName() + " !");
        } else {
            menu.showMessage("Tu laisses l'arme et continues.");
        }
    }

    @Override
    public String toString() {
        return "Case arme (" + arme.getName() + ")";
    }
}