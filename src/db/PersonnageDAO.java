package db;

import characters.Character;
import characters.Warrior;
import characters.Wizard;
import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

import java.sql.*;

public class PersonnageDAO {

    // Enregistre un nouveau personnage en base
    public void sauvegarder(characters.Character perso) {
        String sql = "INSERT INTO personnage (type, name, lifeLevel, attackLevel, weapon, defense) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, perso.getType());
            stmt.setString(2, perso.getName());
            stmt.setInt(3, perso.getLifeLevel());
            stmt.setInt(4, perso.getAttackLevel());
            stmt.setString(5, perso.getWeapon() != null ? perso.getWeapon().getName() : null);
            stmt.setString(6, perso.getDefense() != null ? perso.getDefense().getName() : null);

            stmt.executeUpdate();
            System.out.println("Personnage sauvegardé en base !");

        } catch (SQLException e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }

    // Récupère tous les personnages
    public void afficherTous() {
        String sql = "SELECT * FROM personnage";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("[" + rs.getInt("id") + "] "
                        + rs.getString("type") + " - "
                        + rs.getString("name")
                        + " | PV : " + rs.getInt("lifeLevel")
                        + " | Attaque : " + rs.getInt("attackLevel")
                        + " | Arme : " + rs.getString("weapon")
                        + " | Défense : " + rs.getString("defense"));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lecture : " + e.getMessage());
        }
    }
}