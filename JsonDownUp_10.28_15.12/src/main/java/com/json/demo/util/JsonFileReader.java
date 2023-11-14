package com.json.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.demo.dto.*;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



// 用来读取本地的Json文件 主要参数就是resource_path
public class JsonFileReader {
    public EngineData readJsonFromFile()  {
        ObjectMapper mapper = new ObjectMapper();

        EngineData engineData = null;
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/engine_info.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            engineData = mapper.readValue(new File(String.valueOf(file)), EngineData.class);
            System.out.println("success");
            return engineData;
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
            return null;
        }
    }

    public InitData readInitJsonFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        InitData initData = null;
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/init_info.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            initData = mapper.readValue(new File(String.valueOf(file)), InitData.class);
            System.out.println("success");
            return initData;
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
            return null;
        }
    }

    public Battle_damage_analysis getSettlement(){
        ObjectMapper mapper = new ObjectMapper();
        EngineData engineData=null;
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/engine_info.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            engineData= mapper.readValue(new File(String.valueOf(file)), EngineData.class);
            System.out.println("fToe success");
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
            return null;
        }
        eTob middle=new eTob();
        System.out.println("middle success");
        Battle_damage_analysis battle_damage_analysis=middle.eTob(engineData.getNum_entities_change(),engineData.getNum_entities_change_ratio(),engineData.getFinal_damage_ratio(),
                engineData.getNum_nodes(), engineData.getNum_entities_total(), engineData.getNum_lps(),engineData.getNum_events(), engineData.getCalculation_time(),engineData.getSimulation_time());
        System.out.println("b success");

        System.out.println(battle_damage_analysis);
        return battle_damage_analysis;
    }
//        return engineData;
//        return null;

//    public AssessData readAssessJsonFromFile(){
//        ObjectMapper mapper = new ObjectMapper();
//
//        AssessData assessdata = null;
//        File file = null;
//        try {
//            file = ResourceUtils.getFile("classpath:static/assess_info.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            assessdata = mapper.readValue(new File(String.valueOf(file)), AssessData.class);
//            System.out.println("success");
//            return assessdata;
//        } catch (IOException e) {
//            System.out.println("fail");
//            e.printStackTrace();
//            return null;
//        }
//    }


}
