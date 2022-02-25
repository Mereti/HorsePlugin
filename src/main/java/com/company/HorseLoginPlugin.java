package com.company;

import com.company.listener.BlockListener;
import com.company.listener.InteractListener;
import com.company.listener.JoinListener;
import com.company.repository.AbstractRepository;
import com.company.repository.GamerRepository;
import com.company.repository.PlotRepository;
import com.company.repository.ScoreRepository;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseLoginPlugin extends JavaPlugin {


    //TODO: stworz zmienne dla wszystkich servicow i repository

    GamerService gamerService;
    PlotService plotService;
    GamerRepository gamerRepository;
    AbstractRepository abstractRepository;
    PlotRepository plotRepository;
    ScoreRepository scoreRepository;

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        //TODO: zainicjalizuj wszystkie zmienne servicow i repository
        // np. gamerRepostory = new GamerRepository();
        // gameService = new GameService(repository1, repository2);

        gamerRepository = new GamerRepository();
        abstractRepository = new AbstractRepository();
        plotRepository = new PlotRepository();
        scoreRepository = new ScoreRepository();
        gamerService = new GamerService();
        plotService = new PlotService();

        //TODO: zarejestru BlockListener

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(plotService,gamerService), this);
        getServer().getPluginManager().registerEvents(new BlockListener(gamerService), this);
        //TODO: jak dodac gracza>!!!
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(),this);
       // getServer().getPluginManager().registerEvents(new PlayerQuitEvent(gamerService.removeGamer(Player);),this);
        //Mysql.displayAllGamers();
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
