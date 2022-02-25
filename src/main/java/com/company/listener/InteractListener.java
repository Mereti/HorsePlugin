package com.company.listener;

import com.company.Plot;
import com.company.StaticConfig;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class InteractListener implements Listener {

    private PlotService plotService;
    private GamerService gamerService;

    //TODO: zrob konstruktor ktory ustawi plotService i gamerService;
    public InteractListener(PlotService plotService, GamerService gamerService) {
        this.plotService = plotService;
        this.gamerService = gamerService;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        //TODO: utworz zmienienna Player player i zastap nie wszystkie wywoalania
        // event.getPlayer() - ale ze w onInteract ({tu}) czy juz w fubkcji?

        if(event.getItem() == null)
            return;
        ItemMeta meta = event.getItem().getItemMeta();
        if(meta != null && meta.getDisplayName().equals(StaticConfig.COMPASS_NAME)) {
            gamerService.getGamer(event.getPlayer().getName()).ifPresent(gamer -> {
                Optional<Plot> plotOptional = plotService.getPlotOwnedByGamer(gamer);
                if(plotOptional.isPresent()) {
                    event.getPlayer().teleport(plotOptional.get().getMainPoint());
                } else {
                    Optional<Plot> givenPlot = plotService.givePlot(gamer);
                    if(givenPlot.isPresent()) {
                        event.getPlayer().teleport(givenPlot.get().getMainPoint());
                        event.getPlayer().sendMessage("Otrzymales dzialke");
                    } else {
                        event.getPlayer().sendMessage("Nie ma wolnych dzialek skontaktuj sie z administratorem");
                    }
                }
            });

        }
    }

}
