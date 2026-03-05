package fr.campus.wonderboys.game;

// Dé spécial pour l'exercice : il renvoie toujours 1
public class Dice {

    // Constructeur par défaut
    public Dice() {
        // rien à faire pour l'instant
    }

    // Lancer de dé : renvoie toujours 1
    public int roll() {
        return 1;
    }

    @Override
    public String toString() {
        return "Dice{always=1}";
    }
}
