package com.company.commands;

import com.company.Gamer;
import com.company.listener.HorseRacingListener;
import com.company.repository.GamerRepository;
import com.company.service.HorseService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class GamerCommands implements CommandExecutor {


    private HorseService horseService;
    private GamerRepository gamerRepository;
    private HorseRacingListener horseRacingListener;
    private final Location oneStratBox = new Location(Bukkit.getWorld("world"),119.212,73.85,-83.918);
    private final Location twoStartBox = new Location(Bukkit.getWorld("world"), 119.375,73.85,-81.004);
    private final Location threeStartBox = new Location(Bukkit.getWorld("world"), 119.362,73.85,-77.824);

    public GamerCommands(HorseService horseService, GamerRepository gamerRepository) {
        this.horseService =  horseService;
        this.gamerRepository = gamerRepository;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){ return true; }
        Player player = (Player) sender;

       // /tplobby
        if(cmd.getName().equalsIgnoreCase("tplobby")){
            if (player.getWorld()== Bukkit.getWorld("world_flat")){
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }else{
                player.sendMessage("You are in lobby!");
            }
        }
        else if(cmd.getName().equalsIgnoreCase("horsename")){

            //player.sendMessage("OMG DZIALA TO?" + player.getName());
            if(player.getInventory().contains(Material.HORSE_SPAWN_EGG)){
                player.sendMessage("Posiadasz jajko konia!");
                if(args.length >= 1){
                    try{
                        EntityType horse = EntityType.HORSE;

                      //  player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE).setCustomName(String.valueOf(args[0]));

                        AnimalTamer tamer = player;
                        org.bukkit.entity.Horse horseBukkit = (org.bukkit.entity.Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                        horseBukkit.setCustomName(String.valueOf(args[0]));
                        String id = String.valueOf(horseBukkit.getUniqueId());
                        player.sendMessage("To jest unikalne ID twojego konia: " + id );

                        horseService.createHorse(gamerRepository.getGamerByNick(player.getName()).get(), player.getLocation(),id);
                       /* if(horseBukkit.getLocation() != Bukkit.getWorld("world_flat").getSpawnLocation()){
                            horseBukkit.teleport(Bukkit.getWorld("world_flat").getSpawnLocation());
                        }*/
                        player.getInventory().remove(Material.HORSE_SPAWN_EGG);

                    }catch(IllegalArgumentException e){
                        e.printStackTrace();
                        player.sendMessage("BAD ARGUMENTS");
                    }
                } else{player.sendMessage("To many args");}
             } else{ player.sendMessage("Nie posiadasz jajka konia ! :( ");}
        }

       /* else if(cmd.getName().equalsIgnoreCase("racing")){
          //  HorseRacingListener horseRacingListener = new HorseRacingListener();
            player.sendTitle("Dołączyłeś do wyścigów", "Poczekaj na innych graczy");

            Gamer[] playersRacing = new Gamer[3];
            com.company.Horse[] playersHorse = new com.company.Horse[3];

            if(args.length >= 1){

                try{
                  *//*  Optional<Gamer> gamer = gamerRepository.getGamerByNick(player.getName());
                    com.company.Horse horse = (com.company.Horse) horseService.getGamerHorses(gamer.get());*//*

                    if(playersRacing[0].equals(null)){
                        playersRacing[0] = gamerRepository.getGamerByNick(player.getName()).get();
                        com.company.Horse horse = horseService.getHorseByGamerHorseName(gamerRepository.getGamerByNick(player.getName()).get(),args[0]);
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
                        com.company.Horse horse = horseService.getHorseByGamerHorseName(gamerRepository.getGamerByNick(player.getName()).get(),args[0]);
                        playersHorse[1] = horse;
                        Bukkit.getEntity(UUID.fromString(horse.getBukkitHorseId())).teleport(twoStartBox);
                        player.teleport(twoStartBox);
                        if(player.getLocation().distance(twoStartBox) > 10){
                            playersRacing[1] = null;
                            player.sendTitle("Wyszedłeś ze strefy gry","SPRÓBUJ PÓŹNIEJ",10,50,10);
                        }
                    }else if(playersRacing[2].equals(null)){
                        playersRacing[2] = gamerRepository.getGamerByNick(player.getName()).get();
                        com.company.Horse horse = horseService.getHorseByGamerHorseName(gamerRepository.getGamerByNick(player.getName()).get(),args[0]);
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
