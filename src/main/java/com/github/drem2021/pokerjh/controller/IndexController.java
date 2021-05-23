package com.github.drem2021.pokerjh.controller;

import com.github.drem2021.pokerjh.annotation.AuthToken;
import com.github.drem2021.pokerjh.annotation.PassToken;
import com.github.drem2021.pokerjh.entity.Member;
import com.github.drem2021.pokerjh.entity.PlayRoom;
import com.github.drem2021.pokerjh.entity.Players;
import com.github.drem2021.pokerjh.entity.RoomMember;
import com.github.drem2021.pokerjh.opt.RedisKey;
import com.github.drem2021.pokerjh.opt.RedisUtil;
import com.github.drem2021.pokerjh.opt.Utils;
import com.github.drem2021.pokerjh.opt.R;
import com.github.drem2021.pokerjh.service.PokerService;
import com.github.drem2021.pokerjh.vo.AccountVo;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/")
@RestController
public class IndexController extends BaseController {
    @Autowired
    private Dao dao;
    @Autowired
    private PokerService pokerService;
    private RedisUtil redisUtil;

    @GetMapping("/")
    public String index(){
        String result = "Hello Word";
        return result;
    }

    /**
     * 登录
     * @return
     * @param account
     */
    @PassToken
    @PostMapping("/login")
    public R login(@RequestBody AccountVo account, HttpServletRequest request){
        if(Utils.isBlank(account.getAccount()) || Utils.isBlank(account.getPassword())){
            return new R<String>().error("用户名或密码不能为空");
        }
        String pwd = Utils.sha3(account.getPassword());
        Member mem = dao.fetch(Member.class, Cnd.where("account","=",account.getAccount()).and("password","=",pwd));
        if(mem==null){
            return new R<String>().error("用户名或密码错误");
        }
        if(mem.getStatus()==1){
            return new R<String>().error("帐号已经被禁用");
        }
        mem.setLastIp(mem.getThisIP());
        mem.setLastTime(mem.getThisTime());
        mem.setThisIP(Lang.getIP(request));
        mem.setThisTime(Utils.getNowFullTime());
        dao.updateIgnoreNull(mem);
        String token = account.getToken(mem);
        return new R<String>().ok("登录成功",token);
    }


    /**
     * reg
     * @return
     * @param account
     */
    @PassToken
    @PostMapping("/reg")
    public R reg(@RequestBody AccountVo account, HttpServletRequest request){
        if(Utils.isBlank(account.getAccount()) || Utils.isBlank(account.getPassword())){
            return new R<String>().error("用户名或密码不能为空");
        }
        String pwd = Utils.sha3(account.getPassword());
        int memQty = dao.count(Member.class, Cnd.where("account","=",account.getAccount()));
        if(memQty>0){
            return new R<String>().error("帐号已经存在");
        }
        Member mem = new Member();
        mem.setAccount(account.getAccount());
        mem.setPassword(Utils.sha3(account.getPassword()));
        mem.setNickname(account.getNickname());
        mem.setCreatetime(new Date());
        mem.setUpdatetime(new Date());
        mem.setIsdelete(0);
        mem.setRealIdent(0);
        mem.setEditQty(0);
        mem.setOrigin(0);
        mem.setStatus(0);
        mem.setThisIP(Lang.getIP(request));
        mem.setThisTime(Utils.getNowFullTime());
        mem = dao.insert(mem);
        String token = account.getToken(mem);
        return new R<String>().ok("注册成功",token);
    }

    /**
     * 创建房间
     * @return
     * @param room
     */
    @AuthToken
    @PostMapping("/regPlayRoom")
    public R regPlayRoom(@RequestBody PlayRoom room){
        if(Utils.isBlank(room.getRoomName())){
            return new R<String>().error("房间名称能为空");
        }
        room.setId(Utils.UUID2Long());
        room.setCreatetime(new Date());
        room.setUpdatetime(new Date());
        room.setIsdelete(0);
        room.setStatus(0);
        room = dao.insert(room);

        RoomMember rm = new RoomMember();
        rm.insert();
        rm.setRoomid(room.getId());
        rm.setMemid(room.getMemid());
        dao.insert(rm);
        return new R<PlayRoom>().ok("创建成功",room);
    }

    /**
     * 加入房间
     * @return
     * @param
     */
    @AuthToken
    @GetMapping("/addRoomMember")
    public R addRoomMember(@RequestParam long roomid,@RequestParam String memid){
        if(roomid==0 || Utils.isBlank(memid)){
            return new R<String>().error("参数不能为空");
        }
        //判断是否开始游戏，开始游戏了禁止加入
        if(redisUtil.hasKey(RedisKey.roomStatus+roomid)){
            return new R<String>().error("游戏已经开始禁止加入");
        }
        int rmcount = dao.count(RoomMember.class,Cnd.where("isdelete","=",0).and("roomid","=",roomid));
        if(rmcount>=10){
            return new R<String>().error("房间已满");
        }
        RoomMember rm = new RoomMember();
        rm.insert();
        rm.setRoomid(roomid);
        rm.setMemid(memid);
        dao.insert(rm);
        return new R<PlayRoom>().ok("加入成功");
    }

    @AuthToken
    @GetMapping("/delRoomMember")
    public R delRoomMember(@RequestParam long roomid,@RequestParam String memid){
        if(roomid==0 || Utils.isBlank(memid)){
            return new R<String>().error("参数不能为空");
        }
        RoomMember rm = dao.fetch(RoomMember.class,Cnd.where("isdelete","=",0).and("roomid","=",roomid).and("memid","=",memid));
        if(rm==null){
            return new R<String>().error("没有加入该房间");
        }
        rm.setIsdelete(1);
        rm.setUpdatetime(new Date());
        dao.updateIgnoreNull(rm);
        return new R<PlayRoom>().ok("操作成功");
    }

    /**
     * 发牌
     * @param roomid
     * @return
     */
    @AuthToken
    @GetMapping("/dealCards")
    public R dealCards(@RequestParam long roomid){
        if(roomid==0){
            return new R<String>().error("参数不能为空");
        }
        List<RoomMember> rmlist = dao.query(RoomMember.class,Cnd.where("isdelete","=",0).and("roomid","=",roomid));
        if(rmlist==null){
            return new R<String>().error("没有人加入该房间");
        }
        List<Players> playersList = new ArrayList<>();
        pokerService.createPoker(roomid,true);
        for(RoomMember rm:rmlist){
            Players players = new Players();
            Member member = dao.fetch(Member.class,Cnd.where("isdelete","=",0).and("id","=",rm.getMemid()));
            players.setMember(member);
        }
        List<Players> rslist = pokerService.dealCardsByPlayers(roomid,playersList);
        redisUtil.setObject(RedisKey.roomStatus+roomid,rslist.hashCode());
        return new R<>().ok("操作成功",rslist);
    }




}