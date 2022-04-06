package com.company.listener;

import com.company.Plot;
import com.company.StaticConfig;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class InteractListener implements Listener {

    private PlotService plotService;
    private GamerService gamerService;

    public InteractListener(PlotService plotService, GamerService gamerService) {
        this.plotService = plotService;
        this.gamerService = gamerService;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getItem() == null)
            return;
        ItemMeta meta = event.getItem().getItemMeta();
        if(meta != null && meta.getDisplayName().equals(StaticConfig.COMPASS_NAME)) {
            gamerService.getGamer(player.getName()).ifPresent(gamer -> {
                Optional<Plot> plotOptional = plotService.getPlotOwnedByGamer(gamer);
                if (plotOptional.isPresent()) {
                    player.teleport(plotOptional.get().getMainPoint());
                } else {
                    Optional<Plot> givenPlot = plotService.givePlot(gamer);
                    if (givenPlot.isPresent()) {
                        player.teleport(givenPlot.get().getMainPoint());
                        player.sendMessage("Otrzymales dzialke");
                    } else {
                        player.sendMessage("Nie ma wolnych dzialek skontaktuj sie z administratorem");
                    }
                }
            });
        }
    }
}
