package com.winter.model;

import java.util.List;

public class CatUser {
    private String openid;
    @Override
    public String toString() {
        return "CatUser{" + "openid='" + openid + '\'' + ", maxlevel=" + maxlevel + ", money=" + money + ", props1=" + props1 + ", props2=" + props2 + ", props3=" + props3 + ", totalstarts=" + totalstarts + ", LevelGameData=" + LevelGameData + ", sucess=" + sucess + '}';
    }

    private Integer maxlevel;

    private Integer money;

    private Integer props1;

    private Integer props2;

    private Integer props3;

    private Integer totalstarts;

    private List<CatPoint> LevelGameData;

    private int sucess;

    public int getSucess() {
        return sucess;
    }

    public void setSucess(int sucess) {
        this.sucess = sucess;
    }

    public List<CatPoint> LevelGameData() {
        return LevelGameData;
    }

    public void setPoints(List<CatPoint> points) {
        this.LevelGameData = points;
    }

    public List<CatPoint> getLevelGameData() {
        return LevelGameData;
    }

    public void setLevelGameData(List<CatPoint> levelGameData) {
        LevelGameData = levelGameData;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getMaxlevel() {
        return maxlevel;
    }

    public void setMaxlevel(Integer maxlevel) {
        this.maxlevel = maxlevel;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getProps1() {
        return props1;
    }

    public void setProps1(Integer props1) {
        this.props1 = props1;
    }

    public Integer getProps2() {
        return props2;
    }

    public void setProps2(Integer props2) {
        this.props2 = props2;
    }

    public Integer getProps3() {
        return props3;
    }

    public void setProps3(Integer props3) {
        this.props3 = props3;
    }

    public Integer getTotalstarts() {
        return totalstarts;
    }

    public void setTotalstarts(Integer totalstarts) {
        this.totalstarts = totalstarts;
    }

    public CatUser(String openid) {
        this.openid = openid;
        this.maxlevel = 1;
        this.money = 0;
        this.props1 = 0;
        this.props2 = 0;
        this.props3 = 0;
        this.totalstarts = 0;
    }
    public CatUser() {
    }
}