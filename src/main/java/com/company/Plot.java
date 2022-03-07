package com.company;

import org.bukkit.Location;

public class Plot {

    private int id;
    private int gamerId;
    private Location mainPoint;

    public Plot(int id, int gamerId, Location mainPoint) {
        this.id = id;
        this.gamerId = gamerId;
        this.mainPoint = mainPoint;
    }

    public boolean hasOwner() {
        return gamerId != 0;
    }

    public int getId() {
        return id;
    }

    public int getGamerId() {
        return gamerId;
    }

    public void setGamerId(int gamerId) {
        this.gamerId = gamerId;
    }

    public Location getMainPoint() {
        return mainPoint;
    }

    public void setMainPoint(Location mainPoint) {
        this.mainPoint = mainPoint;
    }

}
