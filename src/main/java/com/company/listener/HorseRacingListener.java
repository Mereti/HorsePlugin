package com.company.listener;


import com.company.service.GamerService;
import com.company.service.HorseService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class HorseRacingListener implements Listener {

    private GamerService gamerService;
    private HorseService horseService;
    private final Location oneStratBox = new Location(Bukkit.getWorld("world"),119.212,73.85,-83.918);
    private final Location twoStartBox = new Location(Bukkit.getWorld("world"), 119.375,73.85,-81.004);
    private final Location threeStartBox = new Location(Bukkit.getWorld("world"), 119.362,73.85,-77.824);
    private final Location aMetaPoint = new Location(Bukkit.getWorld("world"), 111.585,73.00,-76.300);
    private final Location bMetaPoint = new Location(Bukkit.getWorld("world"), 111.300 ,73.00,-84.628);


    public HorseRacingListener(GamerService gamerService, HorseService horseService, Location[] startBox) {
        this.gamerService = gamerService;
        this.horseService = horseService;
    }

    @EventHandler
    public void onHorseRacing(BlockPlaceEvent event, List<Player> players){





    }
}
