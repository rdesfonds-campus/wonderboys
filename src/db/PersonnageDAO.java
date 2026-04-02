package db;

import characters.Warrior;
import characters.Wizard;
import equipment.DefensiveEquipment;
import equipment.OffensiveEquipment;

import java.sql.*;

/**
 * Objet d'accès aux données (DAO) pour les personnages.
 * <p>
 * Gère toutes les opérations SQL sur la table {@code personnage} :
 * sauvegarde, chargement, affichage et mise à jour.
 * Utilise {@link DatabaseConnection} pour obtenir les connexions.
 * </p>
 *
 * @author Romain D
 * @version 1.0
 */
public class PersonnageDAO {

    /**
     * Enregistre un nouveau personnage dans la base de données.
     *
     * @param perso personnage à sauvegarder
     */
    public void sauvegarder(characters.Character perso) {
        String sql = "INSERT INTO personnage (type, name, lifeLevel, attackLevel, weapon, defense) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, perso.getType());
            stmt.setString(2, perso.getName());
            stmt.setInt(3, perso.getLifeLevel());
            stmt.setInt(4, perso.getAttackLevel());
            stmt.setString(5, perso.getWeapon()  != null ? perso.getWeapon().getName()  : null);
            stmt.setString(6, perso.getDefense() != null ? perso.getDefense().getName() : null);

            stmt.executeUpdate();
            System.out.println("Personnage sauvegardé en base !");

        } catch (SQLException e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Met à jour les points de vie, le niveau d'attaque et le score
     * d'un personnage existant en base.
     *
     * @param perso personnage à mettre à jour (doit avoir un id valide)
     */
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

    /**
     * Affiche dans la console tous les personnages de la base de données.
     */
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

    /**
     * Charge un personnage depuis la base de données par son identifiant.
     *
     * @param id identifiant du personnage en base
     * @return le personnage reconstruit, ou {@code null} si non trouvé
     */
    public characters.Character charger(int id) {
        String sql = "SELECT * FROM personnage WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String type       = rs.getString("type");
                String name       = rs.getString("name");
                int lifeLevel     = rs.getInt("lifeLevel");
                int attackLevel   = rs.getInt("attackLevel");
                String weaponName = rs.getString("weapon");
                String defName    = rs.getString("defense");

                OffensiveEquipment arme    = new OffensiveEquipment("Weapon", attackLevel, weaponName);
                DefensiveEquipment defense = new DefensiveEquipment("Shield", 2, defName);

                characters.Character perso;
                if (type.equals("Warrior")) {
                    perso = new Warrior(name, lifeLevel, attackLevel, arme, defense);
                } else {
                    perso = new Wizard(name, lifeLevel, attackLevel, arme, defense);
                }
                perso.setId(id);
                return perso;
            }

        } catch (SQLException e) {
            System.out.println("Erreur chargement : " + e.getMessage());
        }

        return null;

    }
    /**
     * Modifie le nom d'un personnage existant en base.
     *
     * @param id      identifiant du personnage à modifier
     * @param nouveauNom nouveau nom à enregistrer
     */
    public void modifier(int id, String nouveauNom) {
        String sql = "UPDATE personnage SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nouveauNom);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Personnage modifié !");
            } else {
                System.out.println("Aucun personnage trouvé avec l'id " + id);
            }

        } catch (SQLException e) {
            System.out.println("Erreur modification : " + e.getMessage());
        }
    }

    /**
     * Supprime un personnage de la base de données.
     *
     * @param id identifiant du personnage à supprimer
     */
    public void supprimer(int id) {
        String sql = "DELETE FROM personnage WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Personnage supprimé !");
            } else {
                System.out.println("Aucun personnage trouvé avec l'id " + id);
            }

        } catch (SQLException e) {
            System.out.println("Erreur suppression : " + e.getMessage());
        }
    }
}