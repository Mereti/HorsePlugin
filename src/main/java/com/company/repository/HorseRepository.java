package com.company.repository;

import com.company.model.Gamer;
import com.company.model.Horse;
import com.company.model.Breed;
import com.company.model.GamerStud;
import com.company.model.HorseBreed;

import java.sql.*;
import java.util.*;

import static com.company.repository.AbstractRepository.createConnection;

public class HorseRepository{

    public List<Horse> getPlayerHorses(Gamer gamer) {

        String sqlSelectAllGamerHorse = "SELECT * FROM horse " +
                "INNER JOIN gamer_stud ON horse.gamer_stud_id = gamer_stud.gamer_stud_id " +
                "INNER JOIN gamer ON gamer_stud.gamer_id = gamer.gamer_id " +
                "INNER JOIN breed ON horse.breed_id = breed.breed_id " +
                "WHERE gamer.gamer_id = " + gamer.getGamerId();
        List<Horse> gamerHorses = new ArrayList<>();

        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllGamerHorse);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GamerStud gamerStud = new GamerStud(rs.getInt("gamer_stud_id"), gamer.getGamerId(), rs.getString("stud_name"));
                Optional<Breed> breedOptional = getBreedObject(rs.getInt("breed.breed_id"));

                if(breedOptional.isPresent()) {
                    Breed breed = breedOptional.get();
                    Horse horse = new Horse(rs.getInt("horse.horse_id"),rs.getString("bukkit_horse_id"), gamerStud, rs.getString("horse.name"), breed, rs.getDouble("fast"), rs.getDouble("hungry"), rs.getDouble("thirst"), rs.getDouble("appearance"), rs.getDouble("value"));
                    gamerHorses.add(horse);
                }
            }
            return gamerHorses;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Horse saveHorse(Horse horse) {

        String sql = null;

        if(horse.getHorseId() == null ){
            sql = "INSERT INTO horse (horse_id, bukkit_horse_id, gamer_stud_id, name, breed_id, fast, hungry, thirst, appearance, value) " +
                    "VALUES (null,'" + horse.getBukkitHorseId() + "', " + horse.getGamerStud().getGamerStudId() + ", '" + horse.getName() + "',"
                    + horse.getBreed().getBreedId() + "," + horse.getFast() + "," + horse.getHungry()
                    + "," + horse.getThirst() + "," + horse.getAppearance() + "," + horse.getValue() +")";

            try (Connection conn = createConnection();
                Statement ps =  conn.createStatement();)
                 {
                    ps.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {

                        if (generatedKeys.next()) {
                            horse.setHorseId(generatedKeys.getInt(1));
                        }
                        else {
                            throw new SQLException("Creating user failed, no ID obtained.");
                        }

                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql = "UPDATE horse SET bukkit_horse_id = " + horse.getBukkitHorseId() + ", name = " + horse.getName() + ", fast = "+ horse.getFast()
                    + ", hungry = " + horse.getHungry() + " ,thirst =" + horse.getThirst()
                    + ", appearance = " + horse.getAppearance() + " ,value = " + horse.getValue() + " WHERE horse_id = "+ horse.getHorseId();
            try (Connection conn = createConnection();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ){
                boolean rs = ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
        return horse;
    }

    public Optional<Breed> getBreedObject(Integer number){
        String getBreed = "SELECT * FROM breed WHERE breed_id = " + number;
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(getBreed);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("breed_id");
                HorseBreed horseBreed = HorseBreed.getById(rs.getInt("horse_breed")).get();
                double fast = rs.getDouble("fast");
                double hungry = rs.getDouble("hungry");
                double thirst = rs.getDouble("thirst");
                double appearance = rs.getDouble("appearance");
                double value = rs.getDouble("value");
                Breed breed = new Breed(id,horseBreed,fast,hungry,thirst,appearance,value);
                return Optional.of(breed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return Optional.empty();
    }

}
