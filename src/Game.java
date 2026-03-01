public class Game {
    // Attribut : permet de discuter avec le joueur (afficher, poser des questions)
    private Menu menu;

    // Attribut : le personnage actuel du joueur (au début il sera vide)
    private Character currentCharacter;

    // Constructeur : sert à créer une partie avec un menu
    public Game(Menu menu) {
        this.menu = menu;
        this.currentCharacter = null; // pas de personnage au début
    }

    // Méthode : démarre le jeu et affiche le menu principal
    public void start() {
        int choice = 0;

        while (choice != 2) {
            menu.showMessage("Bienvenue dans Wonderboy !");
            menu.showMessage("1 - Nouveau personnage");
            menu.showMessage("2 - Quitter le jeu");
            menu.showMessage("3 - Démarrer la partie");

            choice = menu.askInt("Que veux-tu faire ?");

            if (choice == 1) {
                menu.showMessage("On va créer un nouveau personnage !");
                createCharacter();

            } else if (choice == 2) {
                menu.showMessage("Au revoir !");

            } else if (choice == 3) {
                if (currentCharacter == null) {
                    menu.showMessage("Tu dois créer un personnage avant de démarrer !");
                } else {
                    startGame();
                }

            } else {
                menu.showMessage("Choix invalide. Réessaie.");
            }

        }
    }


    // Méthode : gère la création d'un nouveau personnage
    private void createCharacter() {
        menu.showMessage("Choisis ton type de personnage :");
        menu.showMessage("1 - Warrior");
        menu.showMessage("2 - Wizard");

        int choiceType = menu.askInt("Tape 1 ou 2 :");

        while (choiceType != 1 && choiceType != 2) {
            menu.showMessage("Choix invalide pour le type de personnage.");
            choiceType = menu.askInt("Tape 1 ou 2 :");
        }

        int lifeLevel;
        int attackLevel;
        String type;

        if (choiceType == 1) {
            type = "Warrior";
            lifeLevel = 10;
            attackLevel = 5;
            menu.showMessage("Tu as choisi : Warrior");
        } else {
            type = "Wizard";
            lifeLevel = 6;
            attackLevel = 8;
            menu.showMessage("Tu as choisi : Wizard");
        }

        String name = menu.askString("Quel est le nom de ton personnage ?");
        menu.showMessage("Ton personnage s'appelle : " + name);

        OffensiveEquipment weapon;

        if (type.equals("Warrior")) {
            weapon = new OffensiveEquipment("Weapon", 3, "Épée rouillée");
        } else {
            weapon = new OffensiveEquipment("Spell", 4, "Boule de feu");
        }
        DefensiveEquipment defense;

        if (type.equals("Warrior")) {
            defense = new DefensiveEquipment("Shield", 2, "Petit bouclier en bois");
        } else {
            defense = new DefensiveEquipment("Potion", 3, "Petite potion de soin");
        }

        currentCharacter = new Character(
                type,
                name,
                lifeLevel,
                attackLevel,
                weapon,
                defense
        );

        int subChoice = 0;

        while (subChoice != 3) {
            menu.showMessage("Que veux-tu faire avec ce personnage ?");
            menu.showMessage("1 - Afficher ses infos");
            menu.showMessage("2 - Modifier son nom");
            menu.showMessage("3 - Retour au menu principal");

            subChoice = menu.askInt("Ton choix : ");

            if (subChoice == 1) {
                menu.showMessage("Infos du personnage : " + currentCharacter);
                menu.showMessage("Nom de l'arme : " + currentCharacter.getWeapon().getName());
                menu.showMessage("Nom de la protection : " + currentCharacter.getDefense().getName());

            } else if (subChoice == 2) {
                String newName = menu.askString("Nouveau nom pour ton personnage : ");
                currentCharacter.setName(newName);
                menu.showMessage("Nouveau nom enregistré : " + currentCharacter.getName());
            } else if (subChoice == 3) {
                menu.showMessage("Retour au menu principal...");
            } else {
                menu.showMessage("Choix invalide dans le sous-menu.");
            }
        }


    }
    private void startGame() {

        // On crée le plateau (64 cases)
        Board board = new Board();

        // On crée un dé à 6 faces (par défaut)
        Dice dice = new Dice();

        // Au démarrage, le joueur est sur la case 1
        int position = 1;

        menu.showMessage("Début de la partie !");
        menu.showMessage("Personnage : " + currentCharacter.getName());
        menu.showMessage("Case " + position + " / " + board.getTotalSquares());

        // Tant que tu n'es pas à la fin, tu rejoues un tour
        while (position < board.getTotalSquares()) {

            // Lancer le dé
            int roll = dice.roll();

            // Calculer la nouvelle position
            int newPosition = position + roll;

            // Si on dépasse 64, on s'arrête à 64
            if (newPosition > board.getTotalSquares()) {
                newPosition = board.getTotalSquares();
            }

            menu.showMessage("Tu lances le dé : " + roll);
            menu.showMessage("Tu avances : " + position + " -> " + newPosition);

            position = newPosition;
        }

        menu.showMessage("Bravo ! Tu es arrivé à la case " + board.getTotalSquares() + " !");
        int endChoice = 0;

// On demande au joueur ce qu'il veut faire maintenant
        while (endChoice != 1 && endChoice != 2) {
            menu.showMessage("Que veux-tu faire ?");
            menu.showMessage("1 - Recommencer une partie");
            menu.showMessage("2 - Retour au menu principal");
            endChoice = menu.askInt("Ton choix : ");
        }

        if (endChoice == 1) {
            // On relance une nouvelle partie (même personnage)
            startGame();
        } else {
            // On ne fait rien : on retourne au menu principal (car startGame() se termine)
            menu.showMessage("Retour au menu principal...");
        }

    }

}

