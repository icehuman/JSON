package com.json.demo.util;

import java.util.Comparator;
import com.alibaba.fastjson.JSONObject;

public class JSONObjectComparator implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        // 从 o1 和 o2 中获取 simInfo 的 start_time 属性
        double start_time1 = o1.getJSONObject("simInfo").getLong("start_time");
        double start_time2 = o2.getJSONObject("simInfo").getLong("start_time");

        // 使用compare 比较两个 start_time 属性值
        return Double.compare(start_time1, start_time2);
    }
}
