package com.company;

import com.company.enums.ArenaStatus;
import com.company.model.Gamer;
import com.company.model.Plot;
import com.company.service.GamerService;
import com.company.service.PlotService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RacingArena {

    private List<SpawnPoint> spawnPoints = new ArrayList<>();
    private List<Location> fences = new ArrayList<>();
    private ArenaStatus arenaStatus;
    private long time;

    private GamerService gamerService;
    private PlotService plotService;

    public RacingArena(GamerService gamerService, PlotService plotService) {
        arenaStatus = ArenaStatus.WAITING;
        time = -20;
        spawnPoints.add(new SpawnPoint(new Location(Bukkit.getWorld("world"),119.212,73.85,-83.918)));
        spawnPoints.add(new SpawnPoint(new Location(Bukkit.getWorld("world"), 119.375,73.85,-81.004)));
        spawnPoints.add(new SpawnPoint(new Location(Bukkit.getWorld("world"), 119.362,73.85,-77.824)));

        fences.add(new Location(Bukkit.getWorld("world"), 120,73,-78));
        fences.add(new Location(Bukkit.getWorld("world"), 120,73,-79));

        fences.add(new Location(Bukkit.getWorld("world"), 120,73,-81));
        fences.add(new Location(Bukkit.getWorld("world"), 120,73,-82));

        fences.add(new Location(Bukkit.getWorld("world"), 120,73,-84));
        fences.add(new Location(Bukkit.getWorld("world"), 120,73,-85));

        this.gamerService = gamerService;
        this.plotService = plotService;
    }

    public void addPlayer(Player player, Horse horse) {
        if(arenaStatus == ArenaStatus.WAITING) {
            Optional<SpawnPoint> freeSpawnPoint = spawnPoints.stream().filter(SpawnPoint::isEmpty).findAny();
            if(freeSpawnPoint.isPresent()) {
                SpawnPoint point = freeSpawnPoint.get();
                point.claim(player, horse);
                broadcastMessage(player.getName() + " dolaczyl do areny!");
            }
        }
    }

    public boolean isPlayerRacing(Player player) {
        return spawnPoints.stream().filter(SpawnPoint::isClaimed)
                .map(SpawnPoint::getPlayerOptional)
                .anyMatch(playerOptional -> playerOptional.get().getName().equals(player.getName()));
    }

    public long getTime() {
        return time;
    }

    public void increaseTime() {
        time += 1;
    }

    public boolean canStart() {
        return spawnPoints.stream().filter(SpawnPoint::isClaimed).count() >= 2 && arenaStatus == ArenaStatus.WAITING;
    }

    public boolean canJoin() {
        return spawnPoints.stream().anyMatch(SpawnPoint::isEmpty) && arenaStatus != ArenaStatus.RACING;
    }

    public void startCounting() {
        arenaStatus = ArenaStatus.COUNTING;
    }

    public void startArena() {
        arenaStatus = ArenaStatus.RACING;
        freePlayers();
    }

    public void stopArena(Player winner) {
        winner.getInventory().addItem(new ItemStack(Material.SADDLE));

        spawnPoints.stream().filter(SpawnPoint::isClaimed).forEach(spawnPoints -> {
            Player player = spawnPoints.getPlayerOptional().get();
            player.getInventory().addItem(new ItemStack(Material.HORSE_SPAWN_EGG));
            if(player.getName().equals(winner.getName())) {
                gamerService.addPoints(gamerService.getGamer(player.getName()).get(), 100);
                player.sendTitle(ChatColor.GREEN.toString() + "Wygrana!", "", 10, 50, 10);
            } else {
                gamerService.addPoints(gamerService.getGamer(player.getName()).get(), 50);
                player.sendTitle(ChatColor.YELLOW.toString() + "Zwyciestowo", ChatColor.YELLOW.toString() + winner.getName(), 10, 50, 10);
            }
        });

        arenaStatus = ArenaStatus.WAITING;
        time = -20;
        buildFences();
        spawnPoints.forEach(SpawnPoint::free);
    }
    public void outOfArena(Player player, Horse horse){

        arenaStatus = ArenaStatus.WAITING;
        time = -20;
        buildFences();
        spawnPoints.forEach(SpawnPoint::free);
    }

    public ArenaStatus getArenaStatus() {
        return arenaStatus;
    }

    private void broadcastMessage(String message) {
        spawnPoints.stream().filter(SpawnPoint::isClaimed).forEach(spawnPoint -> spawnPoint.getPlayerOptional().get().sendMessage(message));
    }

    public void broadcastTitle(String title, String subtitle, int fadeIn, int duration, int fadeOut) {
        spawnPoints.stream().filter(SpawnPoint::isClaimed).forEach(spawnPoint -> spawnPoint.getPlayerOptional().get().sendTitle(title, subtitle, fadeIn, duration, fadeOut));
    }

    private class SpawnPoint {
        private Location spawn;
        private Optional<Player> playerOptional;
        private Optional<Horse> horseOptional;

        public SpawnPoint(Location location) {
            this.spawn = location;
            this.playerOptional = Optional.empty();
            this.horseOptional = Optional.empty();
            free();
        }

        public boolean isEmpty() {
            return playerOptional.isEmpty();
        }

        public boolean isClaimed() {
            return playerOptional.isPresent();
        }

        public void claim(Player player, Horse horse) {
            playerOptional = Optional.of(player);
            horseOptional = Optional.of(horse);

            player.teleport(spawn);
            horse.teleport(spawn);
            horse.addPassenger(player);
        }

        public void free() {
            if(playerOptional.isPresent()) {
                Gamer gamer = gamerService.getGamer(playerOptional.get().getName()).get();
                Plot plot = plotService.getPlotOwnedByGamer(gamer).get();

                playerOptional.get().teleport(plot.getMainPoint());
                horseOptional.get().teleport(plot.getMainPoint());
            }

            playerOptional = Optional.empty();
            horseOptional = Optional.empty();
        }

        public Optional<Player> getPlayerOptional() {
            return playerOptional;
        }
    }

    private void freePlayers() {
        for(Location location : fences) {
            location.getBlock().setType(Material.AIR);
        }
    }

    private void buildFences() {
        for(Location location : fences) {
            Block block = location.getBlock();
            block.setType(Material.OAK_FENCE_GATE);
            if(block.getBlockData() instanceof Directional) {
                Directional data = (Directional) block.getBlockData();
                data.setFacing(BlockFace.WEST);
                block.setBlockData(data);
            }
        }
    }

}
