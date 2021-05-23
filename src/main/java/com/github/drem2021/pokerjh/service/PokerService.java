package com.github.drem2021.pokerjh.service;

import com.github.drem2021.pokerjh.entity.Players;
import com.github.drem2021.pokerjh.entity.Poker;
import com.github.drem2021.pokerjh.opt.RedisKey;
import com.github.drem2021.pokerjh.opt.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PokerService {
    private final static String[] suit = {"W","X","Y","Z"};

    private RedisUtil redisUtil;
    /**
     * 创建一副牌
     * @param roomId 房间号
     * @param isCache 是否保存缓存
     * @return
     */
    public List<Poker> createPoker(long roomId,boolean isCache){
        List<Poker> poker = new ArrayList<Poker>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j <= 14; j++) {
                poker.add(new Poker(suit[i],j));
            }
        }
        // 洗牌：List随机排序
        Collections.shuffle(poker);
        if(isCache) {
            redisUtil.setList(RedisKey.pokerCard + roomId, poker);
        }
        return poker;
    }

    /**
     * 发一个人的牌
     * @param roomId
     * @return
     */
    public List<Poker> dealCards(long roomId){
        List<Poker> playPoker = new ArrayList<Poker>();
        List<Poker> pokerPool = redisUtil.getList(RedisKey.pokerCard+roomId);
        if(pokerPool==null || pokerPool.size()<3){
            pokerPool = this.createPoker(roomId,false);
        }
        for(int i=0;i<3;i++){
            playPoker.add(pokerPool.remove(0));
        }
        redisUtil.resetList(RedisKey.pokerCard+roomId,pokerPool);
        return  playPoker;
    }

    /**
     * 发牌:指定玩家
     * @param roomId
     * @param playersList
     * @return
     */
    public List<Players> dealCardsByPlayers(long roomId, List<Players> playersList){
        List<Poker> pokerPool = redisUtil.getList(RedisKey.pokerCard+roomId);
        if(pokerPool==null || pokerPool.size()<(playersList.size()*3)){
            pokerPool = this.createPoker(roomId,false);
        }
        for (Players P:playersList) {
            List<Poker> playPoker = new ArrayList<Poker>();
            for(int i=0;i<3;i++){
                playPoker.add(pokerPool.remove(0));
            }
            P.setPokers(playPoker);
        }
        redisUtil.resetList(RedisKey.pokerCard+roomId,pokerPool);
        return  playersList;
    }
}
