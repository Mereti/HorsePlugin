package com.company.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;

import java.util.ArrayList;
import java.util.List;


public class CreativeGetItemListener implements Listener {

    private List<Material> blockedMaterialTypes = new ArrayList<>();

    public CreativeGetItemListener() {
        blockedMaterialTypes.add(Material.HORSE_SPAWN_EGG);
        blockedMaterialTypes.add(Material.ZOMBIE_HORSE_SPAWN_EGG);
        blockedMaterialTypes.add(Material.SKELETON_HORSE_SPAWN_EGG);
        blockedMaterialTypes.add(Material.SADDLE);
        blockedMaterialTypes.add(Material.DIAMOND_HORSE_ARMOR);
        blockedMaterialTypes.add(Material.GOLDEN_HORSE_ARMOR);
        blockedMaterialTypes.add(Material.LEATHER_HORSE_ARMOR);
        blockedMaterialTypes.add(Material.IRON_HORSE_ARMOR);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCreativeInventory(InventoryCreativeEvent event) {
        if(event.getWhoClicked() instanceof Player) {
            if(event.getCurrentItem() != null && blockedMaterialTypes.contains(event.getCurrentItem().getType())) {
                event.setCancelled(true);
            }
            if(event.getCursor() != null && blockedMaterialTypes.contains(event.getCursor().getType())) {
                event.setCancelled(true);
            }
        }
    }
}
