package com.company.repository;

import com.company.Gamer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;



public class GamerRepository extends AbstractRepository {

    public Optional<Gamer> getGamerByNick(String nickname) {
        String sqlSelectAllPlots = "SELECT * FROM gamer WHERE nickname LIKE " + nickname;
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPlots);
             ResultSet rs = ps.executeQuery()) {
            Gamer gamer = new Gamer(rs.getInt("gamer_id"), rs.getString("nickname"));
            return Optional.of(gamer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

}
