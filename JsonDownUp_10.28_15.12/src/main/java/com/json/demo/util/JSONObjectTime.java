//package com.json.demo.util;
//
//import com.alibaba.fastjson.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledFuture;
//import java.util.concurrent.TimeUnit;
//
//public class JSONObjectTime {
//    // 定义一个 ScheduledExecutorService 和 ScheduledFuture
//    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private ScheduledFuture<?> scheduledFuture;
//
//    // JSON 对象列表
//    private List<JSONObject> jsonList = new ArrayList<>();
//    // 存储时间间隔的数组
//    private List<Long> timeIntervals = new ArrayList<>();
//    // 当前处理的 JSON 对象索引
//    private int currentIndex = 0;
//
//    // 初始时间间隔
//    private long initialDelay = 0; // 0秒
//
//    private void sendJsonData() {
//        if (jsonList.isEmpty()) {
//            return;
//        }
//
//        scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
//            if (!isPaused && currentIndex < jsonList.size()) {
//                String json = jsonList.get(currentIndex).toString();
//                try {
//                    session.getBasicRemote().sendText(json);
//                    currentIndex++;
//                } catch (Exception e) {
//                    // Handle exceptions, e.g., session closed
//                    stopSendingData();
//                }
//            } else {
//                stopSendingData();
//            }
//        }, initialDelay, getDynamicInterval(), TimeUnit.SECONDS);
//    }
//
//    // 计算动态时间间隔
//    private long getDynamicInterval() {
//        // 检查是否有下一个 JSON 对象，如果没有则停止发送数据
//        if (currentIndex + 1 < jsonList.size()) {
//            // 计算下一个时间间隔
//            long startTime1 = jsonList.get(currentIndex).getJSONObject("simInfo").getLong("start_time");
//            long startTime2 = jsonList.get(currentIndex + 1).getJSONObject("simInfo").getLong("start_time");
//            long timeInterval = startTime2 - startTime1;
//
//            // 添加时间间隔到数组
//            timeIntervals.add(timeInterval);
//            return timeInterval;
//        } else {
//            return 0; // 最后一个 JSON 对象，时间间隔为 0
//        }
//    }
//
//    // 停止发送数据
//    public void stopSendingData() {
//        if (scheduledFuture != null && !scheduledFuture.isDone()) {
//            scheduledFuture.cancel(true);
//        }
//    }
//
//
//}
