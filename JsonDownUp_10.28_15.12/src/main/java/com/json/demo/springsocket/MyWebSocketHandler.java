package com.json.demo.springsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends AbstractWebSocketHandler implements HandlerInterceptor {

    // 这里使用一个map来维护全部客户端用户
    private static Map<Long, WebSocketSession> userMap = new ConcurrentHashMap<>();

    // 日志
    private Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

    // 用户id
    private Long userId;

    /**
     * 打开连接时触发事件
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.userId = (Long) session.getAttributes().get("userId");
        userMap.put(userId, session);
        logger.info("用户" + userId + "已上线");
    }

    /**
     * 收到消息时触发事件
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("用户" + userId + "发来的消息为 ：" + message);
        session.sendMessage(new TextMessage("你用户" + userId + "的喜悦，我服某器收到了！ "));

        // 向全体在线成员推送这条消息
        sendMessageFromOneToAll(userId, message);
    }


    /**
     * 关闭连接时触发事件
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        userMap.remove(userId);
        logger.info("用户" + userId + "已下线");
    }

    /**
     * 传输消息错误时触发事件
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("错误发生");
        exception.printStackTrace();
    }


    /**
     * 给某个用户发送消息
     */
    public void sendMessageToUser(Long userId, TextMessage message) {

    }

    /**
     * 给所有在线用户发送消息
     */
    public void sendMessageToUsers(TextMessage message) {

    }

    public void sendMessageFromOneToAll(Long userId, TextMessage message) {
        userMap.forEach((id, session) -> {
            try {
                session.sendMessage(new TextMessage("用户" + userId + "发消息来啦：" + message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}