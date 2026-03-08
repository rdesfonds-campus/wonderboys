package fr.campus.wonderboys.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HeroDAO {

    public void getHeroes() {
        // Ici on écrira la requête SELECT * FROM Character
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `Character`")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String type = resultSet.getString("Type");
                String name = resultSet.getString("Name");
                int lifePoints = resultSet.getInt("LifePoints");
                int strength = resultSet.getInt("Strength");
                String offensive = resultSet.getString("OffensiveEquipment");
                String defensive = resultSet.getString("DefensiveEquipment");

                System.out.println("[" + id + "] "
                        + type + " " + name
                        + " | LifePoints=" + lifePoints
                        + " | Strength=" + strength
                        + " | Off=" + offensive
                        + " | Def=" + defensive);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des héros :");
            e.printStackTrace();
        }
    }
    public void createHero(fr.campus.wonderboys.characters.Character hero) {
        String sql = "INSERT INTO `Character` (" +
                "Type, Name, LifePoints, Strength, OffensiveEquipment, DefensiveEquipment" +
                ") VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hero.getType());
            statement.setString(2, hero.getName());
            statement.setInt(3, hero.getLifeLevel());
            statement.setInt(4, hero.getAttackLevel());
            statement.setString(5, hero.getWeapon() != null ? hero.getWeapon().getName() : null);
            statement.setString(6, hero.getDefense() != null ? hero.getDefense().getName() : null);

            statement.executeUpdate();
            System.out.println("Héros enregistré en base : " + hero.getName());

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement du héros :");
            e.printStackTrace();
        }
    }
    public void editHero(fr.campus.wonderboys.characters.Character hero) {
        String sql = "UPDATE `Character` SET " +
                "Type = ?, " +
                "Name = ?, " +
                "LifePoints = ?, " +
                "Strength = ?, " +
                "OffensiveEquipment = ?, " +
                "DefensiveEquipment = ? " +
                "WHERE Id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             java.sql.PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, hero.getType());
            statement.setString(2, hero.getName());
            statement.setInt(3, hero.getLifeLevel());
            statement.setInt(4, hero.getAttackLevel());
            statement.setString(5, hero.getWeapon() != null ? hero.getWeapon().getName() : null);
            statement.setString(6, hero.getDefense() != null ? hero.getDefense().getName() : null);
            statement.setInt(7, hero.getId());

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Héros mis à jour en base : " + hero.getName());
            } else {
                System.out.println("Aucun héros mis à jour (Id inconnu : " + hero.getId() + ")");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du héros :");
            e.printStackTrace();
        }
    }


}
