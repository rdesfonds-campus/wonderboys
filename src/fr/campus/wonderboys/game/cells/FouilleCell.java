package fr.campus.wonderboys.game.cells;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.characters.heros.Warrior;

public class FouilleCell extends Cell {

    @Override
    public void interact(Character perso) {
        // Marque case comme "visitée" pour éviter piège
        System.out.println("Fouille terminée, case sûre.");
        int pourcent = (int)(Math.random() * 100) + 1;
        System.out.println("Roll: " + pourcent);
        if (pourcent <= 15) {
            System.out.println("Rien trouvé !");
            return;
        } else if (pourcent <= 50) {
            System.out.println("Coffre commun !");
            int sub = (int)(Math.random() * 3) + 1;
            if (sub == 1) {
                System.out.println("Potion soin +10 PV (TODO)");
            } else if (sub == 2) {
                System.out.println("Fragment +2 attaque (TODO)");
            } else {
                System.out.println("+50 score (TODO)");
            }
            return;  // Nouveau !
        } else if (pourcent <= 75) {  // 51-75 : 25% coffre rare
            System.out.println("Coffre rare !");
            if (perso instanceof Warrior) {
                System.out.println("Épée acier 1d8 +3 (TODO)");
            } else {  // Wizard
                System.out.println("Baguette foudre 1d8 +3 (TODO)");
            }
            return;
        } else if (pourcent <= 90) {  // 76-90 : 15% sacré
            System.out.println("*** TRÉSOR SACRÉ ***");
            int tresor = (int)(Math.random() * 4) + 1;  // 1-4
            switch(tresor) {
                case 1: System.out.println("Bazooka légendaire 1d20 (TODO)"); break;
                case 2: System.out.println("Étoile Mario : fin direct (TODO)"); break;
                case 3: System.out.println("Lame Vorpale : kill ennemi (TODO)"); break;
                case 4: System.out.println("Sort Ultima 1d20 (TODO)"); break;
            }
            return;
        } else {  // 91-100 : 10% piège
            System.out.println("*** PIÈGE ACTIVÉ ***");
            int piege = (int)(Math.random() * 4) + 1;
            switch(piege) {
                case 1: System.out.println("💥 Mine : -6 PV"); break;
                case 2: System.out.println("🪨 Éboul. : -5 PV"); break;
                case 3: System.out.println("☁️ Poison : -2 PV"); break;
                case 4: System.out.println("🕳️ Trou : -4 PV"); break;
            }
            // perso.setLifeLevel(perso.getLifeLevel() - degats);
        }



        System.out.println("TODO autres %...");
    }



    @Override
    public String toString() {
        return "Case fouille (?%)";
    }
}
