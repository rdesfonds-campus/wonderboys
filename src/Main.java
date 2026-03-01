public class Main {

    public static void main(String[] args) {
        // Crée le menu
        Menu menu = new Menu();

        // Crée le jeu avec ce menu
        Game game = new Game(menu);

        // Démarre le jeu
        game.start();
    }
}
