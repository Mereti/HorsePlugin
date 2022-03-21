package com.company.repository;

import com.company.Horse;
import com.company.model.Breed;
import com.company.model.GamerStud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.company.repository.AbstractRepository.createConnection;

public class GamerStudRepository {

    public List<GamerStud> getGamersStuds(){
        String sqlSelectAllStuds = "SELECT * FROM gamer_stud";
        List<GamerStud> studList = new ArrayList<>();
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllStuds);
             ResultSet rs = ps.executeQuery()) {
            GamerStud gamerStud = new GamerStud(rs.getInt("gamer_stud_id"), gamer, rs.getString("gamer_stud_name"));

            return gamerHorses;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }
}
