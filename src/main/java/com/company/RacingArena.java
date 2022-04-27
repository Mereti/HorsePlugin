package com.company;

import com.company.enums.ArenaStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RacingArena {

    private List<SpawnPoint> spawnPoints = new ArrayList<>();
    private List<Location> fences = new ArrayList<>();
    private ArenaStatus arenaStatus;

    public RacingArena() {
        arenaStatus = ArenaStatus.WAITING;
        spawnPoints.add(new SpawnPoint(new Location(Bukkit.getWorld("world"),119.212,73.85,-83.918)));
        spawnPoints.add(new SpawnPoint(new Location(Bukkit.getWorld("world"), 119.375,73.85,-81.004)));
        spawnPoints.add(new SpawnPoint(new Location(Bukkit.getWorld("world"), 119.362,73.85,-77.824)));

        fences.add(new Location(Bukkit.getWorld("world"), 120,74,-77));
        fences.add(new Location(Bukkit.getWorld("world"), 120,74,-78));

        fences.add(new Location(Bukkit.getWorld("world"), 120,74,-80));
        fences.add(new Location(Bukkit.getWorld("world"), 120,74,-81));

        fences.add(new Location(Bukkit.getWorld("world"), 120,74,-83));
        fences.add(new Location(Bukkit.getWorld("world"), 120,74,-84));
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

    public boolean canStart() {
        return spawnPoints.stream().filter(SpawnPoint::isClaimed).count() >= 2;
    }

    public void startArena() {
        arenaStatus = ArenaStatus.RACING;
        freePlayers();
    }

    public void stopArena() {
        //TODO: znajdz kolejnosc zwyciezcow i daj nagrody

        arenaStatus = ArenaStatus.WAITING;
        buildFences();
        spawnPoints.forEach(SpawnPoint::free);
    }

    public ArenaStatus getArenaStatus() {
        return arenaStatus;
    }

    private void broadcastMessage(String message) {
        spawnPoints.stream().filter(SpawnPoint::isClaimed).forEach(spawnPoint -> spawnPoint.getPlayerOptional().get().sendMessage(message));
    }

    private class SpawnPoint {
        private Location spawn;
        private Optional<Player> playerOptional;
        private Optional<Horse> horseOptional;

        public SpawnPoint(Location location) {
            this.spawn = location;
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
        }

        public void free() {
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
            block.setType(Material.OAK_FENCE);
            if(block.getBlockData() instanceof Directional) {
                Directional data = (Directional) block.getBlockData();
                data.setFacing(BlockFace.WEST);
                block.setBlockData(data);
            }
        }
    }



}
