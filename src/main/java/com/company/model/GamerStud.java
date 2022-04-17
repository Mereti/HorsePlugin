package com.company.model;

import com.company.Gamer;

public class GamerStud {
    private  Integer gamerStudId;
    private Integer gamerId;
    private String name;

    public GamerStud(Integer gamerStudId, Integer gamerId, String name) {
        this.gamerStudId = gamerStudId;
        this.gamerId = gamerId;
        this.name = name;
    }

    public Integer getGamerStudId() {
        return gamerStudId;
    }

    public void setGamerStudId(Integer gamerStudId) {
        this.gamerStudId = gamerStudId;
    }

    public Integer getGamerId() {
        return gamerId;
    }

    public void setGamerId(Integer gamerId) {
        this.gamerId = gamerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
