package com.company.listener;

import com.company.Gamer;
import com.company.Horse;
import com.company.StaticConfig;
import com.company.model.Breed;
import com.company.service.GamerService;
import com.company.service.HorseService;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class HorseListener implements Listener {

    private HorseService horseService;
    private GamerService gamerService;

    public HorseListener(HorseService horseService, GamerService gamerService) {
        this.horseService = horseService;
        this.gamerService = gamerService;
    }

    //TODO: stworzyc EventHandler dla PlayerInteractEvent
    // zlapac event gdzie event.getItem() to jajko konia
    // anulowac event
    // stworzyc konia samodzielnie wedlug pomyslu
    // horseService.createHorse();
    // zapisac go do bazy danych

    @EventHandler
    private void onPlayerInteractEvent(PlayerInteractEvent interactEvent){
        if(interactEvent.getItem() == null)
            return;
        if(interactEvent.getItem().getItemMeta() == null)
            return;
        ItemMeta itemMeta = interactEvent.getItem().getItemMeta();
        ItemStack itemStack = new ItemStack(Material.HORSE_SPAWN_EGG);
        ItemMeta meta = itemStack.getItemMeta();
        Breed breed;
        Integer number = null;

        if(itemMeta != null && itemMeta.getDisplayName().equals(StaticConfig.HORSE_EGG)){

            interactEvent.setCancelled(true);
            Optional<Gamer> gamerOptional = gamerService.getGamer(interactEvent.getPlayer().getName());
            if(gamerOptional.isPresent()) {
                Gamer gamer = gamerOptional.get();
                Horse horse = horseService.createHorse(gamer, interactEvent.getClickedBlock().getLocation());
            }


        }

    }

}
