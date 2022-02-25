package com.company.repository;

import com.company.Gamer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreRepository extends AbstractRepository {

    public void updateGamerScore(Gamer gamer, int addedPoints){
        String sqlSelectGamer = "UPDATE gamer SET points = " + addedPoints + "WHERE gamer_id=" + gamer.getGamerId();
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectGamer);
             ResultSet resultSet = ps.executeQuery()) {

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
