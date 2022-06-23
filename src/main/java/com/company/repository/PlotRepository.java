package com.company.repository;

import com.company.model.Plot;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlotRepository extends AbstractRepository {

    public static List<Plot> loadAllPlots(){
        String sqlSelectAllPlots = "SELECT * FROM plot";
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPlots);
             ResultSet rs = ps.executeQuery()) {

            List<Plot> plots = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("plot_id");
                int gamer_id = rs.getInt("gamer_id");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int z = rs.getInt("z");

                Location location = Bukkit.getWorld("world_flat").getBlockAt(x,y,z).getLocation();

                plots.add(new Plot(id, gamer_id, location));
            }

            return plots;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public void updateOwner(int plotId, int gamerId) {
        String updateOwner = "UPDATE plot SET gamer_id =" + gamerId + " WHERE plot_id = " + plotId;
        try (Connection conn = createConnection();
             PreparedStatement ps = conn.prepareStatement(updateOwner);
             ) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            // handle the exception
        }
    }


}
