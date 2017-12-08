package com.winter.model;

public class BirdUser {
    private String userid;

    private String name;

    private Integer gold;

    private Integer score;

    private Integer userskin;

    private Integer usercolumnskin;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUserskin() {
        return userskin;
    }

    public void setUserskin(Integer userskin) {
        this.userskin = userskin;
    }

    public Integer getUsercolumnskin() {
        return usercolumnskin;
    }

    public void setUsercolumnskin(Integer usercolumnskin) {
        this.usercolumnskin = usercolumnskin;
    }
}