package com.company.model;

public class Gamer {
    private int gamerId;
    private String nickname;
    private int authmeId;
    private Integer points;



    public Gamer(int id, String nickname, int authmeId, Integer points) {
        this.gamerId = id;
        this.nickname = nickname;
        this.authmeId = authmeId;
        this.points = points;
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

    public int getAuthmeId() {
        return authmeId;
    }

    public void setAuthmeId(int authmeId) {
        this.authmeId = authmeId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
