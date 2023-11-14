package com.json.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ButtonResponse {
    private Object[] event_info;
}