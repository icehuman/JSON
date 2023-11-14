package com.json.demo.dto;

import lombok.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ButtonData {
    private int simulation; //仿真推进方式：时间或事件，事件为0，时间为1
    private int button;
}
