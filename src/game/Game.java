package game;

public class Game {

    private Menu menu;
    private characters.Character currentCharacter;
    private int position;
    private HeroFactory heroFactory;

    public Game(Menu menu) {
        this.menu = menu;
        this.position = 1;
        this.heroFactory = new HeroFactory(menu);
    }

    public void start() {
        menu.showMessage("Bienvenue dans Wonderboys !");
        menu.showMessage("=========================");

        int choix = 0;
        while (choix != 4) {
            menu.showMessage("1 - Créer un personnage");
            menu.showMessage("2 - Choisir un personnage existant");
            menu.showMessage("3 - Jouer");
            menu.showMessage("4 - Quitter");
            choix = menu.askInt("Ton choix :");

            switch (choix) {
                case 1:
                    creerPersonnage();
                    break;
                case 2:
                    choisirPersonnage();
                    break;
                case 3:
                    if (currentCharacter == null) {
                        menu.showMessage("Crée ou choisis d'abord un personnage !");
                    } else {
                        jouer();
                    }
                    break;
                case 4:
                    menu.showMessage("Au revoir !");
                    break;
                default:
                    menu.showMessage("Choix invalide.");
                    break;
            }
        }
    }

    private void creerPersonnage() {
        currentCharacter = heroFactory.creerPersonnage();
        menu.showMessage("Personnage créé !");
        menu.showMessage(currentCharacter.toString());
    }

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

    private void jouer() {
        Board plateau = new Board(menu);
        Dice de = new Dice(6);
        position = 1;

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

            int lancer = de.roll();
            int nouvellePosition = position + lancer;

            menu.showMessage("Tu lances le dé : " + lancer);

            try {
                if (nouvellePosition > plateau.getTotalCases()) {
                    throw new OutOfBoardException("Tu es allé trop vite et tu as raté le trésor, dommage !");
                }

                if (nouvellePosition == plateau.getTotalCases()) {
                    currentCharacter.setScore(currentCharacter.getScore() + 1000);
                    menu.showMessage("Tu tombes pile sur la case 64 - Salle du trésor !");
                    menu.showMessage("BRAVO ! Tu as trouvé le trésor ! +1000 points !");
                    menu.showMessage("Score final : " + currentCharacter.getScore());
                    db.PersonnageDAO dao = new db.PersonnageDAO();
                    dao.mettreAJour(currentCharacter);
                    finDePartie();
                    return;
                }

                position = nouvellePosition;
                menu.showMessage("Tu avances à la case " + position + " / " + plateau.getTotalCases());

                Cell caseActuelle = plateau.getCase(position);
                menu.showMessage("Tu es sur : " + caseActuelle);
                caseActuelle.interact(currentCharacter);

                if (caseActuelle instanceof EnemyCell) {
                    CombatResult resultat = ((EnemyCell) caseActuelle).getDernierResultat();
                    if (resultat.getIssue() == CombatResult.Issue.DEFAITE) {
                        menu.showMessage("Score final : " + currentCharacter.getScore());
                        db.PersonnageDAO dao = new db.PersonnageDAO();
                        dao.mettreAJour(currentCharacter);
                        finDePartie();
                        currentCharacter = null;
                        return;
                    } else if (resultat.getIssue() == CombatResult.Issue.FUITE) {
                        position = Math.max(1, position - resultat.getReculFuite());
                        menu.showMessage("Tu recules à la case " + position + ".");
                    }
                }

                if (currentCharacter != null && currentCharacter.getLifeLevel() <= 0) {
                    menu.showMessage("Score final : " + currentCharacter.getScore());
                    db.PersonnageDAO dao = new db.PersonnageDAO();
                    dao.mettreAJour(currentCharacter);
                    finDePartie();
                    currentCharacter = null;
                    return;
                }

            } catch (OutOfBoardException e) {
                menu.showMessage(e.getMessage());
                menu.showMessage("Score final : " + currentCharacter.getScore());
                db.PersonnageDAO dao = new db.PersonnageDAO();
                dao.mettreAJour(currentCharacter);
                finDePartie();
                return;
            }
        }
    }

    private void finDePartie() {
        menu.showMessage("\nMerci d'avoir joué à Wonderboys !");
        menu.showMessage("Retour au menu principal...");
    }
}