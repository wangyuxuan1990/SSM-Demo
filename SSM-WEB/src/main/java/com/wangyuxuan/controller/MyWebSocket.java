package com.wangyuxuan.controller;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/7 09:39
 * @Description:
 */

//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。

import com.wangyuxuan.pojo.Content;
import com.wangyuxuan.service.ContentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 类似Servlet的注解mapping。无需在web.xml中配置。
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */

@ServerEndpoint(value = "/websocket",configurator = SpringConfigurator.class)
@NoArgsConstructor
public class MyWebSocket {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    // 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @Autowired
    private ContentService contentService;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("来自客户端的消息：" + message);
        //群发消息
        for (MyWebSocket webSocket: webSocketSet){
            try {
                webSocket.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        //保存数据到数据库
        Content content = new Content();
        content.setContent(message);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        content.setCreatedate(simpleDateFormat.format(new Date()));
        contentService.insertSelective(content);

        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return MyWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount(){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        MyWebSocket.onlineCount--;
    }
}
