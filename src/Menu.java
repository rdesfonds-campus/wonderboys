import java.util.Scanner;

public class Menu {

    // Attribut : sert à lire ce que tape le joueur au clavier
    private Scanner scanner = new Scanner(System.in);

    // Méthode : affiche un message au joueur
    public void showMessage(String message) {
        System.out.println(message);
    }

    // Méthode : demande au joueur de taper un texte et le renvoie
    public String askText(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    // Méthode : demande au joueur de taper un nombre entier et le renvoie
    public int askInt(String question) {
        System.out.println(question);
        int value = scanner.nextInt();
        scanner.nextLine(); // vide la ligne pour éviter les bugs de lecture
        return value;

    }
    public String askString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

}
