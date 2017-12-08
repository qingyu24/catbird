package com.winter.model;

public class BirdSkin {
    private Long id;

    private String userID;

    private Integer skinID;

    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getSkinID() {
        return skinID;
    }

    public void setSkinID(Integer skinID) {
        this.skinID = skinID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}