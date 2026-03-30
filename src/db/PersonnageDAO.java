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

    public void mettreAJour(characters.Character perso) {
        String sql = "UPDATE personnage SET lifeLevel = ?, attackLevel = ?, score = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, perso.getLifeLevel());
            stmt.setInt(2, perso.getAttackLevel());
            stmt.setInt(3, perso.getScore());
            stmt.setInt(4, perso.getId());

            stmt.executeUpdate();
            System.out.println("Personnage mis à jour en base !");

        } catch (SQLException e) {
            System.out.println("Erreur mise à jour : " + e.getMessage());
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
    // Récupère un personnage par son id
    public characters.Character charger(int id) {
        String sql = "SELECT * FROM personnage WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                String name = rs.getString("name");
                int lifeLevel = rs.getInt("lifeLevel");
                int attackLevel = rs.getInt("attackLevel");
                String weaponName = rs.getString("weapon");
                String defenseName = rs.getString("defense");

                equipment.OffensiveEquipment arme = new equipment.OffensiveEquipment("Weapon", attackLevel, weaponName);
                equipment.DefensiveEquipment defense = new equipment.DefensiveEquipment("Shield", 2, defenseName);

                if (type.equals("Warrior")) {
                    return new characters.Warrior(name, lifeLevel, attackLevel, arme, defense);
                } else {
                    return new characters.Wizard(name, lifeLevel, attackLevel, arme, defense);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur chargement : " + e.getMessage());
        }

        return null;
    }
}