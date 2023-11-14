package com.json.demo.springsocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Component;
import com.json.demo.util.*;
import com.json.demo.dto.*;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@ServerEndpoint(value = "/websocket/{userId}",encoders = {ServerEncoder.class})
@Component
@Slf4j
public class WebSocketServer {
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全集合，也可以map改成set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";
    /**
     * 中止标志位
     */
    private Boolean isPaused = false;
    /**
     * 追踪断点
     */
    private int currentIndex = 0;
    /**
     * JSON数组对象列表
     */
//     List<JSONObject> jsonList;  //从静态文件中读取
    private List<JSONObject> jsonList = new ArrayList<>();
    private List<JSONObject> jsonorderList = new ArrayList<>();

    // 遍历 Map 中的键值对，将它们转换为 JSONObject 并添加到 List 中
    private void toList() {
        JsonFileReader jsonFileReader = new JsonFileReader();
        EngineData engineData = jsonFileReader.readJsonFromFile();
        Map<String, Sim_info> jsondata = engineData.getSim_info();
        // 创建一个空的 List<JSONObject> 用于存放转换后的数据
        for (String userId : webSocketMap.keySet()) {
            for (Map.Entry<String, Sim_info> entry : jsondata.entrySet()) {
                String key = entry.getKey();
                Sim_info simInfo = entry.getValue();

                // 创建一个新的 JSONObject，并将键值对添加到其中
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", key);
                jsonObject.put("simInfo", JSON.toJSON(simInfo)); //假设 Sim_info 类有一个 toJSONObject() 方法
                // 将 JSONObject 添加到 List
                jsonList.add(jsonObject);
//                jsonObject.get(simInfo.getStart_time());

            }
            /**转换JSON数组为按照start_time升序排列的数组*/
            jsonorderList = new ArrayList<>(jsonList);
            // 创建一个比较器实例
            JSONObjectComparator jsonObjectComparator = new JSONObjectComparator();

            // 对 jsonList 中的 JSONObject 对象进行排序
            Collections.sort(jsonorderList, jsonObjectComparator);


        }

    }
    private int[] intevals = new int[10000];
    public int[] gettimeArray(){
        int Index=0;
//        int[] intevals = new int[jsonorderList.size()];
        while(Index+1<jsonorderList.size()){
            int startTime1 = jsonorderList.get( Index).getJSONObject("simInfo").getIntValue("start_time");
            int startTime2 = jsonorderList.get( Index+1).getJSONObject("simInfo").getIntValue("start_time");
            int timeInterval = startTime2 - startTime1;
            log.info("startTime2:{},startTime1:{}",startTime2,startTime1);
            if(timeInterval==0) {
                intevals[Index]=1;
            }else
            {
                intevals[Index]=timeInterval*60000;
            }
            Index++;

        }
        return intevals;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;

        if (!webSocketMap.containsKey(userId)) {
            //加入集合中
            webSocketMap.put(userId, this);
            //在线数加1
            addOnlineCount();
        }

        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());
        toList();
        gettimeArray();
        log.info("Array contents: " + Arrays.toString(intevals));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从集合中删除
            subOnlineCount();
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) throws InterruptedException {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        try {
            JSONObject button = JSONObject.parseObject(message);
            //仿真方式
            String param1 = button.getString("simulation");
            //按钮
            String param2 = button.getString("command");
            //时间倍速
            String param3 = button.getString("speed");
//            try {
                int speed = Integer.parseInt(param3);
//            } catch (NumberFormatException e) {
//                e.printStackTrace(); // 打印异常信息
//            }
            switch (param1) {
                case "event":
                    //以事件推演
                    if ("start".equals(param2)) {
                        isPaused = false;
                        sendJsonData();
                    } else if ("pause".equals(param2)) {
                        isPaused = true;
                    } else if ("continue".equals(param2)) {
                        isPaused = false;
                        sendJsonData();
                    } else if ("end".equals(param2)) {
                        isPaused = true;
                        currentIndex = 0;
                    } else {
                        // Handle other types of mess   ages from the frontend
                    }
                    break;
                case "time":
                    //以事件推演
                    if ("start".equals(param2)) {
                        isPaused = false;
                        sendOrderJsonData(speed);
                    } else if ("pause".equals(param2)) {
                        isPaused = true;
                    } else if ("continue".equals(param2)) {
                        isPaused = false;
                        sendOrderJsonData(speed);
                    } else if ("end".equals(param2)) {
                        isPaused = true;
                        currentIndex = 0;
                    } else {

                    }
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //        log.info("【websocket消息】收到客户端发来的消息:{}", message);


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */

    public void sendMessage() throws IOException, InterruptedException, EncodeException {
        JsonFileReader jsonFileReader = new JsonFileReader();
        EngineData engineData = jsonFileReader.readJsonFromFile();
        Map<String, Sim_info> jsondata = engineData.getSim_info();
        for (String userId : webSocketMap.keySet()) {
            for (Map.Entry<String, Sim_info> entry : jsondata.entrySet()) {
                webSocketMap.get(userId).session.getBasicRemote().sendObject(JSON.toJSON(entry));
                sleep(1);
            }
        }
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException, EncodeException, InterruptedException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage();
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * 发送LIST的JSON对象
     */
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;

    private void sendJsonData() throws InterruptedException {
        if (jsonList.isEmpty()) {
            return;
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                200,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>());

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                while (currentIndex < jsonList.size() && !isPaused) {
                    String json = jsonList.get(currentIndex++).toString();
                    try {
                        session.getBasicRemote().sendText(json);
                    } catch (Exception e) {
                        // Handle exceptions, e.g., session closed
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }
                // After the loop is done, you can shut down the executor
                threadPoolExecutor.shutdown();
            }
        });
    }

    private int timeIndex=0;

    private void sendOrderJsonData(int speed) {
        if (jsonorderList.isEmpty()) {
            return;
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                200,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>());

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                while (currentIndex < jsonorderList.size() && !isPaused) {
                    String json = jsonorderList.get(currentIndex++).toString();
                    try {
                        session.getBasicRemote().sendText(json);
                    } catch (Exception e) {
                        // Handle exceptions, e.g., session closed
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(intevals[timeIndex+1]/speed);
//                        Thread.sleep(rand.nextInt(3000)+500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    timeIndex++;
                }

                // After the loop is done, you can shut down the executor
                threadPoolExecutor.shutdown();
            }
        });
    }

//    private long getDynamicInterval() {
//        log.info("timeIndex:"+ timeIndex);
////        // 检查是否有下一个 JSON 对象，如果没有则停止发送数据
//        if (this.timeIndex + 1 < jsonorderList.size()) {
////            Random rand = new Random();
////            return rand.nextInt(3000)+100;
//            int startTime1 = jsonorderList.get(timeIndex).getJSONObject("simInfo").getIntValue("start_time");
//            int startTime2 = jsonorderList.get(timeIndex +1).getJSONObject("simInfo").getIntValue("start_time");
//            int timeInterval = startTime2 - startTime1;
////            log.info("startTime1:{},startTime2:{},timeInterval:{}",startTime1,startTime2,timeInterval);
//            if(timeInterval==0) {
//                return 1000;
//            }else
//                if(timeInterval!=0) {
//                    return timeInterval*4000;
//                }
//        }
//        else {
//            return 0; // 最后一个 JSON 对象，时间间隔为 0
//        }
//        return 0;
//    }


    public static synchronized int getOnlineCount () {

            return onlineCount;
        }
    public static synchronized void addOnlineCount () {

        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount () {

        WebSocketServer.onlineCount--;
    }
}