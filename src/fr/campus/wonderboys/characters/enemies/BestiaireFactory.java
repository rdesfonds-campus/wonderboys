package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.characters.enemies.boss.*;
import fr.campus.wonderboys.characters.enemies.d4.*;
import fr.campus.wonderboys.characters.enemies.d6.*;

import java.util.Random;

public class BestiaireFactory {

    private static final Random rand = new Random();

    public static Enemy creerMonstre(int positionCase) {

        // Boss à la fin du donjon
        if (positionCase >= 60) {

            Enemy[] boss = {
                    new Dragon(),
                    new Sorcier(),
                    new Golem(),
                    new Vampire()
            };

            return boss[rand.nextInt(boss.length)];
        }

        // Monstres normaux
        int proba = rand.nextInt(100);

        if (proba < 60) {

            Enemy[] faibles = {
                    new Goblin(),
                    new Serpent(),
                    new Champignon(),
                    new ChauveSouris(),
                    new Crabe(),
                    new Singe()
            };

            return faibles[rand.nextInt(faibles.length)];

        } else if (proba < 90) {

            Enemy[] moyens = {
                    new Chevalier(),
                    new Squelette(),
                    new Brigand()
            };

            return moyens[rand.nextInt(moyens.length)];

        }

        return null; // 10% de chance qu'il n'y ait aucun monstre


    }
}