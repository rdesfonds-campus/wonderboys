package fr.campus.wonderboys.db;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.heros.Warrior;
import fr.campus.wonderboys.characters.heros.Wizard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {

    public void saveCharacter(Character hero) {

        String sql = "INSERT INTO `character` (Type, Name, LifePoints, Strength) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, hero.getType());
            statement.setString(2, hero.getName());
            statement.setInt(3, hero.getLifeLevel());
            statement.setInt(4, hero.getAttackLevel());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                hero.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Character> getAllHeroes() {

        List<Character> heroes = new ArrayList<>();

        String sql = "SELECT * FROM `character`";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                String type = rs.getString("Type");
                String name = rs.getString("Name");
                int life = rs.getInt("LifePoints");
                int strength = rs.getInt("Strength");

                Character hero;

                if (type.equals("Warrior")) {
                    hero = new Warrior(name);
                } else {
                    hero = new Wizard(name);
                }

                hero.setId(rs.getInt("Id"));
                hero.setLifeLevel(life);
                hero.setAttackLevel(strength);

                heroes.add(hero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return heroes;
    }

    public void changeLifePointsCharacter(int id, int lifePoints) {

        String sql = "UPDATE `character` SET LifePoints=? WHERE Id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, lifePoints);
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}