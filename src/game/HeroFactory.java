package game;

import characters.Character;
import characters.Warrior;
import characters.Wizard;
import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

public class HeroFactory {

    private Menu menu;

    public HeroFactory(Menu menu) {
        this.menu = menu;
    }

    public characters.Character creerPersonnage() {
        menu.showMessage("Quel type de personnage veux-tu ?");
        menu.showMessage("1 - Warrior");
        menu.showMessage("2 - Wizard");
        int type = menu.askInt("Ton choix :");

        String nom = menu.askString("Quel est le nom de ton personnage ?");

        characters.Character perso;
        if (type == 1) {
            perso = creerWarrior(nom);
        } else {
            perso = creerWizard(nom);
        }

        // Maintenant perso existe, on peut le sauvegarder
        db.PersonnageDAO dao = new db.PersonnageDAO();
        dao.sauvegarder(perso);

        return perso;
    }

    private characters.Character creerWarrior(String nom) {
        menu.showMessage("Choisis ton arme :");
        menu.showMessage("1 - Épée rouillée (Attaque : 3)");
        menu.showMessage("2 - Hache de guerre (Attaque : 5)");
        menu.showMessage("3 - Dague (Attaque : 2)");
        int choixArme = menu.askInt("Ton choix :");

        OffensiveEquipment arme;
        switch (choixArme) {
            case 2:  arme = new OffensiveEquipment("Weapon", 5, "Hache de guerre"); break;
            case 3:  arme = new OffensiveEquipment("Weapon", 2, "Dague"); break;
            default: arme = new OffensiveEquipment("Weapon", 3, "Épée rouillée"); break;
        }

        menu.showMessage("Choisis ta défense :");
        menu.showMessage("1 - Petit bouclier (Défense : 2)");
        menu.showMessage("2 - Bouclier de fer (Défense : 4)");
        int choixDef = menu.askInt("Ton choix :");

        DefensiveEquipment defense = (choixDef == 2)
                ? new DefensiveEquipment("Shield", 4, "Bouclier de fer")
                : new DefensiveEquipment("Shield", 2, "Petit bouclier");

        return new Warrior(nom, 10, 5, arme, defense);
    }

    private characters.Character creerWizard(String nom) {
        menu.showMessage("Choisis ton sort :");
        menu.showMessage("1 - Boule de feu (Attaque : 4)");
        menu.showMessage("2 - Éclair (Attaque : 6)");
        menu.showMessage("3 - Rayon de givre (Attaque : 3)");
        int choixSort = menu.askInt("Ton choix :");

        OffensiveEquipment arme;
        switch (choixSort) {
            case 2:  arme = new OffensiveEquipment("Spell", 6, "Éclair"); break;
            case 3:  arme = new OffensiveEquipment("Spell", 3, "Rayon de givre"); break;
            default: arme = new OffensiveEquipment("Spell", 4, "Boule de feu"); break;
        }

        menu.showMessage("Choisis ta défense :");
        menu.showMessage("1 - Petite potion (Défense : 3)");
        menu.showMessage("2 - Grande potion (Défense : 5)");
        int choixDef = menu.askInt("Ton choix :");

        DefensiveEquipment defense = (choixDef == 2)
                ? new DefensiveEquipment("Potion", 5, "Grande potion")
                : new DefensiveEquipment("Potion", 3, "Petite potion");

        return new Wizard(nom, 6, 8, arme, defense);
    }
}