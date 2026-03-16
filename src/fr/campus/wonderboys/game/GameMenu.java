package fr.campus.wonderboys.game;

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
                    showPreparationMenu();
                    break;

                case 2:
                    menu.showMessage("Au revoir aventurier.");
                    break;

                default:
                    menu.showMessage("Choix invalide.");
            }
        }
    }

    private void showPreparationMenu() {

        int choice = 0;

        while (choice != 4) {

            menu.showMessage("\nPREPARATIFS");
            menu.showMessage("1 - Créer un personnage");
            menu.showMessage("2 - Choisir un héros existant");
            menu.showMessage("3 - Modifier un personnage");
            menu.showMessage("4 - Démarrer la partie");
            menu.showMessage("5 - Retour");

            choice = menu.askInt("Choix : ");

            switch (choice) {

                case 1:
                    game.createCharacter();
                    break;

                case 2:
                    game.chooseExistingHero();
                    break;

                case 3:
                    game.editExistingHero();
                    break;

                case 4:

                    if (game.getCurrentCharacter() == null) {

                        menu.showMessage("Tu dois choisir un héros.");

                    } else {

                        game.startGame();
                    }

                    break;

                case 5:
                    return;

                default:
                    menu.showMessage("Choix invalide.");
            }
        }
    }

    public void showFouilleMenu(Character hero, Cell cell) {

        int choice;

        menu.showMessage("Vous arrivez dans une nouvelle pièce.");

        menu.showMessage("1 - Fouiller");
        menu.showMessage("2 - Passer");
        menu.showMessage("3 - Statut");

        choice = menu.askInt("Choix : ");

        switch (choice) {

            case 1:
                cell.interact(hero);
                break;

            case 2:
                menu.showMessage("Vous continuez votre chemin.");
                break;

            case 3:
                menu.showMessage(hero.toString());
                showFouilleMenu(hero, cell);
                break;
        }
    }

    public void showEndGameMenu() {

        int choice = menu.askInt(
                "\n1 - Nouvelle partie\n2 - Retour menu\nChoix : "
        );

        if (choice == 1) {

            game.startGame();

        }
    }
}