package com.github.drem2021.pokerjh.opt;

public interface RedisKey {
    /**
     * 房间牌的缓存，后接房间号
     */
    String pokerCard = "poker_card_";
    /**
     * 房间状态，是否开始游戏
     */
    String roomStatus = "ROOM_PLAY_STATUS_";
}
