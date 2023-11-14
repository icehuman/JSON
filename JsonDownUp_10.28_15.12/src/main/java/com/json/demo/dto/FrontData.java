package com.json.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 根据接口传递的json数据定义的大类
public class FrontData implements Serializable{
    // 即前端json中的node_num:1
    private int node_num;
    // 即前端json中的local_ip:"127.0.0.1"
    private String local_ip;
    // 即前端json中的node_ip ["125", "1235"]
    private String[] node_ip;
    // 即前端json中的 operate_time :300min
    private double operate_time;

    // 即前端json中的area_info 换了用数组接收
    Object[] area_info;
    //List<AreaInfo> area_info;
//    AreaInfo area_info ;
}
