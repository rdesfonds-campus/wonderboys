package fr.campus.wonderboys.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardCellDAO {

    public List<BoardCellData> loadBoardCells() {
        List<BoardCellData> cells = new ArrayList<>();

        String sql = "SELECT Position, CellType, ContentName FROM BoardCell ORDER BY Position";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int position = rs.getInt("Position");
                String cellType = rs.getString("CellType");
                String contentName = rs.getString("ContentName");

                BoardCellData data = new BoardCellData(position, cellType, contentName);
                cells.add(data);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des cases de plateau :");
            e.printStackTrace();
        }

        return cells;
    }
}
