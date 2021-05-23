package com.github.drem2021.pokerjh.usr;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/{rid}/{uid}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    private static CopyOnWriteArraySet<Session> sessionPool = new CopyOnWriteArraySet<Session>();
    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
//                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }
    //给指定用户发送信息
    public void sendInfo(String userName, String message){
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "rid") String roomId,@PathParam(value = "rid") String memid){
        sessionPools.put(roomId, session);
        addOnlineCount();
        System.out.println(memid + "加入webSocket！当前人数为" + onlineNum);
        try {
            sendMessage(session, "欢迎" + roomId + "加入连接！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "rid") String roomId){
        sessionPools.remove(roomId);
        subOnlineCount();
        System.out.println(roomId + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException{
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public void BroadCastInfo(String message) throws IOException {
        try {
            for (Session session : sessionPool) {
                if (session.isOpen()) {
                    sendMessage(session, message);
                }
            }
        }catch (IOException e){

        }
    }

    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public void SendMessage(String message, String sessionId) {
        try {
            Session session = null;
            for (Session s : sessionPool) {
                if (s.getId().equals(sessionId)) {
                    session = s;
                    break;
                }
            }
            if (session != null) {
                sendMessage(session, message);
            } else {
                //log.warn("没有找到你指定ID的会话：{}", sessionId);
            }
        }catch (IOException e){

        }
    }


    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

}
