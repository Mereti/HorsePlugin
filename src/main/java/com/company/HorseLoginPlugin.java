package com.company;

import com.company.commands.GamerCommands;
import com.company.listener.*;
import com.company.model.Breed;
import com.company.repository.*;
import com.company.service.AuthmeService;
import com.company.service.GamerService;
import com.company.service.HorseService;
import com.company.service.PlotService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseLoginPlugin extends JavaPlugin {

    GamerService gamerService;
    HorseService horseService;
    PlotService plotService;
    AuthmeService authmeService;
    GamerRepository gamerRepository;
    AbstractRepository abstractRepository;
    PlotRepository plotRepository;
    ScoreRepository scoreRepository;
    HorseRepository horseRepository;
    GamerStudRepository gamerStudRepository;
    AuthmeRepository authmeRepository;

    GamerCommands gamerCommands;

    RacingArena racingArena;


    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");



        gamerRepository = new GamerRepository();
        abstractRepository = new AbstractRepository();
        plotRepository = new PlotRepository();
        scoreRepository = new ScoreRepository();
        horseRepository = new HorseRepository();
        authmeRepository = new AuthmeRepository();
        gamerStudRepository = new GamerStudRepository();
        gamerService = new GamerService(scoreRepository, gamerRepository);
        horseService = new HorseService(horseRepository,gamerStudRepository);
        plotService = new PlotService(plotRepository);
        authmeService = new AuthmeService(authmeRepository);

        racingArena = new RacingArena(gamerService, plotService);
        getServer().getScheduler().runTaskTimer(this, new RacingArenaRunnable(racingArena), 20, 20);

        GamerCommands commands = new GamerCommands(racingArena, horseService, gamerService, gamerStudRepository);

        getCommand("tplobby").setExecutor(commands);
        getCommand("horsename").setExecutor(commands);
        getCommand("racing").setExecutor(commands);
        getCommand("studname").setExecutor(commands);


        getServer().getPluginManager().registerEvents(new JoinListener(gamerService, horseService,authmeService), this);
        getServer().getPluginManager().registerEvents(new InteractListener(plotService,gamerService), this);
        getServer().getPluginManager().registerEvents(new BlockListener(gamerService, plotService), this);
        getServer().getPluginManager().registerEvents(new QuitListener(gamerService),this);
        getServer().getPluginManager().registerEvents(new EggBlockListener(),this);
        getServer().getPluginManager().registerEvents(new CreativeGetItemListener(),this);
        getServer().getPluginManager().registerEvents(new HorseRacingListener(racingArena), this);


        Bukkit.getWorld("world_flat").setMonsterSpawnLimit(0);
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
