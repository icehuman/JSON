package com.json.demo.controller;

import com.json.demo.dto.ButtonData;
import com.json.demo.dto.FrontData;
import com.json.demo.dto.InitData;
import com.json.demo.service.JsonTestService;
import com.json.demo.springsocket.WebSocketServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;

@RestController
public class TestWebController {

    @Resource
    private WebSocketServer webSocketServer;
    @RequestMapping("/push/{toUserId}")
//    ResponseEntity<String>
    public Object pushToWeb(String message, @PathVariable String toUserId) throws IOException, IOException, InterruptedException, EncodeException {
        webSocketServer.sendInfo(message,toUserId);
        return ResponseEntity.ok("good luck");
//        return webSocketServer;
    }
}
