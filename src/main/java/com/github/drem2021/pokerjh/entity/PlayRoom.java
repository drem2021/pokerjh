package com.github.drem2021.pokerjh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Table("play_room")
@Comment("游戏房间信息表")
public class PlayRoom implements Serializable {

    private static final long serialVersionUID = 1710094258386992187L;

    @Id(auto = false)
    @Column("id")
    @ColDefine(type= ColType.INT, width=20)
    @Comment(value="主键,房间号")
    private Long id;

    @Column("memid")
    @ColDefine(type=ColType.TEXT, notNull = false)
    @Comment(value="房间创建者ID")
    private String memid;

    @Column("room_name")
    @ColDefine(type=ColType.TEXT, notNull = false)
    @Comment(value="房间名称")
    private String roomName;

    @Column("room_level")
    @ColDefine(type=ColType.INT,width = 5, notNull = false)
    @Comment(value="房间级别,0:一局,1:一天,2:一个月")
    @Default("0")
    private int roomLevel;

    @Column("game_qty")
    @ColDefine(type=ColType.INT, width=10)
    @Comment(value="局次,一局有20局的小局")
    @Default("1")
    private int gameQty;

    @Column("status")
    @ColDefine(type=ColType.INT, width=3)
    @Comment(value="状态 1 失效 0 正常")
    @Default("0")
    private int status;

    @Column("createtime")
    @ColDefine(notNull=true, type=ColType.DATETIME, width=25)
    @Comment(value="创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @Column("updatetime")
    @ColDefine(notNull=true, type=ColType.DATETIME, width=25)
    @Comment(value="上一次更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    @Column("isdelete")
    @ColDefine(notNull=true, type=ColType.INT, width=2)
    @Comment(value="是否删除： 0没有删除 1 删除")
    @Default("0")
    private int isdelete;

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomLevel() {
        return roomLevel;
    }

    public void setRoomLevel(int roomLevel) {
        this.roomLevel = roomLevel;
    }

    public int getGameQty() {
        return gameQty;
    }

    public void setGameQty(int gameQty) {
        this.gameQty = gameQty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
