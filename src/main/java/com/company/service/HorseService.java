package com.company.service;

import com.company.Gamer;
import com.company.Horse;
import com.company.StaticConfig;
import com.company.model.Breed;
import com.company.model.GamerStud;
import com.company.repository.GamerStudRepository;
import com.company.repository.HorseRepository;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class HorseService {

    private HorseRepository horseRepository;
    private GamerStudRepository gamerStudRepository;
    private List<Horse> horses;

    public HorseService(HorseRepository horseRepository, GamerStudRepository gamerStudRepository) {
        horses = new ArrayList<>();
        this.horseRepository = horseRepository;
        this.gamerStudRepository = gamerStudRepository;
    }



    //TODO: stworzyc metode ktora tworzy konia
    public  Horse createHorse(Gamer gamer, Location spawnLocation, String bukkitHorseId) {
        Random generator = new Random();
        Integer generateBreedNumber = 1; //generator.nextInt(StaticConfig.HORSE_NUMBER);
        Breed randomBreedHorse =  horseRepository.getBreedObject(generateBreedNumber).get();
        GamerStud stud = null;
        if(gamerStudRepository.getStudByGamer(gamer).isPresent()){
            stud = gamerStudRepository.getStudByGamer(gamer).get();
        }else {

            stud =  gamerStudRepository.saveGamerStud(new GamerStud(null, gamer.getGamerId(), "Stud name"));
        /*gamerStudRepository.getStudByGamer(gamer).get();*/
        }

            Horse horse = new Horse(
                    null,
                    bukkitHorseId,
                    stud,
                    "horse",
                    randomBreedHorse,
                    randomBreedHorse.getFast(),
                    randomBreedHorse.getHungry(),
                    randomBreedHorse.getThirst(),
                    randomBreedHorse.getAppearance(),
                    randomBreedHorse.getValue());

            horse = horseRepository.saveHorse(horse);
            org.bukkit.entity.Horse horseBukkit = (org.bukkit.entity.Horse) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.HORSE);


             spawnLocation.getWorld().getLivingEntities().stream().filter(livingentity -> livingentity.getUniqueId() == horseBukkit.getUniqueId()).findAny();

        return horse;
    }

    public  void loadHorses(Gamer gamer){
        horses.addAll(horseRepository.getPlayerHorses(gamer));
    }

    //TODO: to jest ok?

    public List<Horse> getGamerHorses(Gamer gamer) {
        return Collections.emptyList();
    }

    public Horse getHorseByGamerHorseName(Gamer gamer, String name){
        int studId = gamerStudRepository.getStudByGamer(gamer).get().getGamerStudId();
        return (Horse) horses.stream().filter(horse -> horse.getGamerStud().getGamerStudId().equals(studId)).filter(horse -> horse.getName().equals(name));
    }

}
