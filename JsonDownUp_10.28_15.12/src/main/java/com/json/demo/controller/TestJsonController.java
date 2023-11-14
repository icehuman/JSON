package com.json.demo.controller;

import com.json.demo.dto.Battle_damage_analysis;
import com.json.demo.dto.ButtonData;
import com.json.demo.dto.FrontData;
import com.json.demo.dto.InitData;
import com.json.demo.service.JsonTestService;
import com.json.demo.util.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;

/*
* 与前端交互的地方；基础端口配置在application.xml文件中配置，设置端口号等；即localhost:8081
*
* */
@RestController
public class TestJsonController {
    // 注入service层
    @Autowired
    private JsonTestService testService;

    // 获取前端json数据 默认端口号为 http://localhost:8081/ip_info_json
    // 在postman的body中加入将data换为json数据 此时自我测试终端输出service中自我定义的输出node_num 图2
    @RequestMapping(value = "/ip_info_json")
    public void getJson(@RequestBody FrontData frontData) {
        testService.getJsonData(frontData);
    }

    //用来传给前端json数据 默认端口号为 http://localhost:8081/init_json 可以在postman中测试 终端自我测试相关数据输出 图1
    @CrossOrigin(origins = "http:")
    @RequestMapping("/init_json")
    @ResponseBody
    public Object[] returnInitJson(){
        return testService.getInitJson();
    }

    //用来传战速数据给前端json数据 默认端口号为 http://localhost:8081/init_json
    @CrossOrigin(origins = "http:")
    @RequestMapping("/damageAnalysis")
    @ResponseBody
    public Battle_damage_analysis damageAnalysis(){
        return testService.getdamageAnalysis();
    }

    // 测试用service层的逻辑上传需要的东西
    // 即交互接口url为localhost:8081/login 请求方法为RequestMapping，返回类型是String
//    @RequestMapping("/login")
//    public String login() {
//        testService.getshowdata();
//        // testService.getJson();
//        // testService.getJson();
//        return "/index";
//    }

//    用来传给前端json数据 默认端口号为 http://localhost:8081/load_json 可以在postman中测试 终端自我测试相关数据输出 图1
//    @CrossOrigin(origins = "http:")
//    @RequestMapping("/load_json")
//    @ResponseBody
//    public Object returnJson(){
//        return testService.getJson();
//    }
    //接收前端按钮指令,默认端口号为 http://localhost:8081/button_json
//    @CrossOrigin(origins = "http:")
//    @RequestMapping("/button_json")
//    @ResponseBody
//    public Object returnButtonJson(){
//        return testService.getButtonData(new ButtonData());
//    }
    //获取前端按钮数据，根据按钮值返回响应数据 http://localhost:8081/button_json
//    @RequestMapping(value = "/button_json")
//    public Object returnButtonJson(@RequestBody ButtonData buttonData) {
//        return testService.getButtonData(buttonData);
//    }

    // 获取前端json数据 默认端口号为 http://localhost:8081/test_json
    // 在postman的body中加入将data换为json数据 此时自我测试终端输出service中自我定义的输出node_num 图2
//    @RequestMapping(value = "/test_json")
//    public void getJson(@RequestBody FrontData frontData) {
//        testService.getJsonData(frontData);
//    }

    //传给前端数据进行评估分析 默认端口号为 http://localhost:8081/analy_json
//    @CrossOrigin(origins = "http:")
//    @RequestMapping("/analy_json")
//    @ResponseBody
//    public Object[] returnAnalyJson(){
//        return testService.getAnalyJson();
//    }
}
