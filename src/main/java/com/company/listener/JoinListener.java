package com.company.listener;

import com.company.Gamer;
import com.company.Horse;
import com.company.StaticConfig;
import com.company.service.GamerService;
import com.company.service.HorseService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinListener implements Listener {

    private GamerService gamerService;
    private HorseService horseService;

    public JoinListener(GamerService gamerService, HorseService horseService) {
        this.gamerService = gamerService;
        this.horseService = horseService;
    }

    private static Location spawn = Bukkit.getWorld("world").getSpawnLocation();
    private ItemStack item = new ItemStack(Material.HORSE_SPAWN_EGG, 1);
    private ItemStack compass = createCompass();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.teleport(spawn);
        player.getInventory().clear();
        player.setHealthScale(20D);
        player.setInvulnerable(true);//damage no kill
        player.setCanPickupItems(false);
        player.setItemOnCursor(item);
        player.getInventory().setItem(0, compass);

        Gamer gamer = gamerService.loadGamer(player.getName());
        horseService.loadHorses(gamer);
    }

    private static ItemStack createCompass() {
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(StaticConfig.COMPASS_NAME);
        return compass;
    }

}
