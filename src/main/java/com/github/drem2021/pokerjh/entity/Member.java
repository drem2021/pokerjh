package com.github.drem2021.pokerjh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Table("member")
@Comment("会员信息表")
public class Member implements Serializable {
    private static final long serialVersionUID = 9182476449242451169L;

    @Name
    @Column("id")
    @ColDefine(type= ColType.VARCHAR, width=32)
    @Comment(value="主键")
    private String id;

    @Column("edit_qty")
    @ColDefine(type= ColType.INT, width=11)
    @Comment(value="账号修改次数")
    @Default("0")
    private int editQty;

    @Column("account")
    @ColDefine(type=ColType.TEXT)
    @Comment(value="帐号")
    private String account;

    @Column("password")
    @ColDefine(type=ColType.TEXT)
    @Comment(value="密码")
    //@JsonIgnore //该注解移动到getter和setter方法上
    private String password;

    @Column("nickname")
    @ColDefine(type=ColType.VARCHAR, width=50, notNull = false)
    @Comment(value="昵称")
    private String nickname;

    @Column("real_ident")
    @ColDefine(type=ColType.INT, width=5, notNull = false)
    @Comment(value="是否实名认证,0未认证，1认证")
    private int realIdent;

    /**
     * 头像地址
     */
    @Column("portrait_url")
    @ColDefine(type=ColType.TEXT, notNull = false)
    @Comment(value="头像地址")
    private String portraitUrl;

    /**
     * 手机号码
     */
    @Column("phone")
    @ColDefine(type=ColType.VARCHAR, width=15, notNull = false)
    @Comment(value="手机号码")
    private String phone;

    @Column("memlevel")
    @ColDefine(type=ColType.INT, width=10 , notNull = false)
    @Comment(value="会员级别")
    @Default("0")
    private int memlevel;

    @Column("wx_openid")
    @ColDefine(type=ColType.VARCHAR, width=255, notNull = false)
    @Comment(value="微信登录openid")
    private String wxOpenid;

    @Column("qq_openid")
    @ColDefine(type=ColType.VARCHAR, width=255, notNull = false)
    @Comment(value="QQ登录openid")
    private String qqOpenid;

    @Column("apple_openid")
    @ColDefine(type=ColType.VARCHAR, width=255, notNull = false)
    @Comment(value="苹果登录openid")
    private String appleOpenId;

    @Column("alipay_openid")
    @ColDefine(type=ColType.VARCHAR, width=255, notNull = false)
    @Comment(value="支付宝登录openid")
    private String alipayOpenId;

    @Column("last_time")
    @ColDefine(type=ColType.VARCHAR, width=25, notNull = false)
    @Comment(value="上一次登陆时间")
    private String lastTime;

    @Column("last_ip")
    @ColDefine(type=ColType.VARCHAR, width=64, notNull = false)
    @Comment(value="上一次登陆ip")
    private String lastIp;

    @Column("this_time")
    @ColDefine(type=ColType.VARCHAR, width=25, notNull = false)
    @Comment(value="这一次登陆时间")
    private String thisTime;

    @Column("this_ip")
    @ColDefine(type=ColType.VARCHAR, width=64, notNull = false)
    @Comment(value="这一次登陆ip")
    private String thisIP;

    @Column("remarks")
    @ColDefine(type=ColType.TEXT, notNull = false)
    @Comment(value="备注")
    private String remarks;

    @Column("origin")
    @ColDefine(type=ColType.INT, width=3,notNull = false)
    @Comment(value="来源: 0 用户注册 1 后台添加")
    @Default("0")
    private int origin;

    @Column("expiretime")
    @ColDefine(type=ColType.INT, width=32)
    @Comment(value="会员到期时间")
    private String expiretime;

    @Column("status")
    @ColDefine(type=ColType.INT, width=3)
    @Comment(value="状态 1 禁用 0 启用")
    @Default("0")
    private int status;

    @Column("createtime")
    @ColDefine(notNull=true, type=ColType.DATETIME, width=25)
    @Comment(value="记录创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @Column("updatetime")
    @ColDefine(notNull=true, type=ColType.DATETIME, width=25)
    @Comment(value="记录上一次更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    @Column("isdelete")
    @ColDefine(notNull=true, type=ColType.INT, width=2)
    @Comment(value="是否删除： 0没有删除 1 删除")
    @Default("0")
    private int isdelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEditQty() {
        return editQty;
    }

    public void setEditQty(int editQty) {
        this.editQty = editQty;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRealIdent() {
        return realIdent;
    }

    public void setRealIdent(int realIdent) {
        this.realIdent = realIdent;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMemlevel() {
        return memlevel;
    }

    public void setMemlevel(int memlevel) {
        this.memlevel = memlevel;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getAppleOpenId() {
        return appleOpenId;
    }

    public void setAppleOpenId(String appleOpenId) {
        this.appleOpenId = appleOpenId;
    }

    public String getAlipayOpenId() {
        return alipayOpenId;
    }

    public void setAlipayOpenId(String alipayOpenId) {
        this.alipayOpenId = alipayOpenId;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getThisTime() {
        return thisTime;
    }

    public void setThisTime(String thisTime) {
        this.thisTime = thisTime;
    }

    public String getThisIP() {
        return thisIP;
    }

    public void setThisIP(String thisIP) {
        this.thisIP = thisIP;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public String getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(String expiretime) {
        this.expiretime = expiretime;
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
