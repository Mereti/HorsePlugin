package com.company.service;

import com.company.Gamer;
import com.company.Horse;
import com.company.StaticConfig;
import com.company.model.Breed;
import com.company.model.GamerStud;
import com.company.repository.GamerStudRepository;
import com.company.repository.HorseRepository;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.*;

public class HorseService {

    private HorseRepository horseRepository;
    private GamerStudRepository gamerStudRepository;

    private List<Horse> horses;

    public HorseService(HorseRepository horseRepository) {
        horses = new ArrayList<>();
        this.horseRepository = horseRepository;
    }

    //TODO: stworzyc metode ktora tworzy konia
    public  Horse createHorse(Gamer gamer, Location spawnLocation) {
        Random generator = new Random();
        // TODO: w bazie danych jest kilka breed,
        //  chcemy aby byly rangomowo generowane i przypisywane do poczatkowego konia!
        Integer generateBreedNumber = generator.nextInt(StaticConfig.HORSE_NUMBER);
        Optional<Breed> randomBreedHorse =  horseRepository.getBreedObject(generateBreedNumber);
        Optional <GamerStud> stud = null;
        if(gamerStudRepository.getStudByGamer(gamer).isPresent()){
            stud = gamerStudRepository.getStudByGamer(gamer);
        }else{
            gamerStudRepository.saveGamerStud(new GamerStud(null,gamer.getGamerId(),"Stud name"));
            stud = gamerStudRepository.getStudByGamer(gamer);
        }
        //TODO: utowrzyc gamer stud
        // bedzie trzeba stworzyc GamerStudRepository do wczytywania i zapisywania do bazy danych
        // analogiczne do HorseRepository
        // pozniej nalezy stworzyc GamerStudService i zrobic tam metody do tego
        // a pozniej uzyc ich tutaj m.in.
        Horse horse = new Horse(
                null,
                stud.get(),
                "horse",
                randomBreedHorse.get(),
                randomBreedHorse.get().getFast(),
                randomBreedHorse.get().getHungry(),
                randomBreedHorse.get().getThirst(),
                randomBreedHorse.get().getAppearance(),
                randomBreedHorse.get().getValue());

        horse = horseRepository.saveHorse(horse);
        org.bukkit.entity.Horse horseBukkit = (org.bukkit.entity.Horse) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.HORSE);
        //TODO: ustawic parametry konia??????  jak to polaczyc !?
        horseBukkit.setJumpStrength(2);

        horseBukkit.getUniqueId();
        horseBukkit.getEntityId();

        spawnLocation.getWorld().getLivingEntities().stream().filter(livingentity -> livingentity.getUniqueId() == horseBukkit.getUniqueId()).findAny();

        return horse;
    }

    public  void loadHorses(Gamer gamer){
        horses.addAll(horseRepository.getPlayerHorses(gamer));
    }

    //TODO: zwrocic wszystkie konie gracza
    public List<Horse> getGamerHorses(Gamer gamer) {
        return Collections.emptyList();
    }

}
