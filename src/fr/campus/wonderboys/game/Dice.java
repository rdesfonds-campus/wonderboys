package fr.campus.wonderboys.game;

import java.util.Random;

// La classe fr.campus.wonderboys.game.Dice représente un dé qui peut donner un nombre au hasard.
public class Dice {
    // sides = nombre de faces du dé (ex : 6 pour un dé classique)
    private int sides;

    // random = l'outil Java qui permet de générer du hasard
    private Random random;

    // Constructeur par défaut, si on ne précise rien le dé aura 6 faces
    public Dice () {
        this.sides = 6;
        this.random = new Random();

    }

    // Constructeur avec paramètre, on peut choisir le nombre de faces.
    public Dice (int sides) {
        this.sides = sides;
        this.random = new Random ();
    }

    //roll() = "lancé de dé"
    //netInt (sides) donne un nombre entre 0 et (sides - 1)
    // donc on ajoute +1 pour obtenir 1 à sides
    public int roll() {
        return random.nextInt(sides) + 1;

    }

    // Getter : permet de lire le nombre de faces depuis une autre classe
    public int getSides() {
        return sides;
    }

    // toString : utile pour afficher facilement le dé (debug)
    @Override
    public String toString() {
        return "fr.campus.wonderboys.game.Dice{sides=" + sides +"}";
    }
}

