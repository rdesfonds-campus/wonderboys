package fr.campus.wonderboys.characters.enemies;
import fr.campus.wonderboys.characters.enemies.boss.Dragon;
import fr.campus.wonderboys.characters.enemies.boss.Golem;
import fr.campus.wonderboys.characters.enemies.boss.Sorcier;
import fr.campus.wonderboys.characters.enemies.boss.Vampire;
import fr.campus.wonderboys.characters.enemies.d4.Goblin;
import fr.campus.wonderboys.characters.enemies.d6.Chevalier;

import java.util.Random;

public class BestiaireFactory {
    private static Random rand = new Random();

    /**
     * Crée monstre selon case (boss 60+).
     */
    public static Enemy creerMonstre(int caseNum) {
        if (caseNum >= 60) {
            // Boss 60-63
            int choix = rand.nextInt(4);
            switch(choix) {
                case 0: return new Dragon();
                case 1: return new Sorcier();  // À créer
                case 2: return new Golem();
                default: return new Vampire();
            }
        } else {
            // Normal : 60% faible, 30% moyen, 10% rien?
            int pourc = rand.nextInt(100);
            if (pourc < 60) return new Goblin();
            else if (pourc < 90) return new Chevalier();  // Moyen 1d6
            else return null;  // Rien 10%
        }
    }
}
