package com.company.repository;

import com.company.Gamer;
import com.company.Horse;

import java.util.List;

public abstract class HorseRepository {

    //TODO: repostiory dla koni, tak jak GamerRepositry

    public abstract List<Horse> getPlayerHorses(Gamer gamer);

    public abstract void saveHorse(Horse horse);



}
