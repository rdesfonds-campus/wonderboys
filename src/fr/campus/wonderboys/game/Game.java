package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.Warrior;
import fr.campus.wonderboys.characters.Wizard;
import fr.campus.wonderboys.characters.enemies.*;
import fr.campus.wonderboys.db.HeroDAO;
import fr.campus.wonderboys.equipment.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.campus.wonderboys.cells.*;
import fr.campus.wonderboys.game.Board;
import fr.campus.wonderboys.game.Dice;

/**
 * Gère une partie complète de Wonderboys avec menu et personnages.
 * Lance le menu principal, crée guerrier/magicien, déplace sur plateau 64 cases.
 */
public class Game {

    // Pour discuter avec le joueur
    private final Menu menu;

    // Personnage actuel du joueur
    private Character currentCharacter;

    // Position actuelle sur le plateau
    private int playerPosition;

    // Mini-plateau de test (4 cases)
    private List<Cell> board;

    public Game(Menu menu) {
        this.menu = menu;
        this.currentCharacter = null;
        this.playerPosition = 1;

        // Mini-plateau pour les tests
        this.board = new ArrayList<>();
        board.add(new EmptyCell());
        board.add(new EnemyCell());
        board.add(new WeaponCell());
        board.add(new PotionCell());
    }

    /**
     * Démarre le jeu avec boucle menu principal.
     */
    public void start() {
        System.out.println("Bienvenue dans WONDERBOYS !");
        System.out.println("=========================\nSalutations jeune aventurier !! \nEnvie de combats, richesses et aventures ?\nExplore le super donjon et remporte le plus gros trésor !!\n=========================");

        int choice = 0;

        while (choice != 5) {
            menu.showMessage("PREPARATIFS");
            menu.showMessage("\n1 - Créer un personnage");
            menu.showMessage("2 - Choisir un héros existant");
            menu.showMessage("3 - Modifier un personnage");
            menu.showMessage("4 - Démarrer la partie");
            menu.showMessage("5 - Quitter le jeu");

            //menu.showMessage("0 - Test monstres (nouveau !)");

            choice = menu.askInt("\nQue veux-tu faire ?");

            switch (choice) {

                /*case 0:
                    testMonstres();
                    break;*/

                case 1:
                    menu.showMessage("On va créer un nouveau personnage !");
                    createCharacter();
                    break;

                case 2:
                    chooseExistingHero();
                    break;

                case 3:
                    editExistingHero();
                    break;

                case 4:
                    if (currentCharacter == null) {
                        menu.showMessage("Tu dois créer ou choisir un personnage avant de démarrer !");
                    } else {
                        try {
                            startGame();
                        } catch (OutOfBoardException e) {
                            menu.showMessage("Erreur de plateau : " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    menu.showMessage("Au revoir !");
                    break;

                default:
                    menu.showMessage("Choix invalide. Réessaie.");
                    break;
            }
        }
    }

    /**
     * Crée Warrior ou Wizard selon choix joueur.
     * Enregistre le personnage en BDD.
     */
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
        String weaponName = null;
        String defenseName = null;

        if (choiceType == 1) {
            type = "Warrior";
            lifeLevel = 10;
            attackLevel = 10;
            menu.showMessage("Tu as choisi : Warrior");

            // Choix de la nouvelle arme
            menu.showMessage("Choisis une nouvelle arme :");
            menu.showMessage("1 - Épée rouillée");
            menu.showMessage("2 - Massue en bois");
            int weaponChoice = menu.askInt("Ton choix d'arme : ");

            if (weaponChoice == 1) {
                weaponName = "Épée rouillée";
            } else {
                weaponName = "Massue en bois";
            }

            // Choix de la nouvelle défense
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petit bouclier en bois");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petit bouclier en bois";
            } else {
                defenseName = "Grande potion de soin";
            }

        } else {
            type = "Wizard";
            lifeLevel = 6;
            attackLevel = 8;
            menu.showMessage("Tu as choisi : Wizard");

            // Choix de la nouvelle arme (sort)
            menu.showMessage("Choisis un nouveau sort :");
            menu.showMessage("1 - Bâton magique");
            menu.showMessage("2 - Boule de feu");
            int weaponChoice = menu.askInt("Ton choix de sort : ");

            if (weaponChoice == 1) {
                weaponName = "Bâton magique";
            } else {
                weaponName = "Boule de feu";
            }

            // Choix de la nouvelle défense (équipement magique / potion)
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petite potion de soin");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petite potion de soin";
            } else {
                defenseName = "Grande potion de soin";
            }
        }

        String name = menu.askString("Quel est le nom de ton personnage ?");
        menu.showMessage("Ton personnage s'appelle : " + name);

        OffensiveEquipment weapon;
        if (type.equals("Warrior")) {
            weapon = new Weapon(3, weaponName);
        } else {
            weapon = new OffensiveEquipment("Spell", 4, weaponName);
        }

        DefensiveEquipment defense;
        if (type.equals("Warrior")) {
            defense = new Shield();
        } else {
            defense = new Potion(3, defenseName);
        }

        if (type.equals("Warrior")) {
            currentCharacter = new Warrior(name, lifeLevel, attackLevel, weapon, defense);
        } else {
            currentCharacter = new Wizard(name, lifeLevel, attackLevel, weapon, defense);
        }

        int subChoice = 0;

        while (subChoice != 3) {
            menu.showMessage("Que veux-tu faire avec ce personnage ?");
            menu.showMessage("1 - Afficher ses infos");
            menu.showMessage("2 - Modifier son nom");
            menu.showMessage("3 - Retour au menu principal");

            subChoice = menu.askInt("Ton choix : ");

            switch (subChoice) {
                case 1:
                    menu.showMessage("Infos du personnage : " + currentCharacter);
                    menu.showMessage("Nom de l'arme : " + currentCharacter.getWeapon().getName());
                    menu.showMessage("Nom de la protection : " + currentCharacter.getDefense().getName());
                    break;

                case 2:
                    String newName = menu.askString("Nouveau nom pour ton personnage : ");
                    currentCharacter.setName(newName);
                    menu.showMessage("Nouveau nom enregistré : " + currentCharacter.getName());
                    break;

                case 3:
                    menu.showMessage("Retour au menu principal...");
                    break;

                default:
                    menu.showMessage("Choix invalide dans le sous-menu.");
                    break;
            }
        }

        // Enregistrer le personnage en base de données
        HeroDAO heroDAO = new HeroDAO();
        heroDAO.createHero(currentCharacter);
    }

