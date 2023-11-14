package com.json.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//EngineData类 根据本地engin的json数据定义格式（一个大括号基本上算是一个类）
public class EngineData {


//EngineData类 根据本地engin的json数据定义格式（一个大括号基本上算是一个类）

        //  private List<sim_info> info;
        // 即engine_info里面sim_info 定义为map类  key为"info_1.3..." value为新定义类sim_info
        private Map<String, Sim_info> sim_info;
        private  int num_nodes;
        //总实体数量 即engine_info里面的total——entities：10000，
        private  int num_entities_total;
        private int num_lps;
        private int num_events;
        private  double calculation_time;
        private double simulation_time;
        private HashMap<String,double[]> num_entities_change;
        private HashMap<String,double[]>num_entities_change_ratio;
        private HashMap<String,double[]> final_damage_ratio;


    }


