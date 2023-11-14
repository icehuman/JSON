package com.json.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.*;


@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)

//@RequiredArgsConstructor
@Data
public class Battle_damage_analysis {
    private upResult upresult;
    private HashMap<String, HashMap> result;
    private Map<String, Double> redpower;
    private Map<String, Double> bluepower;
    private Map<String, Double> redpercent;
    private Map<String, Double> bluepercent;

    public Battle_damage_analysis() {
        // 在构造函数中初始化 result 属性
        result = new HashMap<>();
        upresult = new upResult();
    }
}
//    public void afterdata() {
//
//        List<Float> after = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            if(i==0){
//                System.out.println( after.add(redpower.get(9)));
//        }else{
//                System.out.println( after.add(redpower.get(i*10-1)));
//            }
//        }
//
//        redpower=after;
//    }

//    public Battle_damage_analysis(){
//        System.out.println("nonono");
//    }

//    public Battle_damage_analysis(upResult upresult,Map<String, detal> result,List<Float> redpower,List<Float> bluepower){
//        this.upresult=upresult;
//        this.result=result;
//        this.redpower=afterdata(redpower);
//        this.bluepower=bluepower;
//    }

//}

