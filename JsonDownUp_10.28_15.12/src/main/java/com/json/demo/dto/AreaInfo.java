package com.json.demo.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 如果前端数据中json中的area_info格式用类接收 则使用 如果还是用数组 则不需要用
public class AreaInfo {
    private String _uuid;

    private String name;

    private Center center;

    private Double number;

    private Double radius;

}
