package fr.campus.wonderboys.game.Menus;

import fr.campus.wonderboys.characters.Character;

public class GameMenu {

    private final Menu menu;
    private final Game game;

    public GameMenu(Menu menu, Game game) {
        this.menu = menu;
        this.game = game;
    }

    public void showMainMenu() {

        menu.showMessage("Bienvenue dans WONDERBOYS !");
        menu.showMessage("=========================");
        menu.showMessage("Explore le donjon et trouve le trésor !");
        menu.showMessage("=========================");

        int choice = 0;

        while (choice != 2) {

            menu.showMessage("\nMENU PRINCIPAL");
            menu.showMessage("1 - Jouer");
            menu.showMessage("2 - Quitter");

            choice = menu.askInt("Choix : ");

            switch (choice) {

                case 1:
                    showPlayMenu();
                    break;

                case 2:
                    menu.showMessage("Au revoir aventurier.");
                    break;

                default:
                    menu.showMessage("Choix invalide.");
            }
        }
    }
}