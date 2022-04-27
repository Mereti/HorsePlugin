package com.company.repository;

import com.company.model.Gamer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;



public class GamerRepository extends AbstractRepository {


    public Optional<Gamer> getGamerByNick(String nickname) {
        String sqlSelectAllPlots = "SELECT * FROM gamer WHERE nickname = \"" + nickname + "\"";
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPlots);
             ResultSet rs = ps.executeQuery()) {
            Optional<Gamer> gamerOptional = Optional.empty();
            if(rs.next())
             gamerOptional = Optional.of(new Gamer(rs.getInt("gamer_id"), rs.getString("nickname"),rs.getInt("authme_id"),rs.getInt("points")));
            return gamerOptional;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

}
