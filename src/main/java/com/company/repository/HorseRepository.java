package com.company.repository;

import com.company.Gamer;
import com.company.Horse;
import com.company.HorseInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.company.repository.AbstractRepository.createConnection;

public class HorseRepository /*implements HorseInterface*/ {

    //TODO: repostiory dla koni, tak jak GamerRepositry

    public List<Horse> getPlayerHorses(Gamer gamer){

        String sqlSelectAllGamerHorse = "SELECT * FROM horse " +
                "INNER JOIN gamer_stud ON horse.gamer_stud = gamer_stud.gamer_stud" +
                "INNER JOIN gamer ON gamer_stud.gamer_id = gamer.gamer_id" +
                "WHERE gamer.gamer_id LIKE  " + gamer.getGamerId() ;
        List<Horse> gamerHorses = new ArrayList<>();

        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllGamerHorse);
             ResultSet rs = ps.executeQuery()) {
            gamerHorses.addAll((Collection<? extends Horse>) rs.getObject("horse")); //(Horse) rs.getObject("horse")
            return gamerHorses;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;//TODO: Zmienić na pustą liste
    }

        public void saveHorse(Horse horse){
            String updateOwner = "UPDATE horse SET gamer_ =" + horse.getHorseId() + " WHERE id = " + plotId;
            try (Connection conn = createConnection();
                 PreparedStatement ps = conn.prepareStatement(updateOwner);
                 ResultSet rs = ps.executeQuery()) {
            } catch (SQLException e) {
                // handle the exception
            }
        }

}
