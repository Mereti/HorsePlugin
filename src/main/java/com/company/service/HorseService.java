package com.company.service;

import com.company.Gamer;
import com.company.Horse;
import com.company.repository.HorseRepository;

import java.util.List;

public abstract class HorseService {

    private HorseRepository horseRepository;

    private List<Horse> horses;

    //TODO: stworzyc konstruktor, ustawic horses na new ArrayList<Horse>();

    //TODO: stworzyc metode ktora tworzy konia
    // nie jestem pewny jakie argumenty beda potrzebne
    // nie ejstem epwny co powinna zwracac
    // com.company.Horse / org.bukkit.Entity / org.bukkit.Horse
    // pamietaj zeby zapisac go w bazie danych
    public abstract void createHorse();

    //TODO: stworzyc metode ktora zaladuje konie gracza
    // uruchomic ja przy PlayerJoinEvent
    public abstract void loadHorses(Gamer gamer);



}