    /**
     * Modifie un héros existant en BDD (nom + équipement).
     */
    private void editExistingHero() {
        menu.showMessage("Liste des héros en base :");

        HeroDAO heroDAO = new HeroDAO();
        heroDAO.getHeroes(); // affiche les héros en console

        int idToEdit = menu.askInt("Quel Id de héros veux-tu modifier ?");

        // On récupère le héros pour connaître son type
        Character heroFromDb = heroDAO.getHeroById(idToEdit);
        if (heroFromDb == null) {
            menu.showMessage("Aucun héros trouvé avec cet Id.");
            return;
        }

        // Nouveau nom (commun aux deux types)
        String newName = menu.askString("Quel nouveau nom veux-tu donner à ce héros ?");

        String weaponName;
        String defenseName;

        // Cas 1 : le héros est un Warrior
        if (heroFromDb instanceof Warrior) {
            // Choix de la nouvelle arme
            menu.showMessage("Choisis une nouvelle arme :");
            menu.showMessage("1 - Épée rouillée");
            menu.showMessage("2 - Massue en bois");
            int weaponChoice = menu.askInt("Ton choix d'arme : ");

            if (weaponChoice == 1) {
                weaponName = "Épée rouillée";
            } else {
                weaponName = "Massue en bois";
            }

            // Choix de la nouvelle défense
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petit bouclier en bois");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petit bouclier en bois";
            } else {
                defenseName = "Grande potion de soin";
            }

            // Création d'un Warrior pour pousser les valeurs en BDD
            Warrior heroToUpdate = new Warrior(
                    newName,
                    heroFromDb.getLifeLevel(),
                    heroFromDb.getAttackLevel(),
                    null,
                    null
            );
            heroToUpdate.setId(idToEdit);
            heroToUpdate.setWeapon(new Weapon(3, weaponName));
            heroToUpdate.setDefense(new Shield());

            heroDAO.editHero(heroToUpdate);

            // Cas 2 : le héros est un Wizard
        } else if (heroFromDb instanceof Wizard) {
            // Choix de la nouvelle arme (sort)
            menu.showMessage("Choisis un nouveau sort :");
            menu.showMessage("1 - Bâton magique");
            menu.showMessage("2 - Boule de feu");
            int weaponChoice = menu.askInt("Ton choix de sort : ");

            if (weaponChoice == 1) {
                weaponName = "Bâton magique";
            } else {
                weaponName = "Boule de feu";
            }

            // Choix de la nouvelle défense (équipement magique / potion)
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petite potion de soin");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petite potion de soin";
            } else {
                defenseName = "Grande potion de soin";
            }

