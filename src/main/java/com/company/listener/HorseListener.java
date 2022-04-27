package com.company.listener;

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

public class HorseListener implements Listener {

    private HorseService horseService;
    private GamerService gamerService;

    public HorseListener(HorseService horseService, GamerService gamerService) {
        this.horseService = horseService;
        this.gamerService = gamerService;
    }

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
        }
    }
}
