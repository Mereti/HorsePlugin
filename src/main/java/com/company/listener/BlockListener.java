package com.company.listener;

import com.company.Gamer;
import com.company.Plot;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.Material;
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
    private Plot plot;

    public BlockListener(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @EventHandler
    public void onBlockPlayer(BlockPlaceEvent event) {
        Optional<Gamer> gamerOptional = gamerService.getGamer(event.getPlayer().getName());
        gamerOptional.ifPresent(gamer -> gamerService.addPoints(gamer, 1));

        if(plotService.getPlotByLocation(event.getBlock().getLocation()) != null ){
            if (plotService.getPlotOwnedByGamer(gamerOptional.get()) != plotService.getPlotByLocation(event.getBlock().getLocation())
            ) {
                event.setCancelled(true);
            }
        } else throw new RuntimeException("Brak działki !!!");
        //TODO: sprawdzic czy gracz buduje na swojej dzialce, jesli nie
        // uzyc event.setCancelled(true);
        // event.getBlock().getLocation
        // plotService.getPlotByLocation
        // jesli optional pusty to nie ma dzialki
        // jesli isPresent == true to sprawdzic czy dzialka nalezy do gracz
    }


    //TODO: zrobic jak wyzej ale dla BlockBreakEvent
    @EventHandler
    public void onBlockBreakPlayer(BlockBreakEvent event){
        Optional<Gamer> gamerOptional = gamerService.getGamer(event.getPlayer().getName());
        gamerOptional.ifPresent(gamer -> gamerService.addPoints(gamer, 1));

        if(plotService.getPlotByLocation(event.getBlock().getLocation()) != null ){
            if (plotService.getPlotOwnedByGamer(gamerOptional.get()) != plotService.getPlotByLocation(event.getBlock().getLocation())
            ) {
                event.setDropItems(false);
                event.setCancelled(true);
            }
        } else throw new RuntimeException("Brak działki !!!");
    }

    //TODO: zrobic jak wyzej ale dla PlayerInteractEvent
    // zrobic ifa zeby nie blokowac na swiecie world
    // event.getPlayer().getLocation().getWorld().getName().equals("world")

    @EventHandler
    public void onInteractWorld(PlayerInteractEvent interactEvent){
        if(interactEvent.getPlayer().getLocation().getWorld().getName().equals("world")){
            if(interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK && interactEvent.getClickedBlock().getType() == Material.OAK_DOOR){
                interactEvent.setCancelled(false);
            } else interactEvent.setCancelled(true);
        }
    }
}
