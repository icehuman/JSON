package com.json.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.Map;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessData {
  private int num_nodes;
  private int num_entities_total;
  private int num_lps;
  private int num_events;

  //推演计算时间
  private double simulation_time;
   //仿真运行时间
  private double calculation_time;
  //
  private Object[] state_data;
//"num_nodes":1,"num_entities_total":10000,"num_lps":16,"num_events":8912,"simulation_time":1599.9690960744394


}
