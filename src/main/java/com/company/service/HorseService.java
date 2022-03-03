package com.company.service;

import com.company.Gamer;
import com.company.Horse;
import com.company.StaticConfig;
import com.company.model.Breed;
import com.company.model.GamerStud;
import com.company.repository.HorseRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class HorseService {

    private HorseRepository horseRepository;

    private List<Horse> horses;

    //TODO: stworzyc konstruktor, ustawic horses na new ArrayList<Horse>();

    //TODO: stworzyc metode ktora tworzy konia
    // nie jestem pewny jakie argumenty beda potrzebne
    // nie ejstem epwny co powinna zwracac
    // com.company.Horse / org.bukkit.Entity / org.bukkit.Horse
    // pamietaj zeby zapisac go w bazie danych
    public  void createHorse(Horse horse, GamerStud gamer) {
        Random generator = new Random();
        // TODO: w bazie danych jest kilka breed,
        //  chcemy aby byly rangomowo generowane i przypisywane do poczatkowego konia!
        Integer generateBreedNumber = generator.nextInt(StaticConfig.HORSE_NUMBER);

        Optional<Breed> randomBreedHorse =  horseRepository.getBreedObject(generateBreedNumber);
        org.bukkit.entity.Horse horse1 = (org.bukkit.entity.Horse) new Horse(
                horseRepository.getHorseNumber()+1,
                gamer,
                "name",
                randomBreedHorse.get(),
                randomBreedHorse.get().getFast(),
                randomBreedHorse.get().getHungry(),
                randomBreedHorse.get().getThirst(),
                randomBreedHorse.get().getAppearance(),
                randomBreedHorse.get().getValue());
    }
    //TODO: stworzyc metode ktora zaladuje konie gracza
    // uruchomic ja przy PlayerJoinEvent
     public  void loadHorses(Gamer gamer){

    }



}
