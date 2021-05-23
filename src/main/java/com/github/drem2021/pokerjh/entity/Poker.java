package com.github.drem2021.pokerjh.entity;

import org.nutz.json.Json;

import java.io.Serializable;

public class Poker implements Serializable {

    private static final long serialVersionUID = 3102165559531964394L;
    private String suit; //花色,W:黑桃 X:红桃 Y:梅花 Z:方块
    private int point; //点数,2,3,4,5,6,7,8,9,10,11:J,12:Q,13:K,14:A

    public Poker(String suit, int point) {
        this.suit = suit;
        this.point = point;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
