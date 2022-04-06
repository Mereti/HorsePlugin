package com.company.commands;

import com.company.repository.GamerRepository;
import com.company.service.GamerService;
import com.company.service.HorseService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;


public class GamerCommands implements CommandExecutor {


    private HorseService horseService;
    private GamerRepository gamerRepository;

    public GamerCommands(HorseService horseService, GamerRepository gamerRepository) {
        this.horseService = horseService;
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

            player.sendMessage("OMG DZIALA TO?" + player.getName());
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
        return true;
    }
}
