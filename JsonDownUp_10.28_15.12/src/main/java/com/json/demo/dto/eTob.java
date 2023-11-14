package com.json.demo.dto;
import lombok.*;
import springfox.documentation.schema.Entry;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class eTob {
    private HashMap<String,double[]> function1;
    private HashMap<String,double[]>function2;
    private HashMap<String,double[]>function3;
    private Battle_damage_analysis battle_damage_analysis=new Battle_damage_analysis();


    public HashMap Function(HashMap<String,double[]> map,int columnIndex){
        double[] columnData = new double[map.size()];
        int vindex = 0;
        int sindex=0;
        String[] Key = new String[map.size()]; // 初始化Key数组
        HashMap<String, Double> Map = new HashMap<>(); // 初始化Map

        for (double[] values : map.values()) {
            if (columnIndex < values.length) {
                columnData[vindex] = values[columnIndex];
            }
            vindex++;
        }

        for (int i = 0; i < vindex; i++) {
//                System.out.println("value "+columnData[i]);
        }

        Set<String> keySet = map.keySet();
        for (String key : keySet){
            Key[sindex]=key;
//                System.out.println("key "+key);
            sindex++;
        }

        if (Key.length == columnData.length) {

            for (int j = 0; j < Key.length; j++) {
                Map.put(Key[j], columnData[j]);
            }
        }


        return Map;
    }

    public void Function1 (){
        HashMap r_num_entities_change = Function(function1,0) ;
        HashMap b_num_entities_change = Function(function1,1) ;
        Map<String,Double> sortedMa1 = new TreeMap<>( r_num_entities_change);
        Map<String,Double> sortedMa2 = new TreeMap<>( b_num_entities_change);

        battle_damage_analysis.setRedpower(sortedMa1);
        battle_damage_analysis.setBluepower(sortedMa2);
//        battle_damage_analysis.setRedpower(r_num_entities_change);
//        battle_damage_analysis.setRedpower(b_num_entities_change);
////
    }

    public void Function2 () {
        HashMap r_num_entities_change = Function(function2, 0);
        HashMap b_num_entities_change = Function(function2, 1);

        Map<String,Double> sortedMa1 = new TreeMap<>( r_num_entities_change);
        Map<String,Double> sortedMa2 = new TreeMap<>( b_num_entities_change);
        battle_damage_analysis.setRedpercent(sortedMa1);
        battle_damage_analysis.setBluepercent(sortedMa2);
    }
//

    public void Function3(){

        HashMap s_num_entities_change =Function(function3,0) ;
        HashMap ship_num_entities_change = Function(function3,1) ;
        HashMap p_num_entities_change = Function(function3,2) ;
        HashMap c_num_entities_change =Function(function3,3) ;

        System.out.println(s_num_entities_change);
        s_num_entities_change = changetozhu(s_num_entities_change) ;
        ship_num_entities_change = changetozhu(ship_num_entities_change) ;
        p_num_entities_change = changetozhu(p_num_entities_change) ;
        c_num_entities_change =changetozhu(c_num_entities_change) ;


        System.out.println("function3 s "+s_num_entities_change);
        System.out.println("function3 ship "+ship_num_entities_change);
        System.out.println("function3 p "+p_num_entities_change);
        System.out.println("function3 c "+c_num_entities_change);
        battle_damage_analysis.getResult().put("卫星",s_num_entities_change);
        battle_damage_analysis.getResult().put("舰船",ship_num_entities_change);
        battle_damage_analysis.getResult().put("战机",p_num_entities_change);
        battle_damage_analysis.getResult().put("地面车辆",c_num_entities_change);

    }

    public  HashMap changetozhu( HashMap hashmap){
        System.out.println(1);
        double newpercent= (double) hashmap.get("red_percent");
        System.out.println(2);
        double newnum= (double) hashmap.get("red_total");
        System.out.println(3);
        double percent= (double) hashmap.get("blue_percent");
        System.out.println(4);
        double num= (double) hashmap.get("blue_total");
        System.out.println(5);
        HashMap <String,Double> zhu =new HashMap<>();

       int red_num= (int) (newpercent*newnum);
       int blue_num= (int) (percent*num);

        zhu.put("红方剩余百分比",newpercent);
        zhu.put("红方初始总数",newnum);
        zhu.put("蓝方剩余百分比",percent);
        zhu.put("蓝方初始总数",num);
//        zhu.put("红方剩余总数",red_num);
//        zhu.put("蓝方剩余总数",blue_num);
        return zhu;
    }


    public Battle_damage_analysis eTob(HashMap<String,double[]>function1, HashMap<String,double[]>function2, HashMap<String, double[]> function3,
                                       int num_nodes, int num_entities_total ,int num_lps,int num_even,double calculatiob_time,double simulation_time){
        this.function1=function1;
        this.function2=function2;
        this.function3=function3;

        Function1();
        System.out.println("f1");
        Function2();
        System.out.println("f2");
        Function3();
        System.out.println("f3");
        battle_damage_analysis.getUpresult().setCtime(calculatiob_time);
        battle_damage_analysis.getUpresult().setNumberofnodes(num_nodes);
        battle_damage_analysis.getUpresult().setNumofdentities(num_entities_total);
        battle_damage_analysis.getUpresult().setLogicalprocesses(num_lps);
        battle_damage_analysis.getUpresult().setNumofevent(num_even);
        battle_damage_analysis.getUpresult().setSimulationtime( simulation_time);
        return (battle_damage_analysis);
    }
}
