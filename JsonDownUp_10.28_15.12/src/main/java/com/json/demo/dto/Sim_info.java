package com.json.demo.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//sim_info类 数据有message event_id global_time
public class Sim_info {
    private String message;

    private  String entity_side;

    private  int entity_id;

//    private double global_time;

    private  int event_type;

    private  int entity_type;

    private int entity_fleet_id;

    private  int entity_warship_type;

    private  int enemy_id;

    private  int enemy_type;

    private  int enemy_warship_type;

    private  int is_hit;

    private  double[] old_coordinate;

    private  double[] new_coordinate;

    private double start_time;

    private double duration_time;

    private  int missile_bearing;
//    private Object[] entry;

}
