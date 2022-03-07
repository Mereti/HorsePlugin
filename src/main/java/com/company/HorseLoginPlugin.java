package com.company;

import com.company.listener.BlockListener;
import com.company.listener.InteractListener;
import com.company.listener.JoinListener;
import com.company.listener.QuitListener;
import com.company.repository.*;
import com.company.service.GamerService;
import com.company.service.HorseService;
import com.company.service.PlotService;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseLoginPlugin extends JavaPlugin {

    GamerService gamerService;
    HorseService horseService;
    PlotService plotService;
    GamerRepository gamerRepository;
    AbstractRepository abstractRepository;
    PlotRepository plotRepository;
    ScoreRepository scoreRepository;
    HorseRepository horseRepository;


    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        gamerRepository = new GamerRepository();
        abstractRepository = new AbstractRepository();
        plotRepository = new PlotRepository();
        scoreRepository = new ScoreRepository();
        horseRepository = new HorseRepository();
        gamerService = new GamerService(scoreRepository, gamerRepository);
        plotService = new PlotService(plotRepository);
        horseService = new HorseService(horseRepository);

        getServer().getPluginManager().registerEvents(new JoinListener(gamerService, horseService), this);
        getServer().getPluginManager().registerEvents(new InteractListener(plotService,gamerService), this);
        getServer().getPluginManager().registerEvents(new BlockListener(gamerService, plotService), this);
         getServer().getPluginManager().registerEvents(new QuitListener(gamerService),this);
         getServer().getPluginManager().registerEvent(new );

         //TODO: zarejrestrowac HorseListener
        //Mysql.displayAllGamers();
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
