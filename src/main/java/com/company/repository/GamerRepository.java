package com.company.repository;

import com.company.Gamer;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class GamerRepository extends AbstractRepository {

    public Gamer getGamerByNick(String nickname) {
        //todo: pobrac z bazy danych jak w PlotRepository

        String sqlSelectAllPlots = "SELECT gamer FROM gamer WHERE nickname LIKE" + nickname;
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPlots);
             ResultSet rs = ps.executeQuery()) {
            Gamer gamer = new Gamer(rs.getInt("gamer_id"), rs.getString("nickname"));
            return gamer;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Gamer(0,"Brak gracza");//??
        //TODO: czy moze byc nullem ?
    }

}
