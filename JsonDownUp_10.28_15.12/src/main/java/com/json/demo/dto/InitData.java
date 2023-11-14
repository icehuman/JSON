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
public class InitData {
    private Object[] coordinate_info;
}
