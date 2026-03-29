package game;

import java.util.Scanner;

public class Menu {

    // Scanner : l'outil Java qui lit ce que tape le joueur au clavier
    // "final" car on n'en a besoin que d'un seul pour toute la vie du Menu
    private final Scanner scanner = new Scanner(System.in);

    // Affiche un message dans la console
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Pose une question et retourne ce que le joueur a tapé (texte)
    public String askString(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    // Pose une question et retourne ce que le joueur a tapé (nombre entier)
    public int askInt(String question) {
        System.out.println(question);
        int valeur = scanner.nextInt();
        scanner.nextLine(); // vide le buffer pour éviter les bugs de lecture
        return valeur;
    }
}