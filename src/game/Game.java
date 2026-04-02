package game;

import board.Board;
import characters.HeroFactory;
import combat.Dice;
import combat.ResultatTour;

/**
 * Classe principale du jeu Wonderboys.
 * <p>
 * Orchestre le menu principal, la création et la sélection de personnages,
 * et la boucle de jeu sur le plateau de 64 cases.
 * Délègue la création de personnages à {@link HeroFactory},
 * la logique de plateau à {@link Board},
 * le déroulement des tours à {@link TourDeJeu},
 * et la persistance à {@link db.PersonnageDAO}.
 * </p>
 *
 * @author Romain D
 * @version 1.0
 */
public class Game {

    /** Interface de dialogue avec le joueur. */
    private Menu menu;

    /** Personnage actuellement sélectionné pour jouer. */
    private characters.Character currentCharacter;

    /** Position actuelle du joueur sur le plateau (1 à 64). */
    private int position;

    /** Fabrique de personnages jouables. */
    private HeroFactory heroFactory;

    /**
     * Crée une nouvelle instance du jeu.
     *
     * @param menu interface de saisie et d'affichage
     */
    public Game(Menu menu) {
        this.menu        = menu;
        this.position    = 1;
        this.heroFactory = new HeroFactory(menu);
    }

    /**
     * Lance la boucle du menu principal.
     * <p>
     * Propose au joueur de créer un personnage, d'en choisir un existant,
     * de lancer une partie ou de quitter. Tourne jusqu'au choix "Quitter".
     * </p>
     */
    public void start() {
        menu.showMessage("Bienvenue dans Wonderboys !");
        menu.showMessage("=========================");

        int choix = 0;
        while (choix != 6) {
            menu.showMessage("1 - Créer un personnage");
            menu.showMessage("2 - Choisir un personnage existant");
            menu.showMessage("3 - Modifier un personnage");
            menu.showMessage("4 - Supprimer un personnage");
            menu.showMessage("5 - Jouer");
            menu.showMessage("6 - Quitter");
            choix = menu.askInt("Ton choix :");

            switch (choix) {
                case 1: creerPersonnage();    break;
                case 2: choisirPersonnage();  break;
                case 3: modifierPersonnage(); break;
                case 4: supprimerPersonnage(); break;
                case 5:
                    if (currentCharacter == null) {
                        menu.showMessage("Crée ou choisis d'abord un personnage !");
                    } else {
                        jouer();
                    }
                    break;
                case 6: menu.showMessage("Au revoir !"); break;
                default: menu.showMessage("Choix invalide."); break;
            }
        }
    }

    /**
     * Délègue la création d'un personnage à {@link HeroFactory}
     * et met à jour le personnage courant.
     */
    private void creerPersonnage() {
        currentCharacter = heroFactory.creerPersonnage();
        menu.showMessage("Personnage créé !");
        menu.showMessage(currentCharacter.toString());
    }
    /**
     * Affiche la liste des personnages et permet au joueur
     * de modifier le nom de l'un d'eux.
     */
    private void modifierPersonnage() {
        db.PersonnageDAO dao = new db.PersonnageDAO();
        menu.showMessage("Personnages disponibles :");
        dao.afficherTous();
        int id = menu.askInt("Quel id veux-tu modifier ?");
        String nouveauNom = menu.askString("Nouveau nom :");
        dao.modifier(id, nouveauNom);
    }

    /**
     * Affiche la liste des personnages et permet au joueur
     * d'en supprimer un par son identifiant.
     * Remet le personnage courant à null s'il est supprimé.
     */
    private void supprimerPersonnage() {
        db.PersonnageDAO dao = new db.PersonnageDAO();
        menu.showMessage("Personnages disponibles :");
        dao.afficherTous();
        int id = menu.askInt("Quel id veux-tu supprimer ?");
        dao.supprimer(id);
        if (currentCharacter != null && currentCharacter.getId() == id) {
            menu.showMessage("Le personnage actif a été supprimé.");
            currentCharacter = null;
        }
    }
    /**
     * Affiche la liste des personnages en base et permet au joueur
     * d'en sélectionner un par son identifiant.
     */
    private void choisirPersonnage() {
        db.PersonnageDAO dao = new db.PersonnageDAO();
        menu.showMessage("Personnages disponibles :");
        dao.afficherTous();
        int id = menu.askInt("Quel id veux-tu choisir ?");
        currentCharacter = dao.charger(id);
        if (currentCharacter != null) {
            menu.showMessage("Personnage chargé : " + currentCharacter.getName());
        } else {
            menu.showMessage("Aucun personnage trouvé avec cet id.");
        }
    }

    /**
     * Lance une partie complète sur le plateau de 64 cases.
     * <p>
     * Délègue chaque tour à {@link TourDeJeu} et réagit à l'état retourné.
     * La partie se termine sur VICTOIRE, DEFAITE ou HORS_PLATEAU.
     * Les résultats sont sauvegardés en base à chaque fin de partie.
     * </p>
     */
    private void jouer() {
        Board plateau    = new Board(menu);
        Dice de          = new Dice(6);
        TourDeJeu tour   = new TourDeJeu(menu, plateau, de);
        position         = 1;

        menu.showMessage("\n--- Début de la partie ! ---");
        menu.showMessage(currentCharacter.getName() + " entre dans le donjon.");

        while (true) {
            String cmd = menu.askString("Entrée pour lancer le dé | 'status' pour voir ton statut...");

            if (cmd.equalsIgnoreCase("status")) {
                menu.showMessage("=== STATUT ===");
                menu.showMessage(currentCharacter.toString());
                menu.showMessage("Score : " + currentCharacter.getScore());
                menu.showMessage("Case : " + position + " / 64");
                continue;
            }

            ResultatTour resultat = tour.jouer(currentCharacter, position);
            position = resultat.getNouvellePosition();

            if (resultat.getEtat() == EtatPartie.EN_COURS) {
                continue;
            }

            // Fin de partie
            menu.showMessage("Score final : " + currentCharacter.getScore());
            db.PersonnageDAO dao = new db.PersonnageDAO();
            dao.mettreAJour(currentCharacter);
            finDePartie();

            if (resultat.getEtat() == EtatPartie.DEFAITE) {
                currentCharacter = null;
            }

            return;
        }
    }

    /**
     * Affiche le message de fin de partie.
     * Appelé après une victoire, une défaite ou un dépassement du plateau.
     */
    private void finDePartie() {
        menu.showMessage("\nMerci d'avoir joué à Wonderboys !");
        menu.showMessage("Retour au menu principal...");
    }
}