package com.company.service;

import com.company.model.Gamer;
import com.company.model.Horse;
import com.company.StaticConfig;
import com.company.model.Breed;
import com.company.model.GamerStud;
import com.company.repository.GamerStudRepository;
import com.company.repository.HorseRepository;
import org.bukkit.Location;
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


    public  Horse createHorse(Gamer gamer, Player player, String horseName) {
        Random generator = new Random();
        Integer generateBreedNumber = generator.nextInt(StaticConfig.HORSE_NUMBER);
        Breed randomBreedHorse =  horseRepository.getBreedObject(generateBreedNumber).get();
        GamerStud stud = null;
        if(gamerStudRepository.getStudByGamer(gamer).isPresent()){
            stud = gamerStudRepository.getStudByGamer(gamer).get();
        }else {
            stud =  gamerStudRepository.saveGamerStud(new GamerStud(null, gamer.getGamerId(), "Stud name"));
            player.sendMessage("Twoja stadnina została stworzona: Stud name");
            player.sendMessage("Jeśli chcesz zmienić jej nazwę użyj komendy: /studname <nazwa stadniny>");
        }

        Location spawnLocation = player.getLocation();

        org.bukkit.entity.Horse horseBukkit = (org.bukkit.entity.Horse) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.HORSE);
        horseBukkit.setCustomName(horseName);
        String bukkitHorseId = String.valueOf(horseBukkit.getUniqueId());
        player.sendMessage("To jest unikalne ID twojego konia: " + bukkitHorseId );

            Horse horse = new Horse(
                    null,
                    bukkitHorseId,
                    stud,
                    horseName,
                    randomBreedHorse,
                    randomBreedHorse.getFast(),
                    randomBreedHorse.getHungry(),
                    randomBreedHorse.getThirst(),
                    randomBreedHorse.getAppearance(),
                    randomBreedHorse.getValue());

            horse = horseRepository.saveHorse(horse);

             spawnLocation.getWorld().getLivingEntities().stream().filter(livingentity -> livingentity.getUniqueId() == horseBukkit.getUniqueId()).findAny();

        return horse;
    }

    public  void loadHorses(Gamer gamer){
        horses.addAll(horseRepository.getPlayerHorses(gamer));
    }

    public List<Horse> getGamerHorses(Gamer gamer) {
        return Collections.emptyList();
    }

    public Optional<Horse> getHorseByName(Gamer gamer, String name) {
        return horses.stream().filter(horse -> horse.getName().equals(name) && horse.getGamerStud().getGamerId() == gamer.getGamerId()).findAny();

    }

}
