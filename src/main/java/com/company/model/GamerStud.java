package com.company.model;

import com.company.Gamer;

public class GamerStud {
    private  Integer gamerStudId;
    private Gamer gamerId;
    private String gamerStudName;

    public GamerStud(Integer gamerStudId, Gamer gamerId, String gamerStudName) {
        this.gamerStudId = gamerStudId;
        this.gamerId = gamerId;
        this.gamerStudName = gamerStudName;
    }

    public Integer getGamerStudId() {
        return gamerStudId;
    }

    public void setGamerStudId(Integer gamerStudId) {
        this.gamerStudId = gamerStudId;
    }

    public Gamer getGamerId() {
        return gamerId;
    }

    public void setGamerId(Gamer gamerId) {
        this.gamerId = gamerId;
    }

    public String getGamerStudName() {
        return gamerStudName;
    }

    public void setGamerStudName(String gamerStudName) {
        this.gamerStudName = gamerStudName;
    }
}
