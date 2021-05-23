package com.github.drem2021.pokerjh.entity;

import java.util.List;

/**
 * 玩家类
 */
public class Players {
    private Member member;
    private List<Poker> pokers;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Poker> getPokers() {
        return pokers;
    }

    public void setPokers(List<Poker> pokers) {
        this.pokers = pokers;
    }
}
