package com.github.drem2021.pokerjh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Table("room_game_log")
@Comment("游戏房间局次详细信息")
public class RoomGameLog implements Serializable {

    private static final long serialVersionUID = -8708574338986753655L;
    @Id
    @Column("id")
    @ColDefine(type= ColType.INT, width=20)
    @Comment(value="主键,小局ID")
    private Long id;

    @Column("room_id")
    @ColDefine(type= ColType.INT, width=20)
    @Comment(value="房间号")
    private Long roomId;

    @Column("game_qty")
    @ColDefine(type=ColType.INT, width=10)
    @Comment(value="局次,大局的次数")
    @Default("1")
    private int ofgameQty;

    @Column("begin_date_time")
    @ColDefine(notNull=true, type=ColType.DATETIME, width=25)
    @Comment(value="开局时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDateTime;

    @Column("begintime")
    @ColDefine(notNull=true, type=ColType.INT, width=20)
    @Comment(value="开局时间，时间戳")
    @Default("0")
    private Long begintime;

    @Column("endtime")
    @ColDefine(notNull=false, type=ColType.INT, width=20)
    @Comment(value="结束时间，时间戳")
    @Default("0")
    private Long endtime;

    @Column("createtime")
    @ColDefine(notNull=true, type=ColType.DATETIME, width=25)
    @Comment(value="创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public int getOfgameQty() {
        return ofgameQty;
    }

    public void setOfgameQty(int ofgameQty) {
        this.ofgameQty = ofgameQty;
    }

    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Long getBegintime() {
        return begintime;
    }

    public void setBegintime(Long begintime) {
        this.begintime = begintime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
