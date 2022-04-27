package com.company.commands;

import com.company.RacingArena;
import com.company.enums.ArenaStatus;
import com.company.model.Gamer;
import com.company.model.GamerStud;
import com.company.model.Horse;
import com.company.repository.GamerStudRepository;
import com.company.service.GamerService;
import com.company.service.HorseService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;


public class GamerCommands implements CommandExecutor {


    private HorseService horseService;
    private GamerService gamerService;
    private GamerStudRepository gamerStudRepository;

    private RacingArena racingArena;

    public GamerCommands(RacingArena racingArena, HorseService horseService, GamerService gamerService, GamerStudRepository gamerStudRepository) {
        this.gamerService = gamerService;
        this.racingArena = racingArena;
        this.horseService =  horseService;
        this.gamerStudRepository = gamerStudRepository;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){ return true; }
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("tplobby")){
            if (player.getWorld()== Bukkit.getWorld("world_flat")){
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }else{
                player.sendMessage("You are in lobby!");
            }
        }
        else if(cmd.getName().equalsIgnoreCase("horsename")){

            if(player.getInventory().contains(Material.HORSE_SPAWN_EGG)){
                player.sendMessage("Posiadasz jajko konia!");
                if(args.length >= 1){
                    try{
                        EntityType horse = EntityType.HORSE;

                        if(player.getLocation().getWorld() == Bukkit.getWorld("world_flat") ){

                            horseService.createHorse(gamerService.getGamer(player.getName()).get(), player,args[0]);
                            player.getInventory().remove(Material.HORSE_SPAWN_EGG);

                        }else player.sendMessage("Nie mozesz tu tworzyć koni!");

                    }catch(IllegalArgumentException e){
                        e.printStackTrace();
                        player.sendMessage("BAD ARGUMENTS");
                    }
                } else{player.sendMessage("To many args");}
             } else{ player.sendMessage("Nie posiadasz jajka konia ! :( ");}
        }
        else if(cmd.getName().equalsIgnoreCase("studname")){
            GamerStud gamerStud = gamerStudRepository.getStudByGamer(gamerService.getGamer(player.getName()).get()).get();
            if(args.length >= 1) {
                try {
                    GamerStud gamerStud1 = new GamerStud(gamerStud.getGamerStudId(),gamerStud.getGamerId(),args[0]);
                    gamerStudRepository.saveGamerStud(gamerStud1);
                    player.sendMessage(gamerStud1.getName());
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    player.sendMessage("BAD ARGUMENTS");
                }
            } else{player.sendMessage("Za dużo argumentów");}
        }
        else if(cmd.getName().equalsIgnoreCase("racing")) {
            String horseName = args[0];
            if(racingArena.getArenaStatus() == ArenaStatus.WAITING) {
                Gamer gamer = gamerService.getGamer(player.getName()).get();
                Optional<Horse> horseOptional = horseService.getHorseByName(gamer, horseName);
                if(horseOptional.isPresent()) {
                    Horse horse = horseOptional.get();
                    racingArena.addPlayer(player, (org.bukkit.entity.Horse) Bukkit.getEntity(UUID.fromString(horse.getBukkitHorseId())));
                    player.sendTitle("Dołączyłeś do wyścigów", "Poczekaj na innych graczy", 10,50,10);
                } else {
                    player.sendMessage("Nie posiadasz konia o tym imieniu");
                }
            } else {
                player.sendMessage("Obecnie trwa wyscig, sprobuj pozniej");
            }


        }

       /* else if(cmd.getName().equalsIgnoreCase("racing")){
          //  HorseRacingListener horseRacingListener = new HorseRacingListener();
            player.sendTitle("Dołączyłeś do wyścigów", "Poczekaj na innych graczy");

            Gamer[] playersRacing = new Gamer[3];
            com.company.model.Horse[] playersHorse = new com.company.model.Horse[3];

            if(args.length >= 1){

                try{
                  *//*  Optional<Gamer> gamer = gamerRepository.getGamerByNick(player.getName());
                    com.company.model.Horse horse = (com.company.model.Horse) horseService.getGamerHorses(gamer.get());*//*

                    if(playersRacing[0].equals(null)){
                        playersRacing[0] = gamerRepository.getGamerByNick(player.getName()).get();
                        com.company.model.Horse horse = horseService.getHorseByGamerHorseName(gamerRepository.getGamerByNick(player.getName()).get(),args[0]);
                        playersHorse[0] = horse;
                        playersHorse[0] = horse;
                        Bukkit.getEntity(UUID.fromString(horse.getBukkitHorseId())).teleport(twoStartBox);
                        player.teleport(oneStratBox);
                        if(player.getLocation().distance(oneStratBox) > 10){
                            playersRacing[0] = null;
                            playersHorse[0] = null;
                            player.sendTitle("Wyszedłeś ze strefy gry","SPRÓBUJ PÓŹNIEJ",10,50,10);
                        }

                    } else if (playersRacing[1].equals(null)){
                        playersRacing[1] = gamerRepository.getGamerByNick(player.getName()).get();
                        com.company.model.Horse horse = horseService.getHorseByGamerHorseName(gamerRepository.getGamerByNick(player.getName()).get(),args[0]);
                        playersHorse[1] = horse;
                        Bukkit.getEntity(UUID.fromString(horse.getBukkitHorseId())).teleport(twoStartBox);
                        player.teleport(twoStartBox);
                        if(player.getLocation().distance(twoStartBox) > 10){
                            playersRacing[1] = null;
                            player.sendTitle("Wyszedłeś ze strefy gry","SPRÓBUJ PÓŹNIEJ",10,50,10);
                        }
                    }else if(playersRacing[2].equals(null)){
                        playersRacing[2] = gamerRepository.getGamerByNick(player.getName()).get();
                        com.company.model.Horse horse = horseService.getHorseByGamerHorseName(gamerRepository.getGamerByNick(player.getName()).get(),args[0]);
                        playersHorse[2] = horse;
                        Bukkit.getEntity(UUID.fromString(horse.getBukkitHorseId())).teleport(threeStartBox);
                        player.teleport(threeStartBox);
                        if(player.getLocation().distance(threeStartBox) > 10){
                            playersRacing[2] = null;
                            player.sendTitle("Wyszedłeś ze strefy gry","SPRÓBUJ PÓŹNIEJ",10,50,10);
                        }
                    }else player.sendTitle("Brak miejsc","SPRÓBUJ PÓŹNIEJ",10,50,10);
                    int numberN = 0;
                    for(int i = 0; i <= 3 ; i++){

                        if(playersRacing[i].equals(null)){
                            i++;
                        }
                        numberN+=1;
                        i++;
                    }

                    if(numberN == 1){
                        player.sendTitle("Zaczekaj na reszte graczy", "Minimum 2 graczy", 10,50,10);

                    }else if(numberN == 2){
                        player.sendMessage("Gra rozpocznie się za 3s");

                    }else if(numberN == 3){
                        player.sendMessage("START");

                    }


                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                    player.sendMessage("BAD ARGUMENTS");
                }
            }
        }*/
        return true;
    }
}
