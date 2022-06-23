package com.company.listener;


import com.company.RacingArena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HorseRacingListener implements Listener {

    private RacingArena racingArena;

    public HorseRacingListener(RacingArena arena) {
        this.racingArena = arena;
    }

    @EventHandler
    public void onHorseRacing(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(racingArena.isPlayerRacing(player)) {
            double x = player.getLocation().getX();
            double z = player.getLocation().getZ();
            if(x > 111 && x < 117 && z < -76 && z > -85) {
                racingArena.stopArena(player);
            }
        }
    }
}
