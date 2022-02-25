package com.company.service;

import com.company.Gamer;
import com.company.repository.GamerRepository;
import com.company.repository.ScoreRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GamerService {

    //TODO: zrobic konstruktor ktory ustawi gamerRepository, scoreRepository i gamers na new HashMap

    private ScoreRepository scoreRepository;
    private GamerRepository gamerRepository;
    private Map<String, Gamer> gamers;

    public GamerService(ScoreRepository scoreRepository, GamerRepository gamerRepository, Map<String, Gamer> gamers) {
        this.scoreRepository = scoreRepository;
        this.gamerRepository = gamerRepository;
        this.gamers = new HashMap<>();
    }

    public GamerService() {

    }

    //TODO: wykonac przy PlayerJoinEvent
    public void loadGamer(String name) {
        gamers.put(name, gamerRepository.getGamerByNick(name));
    }

    //TODO: wykonac przy PlayerQuitEvent
    public void removeGamer(String name) {
        gamers.remove(name);
    }

    public Optional<Gamer> getGamer(String name) {
        return Optional.ofNullable(gamers.get(name));
    }

    //TODO: uzywajac ScoreRepository dodaj graczowi punkty
    public void addPoints(Gamer gamer, int points){
        scoreRepository.updateGamerScore(gamer,points);
    }
}