            // Création d'un Wizard pour pousser les valeurs en BDD
            Wizard heroToUpdate = new Wizard(
                    newName,
                    heroFromDb.getLifeLevel(),
                    heroFromDb.getAttackLevel(),
                    null,
                    null
            );
            heroToUpdate.setId(idToEdit);
            // Ici tu peux utiliser OffensiveEquipment / DefensiveEquipment adaptés aux wizards
            heroToUpdate.setWeapon(new OffensiveEquipment("Spell", 4, weaponName));
            heroToUpdate.setDefense(new Potion(3, defenseName));

            heroDAO.editHero(heroToUpdate);
        }

        menu.showMessage("Modification envoyée en base pour l'Id " + idToEdit +
                " avec le nom : " + newName);
    }

    /**
     * Choisir un héros existant en BDD pour jouer.
     */
    private void chooseExistingHero() {
        HeroDAO heroDAO = new HeroDAO();

        menu.showMessage("Liste des héros disponibles :");
        heroDAO.getHeroes();

        int idToUse = menu.askInt("Quel Id de héros veux-tu utiliser pour jouer ?");

        Character hero = heroDAO.getHeroById(idToUse);

        if (hero == null) {
            menu.showMessage("Aucun héros trouvé avec cet Id.");
        } else {
            currentCharacter = hero;
            menu.showMessage("Tu joueras maintenant avec : " + currentCharacter.getName());
        }
    }

    /**
     * Affiche le mini-plateau de test.
     */
    public void displayBoard() {
        System.out.println("=== MINI-PLATEAU (4 cases) ===");
        for (int i = 0; i < board.size(); i++) {
            System.out.println("Case " + (i + 1) + " : " + board.get(i).toString());
        }
        System.out.println("Position joueur : " + playerPosition);
        System.out.println("=========================");
    }

    /**
     * Lance la vraie partie plateau 64 cases avec dé.
     */
    private void startGame() throws OutOfBoardException {

        // On crée le plateau (64 cases)
        Board board = new Board();

        // On crée un dé à 6 faces (par défaut)
        Dice dice = new Dice();

        // Au démarrage, le joueur est sur la case 0
        currentCharacter.setBoardPosition(0);

        menu.showMessage("Début de la partie !");
        menu.showMessage("Personnage : " + currentCharacter.getName());

        menu.showMessage("Case " + playerPosition + " / " + board.getTotalSquares());

        /*menu.showMessage("Aperçu du plateau :");
        for (int i = 0; i < 10; i++) { // 10 premières cases
            menu.showMessage("Case " + (i + 1) + " : " + board.getCell(i));
        }*/

        // Tant que tu n'es pas à la fin, tu rejoues un tour
        while (currentCharacter.getBoardPosition() < board.getTotalSquares()) {
           //menu.askString("Lancer le dé pour avancer (tour suivant) :");

            int roll = dice.roll();
            System.out.println("Tu lances le dé : " + roll);

            int newPosition = currentCharacter.getBoardPosition() + roll;
            if (newPosition >= 64) {
                if (newPosition == 64) {
                    menu.showMessage("Bravo, vous avez trouvé la salle du trésor. Recevez 1000 points.");
                } else {
                    menu.showMessage("Vous êtes allé trop vite et avez loupé le trésor...\n Au moins vous sortez encore en vie.");
                }
                menu.showMessage("THANK YOU FOR PLAYING ! Score : " + currentCharacter.getScore());
                // Score BDD TODO (if > max)
                HeroDAO dao = new HeroDAO();
                dao.changeLifePointsCharacter(currentCharacter.getId(), currentCharacter.getLifeLevel());
                break;  // CRUCIAL : sort while, enlève "Bravo case 64 !"
            }

            currentCharacter.setBoardPosition(newPosition);
// ... reste jet rencontre



            currentCharacter.setBoardPosition(newPosition);
            menu.showMessage("Tu arrives à la case " + newPosition);

            // JET DE RENCONTRE
            Random rand = new Random();
            double proba = rand.nextDouble();
            if (proba < 0.5) {
                // CHOISIR MONSTRE AVANT menu
                Random randMonstre = new Random();  // Nouveau rand pour éviter bug
                String monsterName;
                double typeProba = randMonstre.nextDouble();
                if (typeProba < 0.6) {
                    String[] petits = {"Serpent", "Champignon", "Singe", "Chauve-souris", "Crabe", "Goblin"};
                    monsterName = petits[randMonstre.nextInt(petits.length)] + " (1d4)";
                } else if (typeProba < 0.9) {
                    String[] moyens = {"Chevalier", "Squelette", "Brigand"};
                    monsterName = moyens[randMonstre.nextInt(moyens.length)] + " (1d6)";
                } else {
                    String[] boss = {"Sorcier", "Dragon", "Golem", "Chevalier", "Vampire", "Papa Ogre"};
                    monsterName = boss[randMonstre.nextInt(boss.length)] + " (1d8)";
                }
                menu.showMessage("Un " + monsterName + " apparaît et vous attaque.");
                menu.showMessage("Que voulez-vous faire ?");

                int choixMonstre;
                do {
                    menu.showMessage("1 - Attaquer");
                    menu.showMessage("2 - Fuir");
                    menu.showMessage("3 - Stats");
                    menu.showMessage("4 - Quitter");
                    choixMonstre = menu.askInt("Choix : ");
                    if (choixMonstre == 3) {
                        menu.showMessage(currentCharacter.toString());
                    } else if (choixMonstre == 1) {
                        // Combat : joueur attaque monstre, puis monstre contre-attaque
                        menu.showMessage("COMBAT !");
                        // Joueur attaque (simulé)
                        int degatsJoueur = rand.nextInt(1, 11) + currentCharacter.getAttackLevel() / 2;  // Force/2 + dé
                        menu.showMessage("Tu infliges " + degatsJoueur + " dégâts au " + monsterName);
                        // Monstre attaque (selon type)
                        int degatsMonstre;
                        if (monsterName.contains("1d4")) degatsMonstre = rand.nextInt(1, 5);
                        else if (monsterName.contains("1d6")) degatsMonstre = rand.nextInt(1, 7);
                        else degatsMonstre = rand.nextInt(1, 9);
                        currentCharacter.setLifeLevel(currentCharacter.getLifeLevel() - degatsMonstre);
                        menu.showMessage(monsterName + " t'inflige " + degatsMonstre + " dégâts. Vie restante : " + currentCharacter.getLifeLevel());
                        if (currentCharacter.getLifeLevel() <= 0) {
                            menu.showMessage("Tu es vaincu ! Game Over.");
                            // Quitte jeu
                            return;
                        }

                    } else if (choixMonstre == 2) {
                        menu.showMessage("Test de fuite...");
                        double fuiteProba = rand.nextDouble();
                        if (fuiteProba < 0.5) {
                            menu.showMessage("Fuite réussie ! \nTu cours vers la prochaine salle.");
                            break;  // QUITTE menu combat, va à "Vous quittez la pièce"
                        } else {
                            menu.showMessage("Fuite ratée ! Le " + monsterName + " te rattrape.");
                            int degatsFuite;
                            if (monsterName.contains("1d4")) degatsFuite = rand.nextInt(1,5);
                            else if (monsterName.contains("1d6")) degatsFuite = rand.nextInt(1,7);
                            else degatsFuite = rand.nextInt(1,9);
                            currentCharacter.setLifeLevel(currentCharacter.getLifeLevel() - degatsFuite);
                            menu.showMessage("Il t'inflige " + degatsFuite + " dégâts. Vie : " + currentCharacter.getLifeLevel());
                            if (currentCharacter.getLifeLevel() <= 0) {
                                menu.showMessage("Tu es mort en fuyant ! Fin du jeu.");
                                return;
                            }
                        }


                    }
                    // Pas d'else pour 4, la boucle s'arrête
                } while (choixMonstre != 4);
            } else {
                // 50% SALLE
                menu.showMessage("Vous arrivez dans une nouvelle salle.");
                menu.showMessage("Que voulez-vous faire ?");
                int choixSalle;
                do {
                    menu.showMessage("1 - Fouiller");
                    menu.showMessage("2 - Quitter");
                    choixSalle = menu.askInt("Choix : ");
                } while (choixSalle != 1 && choixSalle != 2);
                if (choixSalle == 1) {
                    // Fouiller jet
                }

                boolean fouillee = false;
                if (choixSalle == 1) {
                    fouillee = true;
                    // JET FOUILLER
                    double fouilleProba = rand.nextDouble();
                    if (fouilleProba < 0.3) {
                        menu.showMessage("Vous ne trouvez rien.");
                    } else if (fouilleProba < 0.7) {  // 40%
                        menu.showMessage("Vous trouvez un petitTrésor ! +10 score (TODO)");
                    } else if (fouilleProba < 0.9) {  // 20%
                        menu.showMessage("🪨 Vous déclenchez un ÉBOULEMENT ! Les pierres vous infligent -5 PV");
                        currentCharacter.setLifeLevel(currentCharacter.getLifeLevel() - 5);
                        if (currentCharacter.getLifeLevel() <= 0) {
                            menu.showMessage("Mort par piège ! Fin du jeu.");
                            return;
                        }
                    } else {  // 10%
                        menu.showMessage("SuperTresor ! Potion + tous PV");
                        currentCharacter.setLifeLevel(currentCharacter.getMaxLifeLevel());
                    }
                }
                // Interact SEULEMENT si pas fouillé ET pas vide
                Cell caseActuelle = board.getCell(newPosition - 1);
                if (!fouillee && !(caseActuelle instanceof EmptyCell)) {
                    caseActuelle.interact(currentCharacter);
                }
            }
            menu.askString("Vous vous relevez et quittez la pièce. (Entrée pour avancer)");

            Cell caseActuelle = board.getCell(newPosition - 1);
            if (caseActuelle instanceof EmptyCell) {

            } else if (caseActuelle instanceof EnemyCell) {
                System.out.println("La case est occupée par un monstre !");


            }

        else if (caseActuelle instanceof FouilleCell) {
            System.out.println("Tu trouves un trésor !");
            // TODO : Ajouter effet (ex: + vie)
            menu.askString("Appuie Entrée pour continuer...");
        }

        else {
                System.out.println("La case est occupée (trésor ou piège).");
                caseActuelle.interact(currentCharacter); // Active trésor/piège seulement si pas monstre
            }
        }

        menu.showMessage("Bravo ! Tu es arrivé à la case " + board.getTotalSquares() + " !");
        HeroDAO heroDAO = new HeroDAO();
        heroDAO.changeLifePointsCharacter(
                currentCharacter.getId(),
                currentCharacter.getLifeLevel()
        );

        int endChoice = 0;

        while (endChoice != 1 && endChoice != 2) {
            menu.showMessage("Que veux-tu faire ?");
            menu.showMessage("1 - Recommencer une partie");
            menu.showMessage("2 - Retour au menu principal");
            endChoice = menu.askInt("Ton choix : ");
        }

        if (endChoice == 1) {
            startGame();
        } else {
            menu.showMessage("Retour au menu principal...");
        }
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    // ====== MINI-PLATEAU ======

    private int rollDice() {
        return 1;
    }

    /*public void playTurnMiniBoard() {
        System.out.println("\n--- NOUVEAU TOUR ---");
        int diceResult = rollDice();
        playerPosition += diceResult;

        if (playerPosition > board.size()) {
            System.out.println("Fin du mini-plateau !");
            return;
        }

        System.out.println("Position joueur : " + playerPosition);
        Cell currentCase = board.get(playerPosition - 1);
        System.out.println("Tu es sur : " + currentCase.toString());
        System.out.println("------------------");
    }

    private void testMonstres() {
        // Test des héros
        Warrior william = new Warrior("William", 20, 5, null, null);
        Wizard gandalf = new Wizard("Gandalf", 15, 7, null, null);

        System.out.println("=== HÉROS ===");
        System.out.println(william);
        System.out.println(gandalf);
        System.out.println();

        // Test des monstres faibles
        System.out.println("=== MONSTRES FAIBLES ===");
        Serpent serpent = new Serpent();
        Champignon champ = new Champignon();
        ChauveSouris souris = new ChauveSouris();
        Crabe crabe = new Crabe();
        Goblin goblin = new Goblin();
        Singe singe = new Singe();

        System.out.println(serpent);
        System.out.println(champ);
        System.out.println(souris);
        System.out.println(crabe);
        System.out.println(goblin);
        System.out.println(singe);
        System.out.println();

        // Test des monstres moyens
        System.out.println("=== MONSTRES MOYENS ===");
        Chevalier chevalier = new Chevalier();
        Squelette squelette = new Squelette();

        System.out.println(chevalier);
        System.out.println(squelette);
        System.out.println();

        // Test des boss
        System.out.println("=== BOSS ===");
        Sorcier sorcier = new Sorcier();
        Dragon dragon = new Dragon();
        Golem golem = new Golem();
        Vampire vampire = new Vampire();
        PapaOgre ogre = new PapaOgre();

        System.out.println(sorcier);
        System.out.println(dragon);
        System.out.println(golem);
        System.out.println(vampire);
        System.out.println(ogre);
    }*/
}