package com.company.listener;

import com.company.service.GamerService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private GamerService gamerService;

    public QuitListener(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        gamerService.removeGamer(event.getPlayer().getName());
    }

}
