package com.company.repository;

import com.company.Gamer;
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
            while(rs.next()) {
                GamerStud gamerStud = new GamerStud(rs.getInt("gamer_stud_id"), rs.getInt("gamer_id"), rs.getString("name"));
                studList.add(gamerStud.getGamerStudId(),gamerStud);
            }
            return studList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<GamerStud> getStudByGamer(Gamer gamer){
        String sqlSelectStud = "SELECT * FROM gamer_stud WHERE gamer_id = " + gamer.getGamerId();
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectStud);
             ResultSet rs = ps.executeQuery()) {
            Optional<GamerStud> gamerStudOptional = Optional.empty();
            if(rs.next()) {
             gamerStudOptional = Optional.of(new GamerStud(rs.getInt("gamer_stud_id"),rs.getInt("gamer_id"), rs.getString("nickname")));
            }
            return gamerStudOptional;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public GamerStud saveGamerStud(GamerStud gamerStud){
        String sqlCreateSud = null;
        if(gamerStud.getGamerStudId() == null){
            sqlCreateSud = "INSERT INTO gamer_stud (gamer_stud_id,name, gamer_id) VALUES (null,"+ gamerStud.getName() + gamerStud.getGamerId()  +")";

        }else{
            sqlCreateSud = "UPDATE gamer_stud SET name = " + gamerStud.getName();
        }
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlCreateSud);
             ResultSet rs = ps.executeQuery()) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    gamerStud.setGamerStudId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            // handle the exception
        }
        return gamerStud;
    }

}
