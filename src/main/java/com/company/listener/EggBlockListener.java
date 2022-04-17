package com.company.listener;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class EggBlockListener implements Listener {

   /* private GamerService gamerService;

    public EggBlockListener(GamerService gamerService) {
        this.gamerService = gamerService;
    }*/

    public void onInteractEggs(InventoryClickEvent event){
       if(event.getClickedInventory().contains(Material.HORSE_SPAWN_EGG)){
           event.setCancelled(true);
       }

   }
}
