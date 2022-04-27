package com.company;

import com.company.enums.ArenaStatus;
import org.bukkit.ChatColor;

public class RacingArenaRunnable implements Runnable {

    private RacingArena arena;

    public RacingArenaRunnable(RacingArena racingArena) {
        this.arena = racingArena;
    }

    @Override
    public void run() {
        if(arena.getArenaStatus() != ArenaStatus.WAITING) {
         arena.increaseTime();
            if(arena.getTime() < 0) {
                arena.broadcastTitle(ChatColor.YELLOW.toString() + Math.abs(arena.getTime()), "", 4, 12, 4);
            }

            if(arena.getTime() == 0) {
                arena.startArena();
            }
        }
    }
}
