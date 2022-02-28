package com.company.listener;

import com.company.StaticConfig;
import com.company.repository.HorseRepository;
import com.company.service.HorseService;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HorseListener implements Listener {

    private HorseService horseService;

    //TODO: strowrzyc konstruktor

    public HorseListener(HorseService horseService) {
        this.horseService = horseService;
    }

    //TODO: stworzyc EventHandler dla PlayerInteractEvent
    // zlapac event gdzie event.getItem() to jajko konia
    // anulowac event
    // stworzyc konia samodzielnie wedlug pomyslu
    // horseService.createHorse();
    // zapisac go do bazy danych

    @EventHandler
    private void onPlayerInteractEvent(PlayerInteractEvent interactEvent){
        ItemMeta itemMeta = interactEvent.getItem().getItemMeta();
        ItemStack itemStack = new ItemStack(Material.HORSE_SPAWN_EGG);
        ItemMeta meta = itemStack.getItemMeta();

        if(itemMeta != null && itemMeta.getDisplayName().equals(StaticConfig.HORSE_EGG)){

            interactEvent.setCancelled(true);
            meta.addItemFlags(ItemFlag);

        }

    }

}