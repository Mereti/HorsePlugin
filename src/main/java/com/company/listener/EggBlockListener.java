package com.company.listener;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class EggBlockListener implements Listener {

/*    getClickedInventory().contains(Material.HORSE_SPAWN_EGG*/
    public void onInteractEggs(InventoryClickEvent event){

       if(event.getClick().isLeftClick()){
           if(event.getCurrentItem().getType() == Material.HORSE_SPAWN_EGG){
               event.setCancelled(true);
           }
           if(event.getCurrentItem().getType() == Material.ZOMBIE_HORSE_SPAWN_EGG){
               event.setCancelled(true);
           }
           if(event.getCurrentItem().getType() == Material.SKELETON_HORSE_SPAWN_EGG){
               event.setCancelled(true);
           }
       }

   }
}
