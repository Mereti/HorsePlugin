package com.company;

import com.company.commands.GamerCommands;
import com.company.listener.*;
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

    GamerCommands gamerCommands;


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

        GamerCommands commands = new GamerCommands();

        getCommand("tplobby").setExecutor(commands);
        getCommand("horsename").setExecutor(commands);

        getServer().getPluginManager().registerEvents(new JoinListener(gamerService, horseService), this);
        getServer().getPluginManager().registerEvents(new InteractListener(plotService,gamerService), this);
        getServer().getPluginManager().registerEvents(new BlockListener(gamerService, plotService), this);
         getServer().getPluginManager().registerEvents(new QuitListener(gamerService),this);
         getServer().getPluginManager().registerEvents(new HorseListener(horseService,gamerService), this);

         //TODO: zarejrestrowac HorseListener
        //Mysql.displayAllGamers();
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
