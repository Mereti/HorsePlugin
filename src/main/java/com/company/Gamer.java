package com.company;

public class Gamer {
    private int gamerId;
    private String nickname;

    public Gamer(int id, String nickname) {
        this.gamerId = id;
        this.nickname = nickname;
    }

    public int getGamerId() {
        return gamerId;
    }

    public void setGamerId(int gamerId) {
        this.gamerId = gamerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
