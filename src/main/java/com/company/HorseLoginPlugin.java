package com.company;

import com.company.listener.BlockListener;
import com.company.listener.InteractListener;
import com.company.listener.JoinListener;
import com.company.listener.QuitListener;
import com.company.repository.AbstractRepository;
import com.company.repository.GamerRepository;
import com.company.repository.PlotRepository;
import com.company.repository.ScoreRepository;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseLoginPlugin extends JavaPlugin {

    //TODO: stworzyc zmiennie dla HorseRepository i HorseService

    GamerService gamerService;
    PlotService plotService;
    GamerRepository gamerRepository;
    AbstractRepository abstractRepository;
    PlotRepository plotRepository;
    ScoreRepository scoreRepository;

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        //TODO: zainicjowac HorseRepository i HorseService
        gamerRepository = new GamerRepository();
        abstractRepository = new AbstractRepository();
        plotRepository = new PlotRepository();
        scoreRepository = new ScoreRepository();
        gamerService = new GamerService(scoreRepository, gamerRepository);
        plotService = new PlotService(plotRepository);

        getServer().getPluginManager().registerEvents(new JoinListener(gamerService), this);
        getServer().getPluginManager().registerEvents(new InteractListener(plotService,gamerService), this);
        getServer().getPluginManager().registerEvents(new BlockListener(gamerService), this);
         getServer().getPluginManager().registerEvents(new QuitListener(gamerService),this);

         //TODO: zarejrestrowac HorseListener
        //Mysql.displayAllGamers();
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
