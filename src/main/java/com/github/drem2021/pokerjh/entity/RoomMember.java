package com.github.drem2021.pokerjh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.drem2021.pokerjh.opt.Utils;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Table("room_member")
@Comment("游戏房间参与人信息")
public class RoomMember implements Serializable {

    private static final long serialVersionUID = 1710094258386992187L;

    @Id(auto = false)
    @Column("id")
    @ColDefine(type= ColType.INT, width=20)
    @Comment(value="主键")
    private Long id;

    @Column("roomid")
    @ColDefine(type= ColType.INT, width=20)
    @Comment(value="房间号")
    private Long roomid;

    @Column("memid")
    @ColDefine(type=ColType.TEXT, notNull = false)
    @Comment(value="参与人ID")
    private String memid;

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

    public void insert(){
        this.setId(Utils.UUID2Long());
        this.setCreatetime(new Date());
        this.setUpdatetime(new Date());
        this.setIsdelete(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
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
