package com.company.listener;

import com.company.Gamer;
import com.company.service.GamerService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Optional;

public class BlockListener implements Listener {

    private GamerService gamerService;

    public BlockListener(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @EventHandler
    public void onBlockPlayer(BlockPlaceEvent event) {
        Optional<Gamer> gamerOptional = gamerService.getGamer(event.getPlayer().getName());
        gamerOptional.ifPresent(gamer -> {
            gamerService.addPoints(gamer, 1);
        });
    }

}
