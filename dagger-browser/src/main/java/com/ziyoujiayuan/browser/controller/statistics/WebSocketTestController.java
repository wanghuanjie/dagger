package com.ziyoujiayuan.browser.controller.statistics;

import java.io.IOException;
import java.util.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket-测试demo
 * @Author wanghjbuf
 * @Date 2018年3月4日
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketTestController {

    @OnOpen
    public void onOpen(Session session) {
        try {
        	    session.getBasicRemote().sendText(new Date().toString());
        	} catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @OnClose
    public void onClose() {
        log.info("closeing...");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("client message:{};", message);
        
        try {
    	        session.getBasicRemote().sendText(message);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
		}
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error(error.getMessage());
    }
}
