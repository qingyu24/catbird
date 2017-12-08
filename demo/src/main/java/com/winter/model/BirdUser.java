package com.winter.model;

import javafx.scene.control.Skin;

import java.util.List;

public class BirdUser {
    private String userID;

    private String name;

    private Integer gold;

    private Integer score;

    private Integer userskin;

    private Integer usercolumnskin;

    private List<Integer> skins;

    public List<Integer> getSkins() {
        return skins;
    }

    public void setSkins(List<Integer> skins) {
        this.skins = skins;
    }

    public String getuserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
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