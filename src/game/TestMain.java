package game;

public class TestMain {

    public static void main(String[] args) {

        // Test Dice
        Dice de = new Dice(6);
        System.out.println("=== TEST DÉ ===");
        System.out.println("Lancer 1 : " + de.roll());
        System.out.println("Lancer 2 : " + de.roll());
        System.out.println("Lancer 3 : " + de.roll());

        // Test Board
        System.out.println("\n=== TEST PLATEAU ===");
        Board plateau = new Board(new Menu());
        System.out.println(plateau);
        System.out.println("Case 1 : " + plateau.getCase(1));
        System.out.println("Case 2 : " + plateau.getCase(2));
        System.out.println("Case 3 : " + plateau.getCase(3));
        System.out.println("Case 4 : " + plateau.getCase(4));

        // Test connexion BDD
        System.out.println("\n=== TEST CONNEXION BDD ===");
        try {
            java.sql.Connection conn = db.DatabaseConnection.getConnection();
            System.out.println("Connexion réussie !");
            conn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        // Test BDD
        System.out.println("\n=== TEST BDD ===");
        try {
            java.sql.Connection conn = db.DatabaseConnection.getConnection();
            System.out.println("Connexion réussie !");
            conn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        // Test sauvegarde personnage
        System.out.println("\n=== TEST SAUVEGARDE ===");
        equipment.OffensiveEquipment arme = new equipment.OffensiveEquipment("Weapon", 5, "Hache de guerre");
        equipment.DefensiveEquipment bouclier = new equipment.DefensiveEquipment("Shield", 4, "Bouclier de fer");
        characters.Warrior william = new characters.Warrior("William", 10, 5, arme, bouclier);

        db.PersonnageDAO dao = new db.PersonnageDAO();
        dao.sauvegarder(william);
        dao.afficherTous();
    }
}