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
public class  detal{
    private String side;
    private  String name;
    private int percent;
    private int total;
}
