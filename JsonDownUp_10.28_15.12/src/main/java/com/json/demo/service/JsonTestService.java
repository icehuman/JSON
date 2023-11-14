package com.json.demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.json.demo.dto.*;
import com.json.demo.util.JsonFileReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JsonTestService {

    // 从前端接收节点信息
    public void getJsonData(FrontData frontData) {
        int node_num = frontData.getNode_num();
        String local_ip = frontData.getLocal_ip();
        String[] node_ip = frontData.getNode_ip();

        double operate_time = frontData.getOperate_time();
        Object[] area_info = frontData.getArea_info();
        System.out.println(node_num);
    }

    // 按照接口定义的json格式返回需要的格式数据 测试用定义的EngineData返回
    // 将本地的json数据传给前端
    public Object[] getInitJson() {
        JsonFileReader jsonFileReader = new JsonFileReader();
        InitData initData = jsonFileReader.readInitJsonFromFile();
        Object[] coordinate_info = null;
        if (initData != null) {
            coordinate_info = initData.getCoordinate_info();
        }
        return coordinate_info;
    }

    //将本地json数据：战场伤害分析 传递给前端
    public Battle_damage_analysis getdamageAnalysis() {
        JsonFileReader jsonFileReader = new JsonFileReader();
        Battle_damage_analysis damageData = jsonFileReader.getSettlement();
        return damageData;
    }
    // 按照接口定义的json格式返回需要的格式数据 测试用定义的EngineData返回
    // 将本地的json数据传给前端
//    public EngineData getJson() {
//        JsonFileReader jsonFileReader = new JsonFileReader();
//        EngineData engineData = jsonFileReader.readJsonFromFile();
//        if (engineData != null) {
//            int total_entities = engineData.getTotal_entities();
//            int nlp_per_pe = engineData.getNlp_per_pe();
//            long g_tw_events_per_pe = engineData.getG_tw_events_per_pe();
////            Set<String> strings = engineData.getSim_info().keySet();
//            Set<Map.Entry<String, Sim_info>> entries = engineData.getSim_info().entrySet();
//            int num = 0;
//            int cntnum = 0;
//            for (Map.Entry<String, Sim_info> entry : entries) {
//                if (entry.getValue().getGlobal_time() < 1) {
//                    cntnum++;
//                }
//
//                num++;
//                System.out.println(entry.getKey() + "------------------------" + num);
//                System.out.println(entry.getValue());
//            }
//            System.out.println(num);
//            System.out.println(cntnum);
//            System.out.println(engineData.getTotal_entities());
//            System.out.println(engineData.getNlp_per_pe());
//            System.out.println(engineData.getG_tw_events_per_pe());
//            System.out.println(engineData.getSim_info().get("info_0.164871"));
//
//        }
//        return engineData;
//    }

//    public void getJsonData(InitData initData) {
//        int node_num = initData.getNode_num();
//        String local_ip = initData.getLocal_ip();
//        String[] node_ip = initData.getNode_ip();
//
//        double operate_time = initData.getOperate_time();
//        Object[] area_info = initData.getArea_info();
//        System.out.println(node_num);
//
//    }

    //接收前端演进指令：开始，暂停，终止
    //按照指令调用mpi,将引擎数据获取到文件里，传给前端
    //返回的数据类型为EngineData
    public EngineData getButtonData(ButtonData buttonData){
        int button=buttonData.getButton();
        int simulation=buttonData.getSimulation();
        JsonFileReader jsonFileReader = new JsonFileReader();
        EngineData engineData = jsonFileReader.readJsonFromFile();
        switch (button){
            //结束、开始、暂停、继续
            case 0:
                break;
            case 1:
                if(simulation==0){
                    //以事件方式演进
                    return engineData;  //引擎和后端交互的文件
                }
                else if(simulation==1){
                    //以时间方式演进
                    return engineData;
                }
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return engineData;// 加入捕获异常更为合适
    }

}
