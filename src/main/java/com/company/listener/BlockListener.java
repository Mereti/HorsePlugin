package com.company.listener;

import com.company.Gamer;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

public class BlockListener implements Listener {

    private GamerService gamerService;
    private PlotService plotService;

    public BlockListener(GamerService gamerService, PlotService plotService) {
        this.gamerService = gamerService;
        this.plotService = plotService;
    }

    @EventHandler
    public void onBlockPlayer(BlockPlaceEvent event) {
        Optional<Gamer> gamerOptional = gamerService.getGamer(event.getPlayer().getName());
        event.setCancelled(true);
        if(gamerOptional.isPresent()) {
            Gamer gamer = gamerOptional.get();
            gamerService.addPoints(gamer, 1);
            if(plotService.getPlotByLocation(event.getBlock().getLocation()).isPresent()){
                if (plotService.getPlotByLocation(event.getBlock().getLocation()).get().getGamerId() == gamer.getGamerId()) {
                    event.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreakPlayer(BlockBreakEvent event){
        Optional<Gamer> gamerOptional = gamerService.getGamer(event.getPlayer().getName());
        event.setCancelled(true);
        if(gamerOptional.isPresent()) {
            Gamer gamer = gamerOptional.get();
            if(plotService.getPlotByLocation(event.getBlock().getLocation()).isPresent()){
                if (plotService.getPlotByLocation(event.getBlock().getLocation()).get().getGamerId() == gamer.getGamerId()) {
                    event.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onInteractWorld(PlayerInteractEvent interactEvent){
        if(interactEvent.getPlayer().getLocation().getWorld().getName().equals("world")){
            if(interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK && interactEvent.getClickedBlock().getType().name().endsWith("_DOOR")){
                interactEvent.setCancelled(false);
            } else {
                interactEvent.setCancelled(true);
            }
        } else {
            Optional<Gamer> gamerOptional = gamerService.getGamer(interactEvent.getPlayer().getName());
            interactEvent.setCancelled(true);
            if(gamerOptional.isPresent()) {
                Gamer gamer = gamerOptional.get();
                if(plotService.getPlotByLocation(interactEvent.getClickedBlock().getLocation()).isPresent()){
                    if (plotService.getPlotByLocation(interactEvent.getClickedBlock().getLocation()).get().getGamerId() == gamer.getGamerId()) {
                        interactEvent.setCancelled(false);
                    }
                }
            }
        }
    }
}
