package com.yulong.manager.entity;

public class AlienUser {

    private String alienName;
    private String card_id;

    public String getAlienName() {
        return alienName;
    }

    public void setAlienName(String alienName) {
        this.alienName = alienName;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    @Override
    public String toString() {
        return "AlienUser{" +
                "alienName='" + alienName + '\'' +
                ", card_id='" + card_id + '\'' +
                '}';
    }
}
