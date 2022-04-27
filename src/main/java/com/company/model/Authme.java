package com.company.model;

public class Authme {

    private Integer id;
    private String username;
    private Long lastLogin;
    private Long regdate;

    public Authme(Integer id, String username, Long lastLogin, Long regdate) {
        this.id = id;
        this.username = username;
        this.lastLogin = lastLogin;
        this.regdate = regdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getRegdate() {
        return regdate;
    }

    public void setRegdate(Long regdate) {
        this.regdate = regdate;
    }
}
