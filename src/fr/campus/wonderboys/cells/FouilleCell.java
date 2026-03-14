package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.heros.Warrior;
import fr.campus.wonderboys.game.Cell;

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
            System.out.println("Tu trouves un coffre !");
            int sub = (int)(Math.random() * 3) + 1;
            if (sub == 1) {
                System.out.println("Tu trouves une Potion de soin +10 PV ");
            } else if (sub == 2) {
                System.out.println("Fragment +2 attaque ");
            } else {
                System.out.println("Il contient des pièces d'or score +50 points");
            }
            return;  // Nouveau !
        } else if (pourcent <= 75) {  // 51-75 : 25% coffre rare
            System.out.println("Tu trouves un objet top qualité !");
            if (perso instanceof Warrior) {
                System.out.println("Une super Épée acier - 1d8 +3 ");
            } else {  // Wizard
                System.out.println("Une belle Baguette de foudre - 1d8 +3 ");
            }
            return;
        } else if (pourcent <= 90) {  // 76-90 : 15% sacré
            System.out.println("*** TRÉSOR SACRÉ *** \n Du matos collector !!");
            int tresor = (int)(Math.random() * 4) + 1;  // 1-4
            switch(tresor) {
                case 1: System.out.println("Un Bazooka infini - 1d20 de dégats"); break;
                case 2: System.out.println("Une super Étoile !! Tu deviens invincible et tu exploses tous les ennemis"); break;
                case 3: System.out.println("Une lame Vorpale : 1d8 degats, sur 10+ au toucher coup mortel"); break;
                case 4: System.out.println("Sort Ultima - Tu lances des explosions à 1d20 de dégâts"); break;
            }
            return;
        } else {  // 91-100 : 10% piège
            System.out.println("*** PIÈGE ACTIVÉ ***");
            int piege = (int)(Math.random() * 3) + 1;
            switch(piege) {
                case 1: System.out.println("💥 Mine : -4 PV");
                perso.setLifeLevel(perso.getLifeLevel() - 4);
                break;

                case 2: System.out.println("🪨 Éboul. : -3 PV");
                perso.setLifeLevel(perso.getLifeLevel() - 3);
                break;

                case 3: System.out.println("☁️ Poison : -2 PV");
                perso.setLifeLevel(perso.getLifeLevel() - 2);
                break;

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
