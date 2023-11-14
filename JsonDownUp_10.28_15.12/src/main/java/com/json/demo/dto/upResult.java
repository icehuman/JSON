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
public class upResult {
    private  int   Numberofnodes;
    private  int   Numofdentities;
    private  int   Logicalprocesses;
    private  int   numofevent;
    private  double   ctime;
    private  double   simulationtime;
}
