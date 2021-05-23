package com.github.drem2021.pokerjh.controller;

import com.github.drem2021.pokerjh.usr.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api/ws")
@RestController
public class WebSocketController extends BaseController  {

    @Autowired
    private WebSocketServer wss;
    /**
     * 群发消息内容
     * @param message
     * @return
     */
    @GetMapping(value="/sendAll")
    public String sendAllMessage(@RequestParam(required=true) String message){
        try {
            wss.BroadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 指定会话ID发消息
     * @param message 消息内容
     * @param id 连接会话ID
     * @return
     */
    @GetMapping(value="/sendOne")
    public String sendOneMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String id){
        wss.SendMessage(message,id);
        return "ok";
    }
}
