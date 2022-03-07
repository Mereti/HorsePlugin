package com.company.service;

import com.company.Gamer;
import com.company.repository.GamerRepository;
import com.company.repository.ScoreRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GamerService {

    private ScoreRepository scoreRepository;
    private GamerRepository gamerRepository;
    private Map<String, Gamer> gamers;

    public GamerService(ScoreRepository scoreRepository, GamerRepository gamerRepository) {
        this.scoreRepository = scoreRepository;
        this.gamerRepository = gamerRepository;
        this.gamers = new HashMap<>();
    }

    public Gamer loadGamer(String name) {
        Gamer gamer = gamerRepository.getGamerByNick(name).orElseThrow(() -> new RuntimeException("Gracz nie istnieje"));
        gamers.put(name, gamer);
        return gamer;
    }

    public void removeGamer(String name) {
        gamers.remove(name);
    }

    public Optional<Gamer> getGamer(String name) {
        return Optional.ofNullable(gamers.get(name));
    }

    public void addPoints(Gamer gamer, int points){
        scoreRepository.updateGamerScore(gamer,points);
    }
}
