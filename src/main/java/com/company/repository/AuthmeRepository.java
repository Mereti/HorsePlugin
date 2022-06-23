package com.company.repository;

import com.company.model.Authme;
import com.company.model.Gamer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthmeRepository extends AbstractRepository{

    public Optional<Authme> getAuthmeById(int authmeId){
        String sql = "SELECT * FROM authme WHERE id = " + authmeId;

        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            Optional<Authme> authmeOptional = Optional.empty();
            if(rs.next())
                authmeOptional = Optional.of(new Authme(
                        rs.getInt("id"), rs.getString("username")
                        ,rs.getLong("lastLogin"),rs.getLong("regdate")));

            return authmeOptional;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
